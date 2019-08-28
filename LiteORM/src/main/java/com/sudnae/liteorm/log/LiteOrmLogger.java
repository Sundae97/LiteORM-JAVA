package com.sudnae.liteorm.log;

import com.sudnae.liteorm.config.Config;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class LiteOrmLogger {
    public static void error(String content){
        if(!Config.DEBUG) return;
        System.out.println("error ==> " + content);
    }

    public static void info(String content){
        if(!Config.DEBUG) return;
        System.out.println("info ==> " + content);
    }
}
