package com.sudnae.liteorm.sqlbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 2019/8/29
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
abstract class AbstractSqlBuilder<T> {
    protected StringBuilder sqlBuilder;
    protected List<String> wheres = new ArrayList<>();
    protected List<String> columns = new ArrayList<>();

    public abstract T column(String columnName);

    @Override
    public String toString() {
        return sqlBuilder.toString();
    }

    protected void appendList(StringBuilder sqlBuilder, List<String> list, String first, String connector){
        boolean isFirst = true;
        for (String s : list){
            if(isFirst){
                sqlBuilder.append(String.format(" %s ", first));
            }else{
                sqlBuilder.append(String.format(" %s ", connector));
            }
            sqlBuilder.append(s);
            isFirst = false;
        }
    }
}
