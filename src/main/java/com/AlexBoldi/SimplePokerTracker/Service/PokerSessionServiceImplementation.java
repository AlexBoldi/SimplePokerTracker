package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Dao.PokerSessionDao;
import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokerSessionServiceImplementation implements PokerSessionService {

    @Qualifier("sessionDao")
    @Autowired
    private PokerSessionDao pokerSessionDao;

    @Override
    public List<PokerSession> getAll() {
        return pokerSessionDao.getAll();
    }

    @Override
    public void createPokerSession(PokerSession pokerSession) {
        pokerSessionDao.create(pokerSession);
    }

    @Override
    public void deletePokerSessionById(int pokerSessionId) {
        pokerSessionDao.deletePokerSessionById(pokerSessionId);
    }

    @Override
    public List<PokerSession> getStats() {
        return pokerSessionDao.getStats();
    }

}
