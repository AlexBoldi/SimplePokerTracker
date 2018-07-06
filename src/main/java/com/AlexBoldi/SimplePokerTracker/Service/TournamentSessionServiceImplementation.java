package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Dao.TournamentSessionDao;
import com.AlexBoldi.SimplePokerTracker.Domain.TournamentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentSessionServiceImplementation implements TournamentSessionService {

    @Qualifier("tournamentSessionDao")
    @Autowired
    private TournamentSessionDao tournamentSessionDao;

    @Override
    public List<TournamentSession> getAll() {
        return tournamentSessionDao.getAll();
    }

    @Override
    public TournamentSession create(TournamentSession c) {
        return null;
    }

    @Override
    public void createTournamentSession (TournamentSession c) {
        tournamentSessionDao.createTournamentSession(c);
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
        tournamentSessionDao.deleteById(id);
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
