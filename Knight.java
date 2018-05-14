import java.awt.Color;

import javax.swing.ImageIcon;

public class Knight extends Piece {
    public Knight(ChessBoard board, ChessPlayer player) {
        super(board, player);
    }

    public String toString() {//u0482, 1F438, 1F430
//    	 ImageIcon image = new ImageIcon(getClass().getResource("frogger.png"));
//    	 ImageIcon frog = new ImageIcon(getClass().getResource("frog.png"));
    	 String cc2 = "1F438";
    	 String text2 = String.valueOf(Character.toChars(Integer.parseInt(cc2, 16)));
    	 String cc1 = "1F430";
    	 String text1 = String.valueOf(Character.toChars(Integer.parseInt(cc1, 16)));
         return getPlayer() == ChessPlayer.WHITE ? text2 :  text1 ;
    }

    // ------------------------------------------------------------------------
    // Movement
    // ------------------------------------------------------------------------

    /**
     * A knight can move in an `L` shape - two squares horizontally and one square
     * vertically, or one square horizontally and two squares vertically. A knight
     * may not be obstructed by another piece.
     *
     * @param from The source (current) tile.
     * @param to   The destination tile.
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean canMove(ChessTile from, ChessTile to) {
        int d1 = Math.abs(from.getRank() - to.getRank());
        int d2 = Math.abs(from.getFile() - to.getFile());

        return canMoveOrCapture(to) && ((d1 == 1 && d2 == 0) || ((d1 == 0 && d2 == 1) || (d1 == 3 && d2 == 0) || (d1 == 0 && d2 == 3) ));
    }
    

}
