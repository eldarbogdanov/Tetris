public class MoveRotate implements Move {
    @Override
    public boolean apply(Board board) {
        return board.rotatePiece();
    }
}
