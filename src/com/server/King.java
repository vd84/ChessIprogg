package com.server;

import java.util.concurrent.CopyOnWriteArrayList;

public class King extends Piece {
    private boolean isInCheck;
    private boolean isInCheckMate;

    public King(boolean isWhite, BoardPosition boardPosition, int id, ChessServer chessServer) {
        super(isWhite, boardPosition, id, chessServer);
    }

    @Override
    public boolean canMoveToPosition(BoardPosition movePosition) {
        System.out.println("MY POS " + getBoardPosition().getxPos() + "," + getBoardPosition().getyPos());
        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPos();
        int currentY = currentPos.getyPos();
        int desiredX = movePosition.getxPos();
        int desiredY = movePosition.getyPos();

        return (Math.abs(currentX - desiredX) == 1 && currentY == desiredY) || (Math.abs(currentY - desiredY) == 1 && currentX == desiredX) ||
                (Math.abs(currentX - desiredX) == Math.abs(currentY - desiredY) && (Math.abs(currentX - desiredX) == 1 || Math.abs(currentY - desiredY) == 1));


    }


    @Override
    public boolean captureMove(Piece capturedPiece, BoardPosition desiredPosition) {

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


        getPossibleMoves().add(new BoardPosition(currentX + 1, currentY + 1, false));

        if (currentY - 1 > 0)
            getPossibleMoves().add(new BoardPosition(currentX + 1, currentY - 1, false));
        if (currentX - 1 > 0)
            getPossibleMoves().add(new BoardPosition(currentX - 1, currentY + 1, false));
        if (currentX - 1 > 0 && currentY - 1 > 0)
            getPossibleMoves().add(new BoardPosition(currentX - 1, currentY - 1, false));
        if (currentX - 1 > 0)
            getPossibleMoves().add(new BoardPosition(currentX - 1, currentY, false));

        getPossibleMoves().add(new BoardPosition(currentX + 1, currentY, false));
        if (currentY - 1 > 0)
            getPossibleMoves().add(new BoardPosition(currentX, currentY - 1, false));

        getPossibleMoves().add(new BoardPosition(currentX, currentY + 1, false));


    }

    public boolean isInCheck() {
        return isInCheck;
    }

    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
    }


    public boolean isInCheckMate() {
        return isInCheckMate;
    }

    public void setInCheckMate(boolean inCheckMate) {
        isInCheckMate = inCheckMate;
    }
}
