package com.server;

public class BoardPosition {


    private int xPosition;
    private int yPoisition;
    private boolean isOccupied;


    public BoardPosition(int xPosition, int yPoisition, boolean isOccupied) {
        this.xPosition = xPosition;
        this.yPoisition = yPoisition;
        this.isOccupied = isOccupied;
    }


    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPoisition() {
        return yPoisition;
    }

    public void setyPoisition(int yPoisition) {
        this.yPoisition = yPoisition;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
