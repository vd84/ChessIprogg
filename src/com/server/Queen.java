package com.server;

import java.util.concurrent.CopyOnWriteArrayList;

public class Queen extends Piece {

    public Queen(boolean isWhite, BoardPosition boardPosition, int id, ChessServer chessServer) {
        super(isWhite, boardPosition, id, chessServer);
    }


    @Override
    public boolean canMoveToPosition(BoardPosition movePosition) {
        BoardPosition currentPos = this.getBoardPosition();
        int currentX = currentPos.getxPos();
        int currentY = currentPos.getyPos();
        int desiredX = movePosition.getxPos();
        int desiredY = movePosition.getyPos();

        return (currentX - desiredX == 0) ||
                (currentY - desiredY == 0) ||
                (Math.abs(currentX - desiredX) == Math.abs(currentY - desiredY));

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
        boolean[][] whiteOccupiedBoardPositions = this.getChessServer().();
        boolean[][] blackOccupiedBoardPositions = this.getChessServer().getBlackOccupiedBoardPositions();
        while(moveY <7 && moveX <7){

            moveY = moveY + 1;
            moveX = currentX;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }

        moveX = currentX;
        moveY = currentY;
        while(moveY >0 && moveX <7){

            moveY = moveY -1;
            moveX  = currentX;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }

        moveX = currentX;
        moveY = currentY;
        while(moveY <7 && moveX >0){

            moveY = currentY;
            moveX  = moveX -1;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }

        moveX = currentX;
        moveY = currentY;
        while(moveY <7 && moveX <7){

            moveY = currentY;
            moveX = moveX +1;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }

        while(moveY <7 && moveX <7){

            moveY += 1;
            moveX += 1;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }
        moveX = currentX;
        moveY = currentY;

        while(moveY >0 && moveX >0){
            moveY -= 1;
            moveX -= 1;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }

        moveX = currentX;
        moveY = currentY;

        while(moveY <7 && moveX >0){
            moveY += 1;
            moveX -= 1;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }

        moveX = currentX;
        moveY = currentY;

        while(moveY >0 && moveX <7){
            moveY -= 1;
            moveX += 1;
            this.getPossibleMoves().add(new BoardPosition(moveX , moveY, false));

            if (whiteOccupiedBoardPositions[moveX][moveY] || blackOccupiedBoardPositions[moveX][moveY]){
                break;
            }
        }



    }
}
