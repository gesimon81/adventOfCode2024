package day6;

public class Guard {
	private Coordinate guardPosition;
	
	public enum Orientation {UP, RIGHT, DOWN, LEFT}
	
	private Orientation guardOrientation;
	
	public Guard() {}
	
	public Guard(Coordinate position, Orientation orientation) {
		this.guardPosition = position;
		this.guardOrientation = orientation;
	}
	
	public Coordinate getGuardPosition() {
		return guardPosition;
	}

	public void setGuardPosition(Coordinate guardPosition) {
		this.guardPosition = guardPosition;
	}

	public Orientation getGuardOrientation() {
		return guardOrientation;
	}

	public void setGuardOrientation(Orientation guardOrientation) {
		this.guardOrientation = guardOrientation;
	}

	public void turnRight() {
		switch(this.guardOrientation) {
			case Orientation.UP:
				this.guardOrientation = Orientation.RIGHT;
				break;
			case Orientation.RIGHT:
				this.guardOrientation = Orientation.DOWN;
				break;
			case Orientation.DOWN:
				this.guardOrientation = Orientation.LEFT;
				break;
			case Orientation.LEFT:
				this.guardOrientation = Orientation.UP;
				break;
		}
	}
	
	public void moveForward() {
		switch(this.guardOrientation) {
			case Orientation.UP:
				this.guardPosition.setCoordinateY(this.guardPosition.getCoordinateY() - 1);
				break;
			case Orientation.RIGHT:
				this.guardPosition.setCoordinateX(this.guardPosition.getCoordinateX() + 1);
				break;
			case Orientation.DOWN:
				this.guardPosition.setCoordinateY(this.guardPosition.getCoordinateY() + 1);
				break;
			case Orientation.LEFT:
				this.guardPosition.setCoordinateX(this.guardPosition.getCoordinateX() - 1);
				break;
		}
	}
	
	public Coordinate getCoordinateForward() {
		Coordinate coordinateForward = null;
		
		switch(this.guardOrientation) {
			case Orientation.UP:
				coordinateForward = new Coordinate(this.guardPosition.getCoordinateX(), this.guardPosition.getCoordinateY() - 1);
				break;
			case Orientation.RIGHT:
				coordinateForward = new Coordinate(this.guardPosition.getCoordinateX() + 1, this.guardPosition.getCoordinateY());
				break;
			case Orientation.DOWN:
				coordinateForward = new Coordinate(this.guardPosition.getCoordinateX(), this.guardPosition.getCoordinateY() + 1);
				break;
			case Orientation.LEFT:
				coordinateForward = new Coordinate(this.guardPosition.getCoordinateX() - 1, this.guardPosition.getCoordinateY());
				break;
		}
		
		return coordinateForward;
	}
}
