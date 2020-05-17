package com.server;

import java.util.concurrent.CopyOnWriteArrayList;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(boolean isWhite, BoardPosition boardPosition, int id, ChessServer chessServer) {
        super(isWhite, boardPosition, id, chessServer);
        calculatePossibleMoves();

    }

    @Override
    public boolean canMoveToPosition(BoardPosition movePosition) {

        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPos();
        int currentY = currentPos.getyPos();
        int desiredX = movePosition.getxPos();
        int desiredY = movePosition.getyPos();


        if (this.isWhite()) {

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

                if (currentY == desiredY - 1 && currentX == desiredX) {
                    System.out.println("Moved 1 step forward");
                    return true;
                }
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


    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean captureMove(Piece capturedPiece, BoardPosition movePosition) {
        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPos();
        int currentY = currentPos.getyPos();
        int desiredX = movePosition.getxPos();
        int desiredY = movePosition.getyPos();


        if (capturedPiece instanceof King) {
            return false;
        }

        if (isWhite()) {
            if (currentY == desiredY + 1 && currentX == desiredX + 1)
                return true;
            if (currentY == desiredY + 1 && currentX == desiredX - 1)
                return true;
        } else {
            if (currentY == desiredY - 1 && currentX == desiredX + 1)
                return true;
            if (currentY == desiredY - 1 && currentX == desiredX - 1)
                return true;
        }
        return false;

    }

    @Override
    public void calculatePossibleMoves() {
        this.setPossibleMoves(new CopyOnWriteArrayList<>());
        BoardPosition possibleMove;
        int currentX = this.getBoardPosition().getxPos();
        int currentY = this.getBoardPosition().getyPos();


        if (isWhite()) {


            if (currentX + 1 <= 7 && currentY - 1 > 0) {
                possibleMove = new BoardPosition(currentX + 1, currentY - 1, false);
                getPossibleMoves().add(possibleMove);
            }
            if (currentX - 1 > 0 && currentY - 1 > 0) {
                possibleMove = new BoardPosition(currentX - 1, currentY - 1, false);
                getPossibleMoves().add(possibleMove);
            }

        } else {

            if (currentX + 1 <= 7 && currentY + 1 <= 7) {
                possibleMove = new BoardPosition(currentX + 1, currentY + 1, false);
                getPossibleMoves().add(possibleMove);
            }
            if (currentX - 1 > 0 && currentY <= 7) {
                possibleMove = new BoardPosition(currentX - 1, currentY + 1, false);
                getPossibleMoves().add(possibleMove);
            }

        }


    }

    public boolean isHasMoved() {
        return hasMoved;
    }


}
