public class Bishop extends Piece {
    public Bishop(ChessBoard board, ChessPlayer player) {
        super(board, player);
    }

    public String toString() {
        return getPlayer() == ChessPlayer.WHITE ? "\u2657" : "\u265D";
    }

    // ------------------------------------------------------------------------
    // Movement
    // ------------------------------------------------------------------------

    /**
     * A bishop can move on a diagonal path any number of tiles, but cannot jump
     * over another piece.
     *
     * @param from The source (current) tile.
     * @param to   The destination tile.
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean canMove(ChessTile from, ChessTile to) {
        if (from.equals(to)) {
            return false;
        }

        int d1 = Math.abs(from.getRank() - to.getRank());
        int d2 = Math.abs(from.getFile() - to.getFile());

        if (d1 == d2) {
            return canMoveOrCapture(to) && ((d1 == 1 && d2 == 1) || (d1 == 2 && d2 == 2)) && !isPathBlocked(from, to);
        }

        return false;
    }
}
