package com.sudnae.liteorm.sqlbuilder;

import com.sudnae.liteorm.exception.NotDefineTableNameException;
import com.sudnae.liteorm.utils.AnnotationUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019/8/29
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class InsertBuilder extends AbstractSqlBuilder<InsertBuilder> {

    private String tableName;

    public InsertBuilder(String tableName){
        this.tableName = tableName;
    }

    public InsertBuilder(Class<?> tableEntityClz){
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




    @Override
    public String toString() {
        sqlBuilder.append("INSERT INTO ");
        sqlBuilder.append(tableName);
        sqlBuilder.append(" ");
        if(columnList.size() > 0){
            sqlBuilder.append("(");
            appendList(sqlBuilder, columnList, "",",");
            sqlBuilder.append(") ");
        }
        sqlBuilder.append("VALUES");
        sqlBuilder.append(" (");
        appendList(sqlBuilder, valueList, "", ",", true);
        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }
}
