public class Rook extends ChessPiece {
	/**
	 * Constructor for Rooks
	 * @param color color of the rook
	 * @param xLocation x location of the rook
	 * @param yLocation y location of the rook
	 */
	public Rook(boolean color, int xLocation, int yLocation) {
		super(color, xLocation, yLocation);
		if(color) {
			pieceString = "r";
		} else {
			pieceString = "R";
		}
	}
	
	public boolean isBlocked(int xLoc, int yLoc) {
		int currX = this.xLocation;
		int currY = this.yLocation;	
		ChessPiece currentPiece = ChessBoard.head;
		//Checks veritcal
		if(currX == xLoc && currY < yLoc) {
			while(currY < yLoc-1) {
				currY++;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
		}
		//Checks veritcal
		if(currX == xLoc && currY > yLoc) {
			while(currY > yLoc+1) {
				currY--;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
		}
		//Checks horizontal
		if(currY == yLoc && currX > xLoc) {
			while(currX > xLoc+1) {
				currX--;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
		}
		//Checks horizontal
		if(currY == yLoc && currX < xLoc) {
			while(currX < xLoc-1) {
				currX++;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
		}
		return false;
	}
	
	/**
	 * Method to check if the current piece can attack another piece
	 * @param size size of the board
	 * @param xLoc x location of the other piece
	 * @param yLoc y location of the other piece
	 * @return True if the current piece can attack the other piece. false otherwise
	 */
	public boolean canMoveTo(int size ,int xLoc, int yLoc ) {
		if(this.isBlocked(xLoc,yLoc)) {
			return false;
		}
		for (int i = 0; i <= size; i++) {
			if ( i == xLoc && this.yLocation == yLoc ) {
				return true;
			}
		}
		
		for (int i = 0; i <= size; i++) {
			if ( this.xLocation == xLoc && i == yLoc ) {
				return true;
			}
		}
		return false;
	}
	
}