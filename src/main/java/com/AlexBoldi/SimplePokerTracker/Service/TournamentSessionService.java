package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Dao.AbstractDao;
import com.AlexBoldi.SimplePokerTracker.Domain.TournamentSession;

import java.util.List;

public interface TournamentSessionService extends AbstractDao<TournamentSession> {

    //List<TournamentSession> getAll();

    void createTournamentSession(TournamentSession c);

    List<TournamentSession> getStats();

    List<TournamentSession> getResultsOverTime();

    //void deleteById(int id);

}

