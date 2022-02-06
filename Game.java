import java.util.*;

public class Game {
    // Board object 
    private Board board;

    // winVal is the max value user needs to reach to win the game 
    public int winVal = 0, score = 0;

    // Methods can use the scanner without declaring it each time
    private static Scanner scan = new Scanner (System.in);
    
    public Game(int rowSize, int colSize, int winVal){
        this.winVal = winVal;
        board = new Board(rowSize, colSize);
    }

    public void start(){
        // erasing the previous board if any
        board.clear();
        // adding two 2 or 4 at random cell in the board
        board.setRandom();
        board.setRandom();

        score = 0;
        System.out.println("------------New Game Started----------");
        do {
            // checking if any cell is moved in the last move and is there any empty cell present to add new random val
            if(board.numberOfCellsMoved > 0 && board.isPresentVal(0))
                board.setRandom();
            System.out.println("   Score: " + score);
            System.out.println(board);

            // reading and processing next move
            int nextMove = readNextMove(1, 4);
            switch(nextMove){
                case 1: score += board.shiftLeft();
                        break;
                case 2: score += board.shiftRight();
                        break;
                case 3: score += board.shiftUp();
                        break;
                case 4: score += board.shiftDown();
                        break;
                default: System.out.println("Please enter a valid move, 1 to 4 only");
                        continue;
            }
            
        }while(!gameOver());

        if(won()){
            System.out.println("---------Congratulations, You Won The Game-------");
        }else {
            System.out.println("---------You Lost The Game-------");
        }
        System.out.println(board);
        System.out.println("Your final score is: " + score);
    }

    // Checks if game is over or not
    public boolean gameOver(){
        // checking if game is won or not
        if(won())
            return true;

        // checking if any empty cell is present or not
        if(board.isPresentVal(0))
            return false;
        
        // checking if any of the cells can move or not in any direction
        for(int row = 0; row < board.getRowSize(); ++row)
            for(int col = 0; col < board.getColSize(); ++col)
                if(board.canMove(new Cell(row, col)))
                    return false;

        return true;
    }

    // checking if the winVal is present in the board, as it will result in user winning the game
    public boolean won(){
        return board.isPresentVal(winVal);
    }

    // reads next move and checks if it in range between min and max
    public int readNextMove(int min, int max){
        int input = 0;
        boolean flag = false;

        System.out.print("Please enter your next move: ");
		do {
			try {
				input = scan.nextInt();
                flag = true;
			}
			catch(InputMismatchException e) {
				scan.next();
			}

            if(flag && (input >= min && input <= max))
                break;
            else
                System.out.print("Please enter integers only from " + min + " to " + max + " only: ");

		} while(true);
	
		return input;	
    }

}
