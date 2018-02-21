public class ChessPiece {
	
	public boolean color;
	public int xLocation;
	public int yLocation;
	public ChessPiece next;
	public String pieceString;
	/**
	 * Constructor for a chess piece
	 * @param color boolean containing the color of the piece
	 * @param xLocation the x location of the piece
	 * @param yLocation the y location of the piece
	 */
	public ChessPiece(boolean color, int xLocation, int yLocation) {
		this.color = color;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.next = null;
	}
	/**
	 * Dummy canAttack method for other classes to override
	 * @param size size of the board
	 * @param xLoc x location of the other piece
	 * @param yLoc y location of the other piece
	 * @return True if the current piece can attack the other piece. false otherwise
	 */
	public boolean canMoveTo(int size ,int xLoc, int yLoc){
		return false;
	}
	public boolean isBlocked(int xLoc, int yLoc) {
		//checks all pieces between point A and point B
		return false;
	}
	public void makeMove(int xLoc, int yLoc) {
		xLocation = xLoc;
		yLocation = yLoc;
	}
	
}