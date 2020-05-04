package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChessServer implements Runnable {
    private LinkedBlockingQueue<String> commands = new LinkedBlockingQueue<>();
    private ArrayList<LinkedBlockingQueue<String>> blockingQueues;
    private LinkedBlockingQueue<String> moves;
    private final Socket clientSocket;
    HashMap<Integer, Piece> boardPositionWhitePiecesHashMap = new HashMap<>();
    HashMap<Integer, Piece> boardPositionBlackPiecesHashMap = new HashMap<>();



    public ChessServer(Socket clientSocket, ArrayList<LinkedBlockingQueue<String>> blockingQueues, LinkedBlockingQueue<String> myMoves) {
        this.blockingQueues = blockingQueues;
        this.moves = myMoves;

        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        SocketAddress remoteSocketAddress = clientSocket.getRemoteSocketAddress();
        SocketAddress localSocketAddress = clientSocket.getLocalSocketAddress();
        PrintWriter socketWriter = null;

        BufferedReader socketReader = null;

        boolean isGameRunning = true;
        AtomicBoolean isPlayer1sTurn = new AtomicBoolean(true);

        try {
            socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Player player1 = new Player();
        Player player2 = new Player();


        // White Pieces
        // PAWNS
        BoardPosition boardPosition = new BoardPosition(0, 6, true);
        boardPositionWhitePiecesHashMap.put(1, new Pawn(true, boardPosition, 1));

        BoardPosition boardPosition2 = new BoardPosition(1, 6, true);
        boardPositionWhitePiecesHashMap.put(2, new Pawn(true, boardPosition2, 2));

        BoardPosition boardPosition3 = new BoardPosition(2, 6, true);
        boardPositionWhitePiecesHashMap.put(3, new Pawn(true, boardPosition3, 3));

        BoardPosition boardPosition4 = new BoardPosition(3, 6, true);
        boardPositionWhitePiecesHashMap.put(4, new Pawn(true, boardPosition4, 4));

        BoardPosition boardPosition5 = new BoardPosition(4, 6, true);
        boardPositionWhitePiecesHashMap.put(5, new Pawn(true, boardPosition5, 5));

        BoardPosition boardPosition6 = new BoardPosition(5, 6, true);
        boardPositionWhitePiecesHashMap.put(6, new Pawn(true, boardPosition6, 6));

        BoardPosition boardPosition7 = new BoardPosition(6, 6, true);
        boardPositionWhitePiecesHashMap.put(7, new Pawn(true, boardPosition7, 7));

        BoardPosition boardPosition8 = new BoardPosition(7, 6, true);
        boardPositionWhitePiecesHashMap.put(8, new Pawn(true, boardPosition8, 8));

        BoardPosition boardPosition9 = new BoardPosition(0, 7, true);
        boardPositionWhitePiecesHashMap.put(9, new Rook(true, boardPosition9, 9));

        BoardPosition boardPosition10 = new BoardPosition(1, 7, true);
        boardPositionWhitePiecesHashMap.put(10, new Knight(true, boardPosition10, 10));

        BoardPosition boardPosition11 = new BoardPosition(2, 7, true);
        boardPositionWhitePiecesHashMap.put(11, new Bishop(true, boardPosition11, 11));

        BoardPosition boardPosition12 = new BoardPosition(3, 7, true);
        boardPositionWhitePiecesHashMap.put(12, new Queen(true, boardPosition12, 12));

        BoardPosition boardPosition13 = new BoardPosition(4, 7, true);
        boardPositionWhitePiecesHashMap.put(13, new King(true, boardPosition13, 13));

        BoardPosition boardPosition14 = new BoardPosition(5, 7, true);
        boardPositionWhitePiecesHashMap.put(14, new Bishop(true, boardPosition14, 14));

        BoardPosition boardPosition15 = new BoardPosition(6, 7, true);
        boardPositionWhitePiecesHashMap.put(15, new Knight(true, boardPosition15, 15));

        BoardPosition boardPosition16 = new BoardPosition(7, 7, true);
        boardPositionWhitePiecesHashMap.put(16, new Rook(true, boardPosition16, 16));


        //Black Pieces
        BoardPosition boardPosition17 = new BoardPosition(0, 1, true);
        boardPositionBlackPiecesHashMap.put(1, new Pawn(false, boardPosition17, 1));

        BoardPosition boardPosition18 = new BoardPosition(1, 1, true);
        boardPositionBlackPiecesHashMap.put(2, new Pawn(false, boardPosition18, 2));

        BoardPosition boardPosition19 = new BoardPosition(2, 1, true);
        boardPositionBlackPiecesHashMap.put(3, new Pawn(false, boardPosition19, 3));

        BoardPosition boardPosition20 = new BoardPosition(3, 1, true);
        boardPositionBlackPiecesHashMap.put(4, new Pawn(false, boardPosition20, 4));

        BoardPosition boardPosition21 = new BoardPosition(4, 1, true);
        boardPositionBlackPiecesHashMap.put(5, new Pawn(false, boardPosition21, 5));

        BoardPosition boardPosition22 = new BoardPosition(5, 1, true);
        boardPositionBlackPiecesHashMap.put(6, new Pawn(false, boardPosition22, 6));

        BoardPosition boardPosition23 = new BoardPosition(6, 1, true);
        boardPositionBlackPiecesHashMap.put(7, new Pawn(false, boardPosition23, 7));

        BoardPosition boardPosition24 = new BoardPosition(7, 1, true);
        boardPositionBlackPiecesHashMap.put(8, new Pawn(false, boardPosition24, 8));

        BoardPosition boardPosition25 = new BoardPosition(0, 0, true);
        boardPositionBlackPiecesHashMap.put(9, new Rook(false, boardPosition25, 9));

        BoardPosition boardPosition26 = new BoardPosition(1, 1, true);
        boardPositionBlackPiecesHashMap.put(10, new Knight(false, boardPosition26, 10));

        BoardPosition boardPosition27 = new BoardPosition(2, 1, true);
        boardPositionBlackPiecesHashMap.put(11, new Bishop(false, boardPosition27, 11));

        BoardPosition boardPosition28 = new BoardPosition(3, 1, true);
        boardPositionBlackPiecesHashMap.put(12, new Queen(false, boardPosition28, 12));

        BoardPosition boardPosition29 = new BoardPosition(4, 1, true);
        boardPositionBlackPiecesHashMap.put(13, new King(false, boardPosition29, 13));

        BoardPosition boardPosition30 = new BoardPosition(5, 1, true);
        boardPositionBlackPiecesHashMap.put(14, new Bishop(false, boardPosition30, 14));

        BoardPosition boardPosition31 = new BoardPosition(6, 1, true);
        boardPositionBlackPiecesHashMap.put(15, new Knight(false, boardPosition31, 15));

        BoardPosition boardPosition32 = new BoardPosition(7, 1, true);
        boardPositionBlackPiecesHashMap.put(16, new Rook(false, boardPosition32, 16));


        Game game = new Game(player1, player2);

        try {
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }


        if (blockingQueues.size() < 2) {
            socketWriter.println("white");
        } else {
            socketWriter.println("black");
        }
        PrintWriter finalSocketWriter = socketWriter;
        new Thread(() -> {

            while (true) {
                try {
                    System.out.println(this);
                    System.out.println("sending moves");
                    String move = moves.take();
                    finalSocketWriter.println(move);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            while (true) {
                String inputLine = socketReader.readLine();
                System.out.println(inputLine);
                String[] splitString = inputLine.split(",");

                boolean moveSuccessFul;
                int senderId = Integer.parseInt(splitString[0]);
                System.out.println("senderId" + senderId);
                int pieceId = Integer.parseInt(splitString[1]);
                int capturedPieceId = Integer.parseInt(splitString[2]);
                int desiredXCoord = Integer.parseInt(splitString[5]);
                int desiredYCoord = Integer.parseInt(splitString[6]);
                System.out.println("testing y " + desiredYCoord);

                BoardPosition desiredBoardPosition = new BoardPosition(desiredXCoord, desiredYCoord, false);
                Piece piece = boardPositionWhitePiecesHashMap.get(pieceId);
                Piece capturedPiece = boardPositionBlackPiecesHashMap.get(capturedPieceId);
                System.out.println(piece.isWhite());
                if (capturedPiece != null) {
                    moveSuccessFul = piece.capture(capturedPiece, desiredBoardPosition);
                    if (moveSuccessFul) {
                        boardPositionBlackPiecesHashMap.remove(capturedPieceId);
                    }


                } else {
                    moveSuccessFul = piece.move(desiredBoardPosition);

                }
                System.out.println("sending response");

                if (moveSuccessFul) {
                    for (LinkedBlockingQueue<String> queue : blockingQueues){
                        queue.add("move," + senderId + "," + piece.isWhite() + "," + piece.getId() + "," + desiredXCoord + "," + desiredYCoord + "," + capturedPieceId);
                    }
                    piece.setBoardPosition(desiredBoardPosition);
                    System.out.println("sucessfull");

                } else {
                    for (LinkedBlockingQueue<String> queue : blockingQueues){
                        queue.add("failed," + senderId + "," + piece.isWhite() + "," + piece.getId() + "," + desiredXCoord + "," + desiredYCoord + "," + capturedPieceId);
                    }
                    System.out.println("failed move");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        System.out.println("Server started.");
        ServerSocket serverSocket = null;


        try {
            int port = 2000;
            serverSocket = new ServerSocket(port);
            SocketAddress socketAddress = serverSocket.getLocalSocketAddress();
            System.out.println("Listening (" + socketAddress + ").");
            Socket clientSocket;


            ArrayList<LinkedBlockingQueue<String>> moveQueues = new ArrayList<>();

            while (true) {
                if(moveQueues.size() >=2) {
                    moveQueues = new ArrayList<LinkedBlockingQueue<String>>();
                }
                    clientSocket = serverSocket.accept();
                    LinkedBlockingQueue<String> moves = new LinkedBlockingQueue<>();
                    moveQueues.add(moves);

                    System.out.println("clientsockewte " + clientSocket);
                    ChessServer server = new ChessServer(clientSocket, moveQueues, moves);
                    Thread thread = new Thread(server);
                    thread.start();



            }
        } catch (Exception e) {
            System.out.println("null");
            System.out.println(e);
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e) {
                System.out.println("null");

                System.out.println(e);
            }

        }

    }


}


