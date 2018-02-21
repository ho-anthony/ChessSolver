public class Knight extends ChessPiece {
	/**
	 * Constructor for knights
	 * @param color color of the knight
	 * @param xLocation x location of the knight
	 * @param yLocation y location of the knight
	 */
	public Knight(boolean color, int xLocation, int yLocation) {
		super(color, xLocation, yLocation);
		if (color) {
			pieceString = "n";
		} else {
			pieceString = "N";
		}
	}
	/**
	 * Method to check if the current piece can attack another piece
	 * @param size size of the board
	 * @param xLoc x location of the other piece
	 * @param yLoc y location of the other piece
	 * @return True if the current piece can attack the other piece. false otherwise
	 */
	public boolean canMoveTo(int size ,int xLoc, int yLoc ){
		if (this.xLocation - 2 == xLoc && this.yLocation + 1 == yLoc) {
			return true;
		}
		if (this.xLocation + 2 == xLoc && this.yLocation - 1 == yLoc ) {
			return true;
		}
		if (this.xLocation - 1 == xLoc  && this.yLocation + 2 == yLoc ) {
			return true;
		}
		if (this.xLocation + 1  == xLoc && this.yLocation - 2 == yLoc ) {
			return true;
		}
		/////////////////////////////////////////////////////////////////////////////////
		if (this.xLocation - 2 == xLoc && this.yLocation  - 1 == yLoc) {
			return true;
		}
		if (this.xLocation + 2  == xLoc && this.yLocation + 1 == yLoc) {
			return true;
		}
		if (this.xLocation - 1  == xLoc && this.yLocation - 2 == yLoc) {
			return true;
		}
		if (this.xLocation + 1 == xLoc && this.yLocation + 2 == yLoc) {
			return true;
		}
		return false;
	}

}