public class Pawn extends ChessPiece {
	
	public Pawn(boolean color, int xLocation, int yLocation) {
		super(color, xLocation, yLocation);
		if(color) {
			pieceString = "p";
		} else {
			pieceString = "P";
		}
	}
	
}