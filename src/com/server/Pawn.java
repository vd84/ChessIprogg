package com.server;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(boolean isWhite, BoardPosition boardPosition, int id) {
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


        if (this.isWhite()) {
            System.out.println("CurrentX: " + currentX);
            System.out.println("CurrentY: " + currentY);
            System.out.println("DesiredX: " + desiredX);
            System.out.println("DesiredY: " + desiredY);
            if (!hasMoved) {
                if (currentY == desiredY + 1 && currentX == desiredX)
                    return true;
                if (currentY == desiredY + 2 && currentX == desiredX)
                    return true;
                return false;
            } else {
                if (currentY == desiredY + 1 && currentX == desiredX)
                    return true;
                return false;
            }


        } else {
            if (!hasMoved) {
                if (currentY == desiredY - 1 && currentX == desiredX)
                    return true;
                if (currentY == desiredY - 2 && currentX == desiredX)
                    return true;
                return false;
            } else {
                if (currentY == desiredY - 1 && currentX == desiredX)
                    return true;
                return false;
            }

        }
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean captureMove(Piece capturedPiece, BoardPosition movePosition) {
        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPosition();
        int currentY = currentPos.getyPoisition();
        int desiredX = movePosition.getxPosition();
        int desiredY = movePosition.getyPoisition();
        System.out.println("in white");

        if (capturedPiece instanceof King) {
            return false;
        }

        if (isWhite()) {
            if (currentY == desiredY + 1 && currentX == desiredX + 1)
                return true;
            if (currentY == desiredY + 1 && currentX == desiredX - 1)
                return true;
        }
        return false;

    }


}
