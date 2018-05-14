import java.util.List;

public class ChessBoard {
    private Piece[][] board = new Piece[6][6];

    // ------------------------------------------------------------------------
    // Setup
    // ------------------------------------------------------------------------

    /**
     * Removes all pieces from the board.
     */
    public void clear() {
        for (int rank = 0; rank < 6; rank++) {
            for (int file = 0; file < 6; file++) {
                board[rank][file] = null;
            }
        }
    }

    /**
     * Sets the back two ranks of each player to be the starting position. The
     * back rank gets the sequence <R, K, B, Q, K, B, K, N>, and the second to
     * last rank gets eight pawns.
     */
    public void initialize() {
        clear();

        for (int file = 0; file < 6; file++) {
            board[1][file] = new Pawn(this, ChessPlayer.BLACK);
            board[4][file] = new Pawn(this, ChessPlayer.WHITE);
        }

        board[0][0] = new Knight(this, ChessPlayer.BLACK);
        
        board[0][1] = new Bishop(this, ChessPlayer.BLACK);
        board[0][2] = new Queen(this, ChessPlayer.BLACK);
        board[0][3] = new King(this, ChessPlayer.BLACK);
        board[0][4] = new Bishop(this, ChessPlayer.BLACK);
  
        board[0][5] = new Knight(this, ChessPlayer.BLACK);

        board[5][0] = new Knight(this, ChessPlayer.WHITE);

        board[5][1] = new Bishop(this, ChessPlayer.WHITE);
        board[5][2] = new Queen(this, ChessPlayer.WHITE);
        board[5][3] = new King(this, ChessPlayer.WHITE);
        board[5][4] = new Bishop(this, ChessPlayer.WHITE);
     
        board[5][5] = new Knight(this, ChessPlayer.WHITE);
    }

    // ------------------------------------------------------------------------
    // Position
    // ------------------------------------------------------------------------

    /**
     * Determines if a tile is occupied.
     *
     * @param t The tile.
     * @return true if the tile is occupied, false otherwise.
     */
    public boolean isOccupied(ChessTile t) {
        return getPieceAt(t) != null;
    }

    /**
     * Determines if a tile is occupied by a particular player.
     *
     * @param t      The tile.
     * @param player The player.
     * @return true if the tile is occupied by player, false otherwise.
     */
    public boolean isOccupiedByPlayer(ChessTile t, ChessPlayer player) {
        return getPieceAt(t) != null && getPieceAt(t).getPlayer() == player;
    }

    /**
     * Retrieves the piece at the given tile.
     *
     * @param t The tile.
     * @return The piece at the given tile, or null if the tile is unoccupied.
     */
    public Piece getPieceAt(ChessTile t) {
        if (!t.isValid()) {
            return null;
        }

        return board[t.getRank()][t.getFile()];
    }

    /**
     * Updates the piece at the given tile.
     *
     * @param t     The tile.
     * @param piece The piece.
     * @throws RuntimeException If the tile is not valid.
     */
    public void setPieceAt(ChessTile t, Piece piece) {
        if (!t.isValid()) {
            throw new RuntimeException("Tile is not valid.");
        }

        board[t.getRank()][t.getFile()] = piece;
    }
    
//    protected boolean checkPromotion(ChessTile t, Piece p, ChessPlayer player) {
//    	//int board = getBoard();
//  
//    	
//		
//    	boolean inCheckMate = true;
//    	ChessTile tmp;
//    	for (int rank = 0; rank < 6; rank++) {
//            for (int file = 0; file < 6; file++) {
//            	 
//                	if(board[rank][file] == board[0][file] && p.isPawn() == false && getPieceAt(from) == player) {
//                		p = new Queen(this, ChessPlayer.WHITE);
//                	}
//            }
//    	}
//    	return inCheckMate;
//    	
//    }

    // ------------------------------------------------------------------------
    // Movement
    // ------------------------------------------------------------------------

