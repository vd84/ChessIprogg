package com.server;

import java.util.concurrent.ConcurrentHashMap;

public class PieceCreator {

    private ConcurrentHashMap<Integer, Piece> whitePieces;
    private ConcurrentHashMap<Integer, Piece> blackPieces;
    private boolean[][] whiteOccupations;
    private boolean[][] blackOccupations;
    private ChessServer chessServer;


    public PieceCreator(ConcurrentHashMap<Integer, Piece> whitePieces, ConcurrentHashMap<Integer, Piece> blackPieces, boolean[][] whiteOccupations, boolean[][] blackOccupations, ChessServer chessServer) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
        this.whiteOccupations = whiteOccupations;
        this.blackOccupations = blackOccupations;
        this.chessServer = chessServer;

        this.createBoardPositionsAndPieces();
    }

    private void createBoardPositionsAndPieces() {
        BoardPosition boardPosition = null;

        // White Pieces
        // PAWNS
        for (int i = 0; i < 8; i++) {
            boardPosition = new BoardPosition(i, 6, true);

            whitePieces.put(i + 1, new Pawn(true, boardPosition, i + 1, this.chessServer));
            whiteOccupations[i][6] = true;
        }

        BoardPosition boardPosition9 = new BoardPosition(0, 7, true);


        whitePieces.put(9, new Rook(true, boardPosition9, 9, this.chessServer));
        whiteOccupations[0][7] = true;

        BoardPosition boardPosition10 = new BoardPosition(1, 7, true);


        whitePieces.put(10, new Knight(true, boardPosition10, 10, this.chessServer));
        whiteOccupations[1][7] = true;

        BoardPosition boardPosition11 = new BoardPosition(2, 7, true);


        whitePieces.put(11, new Bishop(true, boardPosition11, 11, this.chessServer));
        whiteOccupations[2][7] = true;

        BoardPosition boardPosition12 = new BoardPosition(4, 7, true);

        whitePieces.put(13, new King(true, boardPosition12, 13, this.chessServer));
        whiteOccupations[4][7] = true;

        BoardPosition boardPosition13 = new BoardPosition(3, 7, true);

        whitePieces.put(12, new Queen(true, boardPosition13, 12, this.chessServer));
        whiteOccupations[3][7] = true;

        BoardPosition boardPosition14 = new BoardPosition(5, 7, true);

        whitePieces.put(14, new Bishop(true, boardPosition14, 14, this.chessServer));
        whiteOccupations[5][7] = true;

        BoardPosition boardPosition15 = new BoardPosition(6, 7, true);


        whitePieces.put(15, new Knight(true, boardPosition15, 15, this.chessServer));
        whiteOccupations[6][7] = true;

        BoardPosition boardPosition16 = new BoardPosition(7, 7, true);

        whitePieces.put(16, new Rook(true, boardPosition16, 16, this.chessServer));
        whiteOccupations[7][7] = true;


        //Black Pieces
        //PAWNS
        for (int i = 0; i < 8; i++) {
            BoardPosition boardPosition17 = new BoardPosition(i, 1, true);

            blackPieces.put(i + 1, new Pawn(false, boardPosition17, i + 1, this.chessServer));
            blackOccupations[i][1] = true;


        }


        BoardPosition boardPosition25 = new BoardPosition(0, 0, true);

        blackPieces.put(9, new Rook(false, boardPosition25, 9, this.chessServer));
        blackOccupations[0][0] = true;

        BoardPosition boardPosition26 = new BoardPosition(1, 0, true);

        blackPieces.put(10, new Knight(false, boardPosition26, 10, this.chessServer));
        blackOccupations[1][0] = true;

        BoardPosition boardPosition27 = new BoardPosition(2, 0, true);


        blackPieces.put(11, new Bishop(false, boardPosition27, 11, this.chessServer));
        blackOccupations[2][0] = true;

        BoardPosition boardPosition28 = new BoardPosition(4, 0, true);


        blackPieces.put(13, new King(false, boardPosition28, 13, this.chessServer));
        blackOccupations[4][0] = true;


        BoardPosition boardPosition29 = new BoardPosition(3, 0, true);

        blackPieces.put(12, new Queen(false, boardPosition29, 12, this.chessServer));
        blackOccupations[3][0] = true;

        BoardPosition boardPosition30 = new BoardPosition(5, 0, true);

        blackPieces.put(14, new Bishop(false, boardPosition30, 14, this.chessServer));
        blackOccupations[5][0] = true;

        BoardPosition boardPosition31 = new BoardPosition(6, 0, true);

        blackPieces.put(15, new Knight(false, boardPosition31, 15, this.chessServer));
        blackOccupations[6][0] = true;

        BoardPosition boardPosition32 = new BoardPosition(7, 0, true);


        blackPieces.put(16, new Rook(false, boardPosition32, 16, this.chessServer));
        blackOccupations[7][0] = true;

    }
}
