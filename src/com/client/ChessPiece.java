package com.client;

import javax.swing.*;

public class ChessPiece {

    private boolean isWhite;
    private ChessTile chessTile;
    private int id;

    public ChessPiece(boolean isWhite, ChessTile chessTile, int id) {
        this.isWhite = isWhite;
        this.chessTile = chessTile;
        this.id = id;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }


    public ChessTile getChessTile() {
        return chessTile;
    }

    public void setChessTile(ChessTile chessTile) {
        this.chessTile = chessTile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
