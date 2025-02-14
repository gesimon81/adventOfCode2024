package day6;

import java.util.Objects;

public class Coordinate {
	private int coordinateX;
	
	private int coordinateY;
	
	public Coordinate(int coordinateX, int coordinateY) {
		this.setCoordinateX(coordinateX);
		this.setCoordinateY(coordinateY);
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	// Méthodes equals() et hashCode() pour permettre l'utilisation dans un ensemble (Set)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate coordToCompare = (Coordinate) o;
        return this.coordinateX == coordToCompare.getCoordinateX() && this.coordinateY == coordToCompare.getCoordinateY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.coordinateX, this.coordinateY);
    }

    @Override
    public String toString() {
        return "(" + this.coordinateX + "," + this.coordinateY + ")";
    }
}
