import java.awt.*;

public class Board {
    Board(int height, int width, PieceGenerator pieceGenerator, ColorGenerator colorGenerator) {
        this.board = new Color[height][width];
        this.colorGenerator = colorGenerator;
        this.pieceGenerator = pieceGenerator;
        this.movingPiece = generateNewMovingPiece();
        this.score = 0;
        this.pieces = 0;
        this.isOver = false;
    }
    private final Color[][] board;
    private final PieceGenerator pieceGenerator;
    private final ColorGenerator colorGenerator;
    private PieceOnBoard movingPiece;
    private Piece nextPieceType;
    private boolean isOver;
    private int score;
    private int pieces;

    static class PieceOnBoard {
        final Color color;
        final Piece pieceType;
        final int row;
        final int col;

        PieceOnBoard(Color color, Piece pieceType, int row, int col) {
            this.color = color;
            this.pieceType = pieceType;
            this.col = col;
            this.row = row;
        }

        PieceOnBoard rotate() {
            return new PieceOnBoard(color, pieceType.rotate(), row, col);
        }

        PieceOnBoard moveLeft() {
            return new PieceOnBoard(color, pieceType, row, col - 1);
        }

        PieceOnBoard moveRight() {
            return new PieceOnBoard(color, pieceType, row, col + 1);
        }

        PieceOnBoard moveDown() {
            return new PieceOnBoard(color, pieceType, row + 1, col);
        }
    }

    private static boolean checkIfFull(Color[] row) {
        for(int i = 0; i < row.length; i++)
            if (row[i] == null)
                return false;
        return true;
    }

    private void eraseFullLines() {
        int erased = 0;
        for(int i = board.length - 1; i >= 0; i--) {
            if (checkIfFull(board[i])) {
                ++erased;
                for(int j = i - 1; j >= 0; j--) {
                    System.arraycopy(board[j], 0, board[j + 1], 0, board[j].length);
                }
                ++i;
            }
        }
        if (erased > 0)
            score += 5 * (1 << erased - 1);
    }

    private void addMovingPieceToBoard() {
        for(int i = 0; i < movingPiece.pieceType.fill.length; i++)
            for(int j = 0; j < movingPiece.pieceType.fill[0].length; j++)
                if (movingPiece.pieceType.fill[i][j] == Pieces.USED)
                    board[i + movingPiece.row][j + movingPiece.col] = movingPiece.color;
        eraseFullLines();
    }

    private boolean isPieceValidlyPlaced(PieceOnBoard piece) {
        for(int i = 0; i < piece.pieceType.fill.length; i++)
            for(int j = 0; j < piece.pieceType.fill[0].length; j++)
                if (piece.pieceType.fill[i][j] == Pieces.USED) {
                    if (piece.row + i >= board.length || piece.col + j >= board[0].length) return false;
                    if (board[piece.row + i][piece.col + j] != null) return false;
                }
        return true;
    }

    private PieceOnBoard generateNewMovingPiece() {
        if (nextPieceType == null)
            nextPieceType = pieceGenerator.nextPiece();
        PieceOnBoard ret = new PieceOnBoard(colorGenerator.nextColor(), nextPieceType, 0, board[0].length / 2);
        nextPieceType = pieceGenerator.nextPiece();
        return ret;
    }

    boolean rotatePiece() {
        PieceOnBoard rotated = movingPiece.rotate();
        if (isPieceValidlyPlaced(rotated)) {
            movingPiece = rotated;
            return true;
        }
        return false;
    }

    boolean moveLeft() {
        if (movingPiece.col == 0)
            return false;
        PieceOnBoard moved = movingPiece.moveLeft();
        if (isPieceValidlyPlaced(moved)) {
            movingPiece = moved;
            return true;
        }
        return false;
    }

    boolean moveRight() {
        if (movingPiece.col + 1 == board[0].length)
            return false;
        PieceOnBoard moved = movingPiece.moveRight();
        if (isPieceValidlyPlaced(moved)) {
            movingPiece = moved;
            return true;
        }
        return false;
    }

    boolean moveDown() {
        PieceOnBoard moved = movingPiece.moveDown();
        if (isPieceValidlyPlaced(moved)) {
            movingPiece = moved;
            return true;
        }

        addMovingPieceToBoard();
        movingPiece = generateNewMovingPiece();
        ++pieces;

        if (isPieceValidlyPlaced(movingPiece)) {
            return true;
        }
        isOver = true;
        return false;
    }

    Color[][] getVisual() {
        Color[][] visual = new Color[board.length][board[0].length];
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                visual[i][j] = board[i][j];
        char[][] fill = movingPiece.pieceType.fill;
        for(int i = 0; i < fill.length; i++)
            for(int j = 0; j < fill[0].length; j++)
                if (fill[i][j] == Pieces.USED && i + movingPiece.row < visual.length && j + movingPiece.col < visual[0].length)
                    visual[i + movingPiece.row][j + movingPiece.col] = movingPiece.color;
        return visual;
    }

    boolean isOver() {
        return isOver;
    }
    int getScore() {
        return score;
    }
    int getPieces() {
        return pieces;
    }
    Piece nextPiece() {
        return nextPieceType;
    }

}
