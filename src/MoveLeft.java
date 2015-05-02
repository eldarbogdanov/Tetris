public class MoveLeft implements Move {
    @Override
    public boolean apply(Board board) {
        return board.moveLeft();
    }
}
