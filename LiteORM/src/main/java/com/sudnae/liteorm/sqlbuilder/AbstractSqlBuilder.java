package com.sudnae.liteorm.sqlbuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 2019/8/29
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
abstract class AbstractSqlBuilder {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    protected StringBuilder sqlBuilder = new StringBuilder();
    protected List<String> whereList = new ArrayList<>();
    protected List<String> columnList = new ArrayList<>();

    @Override
    public abstract String toString();

    protected void appendList(StringBuilder sqlBuilder, List list, String first, String connector) {
        appendList(sqlBuilder, list, first, connector, false);
    }

    protected void appendList(StringBuilder sqlBuilder, List list, String first, String connector, boolean autoSqlFormat){
        boolean isFirst = true;
        for (Object s : list){
            if(isFirst){
                sqlBuilder.append(first);
            }else{
                sqlBuilder.append(connector);
            }
            if(autoSqlFormat){
                if(s instanceof String){
                    sqlBuilder.append("'"+s+"'");
                }else if(s instanceof Date){
                    sqlBuilder.append("'"+dateFormat.format(s)+"'");
                }else{
                    sqlBuilder.append(s);
                }
            }else {
                sqlBuilder.append(s);
            }

            isFirst = false;
        }
    }
}
