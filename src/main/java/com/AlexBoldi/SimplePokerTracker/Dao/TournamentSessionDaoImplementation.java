package com.AlexBoldi.SimplePokerTracker.Dao;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;
import com.AlexBoldi.SimplePokerTracker.Domain.TournamentSession;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TournamentSessionDaoImplementation implements TournamentSessionDao {

    private String dbType;
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String password;

    public TournamentSessionDaoImplementation(String dbType, String host, String port, String dbName, String user, String password) {
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
    public List<TournamentSession> getAll() {
        List<TournamentSession> result = new LinkedList<>();
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from tournaments order by date desc")
        ) {
            while(resultSet.next()) {
                TournamentSession s = new TournamentSession();
                s.setDate(resultSet.getString(1));
                s.setBuyIn(resultSet.getFloat(2));
                s.setPrize(resultSet.getFloat(3));
                s.setId(resultSet.getInt(4));
                result.add(s);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public TournamentSession create(TournamentSession c) {
        return null;
    }

    @Override
    public List<TournamentSession> getStats() {
        return null;
    }

    @Override
    public List<TournamentSession> getResultsOverTime() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("delete from tournaments where id = " + id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public TournamentSession update(TournamentSession c) {
        return null;
    }

    @Override
    public TournamentSession delete(TournamentSession c) {
        return null;
    }

}
