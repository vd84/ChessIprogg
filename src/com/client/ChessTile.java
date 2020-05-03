package com.client;

import javax.swing.*;

public class ChessTile {

    private JPanel panel;
    private int xCoord;
    private int yCoord;

    public ChessTile(JPanel panel, int xCoord, int yCoord) {
        this.panel = panel;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }


    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
