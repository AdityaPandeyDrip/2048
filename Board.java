import java.util.*;

public class Board {
    // maintains the size of the board
    public int rowSize = 0, colSize = 0;

    // 2d int array to store each cells value
    public int [][] board;

     // 2d int array to store if the current cell had already merged with another cell
    public int [][] isMerged;

    // to store the number of cells moved after each move
    public int numberOfCellsMoved = 0;

    public Board(int rowSize, int colSize){
        this.rowSize = rowSize;
        this.colSize = colSize;
        board = new int[rowSize][colSize];
        isMerged = new int[rowSize][colSize];
    }

    public void set(Cell cell){
        board[cell.row][cell.col] = cell.val;
    }

    public void setMerged(Cell cell){
        isMerged[cell.row][cell.col] = cell.val;
    }

    public int get(Cell cell){
        return board[cell.row][cell.col];
    }

    public int getMerged(Cell cell){
        return isMerged[cell.row][cell.col];
    }

    public int getRowSize(){
        return rowSize;
    }

    public int getColSize(){
        return colSize;
    }

    public int getNumberOfCellsMoved(){
        return numberOfCellsMoved;
    }

    // Returns true if a val is present in any cell of the board else returns false
    public boolean isPresentVal(int val){
        for(int row = 0; row < getRowSize(); ++row)
            for(int col = 0; col < getColSize(); ++col)
                if(get(new Cell(row, col)) == val)
                    return true;

        return false;
    }

    // resets the board to 0
    public void clear(){
        for(int i = 0; i < getRowSize(); ++i)
            Arrays.fill(board[i], 0);
        clearMerge();
        numberOfCellsMoved = 0;
    }

    // resets the merge to 0
    public void clearMerge(){
        for(int i = 0; i < getRowSize(); ++i)
            Arrays.fill(isMerged[i], 0);
    }

    // sets any empty cell in the board to 2 or 4
    public void setRandom(){
        Cell cell = new Cell();

        // using Math.random() to generate random row,col
        cell.row = (int)(Math.random() * (getRowSize()));
        cell.col = (int)(Math.random() * (getColSize()));

        // generating random 2 and 4
        cell.val = (int)Math.pow(2, 1 + (int)(Math.random() * (2)));

        while(get(cell) != 0){
            cell.row = (int)(Math.random() * (getRowSize()));
            cell.col = (int)(Math.random() * (getColSize()));    
        }
        set(cell);
    }

    // Check if the cell can be moved to Cell to or not
    // returns 1 if the Cell to is empty
    // returns 2 if the Cell to have same val ad Cell from
    // otherwise returns 0
    public int isValid(Cell from, Cell to){
        if(to.row >= 0 && to.row < getRowSize() && to.col >= 0 && to.col < getColSize()){
            if(get(to) == 0){
                return 1;
            }else if( get(to) == get(from) && getMerged(to) == 0){
                return 2;
            }
        }
        return 0;
    }

    // moves from Cell from to Cell to
    public void move(Cell from, Cell to){
        // Adding the val of Cell from and Cell to inorder to merge both the cells
        to.val = get(to) + get(from);
        from.val = 0;
        set(to);
        set(from);
    }

    // Checks if any cell in the board can be moved in any of the four direction or not
    public boolean canMove(Cell from){
        // here dx and dy are storing changes required in row and col to move cell in a certain direction
        int []dx = {0, 0, -1, 1};
        int []dy = {-1, 1, 0, 0};

        for(int i = 0; i < 4; ++i){
            Cell to = new Cell(from.row + dx[i], from.col + dy[i]);
            if(isValid(from, to) != 0)
                return true;
        }
        return false;
    }

    // Shifts Cell in the given direction
    // it returns the score achieved while shifting this cell
    // its a recursive method 
    public int shift(Cell from, int dx, int dy){
        // if the cell is empty no need to shift the cell
        if(get(from) == 0)
            return 0;
        Cell to = new Cell(from.row + dx, from.col + dy);

        int valid = isValid(from, to);

        switch(valid){
            case 0: return 0;
            case 1: move(from, to);
                    ++numberOfCellsMoved;
                    return shift(to, dx, dy);
            case 2: move(from, to);
                    ++numberOfCellsMoved;
                    setMerged(to);
                    return get(to);
        }

        return 0;
    }

    // shifts all possible cells in left direction, returns the score achieved while doing so
    public int shiftLeft(){
        int dx = 0, dy = -1, score = 0;
        numberOfCellsMoved = 0;
        clearMerge();

        for(int col = 0; col < getColSize(); ++col)
            for(int row = 0; row < getRowSize(); ++row)
                score +=shift(new Cell(row, col), dx, dy);

        return score;
    }

    // shifts all possible cells in right direction, returns the score achieved while doing so
    public int shiftRight(){
        int dx = 0, dy = 1, score = 0;
        numberOfCellsMoved = 0;
        clearMerge();

        for(int col = getColSize() - 1; col >= 0; --col)
            for(int row = getRowSize() - 1; row >= 0; --row)
                score += shift(new Cell(row, col), dx, dy);
        
        return score;
    }

    // shifts all possible cells in up direction, returns the score achieved while doing so
    public int shiftUp(){
        int dx = -1, dy = 0, score = 0;
        numberOfCellsMoved = 0;
        clearMerge();

        for(int row = 0; row < getRowSize(); ++row)
            for(int col = 0; col < getColSize(); ++col)
                score += shift(new Cell(row, col), dx, dy);

        return score;
    }

    // shifts all possible cells in down direction, returns the score achieved while doing so
    public int shiftDown(){
        int dx = 1, dy = 0, score = 0;
        numberOfCellsMoved = 0;
        clearMerge();

        for(int row = getRowSize() - 1; row >= 0; --row)
            for(int col = getColSize() - 1; col >= 0; --col)
                score += shift(new Cell(row, col), dx, dy);

        return score;
    } 
    
    // toString method overrided to print the board
    public String toString(){
        String body = "";
        String upFrame = "-------------------------\n";
        String midFrame = "         ";

        for(int i = 0; i < getRowSize(); ++i){
            body += midFrame;
            for(int j = 0; j < getColSize(); ++j)
                body += "|  " + get(new Cell(i, j)) + "  ";
            body += "|\n";
        }

        String out = midFrame + upFrame + body + midFrame + upFrame;
        return out;
    }

}

