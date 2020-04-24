package com.server;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player {

    private int timeLeftSeconds;
    private boolean isInGame;
    private boolean isWhite;
    private boolean isTimerOn = false;


    public Player() {
        this.timeLeftSeconds = 360;
        this.isInGame = false;
        this.determineIfWhite();
    }

    private void determineIfWhite() {
        this.isWhite = new Random().nextInt(2) == 1;
    }

    public void decreaseTimer() {
        while (isTimerOn || timeLeftSeconds > 0) {
            timeLeftSeconds -= 1;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }


    public boolean move(Piece piece, BoardPosition moveToPosition) {

        if (piece.move(moveToPosition)) {
            isTimerOn = false;
            return true;
        }
        return false;


    }


    public boolean isOutOfTime() {
        return timeLeftSeconds <= 0;
    }

    public int getTimeLeftSeconds() {
        return timeLeftSeconds;
    }

    public void setTimeLeftSeconds(int timeLeftSeconds) {
        this.timeLeftSeconds = timeLeftSeconds;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        isInGame = inGame;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }


    public boolean isTimerOn() {
        return isTimerOn;
    }

    public void setTimerOn(boolean timerOn) {
        isTimerOn = timerOn;
    }
}
