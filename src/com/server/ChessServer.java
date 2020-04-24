package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChessServer implements Runnable {

    private static final int PORT = 2000;
    private static final Object lock = new Object();
    private final Socket clientSocket1;
    private final Socket clientSocket2;


    public ChessServer(ArrayList<Socket> clientSockets) {
        this.clientSocket1 = clientSockets.get(0);
        this.clientSocket2 = clientSockets.get(1);

    }

    @Override
    public void run() {

        boolean isGameRunning = true;
        AtomicBoolean isPlayer1sTurn = new AtomicBoolean(true);

        Player player1 = new Player();
        Player player2 = new Player();


        HashMap<Integer, Piece> boardPositionWhitePiecesHashMap = new HashMap<>();
        HashMap<Integer, Piece> boardPositionBlackPiecesHashMap = new HashMap<>();


        // White Pieces
        // PAWNS
        BoardPosition boardPosition = new BoardPosition(0, 0, true);
        boardPositionWhitePiecesHashMap.put(1, new Pawn(true, boardPosition, 1));

        BoardPosition boardPosition2 = new BoardPosition(1, 0, true);
        boardPositionWhitePiecesHashMap.put(2, new Pawn(true, boardPosition2, 2));

        BoardPosition boardPosition3 = new BoardPosition(2, 0, true);
        boardPositionWhitePiecesHashMap.put(3, new Pawn(true, boardPosition3, 3));

        BoardPosition boardPosition4 = new BoardPosition(3, 0, true);
        boardPositionWhitePiecesHashMap.put(4, new Pawn(true, boardPosition4, 4));

        BoardPosition boardPosition5 = new BoardPosition(4, 0, true);
        boardPositionWhitePiecesHashMap.put(5, new Pawn(true, boardPosition5, 5));

        BoardPosition boardPosition6 = new BoardPosition(5, 0, true);
        boardPositionWhitePiecesHashMap.put(6, new Pawn(true, boardPosition6, 6));

        BoardPosition boardPosition7 = new BoardPosition(6, 0, true);
        boardPositionWhitePiecesHashMap.put(7, new Pawn(true, boardPosition7, 7));

        BoardPosition boardPosition8 = new BoardPosition(7, 0, true);
        boardPositionWhitePiecesHashMap.put(8, new Pawn(true, boardPosition8, 8));

        BoardPosition boardPosition9 = new BoardPosition(0, 0, true);
        boardPositionWhitePiecesHashMap.put(9, new Pawn(true, boardPosition9, 9));

        BoardPosition boardPosition10 = new BoardPosition(1, 1, true);
        boardPositionWhitePiecesHashMap.put(10, new Pawn(true, boardPosition10, 10));

        BoardPosition boardPosition11 = new BoardPosition(2, 1, true);
        boardPositionWhitePiecesHashMap.put(11, new Pawn(true, boardPosition11, 11));

        BoardPosition boardPosition12 = new BoardPosition(3, 1, true);
        boardPositionWhitePiecesHashMap.put(12, new Pawn(true, boardPosition12, 12));

        BoardPosition boardPosition13 = new BoardPosition(4, 1, true);
        boardPositionWhitePiecesHashMap.put(13, new Pawn(true, boardPosition13, 13));

        BoardPosition boardPosition14 = new BoardPosition(5, 1, true);
        boardPositionWhitePiecesHashMap.put(14, new Pawn(true, boardPosition14, 14));

        BoardPosition boardPosition15 = new BoardPosition(6, 1, true);
        boardPositionWhitePiecesHashMap.put(15, new Pawn(true, boardPosition15, 15));

        BoardPosition boardPosition16 = new BoardPosition(7, 1, true);
        boardPositionWhitePiecesHashMap.put(16, new Pawn(true, boardPosition16, 16));


        //Black Pieces
        BoardPosition boardPosition17 = new BoardPosition(0, 0, true);
        boardPositionBlackPiecesHashMap.put(1, new Pawn(false, boardPosition17, 1));

        BoardPosition boardPosition18 = new BoardPosition(1, 0, true);
        boardPositionBlackPiecesHashMap.put(2, new Pawn(false, boardPosition18, 2));

        BoardPosition boardPosition19 = new BoardPosition(2, 0, true);
        boardPositionBlackPiecesHashMap.put(3, new Pawn(false, boardPosition19, 3));

        BoardPosition boardPosition20 = new BoardPosition(3, 0, true);
        boardPositionBlackPiecesHashMap.put(4, new Pawn(false, boardPosition20, 4));

        BoardPosition boardPosition21 = new BoardPosition(4, 0, true);
        boardPositionBlackPiecesHashMap.put(5, new Pawn(false, boardPosition21, 5));

        BoardPosition boardPosition22 = new BoardPosition(5, 0, true);
        boardPositionBlackPiecesHashMap.put(6, new Pawn(false, boardPosition22, 6));

        BoardPosition boardPosition23 = new BoardPosition(6, 0, true);
        boardPositionBlackPiecesHashMap.put(7, new Pawn(false, boardPosition23, 7));

        BoardPosition boardPosition24 = new BoardPosition(7, 0, true);
        boardPositionBlackPiecesHashMap.put(8, new Pawn(false, boardPosition24, 8));

        BoardPosition boardPosition25 = new BoardPosition(0, 0, true);
        boardPositionBlackPiecesHashMap.put(9, new Pawn(false, boardPosition25, 9));

        BoardPosition boardPosition26 = new BoardPosition(1, 1, true);
        boardPositionBlackPiecesHashMap.put(10, new Pawn(false, boardPosition26, 10));

        BoardPosition boardPosition27 = new BoardPosition(2, 1, true);
        boardPositionBlackPiecesHashMap.put(11, new Pawn(false, boardPosition27, 11));

        BoardPosition boardPosition28 = new BoardPosition(3, 1, true);
        boardPositionBlackPiecesHashMap.put(12, new Pawn(false, boardPosition28, 12));

        BoardPosition boardPosition29 = new BoardPosition(4, 1, true);
        boardPositionBlackPiecesHashMap.put(13, new Pawn(false, boardPosition29, 13));

        BoardPosition boardPosition30 = new BoardPosition(5, 1, true);
        boardPositionBlackPiecesHashMap.put(14, new Pawn(false, boardPosition30, 14));

        BoardPosition boardPosition31 = new BoardPosition(6, 1, true);
        boardPositionBlackPiecesHashMap.put(15, new Pawn(false, boardPosition31, 15));

        BoardPosition boardPosition32 = new BoardPosition(7, 1, true);
        boardPositionBlackPiecesHashMap.put(16, new Pawn(false, boardPosition32, 16));


        Game game = new Game(player1, player2);

        //Listen to player 1
        new Thread(() -> {
            while (isGameRunning) {
                if (isPlayer1sTurn.get()) {
                    try {
                        BufferedReader player1Command = new BufferedReader(new InputStreamReader(this.clientSocket1.getInputStream()));
                        String commandString = player1Command.readLine();
                        String[] commandsSplit = commandString.split(",");
                        int xCommand = Integer.parseInt(commandsSplit[0]);
                        int yCommand = Integer.parseInt(commandsSplit[1]);
                        int pieceId = Integer.parseInt(commandsSplit[2]);
                        BoardPosition moveToPosition = new BoardPosition(xCommand, yCommand, true);
                        if (player1.move(boardPositionWhitePiecesHashMap.get(pieceId), moveToPosition)) {

                            isPlayer1sTurn.set(false);
                        } else {

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (isGameRunning) {
                if (!isPlayer1sTurn.get()) {
                    try {
                        BufferedReader player1Command = new BufferedReader(new InputStreamReader(this.clientSocket2.getInputStream()));
                        String commandString = player1Command.readLine();
                        String[] commandsSplit = commandString.split(",");
                        int xCommand = Integer.parseInt(commandsSplit[0]);
                        int yCommand = Integer.parseInt(commandsSplit[1]);
                        int pieceId = Integer.parseInt(commandsSplit[2]);
                        BoardPosition moveToPosition = new BoardPosition(xCommand, yCommand, true);
                        if (player1.move(boardPositionWhitePiecesHashMap.get(pieceId), moveToPosition)) {

                            isPlayer1sTurn.set(false);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public static void main(String[] args) {

        System.out.println("Server started.");
        ServerSocket serverSocket = null;

        try {
            int port = 2000;

            serverSocket = new ServerSocket(port);
            SocketAddress socketAddress = serverSocket.getLocalSocketAddress();
            System.out.println("Listening (" + socketAddress + ").");

            while (true) {
                ArrayList<Socket> sockets = new ArrayList<>();
                while (sockets.size() < 2) {
                    sockets.add(serverSocket.accept());
                    System.out.println("Waiting for both to connect");
                }
                System.out.println("Both connected");
                ChessServer server = new ChessServer(sockets);
                Thread thread = new Thread(server);
                thread.start();
                System.out.println();
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


