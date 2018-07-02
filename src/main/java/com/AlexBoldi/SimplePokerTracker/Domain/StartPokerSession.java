package com.AlexBoldi.SimplePokerTracker.Domain;

import java.util.concurrent.TimeUnit;

public class StartPokerSession {
    private boolean stopped = false;
    private long start;
    private long stop;


    public void Chronometer() {
        startChronometer();
    }

    private void startChronometer() {
        start = System.nanoTime();
        stopped = false;
    }

    public void stopChronometer() {
        if (stopped) {
            throw new IllegalStateException("Chronometer already stopped");
        }
        stop = System.nanoTime();
        stopped = true;
    }

    public long getTime(TimeUnit timeUnit) {
        return timeUnit.convert(nanos(), TimeUnit.MINUTES);
    }

    protected long nanos() {
        if (!stopped) {
            throw new IllegalStateException("ba");
        }
        return (stop - start);
    }

}
