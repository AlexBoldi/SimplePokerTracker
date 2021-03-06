package com.AlexBoldi.SimplePokerTracker.Dao;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

import java.util.List;

public interface PokerSessionDao extends AbstractDao<PokerSession> {

    void newPlayer(String name);
    void deleteById(int id);
    List<PokerSession> getStats();
    List<PokerSession> getResultsOverTime();
    void writeCsvFile(List<PokerSession> pokerSessions, String filename);

}
