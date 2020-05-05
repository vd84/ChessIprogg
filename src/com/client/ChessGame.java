package com.client;

import com.server.Piece;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    private ChessGameBackEnd chessGameBackEnd;
    private HashMap<JLabel, ChessPiece> chessPieceHashMap = new HashMap<>();
    private HashMap<JPanel, ChessTile> chessTiles = new HashMap<>();
    private int prevX;
    private int prevY;
    private int playerId = new Random().nextInt(5432254);
    private boolean isWhite;


    public ChessGame(ChessGameBackEnd chessGameBackEnd, boolean isWhite) throws IOException {
        this.isWhite = isWhite;
        this.chessGameBackEnd = chessGameBackEnd;
        this.chessGameBackEnd.setChessGame(this);
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);


        int incrementer = -2;
        if (isWhite) {
            for (int i = 0; i < 64; i++) {
                JPanel square = new JPanel(new BorderLayout());
                chessBoard.add(square);

                int row = (i / 8) % 2;

                JLabel piece;
                JPanel panel;
                ChessTile chessTile = new ChessTile(square, i % 8, (int) Math.floor(i / 8));
                System.out.println((int) Math.floor(i / 8));
                chessTiles.put(square, chessTile);


                if (i >= 8 && i <= 15) {
                    incrementer = incrementer +2;
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_pdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i - incrementer));

                }

                if (i >= 48 && i <= 55) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_plt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, i % 8 + 1));

                }


                if (i == 2 || i == 5) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_bdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 2)
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 14));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 11));


                }
                if (i == 0 || i == 7) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_rdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 0)
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 9));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 16));
                }

                if (i == 1 || i == 6) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_ndt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 1)
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 10));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 15));
                }
                if (i == 3) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_qdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 12));

                }
                if (i == 4) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_kdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 13));

                }
                if (i == 58 || i == 61) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_blt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 58)
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 11));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 14));
                }
                if (i == 63 || i == 56) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_rlt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 63)
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 16));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 9));
                }

                if (i == 57 || i == 62) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_nlt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 57)
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 10));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 15));
                }
                if (i == 59) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_qlt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 13));

                }
                if (i == 60) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_klt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 12));

                }

                if (row == 0)
                    square.setBackground(i % 2 == 0 ? Color.BLUE : Color.white);
                else
                    square.setBackground(i % 2 == 0 ? Color.white : Color.BLUE);
            }


        } else {
            int blackPawnIncrementer = -2;
            int whitePawnIncrementer = -2;

            for (int i = 0; i < 64; i++) {
                JPanel square = new JPanel(new BorderLayout());
                chessBoard.add(square);

                int row = (i / 8) % 2;

                JLabel piece;
                JPanel panel;
                ChessTile chessTile = new ChessTile(square, i % 8, (int) Math.floor(i / 8));
                System.out.println((int) Math.floor(i / 8));
                chessTiles.put(square, chessTile);


                if (i >= 8 && i <= 15) {
                    whitePawnIncrementer = whitePawnIncrementer  +2;
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_plt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, i-whitePawnIncrementer));

                }

                if (i >= 48 && i <= 55) {
                    blackPawnIncrementer = blackPawnIncrementer + 2;

                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_pdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i % 8 + 1));

                }


                if (i == 2 || i == 5) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_blt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 2)
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 11));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 14));


                }
                if (i == 0 || i == 7) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_rlt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 0)
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 9));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 16));
                }

                if (i == 1 || i == 6) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_nlt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 1)
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 10));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 15));
                }
                if (i == 3 +1) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_qlt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 12));

                }
                if (i == 4-1) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_klt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 13));

                }
                if (i == 58 || i == 61) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_bdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 58)
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 11));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 14));
                }
                if (i == 63 || i == 56) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_rdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 63)
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 9));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 16));
                }

                if (i == 57 || i == 62) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_ndt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    if (i == 57)
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 10));
                    else
                        chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 15));
                }
                if (i == 59) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_qdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(true, chessTile, 13));

                }
                if (i == 60) {
                    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Douglas\\Dropbox\\ChessIprogg2\\src\\res\\pieces\\Chess_kdt60.png");
                    Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                    piece = new JLabel(new ImageIcon(scaleImage));
                    panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, 12));

                }

                if (row == 0)
                    square.setBackground(i % 2 == 0 ? Color.BLUE : Color.white);
                else
                    square.setBackground(i % 2 == 0 ? Color.white : Color.BLUE);
            }


        }
    }

    public void mousePressed(MouseEvent e) {

        chessPiece = null;
        Component currentC = chessBoard.findComponentAt(e.getX(), e.getY());
        System.out.println(currentC);
        prevX = e.getX();
        prevY = e.getY();

        if (chessPieceHashMap.get((JLabel) currentC).isWhite() != this.isWhite){
            return;
        }

            if (currentC instanceof JPanel)
                return;

        Point parentLocation = currentC.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) currentC;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);


    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {
        boolean moveSuccessful = false;


        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Component desiredC = chessBoard.findComponentAt(e.getX(), e.getY());
        System.out.println(e.getX());
        System.out.println(e.getY());
        System.out.println("Testing Y: " + chessPieceHashMap.get(chessPiece).getChessTile().getyCoord());

        try {
            int movedPieceId = chessPieceHashMap.get(chessPiece).getId();
            int movedPieceXCoord = chessPieceHashMap.get(chessPiece).getChessTile().getxCoord();
            int movedPieceYCoord = chessPieceHashMap.get(chessPiece).getChessTile().getyCoord();
            ChessPiece capturedPiece = null;
            JLabel capturedChessJLabel = null;
            int capturedChessPieceId = -1;


            if (desiredC instanceof JLabel) {
                capturedChessJLabel = (JLabel) desiredC;
                capturedPiece = chessPieceHashMap.get(desiredC);
                capturedChessPieceId = capturedPiece.getId();

                desiredC = desiredC.getParent();
            }
            System.out.println("HÄR ÄR RUTANS X VÄRDE " + chessTiles.get(desiredC).getxCoord());
            System.out.println("HÄR ÄR RUTANS Y VÄRDE " + chessTiles.get(desiredC).getyCoord());

            int desiredXCoord = chessTiles.get(desiredC).getxCoord();
            int desiredYCoord = chessTiles.get(desiredC).getyCoord();

            //Check if piece is captured, and what piece in that case

            if(capturedPiece != null && capturedPiece.isWhite() == this.isWhite){
                capturedChessPieceId = -99;

            }

            moveSuccessful = this.chessGameBackEnd.clickHandler(playerId + "," + movedPieceId + "," +
                    + capturedChessPieceId + "," + movedPieceXCoord + "," + movedPieceYCoord
                    + "," + desiredXCoord + "," + desiredYCoord);


            if (capturedChessJLabel != null && moveSuccessful) {
                chessBoard.remove(capturedChessJLabel);
                JPanel jPanel = (JPanel) capturedChessJLabel.getParent();
                jPanel.remove(capturedChessJLabel);
                System.out.println("removing: " + capturedChessJLabel);
            }


        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("WAS MOVE SUCESSFUL?? " +moveSuccessful );

        if (!moveSuccessful) {
            System.out.println("ufdsauida");
            System.out.println("XTEST: " + chessPieceHashMap.get(chessPiece).getChessTile().getxCoord());
            System.out.println("YTEST: " + chessPieceHashMap.get(chessPiece).getChessTile().getyCoord());
            desiredC = chessBoard.findComponentAt(prevX, prevY);
        } else {
            System.out.println("move sucess");


        }



        if (desiredC instanceof JLabel) {
            Container parent = desiredC.getParent();
            parent.remove(0);
            parent.add(chessPiece);
        } else {
            Container parent = (Container) desiredC;
            parent.add(chessPiece);
        }

        chessPiece.setVisible(true);
        repaint();


    }

    @Override
    public synchronized void mouseClicked(MouseEvent e) {


    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public synchronized void adjustChessBoard(int playerId, int capturedId, int pieceId, boolean isWhite, int xCoord, int yCoord) {
        if (this.playerId == playerId) {
            return;
        }
        System.out.println("adjusting pieces");

        ChessPiece capturedPiece = null;
        JLabel capturedChessJLabel = null;
        ChessPiece chessPiece = null;
        JLabel chessPieceLabel = null;
        JPanel desiredChessTileJPanel = null;
        ChessTile desiredChessTile;
        for (Map.Entry<JLabel, ChessPiece> entry : chessPieceHashMap.entrySet()) {
            if (entry.getValue().getId() == pieceId && entry.getValue().isWhite() == !this.isWhite) {
                chessPiece = chessPieceHashMap.get(entry.getKey());
                chessPieceLabel = entry.getKey();
            }


            if (entry.getValue().getId() == capturedId && entry.getValue().isWhite() == this.isWhite) {


                capturedChessJLabel = entry.getKey();
                JPanel jPanel = (JPanel) capturedChessJLabel.getParent();
                jPanel.remove(capturedChessJLabel);
                System.out.println("removing: " + capturedChessJLabel);
            }

        }
        for (Map.Entry<JPanel, ChessTile> entry : chessTiles.entrySet()) {
            if (entry.getValue().getxCoord() == (xCoord - 7)*-1 && entry.getValue().getyCoord() == (yCoord - 7) * -1) {
                desiredChessTileJPanel = entry.getKey();
                desiredChessTile = entry.getValue();

            }
        }


        desiredChessTileJPanel.add(chessPieceLabel);

        repaint();


    }


}
