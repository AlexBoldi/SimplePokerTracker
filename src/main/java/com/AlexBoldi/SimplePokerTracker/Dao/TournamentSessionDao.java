package com.AlexBoldi.SimplePokerTracker.Dao;

import com.AlexBoldi.SimplePokerTracker.Domain.TournamentSession;

import java.util.List;

public interface TournamentSessionDao extends AbstractDao<TournamentSession> {

    List<TournamentSession> getAll();

    TournamentSession create(TournamentSession c);

    List<TournamentSession> getStats();

    List<TournamentSession> getResultsOverTime();

    void deleteById(int id);

    TournamentSession update(TournamentSession c);

    TournamentSession delete(TournamentSession c);

}
