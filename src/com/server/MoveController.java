package com.server;


public class MoveController {

    private GameController gameController;


    public MoveController(GameController gameController) {
        this.gameController = gameController;

    }

    public boolean move(Piece movedPiece, Piece capturedPiece, BoardPosition desiredBoardPosition) {
        BoardPosition boardPositionBeforeMove = movedPiece.getBoardPosition();
        boolean moveSuccessFul = movedPiece.move(desiredBoardPosition);


        gameController.setKingToCheckIfKingInCheck();


        if (capturedPiece instanceof King) {
            System.out.println("MOVE FAILED, REASON: CANT CAPTURE KING");

            moveSuccessFul = false;
        }

        if (capturedPiece != null && capturedPiece.isWhite() == movedPiece.isWhite()) {
            System.out.println("MOVE FAILED. REASON: CANT CAPTURE OWN PIECE 1");

            moveSuccessFul = false;
        }

        if (capturedPiece != null && capturedPiece.getId() == -99) {
            System.out.println("MOVE FAILED. REASON: CANT CAPTURE OWN PIECE");

            moveSuccessFul = false;
        }

        if (!(movedPiece instanceof Knight) && gameController.checkIfMoveOverOtherPiece(movedPiece, boardPositionBeforeMove, desiredBoardPosition)) {
            System.out.println("MOVE FAILED. REASON: CANT JUMP OVER PIECE IF NOT KNIGHT");

            moveSuccessFul = false;

        }


        if ((movedPiece instanceof King) && gameController.checkIsKingCheck(desiredBoardPosition)) {
            System.out.println("MOVE FAILED, REASON: THAT MOVE PUTS YOUR KING IN CHECK1");

            moveSuccessFul = false;
        }


        if (!(movedPiece instanceof King) && gameController.checkIsKingCheck(gameController.getMyKing().getBoardPosition())) {
            System.out.println("MOVE FAILED, REASON: THAT MOVE PUTS YOUR KING IN CHECK2");
            moveSuccessFul = false;
        }


        if ((gameController.getMyKing()).isInCheck()) {

            gameController.refreshPossibleMovesForAllPlayers();

            if (!gameController.checkIsKingCheck(gameController.getMyKing().getBoardPosition())) {
                System.out.println("U RESCUED KING");
                moveSuccessFul = true;
            } else {
                System.out.println("Still In CHECK");

                moveSuccessFul = false;
            }

        }
        if (!(movedPiece instanceof King) && ((King) gameController.getMyKing()).isInCheck()) {
            System.out.println("MOVE FAILED, REASON: YOUR KING IS IN CHECK");

            moveSuccessFul = false;
        }

/*                if (!isMyTurn) {
                    System.out.println("MOVE FAILED, REASON: NOT UR TURN");
                    moveSuccessFul = false;
                }*/


        if (moveSuccessFul) {
            gameController.adjustChessBoardMoveSucess(movedPiece, capturedPiece, boardPositionBeforeMove, desiredBoardPosition);
        }


        return moveSuccessFul;
    }


    public void opponentMove(int xCoord, int yCoord, int id) {

        gameController.getMyPieces().get(id).getBoardPosition().setxPos(xCoord);
        gameController.getMyPieces().get(id).getBoardPosition().setyPos(yCoord);


        gameController.getMyOccupations()[xCoord][yCoord] = true;


        gameController.setKingToCheckIfKingInCheck();


    }
}
