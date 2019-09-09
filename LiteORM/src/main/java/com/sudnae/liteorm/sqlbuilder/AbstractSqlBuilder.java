package com.sudnae.liteorm.sqlbuilder;

import com.sudnae.liteorm.utils.SqlUtil;

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
                sqlBuilder.append(SqlUtil.getSqlValueString(s));
            }else {
                sqlBuilder.append(s);
            }

            isFirst = false;
        }
    }
}
