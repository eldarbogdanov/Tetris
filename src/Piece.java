public class Piece {
    public Piece(char[][] fill) {
        this.fill = fill;
    }
    public final char[][] fill;

    public Piece rotate() {
        int n = fill.length;
        char[][] newFill = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                newFill[j][n - i - 1] = fill[i][j];
        }
        int leftmost = Integer.MAX_VALUE, topmost = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (newFill[i][j] == 'X') {
                    leftmost = Math.min(leftmost, j);
                    topmost = Math.min(topmost, i);
                }
        }
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newFill[i][j] = i + topmost < n && j + leftmost < n ? newFill[i + topmost][j + leftmost] : Pieces.EMPTY;
            }
        }
        return new Piece(newFill);
    }
}
