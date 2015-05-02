public class Pieces {
    private final static String[][] fillStr = new String[][] {
        {"XXXX",
         "....",
         "....",
         "...."},
        {"X...",
         "XX..",
         ".X..",
         "...."},
        {"XX..",
         ".XX.",
         "....",
         "...."},
        {".X..",
         "XXX.",
         "....",
         "...."},
        {"XXX.",
         "X...",
         "....",
         "...."},
        {"XXX.",
         "..X.",
         "....",
         "...."},
        {"XX..",
         "XX..",
         "....",
         "...."}
    };

    static final Piece[] fill = generateFill();

    private static char[][] getCharArrays(String[] fill) {
        char[][] ret = new char[fill.length][fill[0].length()];
        for(int i = 0; i < fill.length; i++)
            ret[i] = fill[i].toCharArray();
        return ret;
    }

    private static Piece[] generateFill() {
        Piece[] ret = new Piece[fillStr.length];
        for(int k = 0; k < fillStr.length; k++) {
            ret[k] = new Piece(getCharArrays(fillStr[k]));
        }
        return ret;
    }

    static final char USED = 'X';
    static final char EMPTY = '.';
}
