package com.client;


import java.awt.*;
import java.awt.event.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import javax.swing.*;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    private ChessGameBackEnd chessGameBackEnd;
    private HashMap<JLabel, ChessPiece> chessPieceHashMap = new HashMap<>();
    private ArrayList<ChessTile> chessTiles = new ArrayList<>();


    public ChessGame(ChessGameBackEnd chessGameBackEnd) {
        this.chessGameBackEnd = chessGameBackEnd;
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

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;


            int xCoord = i ;

            JLabel piece;
            JPanel panel;
            ChessTile chessTile = new ChessTile(square, i % 8, (int) Math.floor(i/8));
            chessTiles.add(chessTile);


            if (i >= 8 && i <=15) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_pdt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }

            if (i >= 48 && i <=55) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_plt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }


            if (i == 2 || i == 5) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_bdt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 0 || i == 7) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_rdt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }

            if (i == 1 || i == 6) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_ndt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 3) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_qdt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 4) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_kdt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 58 || i == 61) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_blt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 63 || i == 56) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_rlt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }

            if (i == 57 || i == 62) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_nlt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 59) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_qlt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }
            if (i == 60) {
                ImageIcon imageIcon = new ImageIcon("/Users/douglashammarstam/Dropbox/ChessIprogg/src/res/pieces/Chess_klt60.png");
                Image scaleImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
                piece = new JLabel(new ImageIcon(scaleImage));
                panel = (JPanel) chessBoard.getComponent(i);
                panel.add(piece);
                chessPieceHashMap.put(piece, new ChessPiece(false, chessTile, i));

            }

            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.BLUE : Color.white);
            else
                square.setBackground(i % 2 == 0 ? Color.white : Color.BLUE);
        }


        //Add a few pieces to the board





    }

    public void mousePressed(MouseEvent e) {

        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);



        this.chessGameBackEnd.clickHandler("move, " + chessPieceHashMap.get(chessPiece). + " From\n "  +
                                                                                        chessPieceHashMap.get(chessPiece).getChessTile() + " to \n"
                                                                   + c );
    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {



        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel) {
            Container parent = c.getParent();
            parent.remove(0);
            parent.add(chessPiece);
        } else {
            Container parent = (Container) c;
            parent.add(chessPiece);
        }

        chessPiece.setVisible(true);



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



}
