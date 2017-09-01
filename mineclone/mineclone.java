/*
 * Jonathan Chin
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

import java.util.ArrayList;
import java.util.Collections;
import static mineclone.Game.flagTotal;

public class mineclone {

    //3 static 2D arrays for each level. Dynamic arrays were avoided in order
    //to use primitive types as they are easier to work with.
    //0 is empty and 9 is a mine. 1-8 are the numbers denoting how many adjacent mines
    public static int [][]smallBoard = new int[9][9];
    public static int [][]mediumBoard = new int[16][16];
    public static int [][]largeBoard = new int[16][30];
    
    //boards used to hold the right click flags
    public static int [][]smallFlag = new int[9][9];
    public static int [][]mediumFlag = new int[16][16];
    public static int [][]largeFlag = new int[16][30];
    
    public static void main(String[] args) {
        //default game starts on medium mode
        shuffleMines(mediumBoard, mediumFlag);
        fillAdjacentMines(mediumBoard);
        displayBoard(mediumBoard);
        Game mediumGame = new Game(16,16,1,1, "medium");
        
    }
    
    //radomly shuffles mines onto the board, the dimensions of board and number of mines are passed in
    static void shuffleMines(int board[][], int flag[][]){
        //reset flag counter every new game
        flagTotal=0;
        int row = board.length;
        int col = board[0].length;
       
        int mines=0;
        int total=row*col;
        if(total==81){
            mines=10;
        }
        else if(total==256){
            mines=40;
        }
        else if(total==480){
            mines=99;
        }
        //intitalize board to all blanks and setup arraylist for shuffling
        //also initializes all flags to zero
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                list.add(i*col+j);
                board[i][j]=0;
                flag[i][j]=0;
            }
        }

        //shuffles list, 9 represents a mine on the board
        Collections.shuffle(list);
        for(int i=0; i<mines; i++){
            
            board[list.get(i)/col][list.get(i)%col] = 9;
        }
        
    }
    //places numbers around mines indicating how many nearby mines by checking to see if each adjacent 
    //square is valid and if there is a mine adjacent to it
    static void fillAdjacentMines(int board[][]){
        int row = board.length;
        int col = board[0].length;
        int value;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                int total=0;
                if(isValid(i-1, j-1, board) && board[i-1][j-1]==9){
                    total++;
                }
                if(isValid(i-1, j, board) && board[i-1][j]==9){
                    total++;
                }
                if(isValid(i-1, j+1, board) && board[i-1][j+1]==9){
                    total++;
                }
                if(isValid(i, j-1, board) && board[i][j-1]==9){
                    total++;
                }
                if(isValid(i, j+1, board) && board[i][j+1]==9){
                    total++;
                }
                if(isValid(i+1, j-1, board) && board[i+1][j-1]==9){
                    total++;
                }
                if(isValid(i+1, j, board) && board[i+1][j]==9){
                    total++;
                }
                if(isValid(i+1, j+1, board) && board[i+1][j+1]==9){
                    total++;
                }
                //checks to see if the square is blank before overwriting it
                if(board[i][j]==0){
                    board[i][j]=total;
                }
                
            }
        }
    }
    //checks to make sure the location is not out of bounds
    static boolean isValid(int i, int j, int board[][]){
        //if i greater then row or j greater then col its out of bounds
        int row=board.length-1;
        int col=board[0].length-1;
        if(i<0 || j<0 || i>row || j>col){
            return false;
        }
        return true;
    }
    //used for testing purposes to test the backend board. 
    static void displayBoard(int board[][]){
        int i;
        int j;
        int row = board.length;
        int col = board[0].length;
        for(i=0; i<row; i++){
            for(j=0; j<col; j++){
                System.out.printf("%d ", board[i][j]);
            }
            System.out.printf("\n");
        }
    }

}