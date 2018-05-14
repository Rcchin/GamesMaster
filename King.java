public class King extends Piece {
    public King(ChessBoard board, ChessPlayer player) {
        super(board, player);
    }

    public String toString() {
        return getPlayer() == ChessPlayer.WHITE ? "\u2654" : "\u265A";
    }

    // ------------------------------------------------------------------------
    // Movement
    // ------------------------------------------------------------------------

    /**
     * A king can move in any direction exactly one tile.
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

        return canMoveOrCapture(to) && d1 <= 1 && d2 <= 1;
    }
    
    @Override
    public boolean isKing() {
    	return true;
    }
}
