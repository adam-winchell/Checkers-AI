
public class Move 
{
	private Position startPosition;
	private Position passedOverDuringJumpPosition;
	private Position endPosition;
	private Move nextMove;
	
	public Move(Position startPosition, Position endPosition)
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		nextMove = null;
		this.passedOverDuringJumpPosition = null;
	}
	
	public Move(Position startPosition, Position endPosition, Position passedOverDuringJumpPosition)
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		nextMove = null;
		this.passedOverDuringJumpPosition = passedOverDuringJumpPosition;
	}

	
	
	public Position getPassedOverDuringJumpPosition() {
		return passedOverDuringJumpPosition;
	}



	public void setPassedOverDuringJumpPosition(
			Position passedOverDuringJumpPosition) {
		this.passedOverDuringJumpPosition = passedOverDuringJumpPosition;
	}



	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(Position endPosition) {
		this.endPosition = endPosition;
	}

	public Move getNextMove() {
		return nextMove;
	}

	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}
	
	public String toString()
	{
		return "Start: "+startPosition.toString()+", End: "+endPosition.toString();
	}
	
	
}
