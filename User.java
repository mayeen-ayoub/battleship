import java.util.Scanner;

public class User {
	private Ship[] ships = new Ship[5];
	private int[][] board = new int[10][10];

	public User () {
		ships[0] = new Ship(5);
		ships[1] = new Ship(4);
		ships[2] = new Ship(3);
		ships[3] = new Ship(3);
		ships[4] = new Ship(2);
	}

	public void initialiseUser() {
		Point point = new Point();
		String orient = null;
		Scanner s = new Scanner(System.in);
		boolean isValidShip = false;

		//prompts for all 5 ships
		for (int i = 0; i < 5; i++) {
			do {
				point = Main.enterPoint("Enter a point to place the " + shipName(i) + " (" + this.getShipLength(i) + " long). (eg. A10)");
				System.out.println("What orientation do you want your ship to be facing in relation to your chosen point?");
				System.out.println("(L for left, R for right, U for up, and D for down.)");
				orient = s.nextLine().toUpperCase();
				isValidShip = this.isValid(i, point, orient) && orient.length() == 1;
				if (!(orient.equals("R") || orient.equals("L") || orient.equals("U") || orient.equals("D"))) {
					System.out.println("--You typed an invalid orientation letter--");
				}
				else if (!isValidShip) {
					System.out.println("--Wrong Placement--");
				}
			} while(!(orient.equals("R") || orient.equals("L") || orient.equals("U") || orient.equals("D")) || !isValidShip);
		}
		intialiseBoard();
	}

	public void promptStrike(User otherPlayer) {
		Point point = new Point();
		int hitIndex = 0;

		System.out.println();
		point = Main.enterPoint("Pick a point to strike the other player's board");

		//check if the ship has been hit
		hitIndex = otherPlayer.hitShips(point.getRow(), point.getCol());
		if (hitIndex != -1) {
			this.setBoardPoint(point, 2);
			//check if the ship has sunk
			if (otherPlayer.checkShipStatus(hitIndex)) {
				System.out.println("You have sunk their " + otherPlayer.shipName(hitIndex));
			}
		}
		else {
			this.setBoardPoint(point, 3);
		}
		this.printBoard();
	}

	private Point getShipPoint(int index, int pointIndex) {
		return ships[index].getPoint(pointIndex);
	}

	private int getShipLength(int index) {
		return ships[index].getLength();
	}

	private String shipName (int index) {
		if (index == 0) {
			return "Carrier";
		}
		if (index == 1) {
			return "Battleship";
		}
		if (index == 2) {
			return "Cruiser";
		}
		if (index == 3) {
			return "Submarine";
		}
		return "Destroyer";
	}

	//check validity for each ship
	private boolean isValid(int index, Point point, String orient) {
		if (ships[index].isOnBoard(point, orient)) {
			for (int i = 0; i < index; i++) {
				if (ships[index].isIntersect(ships[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	//check if point hit the ship
	private int hitShips(int row, int col) {
		for(int i = 0; i < ships.length; i++) {
			if (ships[i].hitShip(row, col)) {
				return i;
			}
		}
		return -1;
	}

	//check to see if ship has sunk
	private boolean checkShipStatus(int index) {
		return ships[index].checkPointStatus();
	}

	//checks if game is over
	public boolean gameOver() {
		for (int i = 0; i < 5; i++) {
			if (!checkShipStatus(i)) {
				return false;
			}
		}
		return true;
	}

	//board methods
	private void intialiseBoard() {
		//set the value for the board
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < getShipLength(i); j++) {
				setBoardPoint(getShipPoint(i, j), 1);
			}
		}
		printBoard();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < getShipLength(i); j++) {
				setBoardPoint(getShipPoint(i, j), 0);
			}
		}
	}

	private void setBoardPoint(Point point, int status) {
		board[point.getRow()][point.getCol()] = status;
	}

	private void printBoard() {
		System.out.print("  ");

		for (int j = 0; j < board[0].length; j++) {
			System.out.print(j + 1 + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print((char)('A' + i) + " ");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(getShipChar(board[i][j]) + " ");
			}
			System.out.println();
		}
	}

	private char getShipChar(int i) {
		if (i == 1) {
			return 'S';
		}
		if (i == 2) {
			return 'H';
		}
		if (i == 3) {
			return 'M';
		}
		return '0';
	}
}
