/* Jonathan Chin
 * Copyright (C) 2015 jonc
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
   10/23/15
 */
package mineclone;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import static mineclone.mineclone.displayBoard;
import static mineclone.mineclone.fillAdjacentMines;
import static mineclone.mineclone.largeBoard;
import static mineclone.mineclone.largeFlag;
import static mineclone.mineclone.mediumBoard;
import static mineclone.mineclone.mediumFlag;
import static mineclone.mineclone.shuffleMines;
import static mineclone.mineclone.smallBoard;
import static mineclone.mineclone.smallFlag;

public class Game extends JFrame{
    
    private static JPanel cards;
    private  static JPanel cardEasy;
    private static JPanel cardMedium;
    private static JPanel cardHard;
    //keeps track of how many flags the player placed down
    public static int flagTotal = 0;
    //once flagTotal reaches the corresponding number of flags for the level game is over
    private static final int easyTotal=10;
    private static final int mediumTotal=40;
    private static final int hardTotal=99;
    
    private String level = "easy";

    public Game(int rows, int cols, int rGap, int cGap, String thisLevel){
       
        level=thisLevel;
        //making all three layouts for the three different levels on the JPanel
        
        //making the menu
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        
        menuBar = new JMenuBar();
        menu = new JMenu("New Game");
        //when user presses Alt-N this will open up this menu
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Choose a level and start a new game.");
        menuBar.add(menu);
        
        //adding easy Panel to cardLayout
        menuItem = new JMenuItem("Easy", KeyEvent.VK_E); 
        cardEasy = new JPanel(new GridLayout(9, 9, 1, 1));
        cardEasy.add(menuItem);
        menu.add(menuItem);
        
        menuItem.addActionListener(new java.awt.event.ActionListener() {
  
            @Override
            public void actionPerformed(ActionEvent e) {    
                //configuring backend board
                shuffleMines(smallBoard, smallFlag);
                fillAdjacentMines(smallBoard);
                displayBoard(smallBoard);
                //configuring front end and fetching panel and displaying it
                cardEasy.removeAll();
                updateBoard(9,9, 1, 1, cardEasy, 70, 70, 20, smallFlag, smallBoard, easyTotal);
                
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "easy");
                pack();
            }
        });
       
        //adding medium Panel to cardLayout
        menuItem = new JMenuItem("Medium", KeyEvent.VK_M);
        cardMedium = new JPanel(new GridLayout(16, 16, 1, 1));
        cardMedium.add(menuItem);
        menu.add(menuItem);
        
         menuItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleMines(mediumBoard, mediumFlag);
                fillAdjacentMines(mediumBoard);
                displayBoard(mediumBoard);
                cardMedium.removeAll();
                updateBoard(16,16, 1, 1, cardMedium, 48, 48, 18, mediumFlag, mediumBoard, mediumTotal);
               
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "medium");
                pack();
 
            }
        });
        
        //adding hard Panel to cardLayout
        menuItem = new JMenuItem("Hard", KeyEvent.VK_H);
        cardHard = new JPanel(new GridLayout(16, 30, 1, 1));
        cardHard.add(menuItem);
        menu.add(menuItem);
        
        menuItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleMines(largeBoard, largeFlag);
                fillAdjacentMines(largeBoard);
                displayBoard(largeBoard);
                cardHard.removeAll();
 
                updateBoard(16,30, 1, 1, cardHard, 45, 45, 18, largeFlag, largeBoard, hardTotal);
             
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "hard");
                pack();
            }
        });
        
        //creating Panel to hold all the panel cards
        cards = new JPanel(new CardLayout());
        cards.add(cardEasy, "easy");
        cards.add(cardMedium, "medium");
        cards.add(cardHard, "hard");

        //this function updates the front end board for the default medium game
        updateBoard(16,16, 1, 1, cardMedium, 48, 48, 18, mediumFlag, mediumBoard, mediumTotal);

        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, "medium");
        add(cards);
        pack();
        //sets location of the window
        setLocation(160, 10);
        setTitle("mineclone");
        setJMenuBar(menuBar);
        setVisible(true);
        //allows to properly exit when player hits the x in the top right hand corner
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
    }
    //updates board when player selects different level in the menubar
    static void updateBoard(int rows, int cols, int rGap, int cGap, JPanel panel, int xSize, int ySize, int textSize, int flag[][], int board[][], int total){
        
        JToggleButton [][]buttons = new JToggleButton[rows][cols];
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                
                buttons[i][j]=new JToggleButton();
               
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, textSize));
                //saving index in the button so we can call it later to interact with the backend board
                buttons[i][j].putClientProperty("firstIndex", i);
                buttons[i][j].putClientProperty("secondIndex", j);
                buttons[i][j].setPreferredSize(new Dimension(xSize, ySize));
                
                //places flag on the board with right click and right click to remove flag
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        JToggleButton button = (JToggleButton) mouseEvent.getSource();

                        //keeps button pressed when it is already pressed
                   
                        //accessing the saved indexes to interact with the backend board
                        int row = (int) button.getClientProperty( "firstIndex" );
                        int col = (int) button.getClientProperty( "secondIndex" );

                        if ( mouseEvent.getButton() == 3) {
                           if(flag[row][col]==0 && button.isSelected()==false){
                                URL url = Game.class.getResource("flag.png");
                                ImageIcon img = new ImageIcon(url);
                                button.setIcon(img);
                                flag[row][col]=1;
                                flagTotal++;
                           }
                           else{
                               button.setIcon(null);
                               flag[row][col]=0;
                           }
                           if(flagTotal==total){
                               checkIfWon(flag, board, buttons);
                           }
                       }
                   }
               });
                
                buttons[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JToggleButton button = (JToggleButton) e.getSource();
                        
                        //keeps button pressed when it is already pressed
                        button.setSelected(true);
                        //accessing the saved indexes to interact with the backend board
                        int row = (int) button.getClientProperty( "firstIndex" );
                        int col = (int) button.getClientProperty( "secondIndex" );
                        //creating new colors for the numbers on the button
                        Color darkGreen = new Color(0,153,0);
                        Color darkPurple = new Color(153, 0, 153);
                                  
                        if(board[row][col]==0){
                            floodFill(row, col, buttons, board);
                        }
                        //this will be replaced with a bomb icon
                        else if(board[row][col]==9){
                            URL url = Game.class.getResource("mine.png");
                            ImageIcon img = new ImageIcon(url);
                            button.setIcon(img);

                            button.setContentAreaFilled(false);
                            button.setOpaque(true);
                            button.setBackground(Color.RED);
                            //ends the game by showing the player all the mines left
                            //player score board is frozen and timer will stop
                            gameOver(buttons, board);
                        }
                        else if(board[row][col]==1){
                            button.setForeground(Color.BLUE);
                        }
                        else if(board[row][col]==2){
                            button.setForeground(darkGreen);
                        }

                        else if(board[row][col]==3){
                            button.setForeground(Color.RED);
                        }
                        else if(board[row][col]==4){
                            button.setForeground(darkPurple);
                        }

                        //trick to convert int to string
                        if(board[row][col]!=9 && board[row][col]!=0){
                            button.setText("" + board[row][col]);
                        }
    
                    }
                });
                
                 panel.add(buttons[i][j]);
            }
        }
       
        panel.revalidate();
        panel.repaint(); 
    }
    
    //when a player clicks on a mine then all the mines are displayed on the board
    //the timer will stop and the player score will be shown
    static void gameOver(JToggleButton buttons[][], int board[][]){
        
        int row = board.length;
        int col=board[0].length;
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(board[i][j]==9){
                    URL url = Game.class.getResource("mine.png");
                    ImageIcon img = new ImageIcon(url);
                    buttons[i][j].setIcon(img);
                }
            }
        }
 
    }
    
    //non recursive floodfill that uses a queue for optimization reasons
    static void floodFill(int i, int j, JToggleButton buttons[][], int board[][]){
        Queue<JToggleButton> q = new LinkedList();
        q.add(buttons[i][j]);
        Color darkGreen = new Color(0,153,0);
        Color darkPurple = new Color(153, 0, 153);
        
        while(!q.isEmpty()){
            JToggleButton button =  q.remove();
            //fills the tile in by pressing it down
            button.setSelected(true);
            
            //accessing the saved indexes to interact with the backend board
            int row = (int) button.getClientProperty( "firstIndex" );
            int col = (int) button.getClientProperty( "secondIndex" );
            
            //if the square isn't a blank or bomb then display the number
            if(board[row][col]==1){
                button.setForeground(Color.BLUE);
                button.setText("" + board[row][col]);
            }
            else if(board[row][col]==2){
                button.setForeground(darkGreen);
                button.setText("" + board[row][col]);
            }
            else if(board[row][col]==3){
                button.setForeground(Color.RED);
                button.setText("" + board[row][col]);
            }
            else if(board[row][col]==4){
                button.setForeground(darkPurple);
                button.setText("" + board[row][col]);
            }
            else if(board[row][col]>4 && board[row][col]<9){
                button.setText("" + board[row][col]);
            }

            //if this squre is blank then proceed to check adjacent squares if there valid and selected
            //in order to add to queue
            if(board[row][col]==0){
                if(mineclone.isValid(row+1, col, board) && !buttons[row+1][col].isSelected()){
                q.add(buttons[row+1][col]);
                }
                if(mineclone.isValid(row-1, col, board) && !buttons[row-1][col].isSelected()){
                    q.add(buttons[row-1][col]);
                }
                if(mineclone.isValid(row, col+1, board) && !buttons[row][col+1].isSelected()){
                    q.add(buttons[row][col+1]);
                }
                if(mineclone.isValid(row, col-1, board) && !buttons[row][col-1].isSelected()){
                    q.add(buttons[row][col-1]);
                }
            }
        }
    }
    //checks if player won the game by placing flags on all the mines, if not all mines are flagged
    //then the player lost
    static void checkIfWon(int flag[][], int board[][], JToggleButton buttons[][]){
        int i;
        int j;
        int row = board.length;
        int col = board[0].length;
        //counts how many mines player has flagged
        int counter=0;
        int lost=0;
        for(i=0; i<row; i++){
            for(j=0; j<col; j++){
                
                if(board[i][j]==9 && flag[i][j]==1){
                   counter++;    
                }
                else if(board[i][j]==9 && flag[i][j]==0){
                    lost=1;
                }
            }
        }
        if(lost==1){
             gameOver(buttons, board);
             JOptionPane.showMessageDialog(null, "You lost. You only flagged " + counter + " mines");
        }
        else{
            //if reached here that means all mines have been marked with a flag and player won
            JOptionPane.showMessageDialog(null, "You have won the game!");
        }
        
    }
}