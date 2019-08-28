package com.sudnae.liteorm.utils;

import com.sudnae.liteorm.annotations.ColumnName;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class ReflectUtil {

    /**
     * 返回clz类的泛型的类型
     * @param clz
     * @return
     */
    public static Class getGenericClass(Class clz){
        Type type = clz.getGenericSuperclass();
        Class entityClz = null;
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            entityClz = (Class) parameterizedType[0];
        }
        return entityClz;
    }

    public static <E> List<E> resultSet2List(ResultSet set, Class<E> elementType) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<E> list = new ArrayList<E>();
        //如果没有数据则直接返回
        set.last();
        if(set.getRow() == 0)
            return list;
        set.beforeFirst();
        //回到初始状态

        List<FieldInfo> fieldInfoList = getPrivateFieldInfoByAnnotation(elementType, ColumnName.class);

        while (set.next()){
            E element = createNewInstance(elementType);
            for (FieldInfo fieldInfo : fieldInfoList) {
                String keyName = "";
                if(((ColumnName)fieldInfo.annotation).value().equals(""))
                    keyName = fieldInfo.fieldName;
                else
                    keyName = ((ColumnName)fieldInfo.annotation).value();
                Object o = set.getObject(keyName, fieldInfo.type);
                fieldInfo.setMethod.invoke(element, o);
            }
            list.add(element);
        }
        return list;
    }

    public static <T>T inject2Bean(){
        return null;
    }

    private static List<String> getColumnNames(ResultSet set) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
            columnNames.add(set.getMetaData().getColumnName(i));
        }
        return columnNames;
    }

    private static <T> T createNewInstance(Class<T> clz){
        try {
            return clz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <A extends Annotation>List<FieldInfo> getPrivateFieldInfoByAnnotation(Class clz, Class<A> annotationClz) throws NoSuchMethodException {
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        Field Fields[] = clz.getDeclaredFields();
        for(Field f : Fields){
            if(f.isAnnotationPresent(annotationClz)){
                A annotation = f.getAnnotation(annotationClz);
                FieldInfo<A> fieldInfo = new FieldInfo<>();
                String fieldName = f.getName();
                fieldInfo.fieldName = fieldName;
                fieldInfo.annotation = annotation;
                fieldInfo.type = f.getType();
                fieldInfo.setMethod = clz.getMethod("set" +
                                fieldName.substring(0, 1).toUpperCase() +
                                fieldName.substring(1),
                        f.getType());
                fieldInfoList.add(fieldInfo);
            }
        }
        return fieldInfoList;
    }

}
class FieldInfo<A>{
    String fieldName;
    A annotation;
    Class<?> type;
    Method setMethod;
}
