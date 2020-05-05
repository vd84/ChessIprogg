package com.server;

public class Queen extends Piece {

    public Queen(boolean isWhite, BoardPosition boardPosition, int id) {
        super(isWhite, boardPosition, id);
    }


    @Override
    public boolean canMoveToPosition(BoardPosition movePosition) {

        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPosition();
        int currentY = currentPos.getyPoisition();
        int desiredX = movePosition.getxPosition();
        int desiredY = movePosition.getyPoisition();
        System.out.println("in white");


        return true;

    }

}
