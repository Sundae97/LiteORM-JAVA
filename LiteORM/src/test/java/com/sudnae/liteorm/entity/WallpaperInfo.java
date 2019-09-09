package com.sudnae.liteorm.entity;

import com.sudnae.liteorm.annotations.ColumnName;
import com.sudnae.liteorm.annotations.TableName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * 2019/9/7
 *
 * @author @Sundae
 * @Email 948820549@qq.com
 */
@TableName("wallpaper_table")
public class WallpaperInfo {
    @ColumnName("id")
    private long id;
    @ColumnName("date")
    private String date;
    @ColumnName("baseUrl")
    private String baseUrl;
    @ColumnName("url")
    private String url;
    @ColumnName("copyRight")
    private String copyRight;
    @ColumnName("ossUrl")
    private String ossUrl;
    @ColumnName("mtk")
    private String mtk;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public String getMtk() {
        return mtk;
    }

    public void setMtk(String mtk) {
        this.mtk = mtk;
    }

    @Override
    public String toString() {
        return "WallpaperInfo{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", url='" + url + '\'' +
                ", copyRight='" + copyRight + '\'' +
                ", ossUrl='" + ossUrl + '\'' +
                ", mtk='" + mtk + '\'' +
                '}';
    }
}
