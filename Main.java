public class Main{
    public static void main(String[] args) {
        int size = 4, winVal = 2048;
        Game game = new Game(size, winVal);
        game.start();
    }
}