package com.AlexBoldi.SimplePokerTracker.Service;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;

import java.util.List;

public interface AbstractService<T> {

    List<T> getAll();
    void create(T c);
    void deleteById(int id);
    List<T> getStats();
    List<T> getResultsOverTime();
    void accumulateResultsOverTime(List<T> c);
    void writeCsvFile(List<T> c, String filename);

}
