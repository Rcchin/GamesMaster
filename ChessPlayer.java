public enum ChessPlayer {
    WHITE,
    BLACK;

    /**
     * Get the opposite player.
     *
     * @return The opposite player.
     */
    public ChessPlayer opposite() {
        switch (this) {
            case WHITE: return BLACK;
            case BLACK: return WHITE;
        }

        return null;
    }
}
