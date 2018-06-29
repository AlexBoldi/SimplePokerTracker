package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Dao.PokerSessionDao;
import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
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
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            for (PokerSession s : pokerSessions) {
                fileWriter.append(String.valueOf(s.getPokerSessionDate()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(s.getPokerSessionResult()));
                fileWriter.append("\n");
            }
            System.out.println("CSV file was created successfully");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
            }
        }
    }

}
