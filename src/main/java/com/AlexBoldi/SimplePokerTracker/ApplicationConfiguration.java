package com.AlexBoldi.SimplePokerTracker;

import com.AlexBoldi.SimplePokerTracker.Dao.PokerSessionDao;
import com.AlexBoldi.SimplePokerTracker.Dao.PokerSessionDaoImplementation;
import com.AlexBoldi.SimplePokerTracker.Dao.TournamentSessionDao;
import com.AlexBoldi.SimplePokerTracker.Dao.TournamentSessionDaoImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PokerSessionDao pokerSessionDao(){
        return new PokerSessionDaoImplementation(
                "postgresql",
                "localhost",
                "5433",
                "SimpleTracker",
                "Admin",
                "admin147");
    }

    @Bean
    public TournamentSessionDao tournamentSessionDao(){
        return new TournamentSessionDaoImplementation(
                "postgresql",
                "localhost",
                "5433",
                "SimpleTracker",
                "Admin",
                "admin147");
    }

    @Bean
    public DataSource dataSource() {
        String url = new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append("localhost")
                .append(":")
                .append("5433")
                .append("/")
                .append("SimpleTracker")
                .append("?user=")
                .append("Admin")
                .append("&password=")
                .append("admin147").toString();
        return new SingleConnectionDataSource(url, false);
    }

}