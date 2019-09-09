package com.sudnae.liteorm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2019/9/9
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
public class SqlUtil {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public static String getSqlValueString(Object v){
        if(v instanceof String){
            return "'"+v+"'";
        }else if(v instanceof Date){
            return  "'"+dateFormat.format(v)+"'";
        }else{
            return v+"";
        }
    }
}
