package com.sudnae.liteorm.sqlbuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2019/9/5
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
class SelectBuilderTest {

    @Test
    void testToString() {
        String sql = new SelectBuilder("testTable")
                .column("id")
                .column("name")
                .column("age")
                .where("id=1")
                .where("age=2")
                .toString();
        System.out.println(sql);
    }
}