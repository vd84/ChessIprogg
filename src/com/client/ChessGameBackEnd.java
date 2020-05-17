package com.client;


import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.LinkedBlockingQueue;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ChessGameBackEnd extends Thread {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 2000;
    private final BufferedReader socketReader;
    private final PrintWriter socketWriter;
    private LinkedBlockingQueue<String> commands = new LinkedBlockingQueue<>();
    public LinkedBlockingQueue<String> moves = new LinkedBlockingQueue<>();
    private ChessGame chessGame;
    private static final Object lock = new Object();
    private boolean isWhite;
    private volatile int lastMoveWasSucessful;

    public ChessGameBackEnd(BufferedReader bufferedReader, PrintWriter printWriter) {
        this.socketReader = bufferedReader;
        this.socketWriter = printWriter;
        this.lastMoveWasSucessful = -1;
    }


    public void setLastMoveWasSucessful(int lastMoveWasSucessful) {
        synchronized (lock) {
            this.lastMoveWasSucessful = lastMoveWasSucessful;
        }
    }

    public synchronized int isLastMoveWasSucessful() {
        synchronized (lock) {
            return lastMoveWasSucessful;
        }
    }

    @Override
    public void run() {
        new Thread(() -> {

            while (true) {
                try {

                    String response = socketReader.readLine();
                    String[] responseFromServerSplit = response.split(",");

                    if (response.equals("white")) {
                        this.isWhite = true;
                    }
                    if (response.equals("black")) {
                        this.isWhite = false;
                    }

                    if (chessGame == null) {
                        try {
                            chessGame = new ChessGame(this, isWhite);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        chessGame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        chessGame.pack();
                        chessGame.setResizable(true);
                        chessGame.setLocationRelativeTo(null);
                        chessGame.setVisible(true);
                        return;

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


        new Thread(() -> {
            String responseFromServer = null;

            try {
                while ((responseFromServer = socketReader.readLine()) != null) {

                    String[] responseFromServerSplit = responseFromServer.split(",");
                    System.out.println("IS WHITE PIECE + " + responseFromServerSplit[2]);
                    System.out.println("AM I WHITE+ " + this.isWhite);


                    if (responseFromServerSplit[0].equals("move")) {
                        if (Boolean.parseBoolean(responseFromServerSplit[2]) == this.isWhite) {
                            this.setLastMoveWasSucessful(1);
                        }
                        chessGame.adjustChessBoard(Integer.parseInt(responseFromServerSplit[1]), Integer.parseInt(responseFromServerSplit[6]), Integer.parseInt(responseFromServerSplit[3]), Boolean.parseBoolean(responseFromServerSplit[2]), Integer.parseInt(responseFromServerSplit[4]), Integer.parseInt(responseFromServerSplit[5]));

                    } else {
                        if (Boolean.parseBoolean(responseFromServerSplit[2]) == this.isWhite) {
                            setLastMoveWasSucessful(0);
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
            }


        }).start();


        new Thread(() -> {
            while (true) {

                try {
                    String command = commands.take();
                    socketWriter.println(command);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String ipAdress = "127.0.0.1";
        int port = 2000;

        try {
            socket = new Socket(ipAdress, port);
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            SocketAddress localSocketAddress = socket.getLocalSocketAddress();

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Thread thread = new Thread(new ChessGameBackEnd(bufferedReader, printWriter));
            thread.start();


            System.out.println("Closing connection " + localSocketAddress + " (" + remoteSocketAddress + ").");
        } catch (ConnectException connectException) {
            System.out.println("No server available");
            connectException.printStackTrace();
        } catch (Exception var22) {
            System.out.println(var22);
        }

    }


    public synchronized boolean clickHandler(String command) throws InterruptedException, IOException {
        commands.add(command);
        return getResponseForMove();
    }

    public boolean getResponseForMove() throws InterruptedException, IOException {


        System.out.println("CHECKING IF LAST MOVE IS OK");
        System.out.println("THIS IS VALUE RIGHT NOW: " + isLastMoveWasSucessful());

        while (true) {
            if (isLastMoveWasSucessful() == -1) {
                System.out.println("WAITING FOR SERVER RESPONSE");
                Thread.sleep(50);
            } else {
                System.out.println("MOVE OK " + isLastMoveWasSucessful());
                break;
            }
        }
        int response = isLastMoveWasSucessful();
        setLastMoveWasSucessful(-1);

        return response == 1;


    }


    public ChessGame getChessGame() {
        return chessGame;
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
}
