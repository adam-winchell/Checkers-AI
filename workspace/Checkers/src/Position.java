
public class Position {
	private int x;
	private int y;
	private final int ENGLISH_DRAUGHT_BOARD_SIZE = 8;
	private boolean isValid =true;
	
	public Position(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x < ENGLISH_DRAUGHT_BOARD_SIZE && x > -1)
		{
			this.x = x;
		}
		else
		{
			System.out.println("invalid x position: "+x);
			isValid = false;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(y < ENGLISH_DRAUGHT_BOARD_SIZE && y > -1)
		{
			this.y = y;
		}
		else
		{
			System.out.println("invalid y position: "+y);
			isValid = false;
		}
	}
	

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public String getCords()
	{
		return "("+x+","+y+")";
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
	
}
