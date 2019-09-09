package com.sudnae.liteorm.utils;

import com.sudnae.liteorm.entity.WallpaperInfo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2019/9/9
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
class ReflectUtilTest {

    @Test
    void getColumnNamesAndValues() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        WallpaperInfo wallpaperInfo = new WallpaperInfo();
        wallpaperInfo.setId(5);
        wallpaperInfo.setBaseUrl("test");
        wallpaperInfo.setDate("asdasd");
        Map<String, Object> map = ReflectUtil.getColumnNamesAndValues(wallpaperInfo);
        for (Map.Entry<String, Object> entry :
                map.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());

        }
    }
}