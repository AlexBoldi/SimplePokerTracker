package com.AlexBoldi.SimplePokerTracker.Dao;
import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PokerSessionDaoImplementation implements PokerSessionDao {

    private String dbType;
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String password;

    public PokerSessionDaoImplementation(String dbType, String host, String port, String dbName, String user, String password) {
        this.dbType = dbType;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    private static Connection newConnection(String dbType, String host, String port, String dbName, String user, String password) {
        loadDriver();
        DriverManager.setLoginTimeout(60);
        try {
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append(dbType)
                    .append("://")
                    .append(host)
                    .append(":")
                    .append(port)
                    .append("/")
                    .append(dbName)
                    .append("?user=")
                    .append(user)
                    .append("&password=")
                    .append(password)
                    .toString();
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return null;
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can't load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<PokerSession> getAll() {
        List<PokerSession> result = new LinkedList<>();
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from sessions")
        ) {
            while(resultSet.next()) {
                PokerSession s = new PokerSession();
                s.setPokerSessionDate(resultSet.getString(1));
                s.setPokerSessionDuration(resultSet.getInt(2));
                s.setPokerSessionResult(resultSet.getFloat(3));
                s.setPokerSessionId(resultSet.getInt(4));
                result.add(s);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public void deletePokerSessionById(int pokerSessionId) {
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("delete from sessions where id = " + pokerSessionId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public PokerSession create(PokerSession s) {
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("insert into sessions values ('" + s.getPokerSessionDate() + "', " + s.getPokerSessionDuration() + ", " + s.getPokerSessionResult() + ")");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return s;
    }

    @Override
    public PokerSession update(PokerSession s) {
        return null;
    }

    @Override
    public PokerSession delete(PokerSession s) {
        return null;
    }

    @Override
    public List<PokerSession> getStats() {
        List<PokerSession> stats = new LinkedList<>();
        //DecimalFormat df = new DecimalFormat("#.##");
        try (
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select sum(result) from sessions");
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery("select sum(duration) from sessions")
        ) {
            while (resultSet.next() && resultSet2.next()) {
                PokerSession s = new PokerSession();
                s.setPokerSessionResult(resultSet.getFloat(1));
                //String result = df.format(s.getPokerSessionResult());
                //s.setPokerSessionResult(Float.valueOf(result));
                s.setPokerSessionDuration(resultSet2.getFloat(1)/60);
                //String duration = df.format(s.getPokerSessionDuration()/60);
                //s.setPokerSessionDuration(Float.valueOf(duration));
                stats.add(s);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return stats;
    }

}