package com.sudnae.liteorm.utils;

import com.sudnae.liteorm.annotations.ColumnName;
import com.sudnae.liteorm.annotations.TableName;
import com.sudnae.liteorm.entity.WallpaperInfo;
import com.sudnae.liteorm.exception.NotDefineTableNameException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * 2019/8/29
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
class AnnotationUtilTest {

    @Test
    void getAnnotationValue() throws NoSuchMethodException, IllegalAccessException, NotDefineTableNameException, InvocationTargetException {
        System.out.println(AnnotationUtil.getAnnotationValue(WallpaperInfo.class, TableName.class));
    }

}