    /**
     * Attempts to move a piece from the source tile to the destination tile. This method
     * may fail for the following reasons:
     * <p>
     * <ul>
     * <li>The source or destination tile refers outside the board.</li>
     * <li>The source tile is unoccupied.</li>
     * <li>The piece at the source tile cannot move or capture the destination tile.</li>
     * </ul>
     *
     * @param from The source tile.
     * @param to   The destination tile.
     * @return true if movement succeeded, false otherwise
     */
    public boolean move(ChessTile from, ChessTile to,  ChessPlayer player) {
    	
        Piece piece = getPieceAt(from);
        
    	
    	
        if (!from.isValid() || !to.isValid() || from.equals(to) || !isOccupied(from) || wouldPutInCheck(from,to)) {
        	//checkPromotion(from,piece);
            return false;
        }

        if (!piece.canMove(from, to)) {
        	//checkPromotion(from,piece);
            return false;
        }
      	int rank = to.getRank();
      	int file = to.getFile();
        
            	 
                	/*if(board[rank][file] == board[0][0] && (piece.getClass().equals(Pawn.class))) {
                		
                		piece = new Queen(this, ChessPlayer.WHITE);
                		
                	}*/

                	if(board[rank] == board[0] && (piece.getClass().equals(Pawn.class))) {
                		
                		//piece = new Queen(this, ChessPlayer.WHITE);
                		
                		while(true){
                		SwingDialog dialog = new SwingDialog();
                		String text = dialog.selectValue();
                		
                        if(text.equalsIgnoreCase("queen"))
                        {
                        	piece = new Queen(this,ChessPlayer.WHITE);
                        	break;
                        }
                        if(text.equalsIgnoreCase("bishop"))
                        {
                        	piece = new Bishop(this,ChessPlayer.WHITE);
                        	break;
                        }
                        if(text.equalsIgnoreCase("frog"))
                        {
                        	piece = new Knight(this,ChessPlayer.WHITE);
                        	break;
                        	
                        }
                        if(text.equals(null))//STILL WORKING ON THIS
                        {
                        	piece = new Pawn(this,ChessPlayer.WHITE);
                        	break;
                        }
                        else{
                        }
                        }
                		
                	}
                        
                		
                	
                	if(board[rank] == board[5] &&(piece.getClass().equals(Pawn.class))){
                		//piece = new Queen(this, ChessPlayer.BLACK);
                		while(true){
                    		SwingDialog dialog = new SwingDialog();
                    		String text = dialog.selectValue();
                    		
                            if(text.equalsIgnoreCase("queen"))
                            {
                            	piece = new Queen(this,ChessPlayer.BLACK);
                            	break;
                            }
                            if(text.equalsIgnoreCase("bishop"))
                            {
                            	piece = new Bishop(this,ChessPlayer.BLACK);
                            	break;
                            }
                            if(text.equalsIgnoreCase("frog"))
                            {
                            	piece = new Knight(this,ChessPlayer.BLACK);
                            	break;
                            	
                            }
                            else{
                            }
                            }
                	}
            
    	
        
        setPieceAt(to, piece);
        setPieceAt(from, null);
        return true;
    }
    
	public boolean isPlayerInCheck(ChessPlayer player) {
    	boolean inCheck = true;
    	ChessTile tmp;   	
    	for (int rank = 0; rank < 6; rank++) {
            for (int file = 0; file < 6; file++) {
            	tmp = new ChessTile (rank,file);
            	if (isOccupied(tmp) && (getPieceAt(tmp).getPlayer() == player.opposite())) {
            	   List<ChessTile> moves = getPieceAt(tmp).getAllMoves(tmp);
            	   for(ChessTile t : moves) {
            		   if(getPieceAt(tmp).canCapture(t) && getPieceAt(t).isKing()) {
            			   return inCheck;            			   
            		   }
            	   }
               }
            }
    	}        
    	return !inCheck;
    }
        
    public boolean isPlayerInCheckMate(ChessPlayer player) {
    	boolean inCheckMate = true;
    	ChessTile tmp;
    	for (int rank = 0; rank < 6; rank++) {
            for (int file = 0; file < 6; file++) {
            	tmp = new ChessTile(rank,file);
            	if (isOccupied(tmp) && (getPieceAt(tmp).getPlayer() == player)) {
            		List<ChessTile> moves = getPieceAt(tmp).getAllMoves(tmp);
            		for(ChessTile p : moves) {
            			if (getPieceAt(tmp).canMove(tmp, p) && !wouldPutInCheck(tmp, p)) {
            				return !inCheckMate;
            			}
            		}
            	}
            }
    	}
    	return inCheckMate;
    }
    
    public boolean wouldPutInCheck(ChessTile from, ChessTile to) {
    	boolean wouldCheck = true;
    	Piece wasTo = getPieceAt(to); // before move
    	Piece wasFrom = getPieceAt(from);
        setPieceAt(to, wasFrom);
        setPieceAt(from, null);
        Piece tmpPiece = getPieceAt(to);  // after move
        
        if (isPlayerInCheck(tmpPiece.getPlayer())){
        	//RESET BOARD
        	setPieceAt(from, wasFrom);
            setPieceAt(to, wasTo);
        	return wouldCheck;
        }
        else{
        	//RESET BOARD
        	setPieceAt(from, wasFrom);
            setPieceAt(to, wasTo);
        	return !wouldCheck;
        }
    }
}
