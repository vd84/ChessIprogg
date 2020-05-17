package com.server;

public class BoardPosition {


    private int xPos;
    private int yPos;
    private boolean isOccupied;


    public BoardPosition(int xPos, int yPos, boolean isOccupied) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isOccupied = isOccupied;
    }


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
