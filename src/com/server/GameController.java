package com.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameController {

    private boolean[][] whiteOccupiedBoardPositions;
    private boolean[][] blackOccupiedBoardPositions;
    private boolean[][] myOccupations;
    private boolean[][] oppositeOccupations;
    private ConcurrentHashMap<Integer, Piece> whitePieces;
    private ConcurrentHashMap<Integer, Piece> blackPieces;

    private ConcurrentHashMap<Integer, Piece> myPieces;
    private ConcurrentHashMap<Integer, Piece> opponentPieces;

    private King myKing;
    private King oppositeKing;

    private PieceCreator pieceCreator;


    boolean isWhite;
    private boolean isMyTurn;


    public GameController(boolean[][] whiteOccupiedBoardPositions, boolean[][] blackOccupiedBoardPositions, boolean isWhite, ConcurrentHashMap<Integer, Piece> whitePieces, ConcurrentHashMap<Integer, Piece> blackPieces, ChessServer chessServer) {


        this.isWhite = isWhite;
        this.whiteOccupiedBoardPositions = whiteOccupiedBoardPositions;
        this.blackOccupiedBoardPositions = blackOccupiedBoardPositions;
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;

        this.pieceCreator = new PieceCreator(whitePieces, blackPieces, whiteOccupiedBoardPositions, blackOccupiedBoardPositions, chessServer);

        if (this.isWhite) {
            this.myOccupations = whiteOccupiedBoardPositions;
            this.oppositeOccupations = blackOccupiedBoardPositions;
            this.myPieces = whitePieces;
            this.opponentPieces = blackPieces;
            this.myKing = (King) myPieces.get(13);
            this.oppositeKing = (King) opponentPieces.get(13);
            this.isMyTurn = true;


        } else {
            this.myOccupations = blackOccupiedBoardPositions;
            this.oppositeOccupations = whiteOccupiedBoardPositions;
            this.myPieces = blackPieces;
            this.opponentPieces = whitePieces;
            this.myKing = (King) myPieces.get(13);
            this.oppositeKing = (King) opponentPieces.get(13);
            this.isMyTurn = false;


        }


    }

    public boolean checkIfMoveOverOtherPiece(Piece piece, BoardPosition currentPos, BoardPosition desiredPos) {
        int xToCheck;
        int yToCheck = -1;
        int desiredX = desiredPos.getxPos();
        int desiredY = desiredPos.getyPos();
        int currentY = currentPos.getyPos();
        int currentX = currentPos.getxPos();


        if (piece instanceof Pawn) {
            if (currentY - desiredY == -2) {

                yToCheck = currentY + 1;
                xToCheck = currentX;
                return checkAllTilesForPieceExistance(xToCheck, yToCheck);

            } else if (currentY - desiredY == 2) {

                yToCheck = currentY - 1;
                xToCheck = currentX;
                return checkAllTilesForPieceExistance(xToCheck, yToCheck);
            } else {

                return false;
            }


        }

        return false;
    }


    public boolean checkAllTilesForPieceExistance(int xToCheck, int yToCheck) {


        return blackOccupiedBoardPositions[xToCheck][yToCheck] || whiteOccupiedBoardPositions[xToCheck][yToCheck];


    }

    public boolean checkIfCheckMate() {
        boolean isCheckMate = false;
        Piece saviour = null;
        BoardPosition saviourpos = null;
        BoardPosition prevMove = null;

        if (oppositeKing.isInCheck()) {
            isCheckMate = true;


            ((King) oppositeKing).setInCheckMate(true);

            for (Map.Entry<Integer, Piece> entry : opponentPieces.entrySet()) {

                for (BoardPosition possibleMove : entry.getValue().getPossibleMoves()) {
                    prevMove = entry.getValue().getBoardPosition();
                    entry.getValue().setBoardPosition(possibleMove);


                    if (!checkIsOpponentKingCheck() && !oppositeOccupations[possibleMove.getxPos()][possibleMove.getyPos()]) {
                        saviourpos = possibleMove;
                        saviour = entry.getValue();
                        isCheckMate = false;
                        break;

                    }

                    //Check if king can kill the checker
                    if (entry.getValue() instanceof King) {


                        if (myOccupations[possibleMove.getxPos()][possibleMove.getyPos()]) {
                            if (!checkIfTileIsCheckForOpponent(possibleMove)) {
                                saviourpos = possibleMove;

                                saviour = entry.getValue();
                                isCheckMate = false;
                                entry.getValue().setBoardPosition(prevMove);

                                break;

                            }
                        }


                    } else {
                        if (myOccupations[possibleMove.getxPos()][possibleMove.getyPos()]) {
                            entry.getValue().setBoardPosition(possibleMove);
                            if (!checkIsOpponentKingCheck()) {
                                saviourpos = possibleMove;

                                saviour = entry.getValue();
                                isCheckMate = false;
                                entry.getValue().setBoardPosition(prevMove);

                                break;

                            }
                        }
                    }


                    entry.getValue().setBoardPosition(prevMove);


                    System.out.println("Saviour " + saviour);
                }
                if (prevMove != null)
                    entry.getValue().setBoardPosition(prevMove);

            }


        }
        return isCheckMate;

    }

    public boolean checkIfStaleMate() {
        boolean isStaleMate = false;
        Piece saviour = null;
        BoardPosition saviourpos = null;
        BoardPosition prevMove = null;
        if (!(this.oppositeKing).isInCheck()) {
            isStaleMate = true;


            for (Map.Entry<Integer, Piece> entry : this.opponentPieces.entrySet()) {

                for (BoardPosition possibleMove : entry.getValue().getPossibleMoves()) {
                    prevMove = entry.getValue().getBoardPosition();
                    entry.getValue().setBoardPosition(possibleMove);


                    for (Map.Entry<Integer, Piece> entry1 : blackPieces.entrySet()) {
                        entry1.getValue().calculatePossibleMoves();
                    }
                    for (Map.Entry<Integer, Piece> entry2 : whitePieces.entrySet()) {

                        entry2.getValue().calculatePossibleMoves();
                    }


                    if (!checkIsOpponentKingCheck() && !oppositeOccupations[possibleMove.getxPos()][possibleMove.getyPos()]) {
                        saviourpos = possibleMove;
                        saviour = entry.getValue();
                        isStaleMate = false;
                        break;

                    }

                    //Check if king can kill the checker
                    if (entry.getValue() instanceof King) {


                        if (myOccupations[possibleMove.getxPos()][possibleMove.getyPos()]) {
                            if (!checkIfTileIsCheckForOpponent(possibleMove)) {
                                saviourpos = possibleMove;

                                saviour = entry.getValue();
                                isStaleMate = false;
                                entry.getValue().setBoardPosition(prevMove);

                                break;

                            }
                        }


                    } else {
                        if (myOccupations[possibleMove.getxPos()][possibleMove.getyPos()]) {
                            entry.getValue().setBoardPosition(possibleMove);
                            if (!checkIsOpponentKingCheck()) {
                                saviourpos = possibleMove;

                                saviour = entry.getValue();
                                isStaleMate = false;
                                entry.getValue().setBoardPosition(prevMove);

                                break;

                            }
                        }
                    }


                    entry.getValue().setBoardPosition(prevMove);


                    System.out.println("Saviour " + saviour);
                }
                if (prevMove != null)
                    entry.getValue().setBoardPosition(prevMove);

            }

        }
        return isStaleMate;
    }

    public void refreshPossibleMovesForAllPlayers() {

        for (Map.Entry<Integer, Piece> entry1 : myPieces.entrySet()) {
            entry1.getValue().calculatePossibleMoves();
        }
        for (Map.Entry<Integer, Piece> entry2 : opponentPieces.entrySet()) {

            entry2.getValue().calculatePossibleMoves();
        }

    }


    public boolean checkIsKingCheck(BoardPosition boardPosition) {
        ConcurrentHashMap<Integer, Piece> piecesToCheck;
        Piece offender;
        refreshPossibleMovesForAllPlayers();


        if (this.isWhite)
            piecesToCheck = blackPieces;
        else
            piecesToCheck = whitePieces;

        for (Map.Entry<Integer, Piece> entry : piecesToCheck.entrySet()) {


            for (BoardPosition boardPosition1 : entry.getValue().getPossibleMoves()) {
                if (boardPosition1.getxPos() == boardPosition.getxPos() && boardPosition1.getyPos() == boardPosition.getyPos()) {

                    return true;
                }
            }


        }
        return false;
    }

    public boolean checkIsOpponentKingCheck() {
        King king;

        ConcurrentHashMap<Integer, Piece> piecesToCheck;
        Piece offender;
        refreshPossibleMovesForAllPlayers();

        if (this.isWhite) {
            king = (King) blackPieces.get(13);
            piecesToCheck = whitePieces;
        } else {
            king = (King) whitePieces.get(13);

            piecesToCheck = blackPieces;
        }

        for (Map.Entry<Integer, Piece> whitePiece : piecesToCheck.entrySet()) {


            for (BoardPosition boardPosition1 : whitePiece.getValue().getPossibleMoves()) {
                if (boardPosition1.getxPos() == king.getBoardPosition().getxPos() && boardPosition1.getyPos() == king.getBoardPosition().getyPos()) {
                    System.out.println("Killer position: " + whitePiece.getValue() + "," + boardPosition1.getxPos() + "," + boardPosition1.getyPos());
                    return true;
                }
            }


        }
        return false;
    }

    public boolean checkIfTileIsCheckForOpponent(BoardPosition boardPosition) {

        ConcurrentHashMap<Integer, Piece> piecesToCheck;
        Piece offender;

        refreshPossibleMovesForAllPlayers();

        if (this.isWhite) {
            piecesToCheck = whitePieces;
        } else {

            piecesToCheck = blackPieces;
        }

        for (Map.Entry<Integer, Piece> whitePiece : piecesToCheck.entrySet()) {


            for (BoardPosition boardPosition1 : whitePiece.getValue().getPossibleMoves()) {
                if (boardPosition1.getxPos() == boardPosition.getxPos() && boardPosition1.getyPos() == boardPosition.getyPos()) {
                    System.out.println("Killer position: " + whitePiece.getValue() + "," + boardPosition1.getxPos() + "," + boardPosition1.getyPos());
                    return true;
                }
            }


        }
        return false;
    }

    public void setKingToCheckIfKingInCheck() {
        if (checkIsKingCheck(this.myKing.getBoardPosition())) {
            (this.myKing).setInCheck(true);

        } else {
            (this.myKing).setInCheck(false);
        }
    }

    public boolean[][] getWhiteOccupiedBoardPositions() {
        return whiteOccupiedBoardPositions;
    }

    public void setWhiteOccupiedBoardPositions(boolean[][] whiteOccupiedBoardPositions) {
        this.whiteOccupiedBoardPositions = whiteOccupiedBoardPositions;
    }

    public boolean[][] getBlackOccupiedBoardPositions() {
        return blackOccupiedBoardPositions;
    }

    public void setBlackOccupiedBoardPositions(boolean[][] blackOccupiedBoardPositions) {
        this.blackOccupiedBoardPositions = blackOccupiedBoardPositions;
    }

    public boolean[][] getMyOccupations() {
        return myOccupations;
    }

    public void setMyOccupations(boolean[][] myOccupations) {
        this.myOccupations = myOccupations;
    }

    public boolean[][] getOppositeOccupations() {
        return oppositeOccupations;
    }

    public void setOppositeOccupations(boolean[][] oppositeOccupations) {
        this.oppositeOccupations = oppositeOccupations;
    }

    public ConcurrentHashMap<Integer, Piece> getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(ConcurrentHashMap<Integer, Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public ConcurrentHashMap<Integer, Piece> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(ConcurrentHashMap<Integer, Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public ConcurrentHashMap<Integer, Piece> getMyPieces() {
        return myPieces;
    }

    public void setMyPieces(ConcurrentHashMap<Integer, Piece> myPieces) {
        this.myPieces = myPieces;
    }

    public ConcurrentHashMap<Integer, Piece> getOpponentPieces() {
        return opponentPieces;
    }

    public void setOpponentPieces(ConcurrentHashMap<Integer, Piece> opponentPieces) {
        this.opponentPieces = opponentPieces;
    }

    public King getMyKing() {
        return myKing;
    }

    public void setMyKing(King myKing) {
        this.myKing = myKing;
    }

    public King getOppositeKing() {
        return oppositeKing;
    }

    public void setOppositeKing(King oppositeKing) {
        this.oppositeKing = oppositeKing;
    }

    public PieceCreator getPieceCreator() {
        return pieceCreator;
    }

    public void setPieceCreator(PieceCreator pieceCreator) {
        this.pieceCreator = pieceCreator;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    private void checkIfOpponentKingInCheckAndCheckIfTrue() {

        if (checkIsOpponentKingCheck()) {
            System.out.println("SETTING OPPONENT IN CHECK");
            (oppositeKing).setInCheck(true);
        } else {
            System.out.println("SETTING OPPONENT NOT IN CHECK");

            (oppositeKing).setInCheck(false);

        }
    }


    public void adjustChessBoardMoveSucess(Piece movedPiece, Piece capturedPiece, BoardPosition boardPositionBeforeMove, BoardPosition desiredBoardPosition) {
        movedPiece.setBoardPosition(desiredBoardPosition);

        this.myOccupations[boardPositionBeforeMove.getxPos()][boardPositionBeforeMove.getyPos()] = false;
        this.myPieces.remove(boardPositionBeforeMove);
        this.myOccupations[desiredBoardPosition.getxPos()][desiredBoardPosition.getyPos()] = true;
        if (capturedPiece != null)
            opponentPieces.remove(capturedPiece.getId());


        refreshPossibleMovesForAllPlayers();


        checkIfOpponentKingInCheckAndCheckIfTrue();

        System.out.println("IS CHECK MATE? " + checkIfCheckMate());


    }
}
