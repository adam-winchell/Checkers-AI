
import java.util.ArrayList;


import java.util.Stack;

import processing.core.PApplet;


public class DrawBoard extends PApplet implements Constants
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Board game = new Board();
	int squareSize;
	boolean clicked = false;
	boolean clickedMouse = true;
	int xCordOfPieceToMove;
	int yCordOfPieceToMove;
	Move move;
	Player player = new Player();
	
	
	public void setup()
	{
		size(SCREEN_SIZE, SCREEN_SIZE);
		squareSize = width/ENGLISH_DRAUGHT_BOARD_SIZE;
	}
	
	public void draw()
	{
		drawGrid();
		drawPieces();
		noLoop();//draw will only be called when a move is made
	}
	
	public void drawGrid()
	{
		boolean flip = true;

		for(int i = 0; i < ENGLISH_DRAUGHT_BOARD_SIZE; i++)
		{
			for(int j = 0; j < ENGLISH_DRAUGHT_BOARD_SIZE; j++)
			{
				if(flip)
				{
					fill(250, 250, 210);
					flip = false;

					if(j == ENGLISH_DRAUGHT_BOARD_SIZE -1)
					{
						flip = true;
					}
				}
				else
				{
					fill(250, 10, 250);
					flip = true;

					if(j == ENGLISH_DRAUGHT_BOARD_SIZE -1)
					{
						flip = false;
					}
				}
				rect(i*squareSize, j*squareSize, squareSize, squareSize);
			}
		}
	}
	public void drawPieces()
	{
		ArrayList<Piece> pieces = game.getPieces();

		for(Piece p: pieces)
		{
			if(p.getIsWhite())
			{
				
				fill(255);//white
				
			}
			else
			{
				fill(0);//black
			}
			Position pos = p.getPosition();

			ellipse(pos.getY()*PIECE_OFFSET + squareSize/2,pos.getX()*PIECE_OFFSET + squareSize/2,PIECE_SIZE,PIECE_SIZE);

			if(p.getIsKing())
			{
				fill(255,0,0);//red
				textSize(26);
				text('K',1,1);
			}
		}
	}
	
	public void mousePressed()
	{
		int x = mouseX*ENGLISH_DRAUGHT_BOARD_SIZE/width;//gives the conversion to coordinates
		int y = mouseY*ENGLISH_DRAUGHT_BOARD_SIZE/width;


		if(!clicked)//they start
		{
			Move result = player.alphaBeta(game, 8,false,true);//CHANGE TO INCREASE/DECREASE DIFFICULTY
			game.makeMove(result);
			redraw();
			clicked = !clicked;
		}
		else if(clickedMouse)//piece to move
		{
			xCordOfPieceToMove = x;
			yCordOfPieceToMove = y;
			clickedMouse = !clickedMouse;
		}
		else//position to move to
		{
			move = new Move(new Position(yCordOfPieceToMove,xCordOfPieceToMove), new Position(y,x));//swapped x and y because of how the grid is drawn
			Piece piece = game.getPiece(yCordOfPieceToMove,xCordOfPieceToMove);
			if(piece != null)
			{
				ArrayList<Move> moves = game.getMoves(piece);
				makeMove:for(Move m: moves)
				{
					if(areMovesEqual(move, m))
					{
						game.makeMove(m);
						redraw();
						clicked = !clicked;
						break makeMove;	
					}
				}
				
			}
			clickedMouse = !clickedMouse;
		}
	}
	
	/**
	 * if the moves are equal returns true, otherwise false
	 * @param m1 a move
	 * @param m2 another move
	 * @return a boolean
	 */
	public boolean areMovesEqual(Move m1, Move m2)
	{
		while(m1 != null)
		{
			if(m1.getStartPosition().equals(m2.getStartPosition()) && m1.getEndPosition().equals(m2.getEndPosition()))
			{
				m1 = m1.getNextMove();
				m2 = m2.getNextMove();
			}
			else
			{
				return false;
			}
		}
		
		return true;
	}
	public static void p(String text)
	{
		System.out.println(text);
	}
}
