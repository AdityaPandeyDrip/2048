import java.util.*;

public class Board {
    int [][] board;
    public Board(int size){
        board = new int[size][size];
    }

    public void set(int x, int y, int val){
        board[x][y] = val;
    }

    public int get(int x, int y){
        return board[x][y];
    }

    public int getSize(){
        return board.length;
    }

    public void clear(){
        for(int i = 0; i < getSize(); ++i)
            Arrays.fill(board[i], 0);
    }

    public void setRandom(){
        int x = (int)(Math.random() * (4));
        int y = (int)(Math.random() * (4));
        int val = (int)Math.pow(2, 1 + (int)(Math.random() * (2)));
        while(get(x, y) != 0){
            x = (int)(Math.random() * (4));
            y = (int)(Math.random() * (4));
        }
        set(x, y, val);
    }

    public String toString(){
        String body = "";
        String upFrame = "-----------------\n";
        String midFrame = "         ";
        for(int i = 0; i < getSize(); ++i){
            body += midFrame;
            for(int j = 0; j < getSize(); ++j){
                body += "| " + board[i][j] + " ";
            }
            body += "|\n";
        }
        String out = midFrame + upFrame + body + midFrame + upFrame;
        return out;
    }

    // will complete below methods in future commits
    public void shiftLeft(){

    }
    public void shiftRight(){
        
    }
    public void shiftUp(){
        
    }
    public void shiftDown(){
        
    }

    

    

}

