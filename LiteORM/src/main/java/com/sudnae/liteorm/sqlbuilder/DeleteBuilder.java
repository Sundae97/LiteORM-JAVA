package com.sudnae.liteorm.sqlbuilder;

import com.sudnae.liteorm.exception.NotDefineTableNameException;
import com.sudnae.liteorm.utils.AnnotationUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 2019/8/29
 * @author @Sundae
 * @Email 948820549@qq.com
 */
public class DeleteBuilder extends AbstractSqlBuilder<DeleteBuilder> {
    private String tableName;

    public DeleteBuilder(String tableName){
        this.tableName = tableName;
    }

    public DeleteBuilder(Class<?> tableEntityClz){
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

    public DeleteBuilder where(String whereExpr) {
        if(whereExpr == null || whereExpr.length() == 0)
            return this;
        whereList.add(whereExpr);
        return this;
    }

    @Override
    public String toString() {
        sqlBuilder.append("DELETE FROM ");
        sqlBuilder.append(tableName);
        appendList(sqlBuilder, whereList, " WHERE ", " AND ");
        return sqlBuilder.toString();
    }
}
