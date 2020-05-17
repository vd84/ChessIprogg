package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ChessServer implements Runnable {

    private GameController gameController;
    private MoveController moveController;

    private LinkedBlockingQueue<String> commands = new LinkedBlockingQueue<>();
    private ArrayList<LinkedBlockingQueue<String>> commandQueues;
    private LinkedBlockingQueue<String> moves;
    private final Socket clientSocket;
    private volatile ConcurrentHashMap<Integer, Piece> boardPositionWhitePiecesHashMap;
    private volatile ConcurrentHashMap<Integer, Piece> boardPositionBlackPiecesHashMap;
    private volatile ConcurrentHashMap<BoardPosition, BoardPosition> boardPositions = new ConcurrentHashMap<>();
    private volatile boolean[][] blackOccupiedBoardPositions;
    private volatile boolean[][] whiteOccupiedBoardPositions;

    private boolean isWhite;
    private boolean kingIsInCheck;
    private boolean isMyTurn;
    private boolean[][] myOccupations;


    public ChessServer(Socket clientSocket, ArrayList<LinkedBlockingQueue<String>> commandQueues, LinkedBlockingQueue<String> myMoves, boolean isWhite, boolean isMyTurn,
                       ConcurrentHashMap<Integer, Piece> whiteHashMap, ConcurrentHashMap<Integer, Piece> blackHashMap) {
        this.commandQueues = commandQueues;
        this.moves = myMoves;
        this.clientSocket = clientSocket;
        this.isWhite = isWhite;
        this.whiteOccupiedBoardPositions = new boolean[8][8];
        this.blackOccupiedBoardPositions = new boolean[8][8];

        this.isMyTurn = isMyTurn;
        this.boardPositionWhitePiecesHashMap = whiteHashMap;
        this.boardPositionBlackPiecesHashMap = blackHashMap;
        this.gameController = new GameController(whiteOccupiedBoardPositions, blackOccupiedBoardPositions, isWhite, boardPositionWhitePiecesHashMap, boardPositionBlackPiecesHashMap, this);

        this.moveController = new MoveController(this.gameController);


    }

    @Override
    public void run() {
        PrintWriter socketWriter = null;
        BufferedReader socketReader = null;
        try {
            socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }


        if (commandQueues.size() < 2) {
            socketWriter.println("white");
        } else {
            socketWriter.println("black");
        }
        PrintWriter finalSocketWriter = socketWriter;
        new Thread(() -> {

            while (true) {
                try {
                    String move = moves.take();
                    String[] splitMove = move.split(",");
                    int movePieceId = Integer.parseInt(splitMove[3]);
                    int xCoord = Integer.parseInt(splitMove[4]);
                    int yCoord = Integer.parseInt(splitMove[5]);
                    moveController.opponentMove(xCoord, yCoord, movePieceId);
                    finalSocketWriter.println(move);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            String inputLine = null;
            while ((inputLine = socketReader.readLine()) != null) {
                String[] splitString = inputLine.split(",");

                int senderId = Integer.parseInt(splitString[0]);
                int pieceId = Integer.parseInt(splitString[1]);
                int capturedPieceId = Integer.parseInt(splitString[2]);
                int desiredXCoord = Integer.parseInt(splitString[5]);
                int desiredYCoord = Integer.parseInt(splitString[6]);
                Piece king;
                Piece opponentKing;

                BoardPosition desiredBoardPosition = new BoardPosition(desiredXCoord, desiredYCoord, false);
                Piece movedPiece;
                Piece capturedPiece;
                ConcurrentHashMap<Integer, Piece> oppositePieces;


                if (this.isWhite) {
                    movedPiece = boardPositionWhitePiecesHashMap.get(pieceId);
                    capturedPiece = boardPositionBlackPiecesHashMap.get(capturedPieceId);


                } else {
                    movedPiece = boardPositionBlackPiecesHashMap.get(pieceId);
                    capturedPiece = boardPositionWhitePiecesHashMap.get(capturedPieceId);


                }


                BoardPosition boardPositionBeforeMove = movedPiece.getBoardPosition();


                if (moveController.move(movedPiece, capturedPiece, desiredBoardPosition)) {
                    System.out.println("MOVE SUCCESS");
                    String move = "move," + senderId + "," + movedPiece.isWhite() + "," + movedPiece.getId() + "," + desiredXCoord + "," + desiredYCoord + "," + capturedPieceId;
                    for (LinkedBlockingQueue<String> commandQueue : commandQueues) {
                        commandQueue.add(move);
                    }






                } else {

                    String message = "failed," + senderId + "," + movedPiece.isWhite() + "," + movedPiece.getId() + "," + movedPiece.getBoardPosition().getxPos() + "," + movedPiece.getBoardPosition().getyPos() + "," + capturedPieceId;
                    for (LinkedBlockingQueue<String> commandQueue : commandQueues) {
                        commandQueue.add(message);
                    }
                    movedPiece.setBoardPosition(boardPositionBeforeMove);


                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {

        ServerSocket serverSocket = null;


        try {
            int port = 2000;
            serverSocket = new ServerSocket(port);
            SocketAddress socketAddress = serverSocket.getLocalSocketAddress();
            Socket clientSocket;


            ArrayList<LinkedBlockingQueue<String>> moveQueues = new ArrayList<>();
            ConcurrentHashMap<Integer, Piece> whiteHashMap = new ConcurrentHashMap<>();
            ConcurrentHashMap<Integer, Piece> blackHashMap = new ConcurrentHashMap<>();

            boolean isWhite;
            boolean isMyTurn;

            while (true) {

                if (moveQueues.size() >= 2) {
                    moveQueues = new ArrayList<LinkedBlockingQueue<String>>();
                    whiteHashMap = new ConcurrentHashMap<>();
                    blackHashMap = new ConcurrentHashMap<>();
                }
                isWhite = moveQueues.size() == 0;
                isMyTurn = moveQueues.size() == 0;
                clientSocket = serverSocket.accept();
                LinkedBlockingQueue<String> moves = new LinkedBlockingQueue<>();
                moveQueues.add(moves);

                ChessServer server = new ChessServer(clientSocket, moveQueues, moves, isWhite, isMyTurn, whiteHashMap, blackHashMap);
                Thread thread = new Thread(server);
                thread.start();


            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e) {


                System.out.println(e);
            }

        }

    }




}





