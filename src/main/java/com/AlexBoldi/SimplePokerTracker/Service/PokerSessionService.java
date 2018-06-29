package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

import java.util.List;

public interface PokerSessionService {

    List<PokerSession> getAll();
    void createPokerSession(PokerSession pokerSession);
    void deletePokerSessionById(int pokerSessionId);
    List<PokerSession> getStats();
    List<PokerSession> getResultsOverTime();

}