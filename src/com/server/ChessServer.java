package com.server;

import com.client.ChessGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChessServer implements Runnable {

    private static final int PORT = 2000;
    private static final Object lock = new Object();
    private LinkedBlockingQueue<String> commands = new LinkedBlockingQueue<>();
    private final Socket clientSocket;



    public ChessServer(Socket clientSocket) {


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

        PrintWriter finalSocketWriter = socketWriter;

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

        try {
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                String inputLine = socketReader.readLine();
                System.out.println(inputLine);
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


            while (true) {
                clientSocket = serverSocket.accept();
                ChessServer server = new ChessServer(clientSocket);
                Thread thread = new Thread(server);
                thread.start();
                System.out.println();

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

    public LinkedBlockingQueue<String> getCommands() {
        return commands;
    }
}


