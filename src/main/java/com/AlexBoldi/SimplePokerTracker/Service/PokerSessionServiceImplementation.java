package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Dao.PokerSessionDao;
import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

@Service
public class PokerSessionServiceImplementation implements PokerSessionService {

    @Qualifier("pokerSessionDao")
    @Autowired
    private PokerSessionDao pokerSessionDao;

    @Override
    public List<PokerSession> getAll() {
        return pokerSessionDao.getAll();
    }

    @Override
    public void create(PokerSession pokerSession) {
        pokerSessionDao.create(pokerSession);
    }

    @Override
    public void deleteById(int id) {
        pokerSessionDao.deleteById(id);
    }

    @Override
    public List<PokerSession> getStats() {
        return pokerSessionDao.getStats();
    }

    @Override
    public List<PokerSession> getResultsOverTime() {
        return pokerSessionDao.getResultsOverTime();
    }

    @Override
    public void accumulateResultsOverTime(List<PokerSession> pokerSessions) {
        DecimalFormat df = new DecimalFormat("#.##");
        float i=0;
        for (PokerSession pokerSession : pokerSessions) {
            i += pokerSession.getPokerSessionResult();
            pokerSession.setPokerSessionResult(i);
            String result = df.format(pokerSession.getPokerSessionResult());
            pokerSession.setPokerSessionResult(Float.valueOf(result));
        }
        Collections.reverse(pokerSessions);
    }

    @Override
    public void writeCsvFile(List<PokerSession> pokerSessions, String filename) {
        pokerSessionDao.writeCsvFile(pokerSessions, filename);
    }

}
