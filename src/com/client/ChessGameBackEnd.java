package com.client;


import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ChessGameBackEnd extends Thread {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 2000;
    private final BufferedReader socketReader;
    private final PrintWriter socketWriter;
    private LinkedBlockingQueue<String> commands = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Integer> responseFromServer = new LinkedBlockingQueue<>();


    public ChessGameBackEnd(BufferedReader bufferedReader, PrintWriter printWriter) {
        this.socketReader = bufferedReader;
        this.socketWriter = printWriter;
    }


    @Override
    public void run() {

        ChessGame chessGame = null;
        try {
            System.out.println("creating game");
            chessGame = new ChessGame(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        chessGame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        chessGame.pack();
        chessGame.setResizable(true);
        chessGame.setLocationRelativeTo(null);
        chessGame.setVisible(true);


        while (true) {

            try {
                String command = commands.take();
                System.out.println(command);
                socketWriter.println(command);
                System.out.println(socketReader);
                Thread.sleep(100);
                int response = Integer.parseInt(socketReader.readLine());
                System.out.println("RESPONSEdfsagdfasfdsafdsa");
                responseFromServer.add(response);
                Thread.sleep(100);
                System.out.println(commands);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }


        }



    }

    public static void main(String[] args) {
        System.out.println("Client started.");
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String ipAdress = "127.0.0.1";
        int port = 2000;

        try {
            socket = new Socket(ipAdress, port);
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            SocketAddress localSocketAddress = socket.getLocalSocketAddress();
            System.out.println("Connected to server " + remoteSocketAddress + " (" + localSocketAddress + ").");

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("socket " + socket);
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


    public synchronized boolean clickHandler(String command) throws InterruptedException {
        System.out.println("sending to " + socketWriter);
        commands.add(command);
        return getResponseForMove();
    }

    public synchronized boolean getResponseForMove() throws InterruptedException {
        System.out.println("getting response");

        System.out.println(responseFromServer);
        return responseFromServer.take() == 1;


    }


}
