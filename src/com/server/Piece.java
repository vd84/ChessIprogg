package com.server;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Piece {


    private ChessServer chessServer;
    private boolean isOnBoard;
    private boolean isWhite;
    private BoardPosition boardPosition;
    private int id;
    private CopyOnWriteArrayList<BoardPosition> possibleMoves;


    public Piece(boolean isWhite, BoardPosition boardPosition, int id, ChessServer chessServer) {

        possibleMoves = new CopyOnWriteArrayList<>();
        this.isWhite = isWhite;
        this.isOnBoard = true;
        this.boardPosition = boardPosition;
        this.id = id;
        this.chessServer = chessServer;
    }

    public void calculatePossibleMoves() {

    }

    public boolean move(BoardPosition moveToPosition) {

        if (canMoveToPosition(moveToPosition)) {
            this.setBoardPosition(moveToPosition);

            if (this instanceof Pawn){
                ((Pawn) this).setHasMoved(true);
            }



            return true;

        } else {
            this.setBoardPosition(moveToPosition);

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

    public boolean captureMove(Piece capturedPiece, BoardPosition desiredPosition) {
        return false;
    }

    public CopyOnWriteArrayList<BoardPosition> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(CopyOnWriteArrayList<BoardPosition> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public ChessServer getChessServer() {
        return chessServer;
    }
}
