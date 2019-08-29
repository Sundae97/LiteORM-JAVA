package com.sudnae.liteorm.utils;

import com.sudnae.liteorm.annotations.TableName;
import com.sudnae.liteorm.exception.NotDefineTableNameException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class AnnotationUtil {

    public static <A extends Annotation>Annotation getClassAnnotation(Class clz, Class<A> annotationClz){
        try{
            return clz.getAnnotation(annotationClz);
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }

    public static Object getTableNameValue(Class<?> clz) throws NotDefineTableNameException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return getAnnotationValue(clz, TableName.class);
    }

    public static Object getAnnotationValue(Class<?> clz, Class<? extends Annotation> annotationClz) throws NotDefineTableNameException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object value = null;

        Annotation annotation = AnnotationUtil.getClassAnnotation(clz, annotationClz);
        if(annotation == null)
            throw new NotDefineTableNameException(clz);
        value = annotation.getClass().getMethod("value").invoke(annotation);

        return value;
    }

}
