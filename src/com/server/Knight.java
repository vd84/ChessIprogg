package com.server;

import java.util.concurrent.CopyOnWriteArrayList;

public class Knight extends Piece {
    public Knight(boolean isWhite, BoardPosition boardPosition, int id, ChessServer chessServer) {
        super(isWhite, boardPosition, id, chessServer);
    }


    @Override
    public boolean canMoveToPosition(BoardPosition movePosition) {
        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPos();
        int currentY = currentPos.getyPos();
        int desiredX = movePosition.getxPos();
        int desiredY = movePosition.getyPos();


        return (currentY - desiredY == 2 && currentX - desiredX == -1) ||
                (currentY - desiredY == 2 && currentX - desiredX == 1) ||
                (currentY - desiredY == -2 && currentX - desiredX == -1) ||
                (currentY - desiredY == -2 && currentX - desiredX == 1) ||
                (currentY - desiredY == -1 && currentX - desiredX == -2) ||
                (currentY - desiredY == -1 && currentX - desiredX == 2) ||
                (currentY - desiredY == 1 && currentX - desiredX == -2) ||
                (currentY - desiredY == 1 && currentX - desiredX == 2);

    }


    @Override
    public boolean captureMove(Piece capturedPiece, BoardPosition desiredPosition) {
        System.out.println("PIECE TAKEN: " + capturedPiece);

        if (capturedPiece instanceof King) {
            return false;
        }
        return move(desiredPosition);

    }

    @Override
    public void calculatePossibleMoves() {


        this.setPossibleMoves(new CopyOnWriteArrayList<>());
        int currentX = this.getBoardPosition().getxPos();
        int currentY = this.getBoardPosition().getyPos();

        int moveX = currentX;
        int moveY = currentY;
        if (currentX + 2 <= 7 && currentY +1 <= 7)
            getPossibleMoves().add(new BoardPosition(currentX + 2, currentY + 1, false));
        if (currentX +2 <= 7 && currentY -1 > 0)
            getPossibleMoves().add(new BoardPosition(currentX + 2, currentY - 1, false));
        if (currentX-2 > 0 && currentY-1 >0)
            getPossibleMoves().add(new BoardPosition(currentX - 2, currentY - 1, false));
        if (currentX-2 > 0 && currentY-1 > 7)
            getPossibleMoves().add(new BoardPosition(currentX - 2, currentY + 1, false));
        if (currentX-1 > 0 && currentY +2<= 7)
            getPossibleMoves().add(new BoardPosition(currentX - 1, currentY + 2, false));
        if (currentX+2 <= 7 && currentY+2 <= 7)
            getPossibleMoves().add(new BoardPosition(currentX + 1, currentY + 2, false));
        if (currentX -1> 0 && currentY-2 > 0)
            getPossibleMoves().add(new BoardPosition(currentX - 1, currentY - 2, false));
        if (currentX+1 <= 7 && currentY-2 > 0)
            getPossibleMoves().add(new BoardPosition(currentX + 1, currentY - 2, false));


    }
}
