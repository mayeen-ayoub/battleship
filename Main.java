import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		User player1 = new User();
		User player2 = new User();

		System.out.println("-------Welcome to Battleship-------");

		//instructions
		System.out.println("Type i for instructions if you want them! Type any other letter to proceed.");
		if (s.nextLine().equalsIgnoreCase("i")) {
			System.out.println("Two players will play the game");
			System.out.println("Both players will get to pick where they want to place their ships on the board.");
			System.out.println("First, you enter a starter point (letter between A and J, then a number between 1 and 10)");
			System.out.println("Then you enter an orientation to decide where the ship will end up.");
			System.out.println("After both players have placed their ships, the users then have to choose where to strike by choosing a letter than a number");
			System.out.println("After striking you will see a M or H");
			System.out.println("M means you have missed, while H means you have hit");
			System.out.println("The computer will keep track of the game's progress and who wins the game in the end");
			System.out.println("Good Luck and Have Fun!");
			System.out.println("--------");
		}

		//start game
		//Player 1
		System.out.println("\nPlayer 1, place the coordinates for your ship. Player 2, step away from the computer.");
		player1.initialiseUser();

		System.out.println("\n---------------------");
		System.out.println("*\n*\n*\n*\n*\n*\n*");
		System.out.println("---------------------\n");

		//Player 2
		System.out.println("Player 2, place the coordinates for your ship. Player 1, step away from the computer.");
		player2.initialiseUser();

		// keep striking as long as the game isn't over
		while (!player1.gameOver() && !player2.gameOver()) {
			System.out.println();
			System.out.print("\n---Player 1---");
			player1.promptStrike(player2);

			System.out.print("\n---Player 2---");
			player2.promptStrike(player1);
		}

		//when game is over check winner
		System.out.println("-----------");
		if (player1.gameOver()) {
			System.out.println("Player 2 wins!");
		}
		else {
			System.out.println("Player 1 wins!");
		}
	}

	public static Point enterPoint(String message) {
		Scanner s = new Scanner(System.in);
		Point point = new Point();
		String placement = null;

		do {
			System.out.println(message);
			placement = s.nextLine();
			point = getPoint(placement);
			if (point == null) {
				System.out.println("--You typed an invalid starter point--");
			}
		} while (point == null);
		return point;
	}

	//checks validity of point and changes the string into two int values
	private static Point getPoint(String placement) {
		int row = 0;
		int col = 0;
		Point point = new Point();

		if (placement == null || placement.length() < 2) {
			return null;
		}

		row = rowToNum(placement.toUpperCase().charAt(0));
		if (row == -1) {
			return null;
		}
		point.setRow(row);

		try {
			col = Integer.parseInt(placement.substring(1));
			if (col > 10 || col < 1) {
				return null;
			}
			point.setCol(col - 1);
		}
		catch (NumberFormatException e) {
			return null;
		}

		return point;
	}

	private static int rowToNum (char row) {
		if (row < 'A' || row > 'J') {
			return -1;
		}
		return row - 'A';
	}
}
