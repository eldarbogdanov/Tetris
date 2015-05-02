import java.awt.*;

public interface Graphics {
    void render(Color[][] board, int score, int pieces, Piece nextPiece);
}
