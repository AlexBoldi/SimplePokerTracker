package com.AlexBoldi.SimplePokerTracker.Dao;

import java.util.List;

public interface AbstractDao<T> {

    List<T> getAll();
    T create(T c);
    List<T> getStats();
    List<T> getResultsOverTime();
    void deleteById(int id);
    T update(T c);
    T delete(T c);

}
