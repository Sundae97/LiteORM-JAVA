package com.sudnae.liteorm.dao;

import com.sudnae.liteorm.entity.User;
import com.sudnae.liteorm.entity.WallpaperInfo;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 2019/9/9
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
class LiteOrmBaseDaoTest {

    @Test
    void add() {
        UserDao dao = new UserDao();
        User user = new User();
        user.setId(1);
        user.setName("tony");
        user.setSex(true);
        user.setBirth(new Date());
        dao.add(user);
    }
}