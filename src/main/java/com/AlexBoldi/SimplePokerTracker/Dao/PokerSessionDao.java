package com.AlexBoldi.SimplePokerTracker.Dao;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

import java.util.List;

public interface PokerSessionDao extends AbstractDao<PokerSession> {

    void deletePokerSessionById(int pokerSessionId);
    List<PokerSession> getStats();
    List<PokerSession> getResultsOverTime();
}
