package com.sudnae.liteorm.dao;

import com.sudnae.liteorm.annotations.TableName;
import com.sudnae.liteorm.exception.NotDefineTableNameException;
import com.sudnae.liteorm.session.LiteOrmSqlSession;
import com.sudnae.liteorm.utils.AnnotationUtil;
import com.sudnae.liteorm.utils.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class LiteOrmBaseDao<T> {
    private Class<T> entityClz = ReflectUtil.getGenericClass(getClass());
    private LiteOrmSqlSession session;
    private String tableName = "";
    public LiteOrmBaseDao() {
        session = LiteOrmSqlSession.getInstance();

        try {
            Annotation annotation = AnnotationUtil.getClassAnnotation(entityClz, TableName.class);
            if(annotation == null)
                throw new NotDefineTableNameException(entityClz);
            tableName = ((TableName)annotation).value();
        } catch (NotDefineTableNameException e) {
            e.printStackTrace();
        }
    }

    public List<T> listAll(){
        String sql = "SELECT * FROM " + tableName;
        List<T> list = null;
        try {
            ResultSet resultSet = session.executeQuery(sql);
            list = ReflectUtil.resultSet2List(resultSet, entityClz);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }


}
