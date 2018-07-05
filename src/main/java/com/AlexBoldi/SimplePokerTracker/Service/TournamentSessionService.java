package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Dao.AbstractDao;
import com.AlexBoldi.SimplePokerTracker.Domain.TournamentSession;

import java.util.List;

public interface TournamentSessionService extends AbstractDao<TournamentSession> {

    List<TournamentSession> getAll();

    TournamentSession create(TournamentSession c);

    List<TournamentSession> getStats();

    List<TournamentSession> getResultsOverTime();

    void deleteById(int id);

    TournamentSession update(TournamentSession c);

    TournamentSession delete(TournamentSession c);

}

