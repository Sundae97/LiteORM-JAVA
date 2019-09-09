package com.sudnae.liteorm.entity;

import com.sudnae.liteorm.annotations.ColumnName;
import com.sudnae.liteorm.annotations.TableName;

import java.util.Date;

/**
 * 2019/9/9
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
@TableName("TestTable")
public class User {
    @ColumnName("id")
    private int id;
    @ColumnName("name")
    private String name;
    @ColumnName("sex")
    private boolean sex;
    @ColumnName("birth")
    private Date birth;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex; //TODO 这日了狗的boolean值居然是is
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
