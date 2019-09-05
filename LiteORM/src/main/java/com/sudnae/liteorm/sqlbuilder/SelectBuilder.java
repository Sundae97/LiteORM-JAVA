package com.sudnae.liteorm.sqlbuilder;

import com.sudnae.liteorm.exception.NotDefineTableNameException;
import com.sudnae.liteorm.utils.AnnotationUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * 2019/8/29
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class SelectBuilder extends AbstractSqlBuilder {
    private String tableName;
    public SelectBuilder(String tableName){
        this.tableName = tableName;
    }

    public SelectBuilder(Class<?> tableEntityClz){
        try {
            this.tableName = (String) AnnotationUtil.getTableNameValue(tableEntityClz);
        } catch (NotDefineTableNameException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public SelectBuilder column(String columnName){
        if(columnName == null || columnName.length() == 0)
            return this;
        columnList.add(columnName);
        return this;
    }

    public SelectBuilder where(String whereExpr) {
        if(whereExpr == null || whereExpr.length() == 0)
            return this;
        whereList.add(whereExpr);
        return this;
    }

    @Override
    public String toString() {
        sqlBuilder.append("SELECT ");
        appendList(sqlBuilder, columnList, "", ",");
        sqlBuilder.append(" FROM ");
        sqlBuilder.append(tableName);
        appendList(sqlBuilder, whereList, " WHERE ", " AND ");
        return sqlBuilder.toString();
    }
}
