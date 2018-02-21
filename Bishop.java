public class Bishop extends ChessPiece {
	/**
	 * Constructor for bishops
	 * @param color color of bishop
	 * @param xLocation x location of the bishop
	 * @param yLocation y location of the bishop
	 */
	public Bishop(boolean color, int xLocation, int yLocation) {
		super(color, xLocation, yLocation);
		if(color) {
			pieceString = "b";
		} else {
			pieceString = "B";
		}
	}
	
	public boolean isBlocked(int xLoc, int yLoc) {
		int currX = this.xLocation;
		int currY = this.yLocation;
		int originalX = this.xLocation;
		int originalY = this.yLocation;
		ChessPiece currentPiece = ChessBoard.head;
		//Checks first diag
		if(currY < yLoc && currX < xLoc) {
			while(currX < xLoc-1 && currY < yLoc-1) {
				currX++;
				currY++;
				
				while (currentPiece != null) {
					
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {		
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
			currX = originalX;
			currY = originalY;
		}
		//Checks second diag
		if(currY > yLoc && currX > xLoc) {
			while(currX > xLoc+1 && currY > yLoc+1) {
				currX--;
				currY--;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
			currX = originalX;
			currY = originalY;
		}
		//Checks third diag
		if(currY < yLoc && currX > xLoc) {
			while(currX > xLoc +1 && currY < yLoc -1) {
				currX--;
				currY++;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
			currX = originalX;
			currY = originalY;
		}
		//Checks fourth diag
		if(currY > yLoc && currX < xLoc) {
			while(currX < xLoc-1 && currY > yLoc+1) {
				currX++;
				currY--;
				while (currentPiece != null) {
					if(currentPiece.xLocation == currX && currentPiece.yLocation == currY) {
						return true;
					}
					currentPiece = currentPiece.next;
				}
				currentPiece = ChessBoard.head;
			}
			currX = originalX;
			currY = originalY;
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
	public boolean canMoveTo(int size ,int xLoc, int yLoc ){
		if(this.isBlocked(xLoc, yLoc)) {
			return false;
		}
		for(int i = this.xLocation + 1, j = this.yLocation + 1 ; i <= size && j <= size ; i++, j++) {
			if(i == xLoc && j == yLoc) {
				return true;
			}
		}
		
		for(int i = this.xLocation - 1, j = this.yLocation - 1 ; i >= 0 && j >= 0 ; i--, j--) {
			if(i == xLoc && j == yLoc) {
				return true;
			}
		}
		
		for(int i = this.xLocation + 1, j = this.yLocation - 1 ; i <= size && j >= 0 ; i++, j--) {
			if(i == xLoc && j == yLoc) {
				return true;
			}
		}
		for(int i = this.xLocation - 1, j = this.yLocation + 1 ; i >= 0 && j <= size ; i--, j++) {
			if(i == xLoc && j == yLoc) {
				return true;
			}
		}
		return false;
	}
	
}