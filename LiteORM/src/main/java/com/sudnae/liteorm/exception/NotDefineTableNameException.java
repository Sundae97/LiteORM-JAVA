package com.sudnae.liteorm.exception;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class NotDefineTableNameException extends Exception{
    public NotDefineTableNameException(Class clz) {
        super(clz.getName() + " Not define TableName Annotation");
    }
}
