package com.AlexBoldi.SimplePokerTracker.Domain;

public class TournamentSession {

    private String date;
    private float buyIn;
    private float prize;
    private int id;

    public TournamentSession(String date, float buyIn, float prize, int id) {
        this.date = date;
        this.buyIn = buyIn;
        this.prize = prize;
        this.id = id;
    }

    public TournamentSession() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getBuyIn() {
        return buyIn;
    }

    public void setBuyIn(float buyIn) {
        this.buyIn = buyIn;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
