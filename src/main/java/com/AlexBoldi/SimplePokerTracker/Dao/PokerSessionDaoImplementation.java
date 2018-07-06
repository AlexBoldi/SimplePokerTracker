package com.AlexBoldi.SimplePokerTracker.Dao;
import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

public class PokerSessionDaoImplementation implements PokerSessionDao {

    private String dbType;
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String password;

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Date,Amount won\nDate,0";
    private static final String FILE_FOOTER = "0,0";

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
                ResultSet resultSet = statement.executeQuery("select * from sessions order by date desc")
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
       /* Collections.sort(result, new Comparator<PokerSession>() {
            @Override
            public int compare(PokerSession o1, PokerSession o2) {
                return o2.getPokerSessionDate().compareTo(o1.getPokerSessionDate());
            }
        }); */
        return result;
    }

    @Override
    public void newPlayer(String name) {
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("CREATE SCHEMA " + name + " AUTHORIZATION \042Admin\042");
            statement.execute("SET search_path TO" + name);
            statement.execute("CREATE TABLE sessions (date varchar(10), duration numeric(6,2), result numeric(10,2), id serial PRIMARY KEY)");
            statement.execute("CREATE TABLE tournaments (date varchar(10), buyIn numeric(10,2), result numeric(10,2), id serial PRIMARY KEY)");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try(
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("delete from sessions where id = " + id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public List<PokerSession> getResultsOverTime() {
        List<PokerSession> result = new LinkedList<>();
        try (
                Connection connection = newConnection(dbType, host, port, dbName, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select date from sessions order by date asc");
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery("select result from sessions order by date asc")
        ) {
            while (resultSet.next() && resultSet2.next()) {
                PokerSession s = new PokerSession();
                s.setPokerSessionDate(resultSet.getString(1));
                s.setPokerSessionResult(resultSet2.getFloat(1));
                result.add(s);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        /*Collections.sort(result, new Comparator<PokerSession>() {
            @Override
            public int compare(PokerSession o1, PokerSession o2) {
                return o2.getPokerSessionDate().compareTo(o1.getPokerSessionDate());
            }
        });*/

        return result;
    }

    @Override
    public void writeCsvFile(List<PokerSession> pokerSessions, String filename) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            fileWriter.append(FILE_HEADER).append(NEW_LINE_SEPARATOR);
            for (PokerSession s : pokerSessions) {
                fileWriter.append(String.valueOf(s.getPokerSessionDate()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(s.getPokerSessionResult()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            fileWriter.append(FILE_FOOTER);
            System.out.println("CSV file was created successfully");
        } catch (Exception e) {
            System.out.println("Error in CSV FileWriter");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
            }
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