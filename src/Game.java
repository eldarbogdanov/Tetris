import java.util.Random;

public class Game {
    static final int HEIGHT = 20;
    static final int WIDTH = 10;
    static final int FALL_MS = 1000;
    static final int ACTION_MS = 100;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Graphics graphics = new GraphicsConsole(HEIGHT, WIDTH);
        Board board = new Board(HEIGHT, WIDTH, new PieceGeneratorRandom(random), new ColorGeneratorBlack());
        Player player = new PlayerKeyboard(new KeyBindingArrows());
        long timePassed = 0;
        while(true) {
            graphics.render(board.getVisual(), board.getScore(), board.getPieces(), board.nextPiece());
            if (timePassed >= FALL_MS) {
                timePassed = 0;
                board.moveDown();
                if (board.isOver()) break;
                continue;
            }
            Move nextMove = player.getMove();
            if (nextMove != null) nextMove.apply(board);
            if (board.isOver()) break;
            Thread.sleep(ACTION_MS);
            timePassed += ACTION_MS;
        }
    }
}
