package com.server;

import java.util.HashMap;

public class Board {


    private HashMap<BoardPosition, Piece> boardPositionPieceHashMap = new HashMap<>();


    public Board(HashMap<BoardPosition, Piece> boardPositionPieceHashMap) {
        this.boardPositionPieceHashMap = boardPositionPieceHashMap;
    }
}
