import java.util.Scanner;
import java.io.*;
/**
 * HW#3 This program stores a chessboard as a linked list of chess pieces, impletements moves
 * and determines if king is under attack.
 * (ankho@ucsc.edu)
 */
public class ChessBoard {
	public static int size; // size of the board
	public static ChessPiece head; // linkedlist of chess pieces
	/**
	 * Method to set the size of the board and set the head of the linked list
	 * @param a desired size of board
	 */
	public static void setBoard(int a) {
		size = a;
		head = null;
	}
	/**
	 * Method to find what piece is on the queried location
	 * @param xLoc query x location
	 * @param yLoc query y location
	 * @return null if empty, else, return the piece on the queried location
	 */
	public static ChessPiece find(int xLoc, int yLoc) {
		ChessPiece currentPiece = head;
		while (currentPiece != null) {
			if (currentPiece.xLocation == xLoc && currentPiece.yLocation == yLoc) {
				return currentPiece;
			}
			currentPiece = currentPiece.next;
		}
		return null;
	}
	/**
	 * Method to insert a chess piece into the linked list
	 * @param newPiece the new piece to be inserted
	 */
	public static void insert(ChessPiece newPiece) {
		newPiece.next = head;
		head = newPiece;
	}
	/**
	 * Method to delete a chess piece from the linked list
	 * @param delPiece The piece to be deleted
	 */
	public static void delete(ChessPiece delPiece) {
		ChessPiece prev = null;
		ChessPiece curr = head;
		while(curr != delPiece) {
			prev = curr;
			curr = curr.next;
		}
		if(prev == null) {
			head = head.next;
		} else {
			prev.next = curr.next;
		}
	}
	/**
	 * Method to turn the commandsd into pieces to store in the linked list
	 * @param input an array of tokenized commands
	 */
	public static void createPieceList(String[] input) {
		for (int i = 1; i <= input.length - 1; i += 3) {
			if (input[i].equals("B")) {
				Bishop piece = new Bishop(false, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			if (input[i].equals("b")) {
				Bishop piece = new Bishop(true, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			////////////////////////////////////////////////////////////////////////////////////////
			if (input[i].equals("R")) {
				Rook piece = new Rook(false, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			if (input[i].equals("r")) {
				Rook piece = new Rook(true, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			////////////////////////////////////////////////////////////////////////////////////////
			if (input[i].equals("K")) {
				King piece = new King(false, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			if (input[i].equals("k")) {
				King piece = new King(true, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			////////////////////////////////////////////////////////////////////////////////////////
			if (input[i].equals("Q")) {
				Queen piece = new Queen(false, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			if (input[i].equals("q")) {
				Queen piece = new Queen(true, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			////////////////////////////////////////////////////////////////////////////////////////
			if (input[i].equals("N")) {
				Knight piece = new Knight(false, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			if (input[i].equals("n")) {
				Knight piece = new Knight(true, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			////////////////////////////////////////////////////////////////////////////////////////
			if (input[i].equals("P")) {
				Pawn piece = new Pawn(false, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			if (input[i].equals("p")) {
				Pawn piece = new Pawn(true, Integer.parseInt(input[i + 1]), Integer.parseInt(input[i + 2]));
				insert(piece);
			}
			////////////////////////////////////////////////////////////////////////////////////////
		}
	}
	/**
	 * Method to determine if a move can be made. If possible, make a move.
	 * @param curr the chess piece to be moved
	 * @param xLoc the desired x location
	 * @param yLoc the desired y location
	 * @return true/false if move has been made
	 */
	public static boolean tryMove(ChessPiece curr, int xLoc, int yLoc) {
		ChessPiece target = find(xLoc,yLoc);
		if(target == null && curr.canMoveTo(size, xLoc, yLoc)) {
			curr.makeMove(xLoc,yLoc);
			return true;
		} else if(target!=null){
			if(target.color != curr.color) {
				if(curr.canMoveTo(size, xLoc, yLoc)) {
					delete(target);
					curr.makeMove(xLoc,yLoc);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Method to find the kings in the linked list
	 * @param query the piece to be found
	 * @return the king
	 */
	public static ChessPiece findKings(String query) {
		ChessPiece currentPiece = head;
		while (currentPiece != null) {
			if (currentPiece.pieceString.equals(query)) {
				return currentPiece;
			}
			currentPiece = currentPiece.next;
		}
		return null;
	}	
	/**
	 * Method to determine if a king is in check
	 * @param King The king chess piece
	 * @return true/false if the king is in check
	 */
	public static boolean determineCheck(ChessPiece King) {
		ChessPiece currentPiece = head;
		while (currentPiece != null) {
			if(currentPiece.color != King.color && currentPiece.canMoveTo(size ,King.xLocation , King.yLocation)) {
				return true;
			}
			currentPiece = currentPiece.next;
		}
		return false;
	}
	/**
	 * Method to determine checkmate
	 * @param King The king chesspiece
	 * @return true/false for checkmate
	 */
	public static boolean determineMate(ChessPiece King) {	
		int originalX = King.xLocation;
		int originalY = King.yLocation;
		
		if(!determineCheck(King)) {
			return false;
		}
		
		if(originalX + 1 > 0 && originalX + 1 <= size && originalY + 1 > 0 && originalY + 1 <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX + 1, originalY + 1)) {
				if(find(originalX + 1 , originalY + 1) != null) {
					if(find(originalX + 1 , originalY + 1).color != King.color ) {
						deletedPiece = find(originalX + 1 , originalY + 1);
						delete(find(originalX + 1 , originalY + 1));
						deleted = true;
					}
				}
				King.makeMove(originalX + 1, originalY + 1);
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		if(originalX - 1 > 0 && originalX - 1 <= size && originalY - 1 > 0 && originalY - 1 <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX - 1, originalY - 1)) {
				if(find(originalX - 1 , originalY - 1) != null) {
					if(find(originalX - 1 , originalY - 1).color != King.color ) {
						deletedPiece = find(originalX - 1 , originalY - 1);
						delete(find(originalX - 1 , originalY - 1));
						deleted = true;
					}
				}
				King.makeMove(originalX - 1, originalY - 1);
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		if(originalX - 1 > 0 && originalX - 1 <= size && originalY + 1 > 0 && originalY + 1 <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX - 1, originalY + 1)) {
				if(find(originalX - 1 , originalY + 1) != null) {
					if(find(originalX - 1 , originalY + 1).color != King.color ) {
						deletedPiece = find(originalX - 1 , originalY + 1);
						delete(find(originalX - 1 , originalY + 1));
						deleted = true;
					}
				}
				King.makeMove(originalX - 1, originalY + 1);
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		if(originalX + 1 > 0 && originalX + 1 <= size && originalY - 1 > 0 && originalY - 1 <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX + 1, originalY - 1)) {
				if(find(originalX + 1 , originalY - 1) != null) {
					if(find(originalX + 1 , originalY - 1).color != King.color ) {
						deletedPiece = find(originalX + 1 , originalY - 1);
						delete(find(originalX + 1 , originalY - 1));
						deleted = true;
					}
				}
				King.makeMove(originalX + 1, originalY - 1);
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		if(originalX  > 0 && originalX  <= size && originalY + 1 > 0 && originalY + 1 <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX , originalY + 1)) {
				if(find(originalX , originalY + 1) != null) {
					if(find(originalX , originalY + 1).color != King.color ) {
						deletedPiece = find(originalX , originalY + 1);
						delete(find(originalX , originalY + 1));
						deleted = true;
					}
				}
				King.makeMove(originalX , originalY + 1);
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		if(originalX  > 0 && originalX  <= size && originalY - 1 > 0 && originalY - 1 <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX , originalY - 1)) {
				if(find(originalX , originalY - 1) != null) {
					if(find(originalX , originalY - 1).color != King.color ) {
						deletedPiece = find(originalX , originalY - 1);
						delete(find(originalX , originalY - 1));
						deleted = true;
					}
				}
				King.makeMove(originalX , originalY - 1);
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		
		if(originalX + 1 > 0 && originalX + 1 <= size && originalY  > 0 && originalY  <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX + 1 , originalY )) {
				if(find(originalX + 1 , originalY) != null) {
					if(find(originalX + 1 , originalY).color != King.color ) {
						deletedPiece = find(originalX + 1 , originalY);
						delete(find(originalX + 1 , originalY));
						deleted = true;
					}
				}
				King.makeMove(originalX + 1, originalY );
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		if(originalX - 1 > 0 && originalX - 1 <= size && originalY  > 0 && originalY  <= size ) {
			boolean deleted = false;
			ChessPiece deletedPiece = null;
			if(King.canMoveTo(size, originalX - 1, originalY )) {
				if(find(originalX - 1 , originalY ) != null) {
					if(find(originalX - 1 , originalY ).color != King.color ) {
						deletedPiece = find(originalX - 1 , originalY);
						delete(find(originalX - 1 , originalY));
						deleted = true;
					}
				}
				King.makeMove(originalX - 1, originalY );
				if(!determineCheck(King)) {
					return false;
				} else {
					King.xLocation = originalX;
					King.yLocation = originalY;
					if(deleted) {
						insert(deletedPiece);
					}
				}
			}
		}
		
		King.xLocation = originalX;
		King.yLocation = originalY;
		return true;
	}
	/** Main Method for ChessBoard */
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		Scanner in = new Scanner(file);
		PrintWriter out = new PrintWriter("analysis.txt");
		while(in.hasNextLine()) {
			String input = in.nextLine().trim();
			String moves = in.nextLine().trim();
			String[] list = input.split("\\s+");
			String[] movesList = moves.split("\\s+");
			setBoard(Integer.parseInt((list[0])));
			createPieceList(list);
			ChessPiece whiteKing = findKings("k");
			ChessPiece blackKing = findKings("K");
			boolean whiteCheck = determineCheck(whiteKing);
			boolean blackCheck = determineCheck(blackKing);
			boolean whiteMate = determineMate(whiteKing);
			boolean blackMate = determineMate(blackKing);
			head = null;
			createPieceList(list);
			for(int i = 0 ; i < movesList.length ; i += 4) {
				ChessPiece currPiece = find(Integer.parseInt(movesList[i]) , Integer.parseInt(movesList[i+1]));
				if(tryMove(currPiece , Integer.parseInt(movesList[i+2]) , Integer.parseInt(movesList[i+3]))) {
					out.print("Valid ");
				} else {
					out.print("Invalid ");
					break;
				}
			}
			out.println();
			if(whiteMate) {
				out.print("White checkmated ");
			} else if(whiteCheck){
				out.print("White in check ");
			}
			if(blackMate && !whiteMate) {
				out.print("Black checkmated ");
			} else if(blackCheck && !whiteMate){
				out.print("Black in check ");
			}
			if(!whiteMate && !whiteCheck && !blackMate && !blackCheck) {
				out.print("All kings safe");
			}
			out.println();
		}
	      in.close();
	      out.close();
	}
}