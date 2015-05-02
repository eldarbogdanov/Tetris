import java.awt.*;
import javax.swing.*;

public class GraphicsConsole implements Graphics {

    private final JLabel textLabel;
    GraphicsConsole(int boardHeight, int boardWidth) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(boardWidth * 20, boardHeight * 20 + 100));
        frame.getContentPane().add(textLabel, BorderLayout.NORTH);
        //Display the window.
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private static String pieceVisual(Piece piece) {
        String ret = "Next piece:\n";
        for(int i = 0; i < piece.fill.length; i++) {
            ret += new String(piece.fill[i]) + "\n";
        }
        return ret;
    }

    @Override
    public void render(Color[][] board, int score, int pieces, Piece nextPiece) {
        String s = "<html><pre>\n";
        s += "Score: " + score + "\n";
        s += "Pieces: " + pieces + "\n";
        s += pieceVisual(nextPiece);
        s += "\n";
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == null) {
                    s += Pieces.EMPTY;
                } else {
                    s += Pieces.USED;
                }
            s += "\n";
        }
        s += "</pre></html>";
        textLabel.setText(s);
    }

}
