
public class Point {
	private int row;
	private int col;
	private boolean status;

	public Point() {
		row = 0;
		col = 0;
		status = false;
	}

	public Point(Point point) {
		col = point.getCol();
		row = point.getRow();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean hitPoint(int row, int col) {
		return this.row == row && this.col == col;
	}

	//check to make sure the ship does not go off the board by checking the last point of the ship
	public boolean isValid() {
		return col >= 0 && col <= 9 && row >= 0 && row <= 9;
	}
}

