import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;


public class DrawBoard extends PApplet implements Constants
{
	
	Board game = new Board();
	
	
	public void setup()
	{
		size(SCREEN_SIZE, SCREEN_SIZE);
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
				rect(i*width/ENGLISH_DRAUGHT_BOARD_SIZE, j*width/ENGLISH_DRAUGHT_BOARD_SIZE, width/ENGLISH_DRAUGHT_BOARD_SIZE, width/ENGLISH_DRAUGHT_BOARD_SIZE);
			}
		}
	}
	public void drawPieces()
	{
		ArrayList<Piece> pieces = ;

		for(int i = 0; i < pieces.size(); i += 3)
		{
			if(//is white)
			{
				
				fill(255);//white
				
			}
			else
			{
				fill(0);//black
			}
			
			ellipse(

			if((int)pieces.get(i) > 2)
			{
				//its a king
				fill(255,0,0);
				textSize(26);
				text('K',);
			}
		}
	}
}
