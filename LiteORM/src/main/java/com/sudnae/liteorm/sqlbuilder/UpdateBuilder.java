package com.sudnae.liteorm.sqlbuilder;

import com.sudnae.liteorm.exception.NotDefineTableNameException;
import com.sudnae.liteorm.utils.AnnotationUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 2019/8/29
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class UpdateBuilder extends AbstractSqlBuilder {

    private String tableName;
    private LinkedHashMap<String,Object> kvMap = new LinkedHashMap<>(); //有序

    public UpdateBuilder(String tableName){
        this.tableName = tableName;
    }

    public UpdateBuilder(Class<?> tableEntityClz){
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

    public UpdateBuilder set(String key, Object value){
        kvMap.put(key, value);
        return this;
    }

    public UpdateBuilder where(String whereExpr) {
        if(whereExpr == null || whereExpr.length() == 0)
            return this;
        whereList.add(whereExpr);
        return this;
    }

    @Override
    public String toString() {
        sqlBuilder.append("UPDATE ");
        sqlBuilder.append(tableName);
        List<String> tmp = new ArrayList<>();
        for (Map.Entry<String, Object> entry : kvMap.entrySet()) {
            tmp.add(entry.getKey() + "=" + getSqlValueString(entry.getValue()));
        }
        appendList(sqlBuilder, tmp, " SET ",",");
        appendList(sqlBuilder, whereList, " WHERE ", " AND ");
        return sqlBuilder.toString();
    }
}
