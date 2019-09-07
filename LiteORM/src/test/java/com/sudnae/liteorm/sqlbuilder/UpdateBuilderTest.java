package com.sudnae.liteorm.sqlbuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2019/9/7
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
class UpdateBuilderTest {

    @Test
    void testToString() {
        String sql = new UpdateBuilder("TEST_DB")
                .set("name", "May")
                .set("age", 18)
                .set("addres", "age")
                .where("id=1")
                .toString();
        System.out.println(sql);

    }
}