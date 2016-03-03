import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Board 
{
	private HashMap<String, Piece> board;
	private Stack<HashMap<String, Piece>> oldBoards = new Stack<HashMap<String, Piece>>();
	private int whiteCount;
	private int blackCount;
	private final int ENGLISH_DRAUGHT_PIECE_COUNT = 12;
	private final int ENGLISH_DRAUGHT_BOARD_SIZE = 8;
	private final int ENGLISH_DRAUGHT_BLACK_KING_HOME = 0;
	private final int ENGLISH_DRAUGHT_WHITE_KING_HOME = ENGLISH_DRAUGHT_BOARD_SIZE -1;
	private final int ENGLISH_DRAUGHT_NUMBER_OF_ROWS_FOR_PIECES = 3;
	private final int ENGLISH_DRAUGHT_WHITE_OFFSET = ENGLISH_DRAUGHT_BOARD_SIZE - ENGLISH_DRAUGHT_NUMBER_OF_ROWS_FOR_PIECES;
	private final boolean WHITE =true;
	private final int LEFT = -1;
	private final int RIGHT = 1;
	private final int UP = -1;
	private final int  DOWN= 1;
	
	public Piece getPieceTesting(int x,int y)
	{
		return board.get(makeCords(x,y));
	}
	
	public void insertPieceTesting(int x, int y, boolean isWhite)
	{
		board.put(makeCords(x,y), new Piece(isWhite,new Position(x,y)));
	}
	
	public void deleteTesting(int x, int y)
	{
		board.remove(makeCords(x,y));
	}
	
	/**
	 * constructor for board
	 */
	public Board()
	{
		board = new HashMap<String, Piece>();
		insertBlackPieces(board);
		insertWhitePieces(board);
		whiteCount = ENGLISH_DRAUGHT_PIECE_COUNT;
		blackCount = ENGLISH_DRAUGHT_PIECE_COUNT;
	}
	
	/**
	 * Makes string coordinate that is used to find pieces in the hasmap
	 * @param x
	 * @param y
	 * @return string coordinates
	 */
	private String makeCords(int x, int y)
	{
		return "("+x+","+y+")";
	}
	
	/**
	 * inserts white pieces into the board
	 * @param board
	 */
	private void insertWhitePieces(HashMap<String, Piece> board)
	{
		for(int j=0;j<ENGLISH_DRAUGHT_NUMBER_OF_ROWS_FOR_PIECES;j++)
		{
			for(int i=0;i<ENGLISH_DRAUGHT_BOARD_SIZE;i++)
			{
			
				if(j%2 == 0 && i%2 != 0)
				{
					board.put(makeCords(j,i), new Piece(true,new Position(j,i)));//j is the row number so its the x value
				}
				if(j%2 != 0 && i%2 == 0)
				{
					board.put(makeCords(j,i), new Piece(true,new Position(j,i)));
				}
			}
		}
	}
	
	/**
	 * inserts black pieces into the board
	 * @param board
	 */
	private void insertBlackPieces(HashMap<String, Piece> board)
	{
		for(int j=ENGLISH_DRAUGHT_WHITE_OFFSET;j<ENGLISH_DRAUGHT_BOARD_SIZE;j++)
		{
			for(int i=0;i<ENGLISH_DRAUGHT_BOARD_SIZE;i++)
			{
			
				if(j%2 != 0 && i%2 == 0)
				{
					board.put(makeCords(j,i), new Piece(false,new Position(j,i)));//j is the row number so its the x value
				}
				if(j%2 == 0 && i%2 != 0)
				{
					board.put(makeCords(j,i), new Piece(false,new Position(j,i)));
				}
			}
		}
	}
	
	/**
	 * calls the proper method for the given piece
	 * @param piece
	 * @return arraylist of all the moves for the given piece
	 */
	public ArrayList<Move> getMoves(Piece piece)
	{
		ArrayList<Move> moves = null;
		
		if(piece.getIsKing())
		{
			moves = getKingMoves(piece);
		}
		else if (piece.getIsWhite())
		{
			moves = getWhiteMoves(piece);
		}
		else
		{
			moves = getBlackMoves(piece);
		}
		return moves;
	}
	
	/**
	 * calls the proper methods to determine the potential moves for a king and collects the results
	 * @param piece
	 * @return a list of all the potential moves
	 */
	private ArrayList<Move> getKingMoves(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,move(piece,DOWN,LEFT));
		combineLists(result,move(piece,DOWN,RIGHT));
		combineLists(result,move(piece,UP,LEFT));
		combineLists(result,move(piece,UP,RIGHT));

		return result;
	}
	
	/**
	 * Combines two lists
	 * @param mainList
	 * @param otherList
	 */
	private void combineLists(ArrayList<Move> mainList, ArrayList<Move> otherList)
	{
		for(Move m: otherList)
		{
			if(m != null)
			{
				mainList.add(m);
			}
		}
	}
	/**
	 * calls the proper methods to determine the potential moves for a white piece and collects the results
	 * @param piece
	 * @return a list of all the potential moves
	 */
	private ArrayList<Move> getWhiteMoves(Piece piece)
	{	
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,move(piece,DOWN,LEFT));
		combineLists(result,move(piece,DOWN,RIGHT));
		
		return result;
	}
	
	/**
	 * calls the proper methods to determine the potential moves for a black piece and collects the results
	 * @param piece
	 * @return a list of all the potential moves
	 */
	private ArrayList<Move> getBlackMoves(Piece piece)
	{		
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,move(piece,UP,LEFT));
		combineLists(result,move(piece,UP,RIGHT));
		
		return result;
	}
	/**
	 * Decides which method to all to determine jumps
	 * @param piece
	 * @return arraylist with the moves that involve jumping
	 */
	private ArrayList<Move> getMovesJumping(Piece piece)
	{
		ArrayList<Move> moves = null;
		
		if(piece.getIsKing())
		{
			moves = getKingMovesJumping(piece);
		}
		else if (piece.getIsWhite())
		{
			moves = getWhiteMovesJumping(piece);
		}
		else
		{
			moves = getBlackMovesJumping(piece);
		}
		
		return moves;
	}
	
	/**
	 * quick method for printing
	 * @param text
	 */
	private void p(String text)
	{
		System.out.println(text);
	}
	/**
	 * Calls proper jumping methods for a king piece and collects the results
	 */
	private ArrayList<Move> getKingMovesJumping(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,moveJumping(piece,DOWN,LEFT));
		combineLists(result,moveJumping(piece,DOWN,RIGHT));
		combineLists(result,moveJumping(piece,UP,LEFT));
		combineLists(result,moveJumping(piece,UP,RIGHT));
		
		return result;
	}
	/**
	 * Calls proper jumping methods for a white piece and collects the results
	 */
	private ArrayList<Move> getWhiteMovesJumping(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,moveJumping(piece,DOWN,LEFT));
		combineLists(result,moveJumping(piece,DOWN,RIGHT));
		
		return result;
	}
	
	/**
	 * Calls proper jumping methods for a black piece and collects the results
	 */
	private ArrayList<Move> getBlackMovesJumping(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,moveJumping(piece,UP,LEFT));
		combineLists(result,moveJumping(piece,UP,RIGHT));
		
		return result;
	}
	
	/**
	 * Called when the piece in question is already in a jump and looking for more jumps
	 * @param piece
	 * @param rowDirectionMove
	 * @param columnDirectionMove
	 * @return arraylist of all moves that are a part of a jump
	 */
	private ArrayList<Move> moveJumping(Piece piece, int rowDirectionMove, int columnDirectionMove)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		Position position = piece.getPosition();
		Position destinationPosition = new Position(position.getX()+rowDirectionMove, position.getY()+columnDirectionMove);
		
		if(destinationPosition.isValid())
		{
			Piece destinationPiece = board.get(destinationPosition.getCords());
			
			if(destinationPiece != null && destinationPiece.getIsWhite() != piece.getIsWhite())
			{
				//we have the potential to jump
				Position jumpPosition = new Position(position.getX()+2*rowDirectionMove, position.getY()+2*columnDirectionMove);
				if(jumpPosition.isValid())
				{
					Piece jumpPiece = board.get(jumpPosition.getCords());
					if(jumpPiece == null)
					{
						//we can jump
						Move move = new Move(position,jumpPosition,destinationPosition);
						
						//setting up a temporary piece below to use in the next set of calls
						Piece temp = new Piece(piece.getIsWhite(),jumpPosition,piece.getIsKing());
						
						addPieceTemporary(jumpPosition, temp); //to occupy the space so jumping bug is squashed

						ArrayList<Move> potentialJumps = getMovesJumping(temp);
						
						for(Move m: potentialJumps)
						{
							Move temporary = move;
							temporary.setNextMove(m);
							moves.add(temporary); 
						}
						if(potentialJumps.isEmpty())
						{
							moves.add(move);
						}
						
						removeTemporaryPiece(jumpPosition); //removed temporary piece
					}
				}
				
			}
		}
		
		return moves;
	}
	/*
	 * UP means a smaller value in the array,
	 * the x cordinate of the position is the row number
	 */
	
	/**
	 * Finds all potential moves for the given piece, will call getMovesJumping if a jump is availiable
	 * @param piece is the piece being worked on
	 * @param rowDirectionMove
	 * @param columnDirectionMove
	 * @return an arraylist of all potential moves
	 */
	private ArrayList<Move> move(Piece piece, int rowDirectionMove, int columnDirectionMove)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		Position position = piece.getPosition();
		Position destinationPosition = new Position(position.getX()+rowDirectionMove, position.getY()+columnDirectionMove);
		
		if(destinationPosition.isValid())
		{
			Piece destinationPiece = board.get(destinationPosition.getCords());
			
			if(destinationPiece == null)
			{
				//piece can move here
				moves.add(new Move(position,destinationPosition));
			}
			else if(destinationPiece.getIsWhite() != piece.getIsWhite())
			{
				//we have the potential to jump
				Position jumpPosition = new Position(position.getX()+2*rowDirectionMove, position.getY()+2*columnDirectionMove);
				if(jumpPosition.isValid())
				{
					Piece jumpPiece = board.get(jumpPosition.getCords());
					if(jumpPiece == null)
					{
						//we can jump
						Move move = new Move(position,jumpPosition,destinationPosition);
						
						//setting up a temporary piece below to use in the next set of calls
						Piece temp = new Piece(piece.getIsWhite(),jumpPosition,piece.getIsKing());
						
						addPieceTemporary(jumpPosition, temp); //to occupy the space so jumping bug is squashed
						
						ArrayList<Move> potentialJumps = getMovesJumping(temp);
						
						for(Move m: potentialJumps)
						{
							Move temporary = move;
							temporary.setNextMove(m);
							moves.add(temporary); 
						}
						if(potentialJumps.isEmpty())
						{
							moves.add(move);
						}
						
						removeTemporaryPiece(jumpPosition); //removed temporary piece
					}
				}
				
			}
		}
		
		return moves;
	}
	
	/**
	 * Used to add pieces temporarily to the board so the jumping methods are not called forever
	 * @param pos is the position of the piece
	 * @param piece
	 */
	private void addPieceTemporary(Position pos, Piece piece)
	{
		board.put(makeCords(pos.getX(),pos.getY()), piece);
	}
	
	/**
	 * Deletes the temporary piece used during the jumping methods
	 * @param pos is the position of the piece to delete
	 */
	private void removeTemporaryPiece(Position pos)
	{
		board.remove(makeCords(pos.getX(),pos.getY()));
	}
	
	/**
	 * Returns a string that represents the board
	 * - is empty square
	 * 0 is white
	 * X is black
	 */
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		
		for(int j=0;j<ENGLISH_DRAUGHT_BOARD_SIZE;j++)
		{
			for(int i=0;i<ENGLISH_DRAUGHT_BOARD_SIZE;i++)
			{
				Piece temp = board.get(makeCords(j,i));
				
				if(temp == null)
				{
					buff.append(" - ");
				}
				else if(temp.getIsWhite())
				{
					buff.append(" O ");
				}
				else
				{
					buff.append(" X ");
				}
				
				if(i==7)
				{
					buff.append("\n");
				}
			}
		}
		
		return buff.toString();
	}
	
	/**
	 * Copies the board and returns it
	 * @return a new copy of the board
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Piece> makeCopy()
	{
		HashMap<String, Piece> result = new HashMap<String, Piece>();
		result = (HashMap<String, Piece>) board.clone();
		return result;
	}
	
	/**
	 * Makes the specified move.  As of now if a piece becomes a king during its movement, that is not taken into account when determing said movement
	 * @param m
	 */
	public void makeMove(Move m)
	{
		oldBoards.push(makeCopy());
		
		Piece temp = board.get(m.getStartPosition().getCords());
		do
		{
			board.remove(m.getStartPosition().getCords());//remove piece from starting position
			if(m.getPassedOverDuringJumpPosition() != null)
			{
				board.remove(m.getPassedOverDuringJumpPosition().getCords());//remove piece it jumped
			}
			temp.setPosition(m.getEndPosition());
			isPieceKing(temp);
			board.put(m.getEndPosition().getCords(), temp);
	
		}while(m.getNextMove() != null);
	}
	
	/**
	 * Upgrades a piece to a king if it makes it to the proper place
	 * @param piece
	 */
	public void isPieceKing(Piece piece)
	{
		if(piece.getIsWhite() && piece.getPosition().getX() == ENGLISH_DRAUGHT_WHITE_KING_HOME)
		{
			piece.setIsKing(true);
		}
		else if(!(piece.getIsWhite()) && piece.getPosition().getX() == ENGLISH_DRAUGHT_BLACK_KING_HOME)
		{
			piece.setIsKing(true);
		}
	}
	
	public void undoMove()
	{
		board = oldBoards.pop();
	}
}
