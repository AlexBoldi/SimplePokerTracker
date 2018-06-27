package com.AlexBoldi.SimplePokerTracker.Domain;

public class PokerSession {

    private String pokerSessionDate;
    private int pokerSessionDuration;
    private float pokerSessionResult;
    private int pokerSessionId;

    public PokerSession(String pokerSessionDate, int pokerSessionDuration, float pokerSessionResult, int pokerSessionId) {
        this.pokerSessionDate = pokerSessionDate;
        this.pokerSessionDuration = pokerSessionDuration;
        this.pokerSessionResult = pokerSessionResult;
        this.pokerSessionId = pokerSessionId;
    }

    public PokerSession(){

    }

    public String getPokerSessionDate() {
        return pokerSessionDate;
    }

    public void setPokerSessionDate(String pokerSessionDate) {
        this.pokerSessionDate = pokerSessionDate;
    }

    public int getPokerSessionDuration() {
        return pokerSessionDuration;
    }

    public void setPokerSessionDuration(int pokerSessionDuration) {
        this.pokerSessionDuration = pokerSessionDuration;
    }

    public float getPokerSessionResult() {
        return pokerSessionResult;
    }

    public void setPokerSessionResult(float pokerSessionResult) {
        this.pokerSessionResult = pokerSessionResult;
    }

    public int getPokerSessionId() {
        return pokerSessionId;
    }

    public void setPokerSessionId(int pokerSessionId) {
        this.pokerSessionId = pokerSessionId;
    }
}