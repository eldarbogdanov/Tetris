import java.util.Random;

public class PieceGeneratorRandom implements PieceGenerator {

    private final Random random;

    PieceGeneratorRandom(Random random) {
        this.random = random;
    }

    @Override
    public Piece nextPiece() {
        Piece piece = Pieces.fill[random.nextInt(Pieces.fill.length)];
        int rotate = random.nextInt(4);
        for(int it = 0; it < rotate; it++)
            piece = piece.rotate();
        return piece;
    }
}
