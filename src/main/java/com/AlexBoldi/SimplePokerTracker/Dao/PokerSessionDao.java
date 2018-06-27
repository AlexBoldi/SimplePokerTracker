package com.AlexBoldi.SimplePokerTracker.Dao;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

public interface PokerSessionDao extends AbstractDao<PokerSession> {

    void deletePokerSessionById(int pokerSessionId);

}
