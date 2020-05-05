package com.server;

public class Bishop extends Piece {

    public Bishop(boolean isWhite, BoardPosition boardPosition, int id) {
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


        System.out.println("moving rook");
        System.out.println("currentx: " + currentX);
        System.out.println("desiredx: " + desiredX);
        System.out.println("currenty: " + currentY);
        System.out.println("desiredY: " + desiredY);


        System.out.println("CurrX - DesirX" + (currentX-desiredX));
        System.out.println("CurrY-desirY" + (currentY-desiredY));


        return Math.abs(currentX-desiredX) == Math.abs(currentY -desiredY);

    }

    @Override
    public boolean captureMove(Piece capturedPiece, BoardPosition desiredPosition) {
        System.out.println("PIECE TAKEN: " + capturedPiece);

        if (capturedPiece instanceof King){
            return false;
        }
        return move(desiredPosition);

    }
}
