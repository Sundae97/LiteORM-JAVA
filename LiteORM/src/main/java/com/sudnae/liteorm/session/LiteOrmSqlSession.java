package com.sudnae.liteorm.session;

import com.sudnae.liteorm.log.LiteOrmLogger;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.*;
import java.util.Map;

/**
 * 2019/8/27
 * CopyRight @Sundae
 * Email 948820549@qq.com
 */
public class LiteOrmSqlSession {

    private volatile static LiteOrmSqlSession instance = null;
    private Connection connection = null;
    private static String url;
    private static String userName;
    private static String password;
    private static String driverClass;

    public static LiteOrmSqlSession getInstance(){
        if(instance == null){
            synchronized (LiteOrmSqlSession.class){
                if(instance == null)
                    instance = new LiteOrmSqlSession();
            }
        }
        return instance;
    }

    public LiteOrmSqlSession(){
        InputStream ymlInputStream = LiteOrmSqlSession.class.getResourceAsStream("/application.yml");
        Map ymlMap = new Yaml().loadAs(ymlInputStream, Map.class);
        Map<String, Object> datasourceMap = (Map<String, Object>) ymlMap.get("datasource");
        url = datasourceMap.get("url").toString();
        userName = datasourceMap.get("username").toString();
        password = datasourceMap.get("password").toString();
        driverClass = datasourceMap.get("driver").toString();
        LiteOrmLogger.info("url = " + url +
                "\nusername = " + userName +
                "\npassword = " + password +
                "\ndriver = " + driverClass);

        try {
            initDriver();
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initDriver() throws ClassNotFoundException {
        Class.forName(driverClass);
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        connection.setAutoCommit(false);
    }

    private void reconnect(){
        //TODO
        LiteOrmLogger.error("SQL disconnect");
    }

    private void checkConnected(){
        try {
            if(connection.isClosed()){
                reconnect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        checkConnected();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public int execute(String sql) throws SQLException {
        checkConnected();
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

}
