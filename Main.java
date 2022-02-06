public class Main{
    public static void main(String[] args) {
        int rowSize = 4, colSize = 4, winVal = 2048;
        Game game = new Game(rowSize, colSize, winVal);
        game.start();
    }
}