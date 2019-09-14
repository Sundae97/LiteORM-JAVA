package com.sudnae.liteorm.sqlbuilder;

import com.sudnae.liteorm.sqlbuilder.where.Where;
import com.sudnae.liteorm.utils.SqlUtil;

/**
 * 2019/9/10
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
//TODO
public class WhereBuilder {

    private StringBuilder whereBuilder = new StringBuilder(" WHERE ");

    public WhereBuilder(Where where){ }

    public WhereBuilder where(Where whereEntry){
        return this;
    }

    public WhereBuilder or(Where whereEntry){
        return this;
    }

    public WhereBuilder and(Where whereEntry){
        return this;
    }




}
