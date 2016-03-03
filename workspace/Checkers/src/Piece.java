
public class Piece 
{
	private boolean isWhite;
	private boolean isKing;
	private Position position;
	
	public Piece(Boolean isWhite, Position position)
	{
		this.isWhite = isWhite;
		this.position = position;
	}

	
	
	public Position getPosition() {
		return position;
	}
	
	public String getCords()
	{
		return position.getCords();
	}



	public void setPosition(Position position) {
		this.position = position;
	}



	public boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}

	public boolean getIsKing() {
		return isKing;
	}

	public void setIsKing(Boolean isKing) {
		this.isKing = isKing;
	}



	@Override
	public String toString() {
		return "Piece [isWhite=" + isWhite + ", isKing=" + isKing
				+ ", position=" + position + "]";
	}
	
	
}
