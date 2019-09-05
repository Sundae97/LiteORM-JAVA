package com.sudnae.liteorm.sqlbuilder;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2019/9/5
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
class InsertBuilderTest {

    @Test
    void testToString() {
        String sql = new InsertBuilder("TestTable")
                .columns("id","name","age","date")
                .values(2,"tony",18,new Date())
                .toString();
        System.out.println(sql);
    }
}