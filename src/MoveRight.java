public class MoveRight implements Move {

    @Override
    public boolean apply(Board board) {
        return board.moveRight();
    }
}
