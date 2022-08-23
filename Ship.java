
public class Ship {
	private Point[] pointsOfShip;

	public Ship(int length) {
		pointsOfShip = new Point[length];
		for (int i = 0; i < pointsOfShip.length; i++) {
			pointsOfShip[i] = new Point();
		}
	}

	public Point getPoint(int index) {
		return pointsOfShip[index];
	}

	public boolean isOnBoard (Point point, String orient) {
		setValues(point, orient);
		return pointsOfShip[pointsOfShip.length - 1].isValid();
	}

	//checks if it is intersected with another ship
	public boolean isIntersect (Ship ship) {
		for (int i = 0; i < pointsOfShip.length; i++) {
			for (int j = 0; j < ship.pointsOfShip.length; j++) {
				if (pointsOfShip[i].getRow() == ship.pointsOfShip[j].getRow() && pointsOfShip[i].getCol() == ship.pointsOfShip[j].getCol()) {
					return true;
				}
			}
		}
		return false;
	}

	//set points of the ship
	public void setValues(Point point, String orient) {
		int row = point.getRow();
		int col = point.getCol();

		for (int i = 0; i < pointsOfShip.length; i++) {
			if(orient.equals("R")) {
				pointsOfShip[i].setCol(col + i);
			}
			else if(orient.equals("L")) {
				pointsOfShip[i].setCol(col - i);
			}
			else if(orient.equals("U")) {
				pointsOfShip[i].setRow(row - i);
			}
			else {
				pointsOfShip[i].setRow(row + i);
			}

			if (orient.equals("R") || orient.equals("L")) {
				pointsOfShip[i].setRow(row);
			}
			else {
				pointsOfShip[i].setCol(col);
			}
		}
	}

	//checks if a ship has been hit
	public boolean hitShip(int row, int col) {
		for (int i = 0; i < pointsOfShip.length; i++) {
			if (pointsOfShip[i].hitPoint(row, col)) {
				pointsOfShip[i].setStatus(true);
				return true;
			}
		}
		return false;
	}

	//check to see if a ship has sunk by checking the status of all the points of the ship
	public boolean checkPointStatus() {
		for (int i = 0; i < pointsOfShip.length; i++) {
			if (!pointsOfShip[i].getStatus()) {
				return false;
			}
		}
		return true;
	}

	public int getLength() {
		return pointsOfShip.length;
	}

}
