/*
 * GameCanvas.java
 * 
 * Created on Apr 2, 2008, 2:27:36 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioe.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import com.ioe.constants.*;
import java.awt.Toolkit;
import java.awt.Point;
import com.ioe.core.GameEngine;
import com.ioe.core.Coordinate;
import com.ioe.core.MouseHandler;
import com.ioe.core.Cell;
import org.apache.log4j.Logger;

/**
 *
 * @author lakesh
 */
public class GameCanvas extends Canvas implements Runnable {
    private GameEngine reversi;
    private Graphics g;
    private Logger logger;
    private Main main;
    private Thread gameThread;
    private int turn;
    private boolean movemade = false;
    private Coordinate[][] boardSquareCoordinates;
    private Image white;
    private Image black;
    private Toolkit toolkit;
    

    public GameCanvas(GameEngine reversi, Main main) {
        boardSquareCoordinates = new Coordinate[8][8];
        toolkit = Toolkit.getDefaultToolkit();
        ClassLoader cl = this.getClass().getClassLoader();
        white = toolkit.getImage(cl.getResource("resources/white.png"));
        black = toolkit.getImage(cl.getResource("resources/black.png"));
        this.setSize(CanvasSize.WIDTH, CanvasSize.HEIGHT);
        this.setVisible(true);
        setBackground(Color.WHITE);
        this.reversi = reversi;
        this.main = main;
        gameThread = new Thread(this);
        gameThread.setName("Game thread");
        logger = Logger.getLogger("com.ioe.ui.GameCanvas");
        turn = Coin.WHITE;        
        main.setMoveLabel("Your turn");
        main.setScore(reversi.countWhite(), reversi.countBlack());
        this.addMouseListener(new MouseHandler(this));
        gameThread.start();
    }

    public boolean isMovePassAllowed() {
        return reversi.ispassallowed();
    }
 
    
    public void reset() {
        reversi.resetBoard();
        turn = Coin.WHITE;
        repaint();
    }

