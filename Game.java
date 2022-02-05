import java.util.*;

public class Game {
    private Board board;
    private static Scanner scan = new Scanner (System.in);
    public int winVal;

    public Game(int size, int winVal){
        this.winVal = winVal;
        board = new Board(size);
    }

    public void start(){
        // erasing the previous board if any
        board.clear();

        // setting a random block with 2 or 4
        board.setRandom();

        // printing the board
        System.out.println("---------New Game Started-------");
        System.out.println(board);

        while(!gameOver()){
            int nextMove = readMove();
            switch(nextMove){
                case 1: board.shiftLeft();
                        break;
                case 2: board.shiftRight();
                        break;
                case 3: board.shiftUp();
                        break;
                case 4: board.shiftDown();
                        break;
                default: System.out.println("Please enter valid move");
            }

            System.out.println(board);

        }

        if(won()){
            System.out.println("---------Congratulations, You Won The Game-------");
        }
    }

    // will complete below methods in future commits
    public boolean gameOver(){
        return true;
    }

    public boolean won(){
        return true;
    }

    public int readMove(){
        return 0;
    }

}
