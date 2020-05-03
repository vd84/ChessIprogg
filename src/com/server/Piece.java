package com.server;

public class Piece {


    private boolean isOnBoard;
    private boolean isWhite;
    private BoardPosition boardPosition;
    private int id;

    public Piece(boolean isWhite, BoardPosition boardPosition, int id) {
        this.isWhite = isWhite;
        this.isOnBoard = true;
        this.boardPosition = boardPosition;
        this.id = id;
    }

    public boolean move(BoardPosition moveToPosition) {
        if (canMoveToPosition(moveToPosition)) {
            if (this instanceof Pawn) {
                ((Pawn) this).setHasMoved(true);
            }

            this.boardPosition = moveToPosition;
            return true;

        } else {
            return false;
        }

    }

    public boolean canMoveToPosition(BoardPosition movePosition) {
        return false;
    }


    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(BoardPosition boardPosition) {
        this.boardPosition = boardPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean capture(Piece capturedPiece, BoardPosition desiredPosition) {

        return captureMove(capturedPiece, desiredPosition);




    }

    public boolean captureMove(Piece capturedPiece, BoardPosition desiredPosition){
        return false;
    }
}
