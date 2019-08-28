package com.sudnae.liteorm;

import com.sudnae.liteorm.annotations.ColumnName;
import com.sudnae.liteorm.annotations.TableName;
import com.sudnae.liteorm.dao.WallpaperInfoDao;
import com.sudnae.liteorm.entity.WallpaperInfo;
import com.sudnae.liteorm.session.LiteOrmSqlSession;
import com.sudnae.liteorm.utils.ReflectUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("hello");
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url = "jdbc:mysql://47.100.183.36:3306/bing_wallpaper_db";
//        String user = "root";
//        String password = "cfxz1997";
//
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//            String sql = "SELECT * FROM wallpaper_table_copy2";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()){
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
//                    System.out.println(rs.getObject(i));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        InputStream inputStream = Main.class.getResourceAsStream("/application.yml");
//        Map map = new Yaml().loadAs(inputStream, Map.class);
//
//        System.out.println(((Map)map.get("datasource")).get("username"));



//        LiteOrmSqlSession session = LiteOrmSqlSession.getInstance();
//        try {
//            ResultSet rs = session.executeQuery("SELECT * FROM wallpaper_table_copy2");
//            while (rs.next()){
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
//                    System.out.print(rs.getObject(i) + "\t");
//                System.out.println("");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        WallpaperInfoDao dao = new WallpaperInfoDao();
        List<WallpaperInfo> list = dao.listAll();
        for (WallpaperInfo info :
                list) {
            System.out.println(info.toString());
        }

//        List fieldInfoList = ReflectUtil.getPrivateFieldInfoByAnnotation(WallpaperInfo.class, ColumnName.class);
//        System.out.println(fieldInfoList);
    }

}
