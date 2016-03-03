import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;


public class DrawBoard extends PApplet implements Constants
{
	
	Board game = new Board();
	int squareSize;
	
	
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
	
	public static void p(String text)
	{
		System.out.println(text);
	}
}
