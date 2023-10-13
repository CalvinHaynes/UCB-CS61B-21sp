package game2048;

import java.util.*;


/** The state of a game of 2048.
 *  @author CalvinHaynes
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        String originalBoard = this.board.toString();

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(side);

        // 移动
        for (int i = 0; i < 4; i++) {
            tiltOneColumn(i);
        }

        board.setViewingPerspective(Side.NORTH);

        if (!board.toString().equals(originalBoard)) {
            changed = true;
        }

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }


    /**
     * helper method : 用于处理某一列,将原本的问题继续简化了,然后在tile中使用这个
     */
     private void tiltOneColumn(int col) {
         Tile[] tiles = new Tile[4];
         Tile[] originalTiles = new Tile[4];
         Stack<Tile> tileStack = new Stack<>();
         for (int i = 0; i < 4; i++) {
             tiles[i] = board.tile(col, i);
             if (tiles[i] != null) {
                 tileStack.push(tiles[i]);
             }
         }
         System.arraycopy(tiles,0,originalTiles,0,4);

         switch (tileStack.size()) {
             case 1:
                 Tile t1 = tileStack.pop();
                 board.move(col, 3, t1);
                 break;
             case 2:
                 Tile[] t2 = new Tile[2];
                 for (int i = 0; i < 2; i++) {
                     t2[i] = tileStack.pop();
                 }
                 if (t2[0].value() == t2[1].value()) {
                     board.move(col, 3, t2[0]);
                     board.move(col, 3, t2[1]);
                     score += 2 * t2[0].value();
                 } else {
                     board.move(col, 3, t2[0]);
                     board.move(col, 2, t2[1]);
                 }
                 break;
             case 3:
                 Tile[] t3 = new Tile[3];
                 for (int i = 0; i < 3; i++) {
                     t3[i] = tileStack.pop();
                 }
                 if (t3[0].value() == t3[1].value()) {
                     board.move(col, 3, t3[0]);
                     board.move(col, 3, t3[1]);
                     board.move(col, 2, t3[2]);
                     score += 2 * t3[0].value();
                 } else if (t3[1].value() == t3[2].value()) {
                     board.move(col, 3, t3[0]);
                     board.move(col, 2, t3[1]);
                     board.move(col, 2, t3[2]);
                     score += 2 * t3[1].value();
                 } else {
                     board.move(col, 3, t3[0]);
                     board.move(col, 2, t3[1]);
                     board.move(col, 1, t3[2]);
                 }
                 break;
             case 4:
                 Tile[] t4 = new Tile[4];
                 for (int i = 0; i < 4; i++) {
                     t4[i] = tileStack.pop();
                 }
                 if (t4[0].value() == t4[1].value()) {
                     board.move(col, 3, t4[1]);
                     score += 2 * t4[0].value();
                     if (t4[2].value() == t4[3].value()) {
                         board.move(col, 2, t4[2]);
                         board.move(col, 2, t4[3]);
                         score += 2 * t4[2].value();
                     } else {
                         board.move(col, 2, t4[2]);
                         board.move(col, 1, t4[3]);
                     }
                 } else if (t4[1].value() == t4[2].value()) {
                     board.move(col, 2, t4[2]);
                     board.move(col, 1, t4[3]);
                     score += 2 * t4[1].value();
                 } else if (t4[2].value() == t4[3].value()) {
                     board.move(col, 1, t4[3]);
                     score += 2 * t4[2].value();
                 }
                 break;
         }
     }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // 检测Board是否有empty space实现完毕, 有 empty space 就是指游戏板上有无值的空位
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if(b.tile(i,j) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // 如果游戏版上出现2048的tile则游戏结束, 返回true, 否则返回false
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i,j) != null) {
                    if (b.tile(i, j).value() == MAX_PIECE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // 只要未来有至少一步能走, 就返回true
        if (emptySpaceExists(b)) {
            return true;
        }
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (i+1 < b.size() && b.tile(i, j).value() == b.tile(i+1, j).value() ||
                    i-1 >=0 && i-1 < b.size() && b.tile(i, j).value() == b.tile(i-1, j).value() ||
                    j-1 >= 0 && j-1 < b.size() && b.tile(i, j).value() == b.tile(i, j-1).value() ||
                    j+1 < b.size() && b.tile(i, j).value() == b.tile(i, j+1).value()) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
