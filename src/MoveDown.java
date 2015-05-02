public class MoveDown implements Move {
    @Override
    public boolean apply(Board board) {
        return board.moveDown();
    }
}
