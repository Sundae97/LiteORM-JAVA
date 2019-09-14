package com.sudnae.liteorm.sqlbuilder.where;

import com.sudnae.liteorm.utils.SqlUtil;
//TODO
public class Where {

    protected static final String OP_EQUAL = "=";
    protected static final String OP_NOTEQUAL = "<>";
    protected static final String OP_GREATER = ">";
    protected static final String OP_LESS = "<";
    protected static final String OP_GREATER_OR_EQUAL = ">=";
    protected static final String OP_LESS_OR_EQUAL = "<=";
    protected static final String OP_BETWEEN = "BETWEEN";
    protected static final String OP_LIKE = "LIKE";

    protected static final String CONN_AND = "AND";
    protected static final String CONN_OR = "OR";

    protected StringBuilder whereEntryBuilder = new StringBuilder();
    private ConditionEntry conditionEntry;
    protected Where(){}

    public Where(String columnName){
        conditionEntry.columnName = columnName;
    }

    public ConnectorWhere equal(Object value){
        conditionEntry.operator = OP_EQUAL;
        conditionEntry.value = SqlUtil.getSqlValueString(value);
        return new ConnectorWhere();
    }

    @Override
    public String toString() {
        //TODO
        return super.toString();
    }

    class ConnectorWhere{

        @Override
        public String toString() {
            return super.toString();
        }
    }

    class ConditionEntry{
        String columnName;
        String operator;
        String value;
    }

}
