package com.sudnae.liteorm.sqlbuilder;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.AnnotationUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2019/9/2
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
class DeleteBuilderTest {

    @Test
    void testToString() {
        String sql = new DeleteBuilder("wallpaper_table")
                .where("id=1 OR id=2")
                .toString();
        System.out.println(sql);
    }
}