public class King extends ChessPiece {
	/**
	 * Constructor for kings
	 * @param color color of the king
	 * @param xLocation x location of the king
	 * @param yLocation y location of the king
	 */
	public King(boolean color, int xLocation, int yLocation) {
		super(color, xLocation, yLocation);
		if (color) {
			pieceString = "k";
		} else {
			pieceString = "K";
		}
	}
		
	/**
	 * Method to check if the current piece can attack another piece
	 * @param size size of the board
	 * @param xLoc x location of the other piece
	 * @param yLoc y location of the other piece
	 * @return True if the current piece can attack the other piece. false otherwise
	 */
	public boolean canMoveTo(int size ,int xLoc, int yLoc){
		if (this.xLocation == xLoc + 1 && this.yLocation == yLoc + 1) {
			return true;
		}
		if (this.xLocation == xLoc - 1 && this.yLocation == yLoc - 1) {
			return true;
		}
		if (this.xLocation == xLoc + 1 && this.yLocation == yLoc - 1) {
			return true;
		}
		if (this.xLocation == xLoc - 1 && this.yLocation == yLoc + 1) {
			return true;
		}
		if (this.xLocation == xLoc + 1 && this.yLocation == yLoc) {
			return true;
		}
		if (this.xLocation == xLoc && this.yLocation == yLoc + 1) {
			return true;
		}
		if (this.xLocation == xLoc && this.yLocation == yLoc - 1) {
			return true;
		}
		if (this.xLocation == xLoc - 1 && this.yLocation == yLoc) {
			return true;
		}
		return false;
	}

	
}