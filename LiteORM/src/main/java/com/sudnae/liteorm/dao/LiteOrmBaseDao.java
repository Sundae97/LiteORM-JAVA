package com.sudnae.liteorm.dao;

import com.sudnae.liteorm.exception.NotDefineTableNameException;
import com.sudnae.liteorm.log.LiteOrmLogger;
import com.sudnae.liteorm.session.LiteOrmSqlSession;
import com.sudnae.liteorm.sqlbuilder.DeleteBuilder;
import com.sudnae.liteorm.sqlbuilder.InsertBuilder;
import com.sudnae.liteorm.sqlbuilder.SelectBuilder;
import com.sudnae.liteorm.sqlbuilder.UpdateBuilder;
import com.sudnae.liteorm.utils.AnnotationUtil;
import com.sudnae.liteorm.utils.ReflectUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class LiteOrmBaseDao<T> {
    private Class<T> entityClz = ReflectUtil.getGenericClass(getClass());
    private LiteOrmSqlSession session;
    private String tableName = "";
    private boolean isBeginTransaction = false;
    private List<String> sqlQueue = new ArrayList<>();
    public LiteOrmBaseDao() {
        session = LiteOrmSqlSession.getInstance();

        try {
            tableName = (String) AnnotationUtil.getTableNameValue(entityClz);
        } catch (NotDefineTableNameException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public List<T> listAll(){
        String sql = "SELECT * FROM " + tableName;
        return toList(sql);
    }

    public List<T> findEntitiesWhere(String where){
        String sql = new SelectBuilder(tableName)
                .where(where)
                .toString();
        return toList(sql);
    }

    public T findEntity(String where){
        String sql = new SelectBuilder(tableName)
                .where(where)
                .toString();
        return toList(sql).get(0);
    }

    private List<T> toList(String sql){
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

    public void add(T entity){
        beginTransaction();
        try {
            Map<String,Object> map = ReflectUtil.getColumnNamesAndValues(entity);
            String sql = new InsertBuilder(tableName)
                    .columns(new ArrayList<>(map.keySet()))
                    .values(new ArrayList<>(map.values()))
                    .toString();
            LiteOrmLogger.info(sql);
            sqlQueue.add(sql);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void delete(String where){
        beginTransaction();
        String sql = new DeleteBuilder(tableName)
                .where(where)
                .toString();
        sqlQueue.add(sql);
    }

    public void update(T entity){
        beginTransaction();
        try {
            Map<String,Object> map = ReflectUtil.getColumnNamesAndValues(entity);
            UpdateBuilder updateBuilder = new UpdateBuilder(tableName);
            for (String key : map.keySet()){
                if(key.toLowerCase().equals("id"))
                    updateBuilder.where("id=" + map.get(key));
                else
                    updateBuilder.set(key, map.get(key));
            }
            String sql = updateBuilder.toString();
            sqlQueue.add(sql);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void beginTransaction(){
        isBeginTransaction = true;
    }

    public void saveChanges() throws SQLException {
        isBeginTransaction = false;
        for (String sql : sqlQueue) {
            session.execute(sql);
        }
        session.commit();
    }

    public void rollback() throws SQLException {
        session.rollback();;
    }


}