    public void flipCells(int x, int y, int player) {
        int i, j;
        i = x;
        j = y;
        if (reversi.board[i][j + 1] != player) {

            j++;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                j++;
            }
            if (reversi.board[i][j] == player) {
                while (j != y) {
                    j--;
                    reversi.board[i][j] = player;                    
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }

                }
            }
        }
        i = x;
        j = y;
        if (reversi.board[i][j - 1] != player) {
            j--;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                j--;
            }

            if (reversi.board[i][j] == player) {
                while (j != y) {
                    j++;
                    reversi.board[i][j] = player;
                    //logger.info("j--");
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }
        }
        
        i = x;
        j = y;
        if (reversi.board[i + 1][j - 1] != player) {

            i++;
            j--;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                i++;
                j--;
            }
            if (reversi.board[i][j] == player) {
                while (i != x && j != y) {
                    j++;
                    i--;
                    reversi.board[i][j] = player;                    
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }
        }
        i = x;
        j = y;
        if (reversi.board[i - 1][j - 1] != player) {

            i--;
            j--;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                i--;
                j--;
            }
            if (reversi.board[i][j] == player) {
                while (i != x && j != y) {
                    j++;
                    i++;
                    reversi.board[i][j] = player;
                    //logger.info("j-1 & i-1");
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }


        }
        i = x;
        j = y;
        if (reversi.board[i + 1][j + 1] != player) {

            i++;
            j++;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                i++;
                j++;
            }
            if (reversi.board[i][j] == player) {
                while (i != x && j != y) {
                    j--;
                    i--;
                    reversi.board[i][j] = player;
                    //logger.info("j+1 & i+1");
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }

        }
        i = x;
        j = y;
        if (reversi.board[i - 1][j + 1] != player) {

            i--;
            j++;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                i--;
                j++;
            }
            if (reversi.board[i][j] == player) {
                while (i != x && j != y) {
                    j--;
                    i++;
                    reversi.board[i][j] = player;
                    //logger.info("j+1 & i-1");
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }
        }
        i = x;
        j = y;
        if (reversi.board[i - 1][j] != player) {

            i--;
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                i--;
            }
            if (reversi.board[i][j] == player) {
                while (i != x) {
                    i++;
                    reversi.board[i][j] = player;
                    //logger.info("i--");
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }
        }

        i = x;
        j = y;
        if (reversi.board[i + 1][j] != player) {

            i++;
            if (x == 3 && y == 6) {
                //logger.info("I am testing again");
            }
            while (reversi.board[i][j] != -1 && reversi.board[i][j] != player) {
                if (reversi.board[i][j] == 0) {
                    break;
                }
                i++;
            }
            if (reversi.board[i][j] == player) {
                //logger.info("Testing");
                while (i != x) {
                    i--;
                    reversi.board[i][j] = player;
                    //logger.info("i++");
                    if (player == Coin.WHITE) {
                        drawCircle((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));

                    } else {
                        drawCross((Graphics2D) this.getGraphics(), new Cell(i - 1, j - 1));
                    }
                }
            }
        }
    }

    public void setmovemade() {
        movemade = true;
    }

    public void MouseResponse(int x, int y) {
        if (turn == Coin.WHITE) {
            Cell position = findposition(x, y);
            if (position != null) {
                //logger.info(position.getx() + " " + position.gety());
                if (reversi.checkmovevalidity(reversi.board,position.getx() + 1, position.gety() + 1, Coin.WHITE) == true) {            
                    position.setx(position.getx() + 1);
                    position.sety(position.gety() + 1);
                    reversi.board[position.getx()][position.gety()] = Coin.WHITE;
                    flipCells(position.getx(), position.gety(), Coin.WHITE);
                    reversi.increaseNoOfMoves();      
                    logger.info("Setting move made to true");                  
                    movemade = true;                    
                    logger.info("The movemade value is " + movemade);
                } else {
                    main.setMoveLabel("Invalid move");
                    logger.info("Invalid move from the user");
                }
            }
        }

    }

    private Cell findposition(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkmatch(boardSquareCoordinates[i][j], x, y) == true) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }

    private boolean checkmatch(Coordinate position, int x, int y) {
        if ((x > position.getPoint(0).x && y > position.getPoint(0).y) && (x < position.getPoint(2).x && y < position.getPoint(2).y)) {
            return true;
        } else {
            return false;
        }

    }

    public void run() {
        initializeBoard();
        while (true) {
            if (reversi.checkDraw() == true) {
                if(reversi.getWinner() == Coin.BLACK) {
                    main.setMoveLabel("Game Ended. The winner is BLACK");
                } else if(reversi.getWinner() == Coin.WHITE) {
                    main.setMoveLabel("Game Ended. The winner is WHITE");
                } else {
                    main.setMoveLabel("Game Ended. Draw ");
                }

            } else {
                if (turn == Coin.WHITE) {
                    logger.debug("Player's turn");
                    while (movemade == false) {
                      logger.debug("Movemade is false");
                    }
                    reversi.display();                   
                    main.setScore(reversi.countWhite(), reversi.countBlack());                   
                    turn = Coin.BLACK;
                    main.setMoveLabel("I'm thinking");
                    movemade = false;


                } else if (turn == Coin.BLACK) {
                    logger.info("AI's turn");
                    Cell move = reversi.computerMove();
                    if(move == null) {
                        main.setMoveLabel("Your turn");
                        turn = Coin.WHITE;
                    } else {
                        reversi.board[move.getx()][move.gety()] = Coin.BLACK;           
                        flipCells(move.getx(), move.gety(), Coin.BLACK);
                        main.setScore(reversi.countWhite(), reversi.countBlack());
                        main.setMoveLabel("Your turn");
                        reversi.increaseNoOfMoves();
                        turn = Coin.WHITE;
                        logger.info("The following move is computer's move");
                        reversi.display();
                    }
                }
           
            }

        }
    }

    public void drawCircle(Graphics2D g, Cell c) {
        

        //to be fixed
        /*
        For the first time when the image is not loaded completely
        no white is displayed on user's click so using this loop
        until the image is loaded and displayed correctly;
         */
        int xOffset = (int) (((CanvasSize.WIDTH) / 8 - Coin.DIMENSION) / 2);
        int yOffset = (int) (((CanvasSize.HEIGHT) / 8 - Coin.DIMENSION) / 2);
        Point a = boardSquareCoordinates[c.getx()][c.gety()].getPoint(0);
        while (g.drawImage(white, a.x + xOffset, a.y + yOffset, null) == false) {
        }
    }

    public void drawCross(Graphics2D g, Cell c) {
        int xOffset = (int) (((CanvasSize.WIDTH) / 8 - Coin.DIMENSION) / 2);
        int yOffset = (int) (((CanvasSize.HEIGHT) / 8 - Coin.DIMENSION) / 2);
        Point a = boardSquareCoordinates[c.getx()][c.gety()].getPoint(0);
        while (g.drawImage(black, a.x + xOffset, a.y + yOffset, null) == false) {
        }

    }

    public void initializeBoard() {       
        int horizontal_incr = CanvasSize.WIDTH / 8;
        int vertical_incr = CanvasSize.HEIGHT / 8;
        int horizontal_start = 0;
        int vertical_start = 0;
        int i, j = 0;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {                
                Point a = new Point(horizontal_start, vertical_start);
                Point b = new Point(horizontal_start + horizontal_incr, vertical_start);
                Point c = new Point(horizontal_start + horizontal_incr, vertical_start + vertical_incr);
                Point d = new Point(horizontal_start, vertical_start + vertical_incr);
                boardSquareCoordinates[i][j] = new Coordinate(a, b, c, d);
                horizontal_start += horizontal_incr;
            }
            horizontal_start = 0;
            vertical_start += vertical_incr;

        }

    }

    
    @Override
    public void paint(Graphics g) {
        logger.debug("Painting the canvas");
        g.setColor(new Color(4, 118, 223));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(200, 200, 200));
        int i, j;
        for (i = 0; i < 8; i++) {
            g.drawLine(boardSquareCoordinates[0][i].getPoint(1).x, boardSquareCoordinates[0][i].getPoint(1).y, boardSquareCoordinates[7][i].getPoint(2).x, boardSquareCoordinates[7][i].getPoint(2).y);
        }
        for (j = 0; j < 8; j++) {
            g.drawLine(boardSquareCoordinates[j][0].getPoint(3).x, boardSquareCoordinates[j][0].getPoint(3).y, boardSquareCoordinates[j][7].getPoint(2).x, boardSquareCoordinates[j][7].getPoint(2).y);
        }
        for (i = 0; i < Engine.WIDTH; i++) {
            for (j = 0; j < Engine.HEIGHT; j++) {
                if (reversi.board[i][j] == Coin.BLACK) {
                    drawCross((Graphics2D) g, new Cell(i - 1, j - 1));
                } else if (reversi.board[i][j] == Coin.WHITE) {
                    drawCircle((Graphics2D) g, new Cell(i - 1, j - 1));
                }
            }
        }


    }

 
}


