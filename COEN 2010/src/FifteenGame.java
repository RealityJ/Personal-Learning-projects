import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.math.*;

/**
 * fifteenGame.java
 *
 * Author: Joaquin Castro-Calvo
 * 
 * COSC2100/COSC2010 HW1
 * 
 * Implements the Game of Fifteen (generalized to d x d).
 * 
 * Usage: java fifteen d
 * 
 * whereby the board's dimensions are to be d x d, where d must be in [MIN,MAX]
 */
public class FifteenGame {
	// board's minimal dimension
	public static int MIN = 3;
	// board's maximal dimension
	public static int MAX = 9;

	// board, whereby board[i][j] represents row i and column j
	private int board[][];

	// board's dimension
	private int d;

	// log
	String log = "log.txt";

	// delete existing log, if any, before first save
	boolean saved = false;
	BufferedWriter out;

	public FifteenGame(int d) throws IOException {
		this.d = d;
		board = new int[d][d];
		File f = new File(log);

		if (!saved) {
			f.delete();
			saved = true;
		}

		// open log
		out = new BufferedWriter(new FileWriter(log));
	}

	public void closeLog() throws IOException {
		// close log
		out.close();
	}

	/**
	 * Greets player.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void greet() throws InterruptedException, IOException {
		System.out.printf("GAME OF FIFTEEN\n");
		Thread.sleep(2000);
	}

	/**
	 * Initializes the game's board with tiles numbered 1 through d*d - 1,
	 * (i.e., fills board with values but does not actually print them), whereby
	 * board[i][j] represents row i and column j.
	 */
	public void init() {
		int n = d * d - 1;
		for (int r = 0; r < d; r++) {
			for (int c = 0; c < d; c++) {
				board[r][c] = n;
				n--;
			}

		}
		if (d % 2 == 0) {
			int t = board[d - 1][d - 3];
			board[d - 1][d - 3] = board[d - 1][d - 2];
			board[d - 1][d - 2] = t;
		}
	}

	/**
	 * Prints the board in its current state.
	 */
	public void draw() {
		int counter = 0;
		for (int i = 0; i <= board.length-1; i++){
			for(int j = 0; j <= board[i].length-1; j++){
				counter++;
				if(counter != d & board[i][j] <= 9){
					System.out.print(board[i][j] + " " + " ");
					
				}
				else if(counter != d & board [i][j] > 9){
					System.out.print(board[i][j] + " ");
				}
				else{
					System.out.println(board[i][j]);
					counter = 0;
				}
			}
		}

	}

	/**
	 * If tile borders empty space, moves tile and returns true, else returns
	 * false.
	 */
	public boolean move(int tile) {
		 int empty = 0;
		 boolean valid = false;
		 int placeOfI = 0;
		 int placeOfJ = 0;
		 int tileToMoveI = 0;
		 int tileToMoveJ = 0;
		 boolean LegalMove;
		 int otherCounter = 0;
		 
		 for (int i = 0; i<=board.length-1;i++){
			 for(int j=0; j<= board.length-1; j ++){
				 if(board[i][j] == 0){
					 placeOfI = i;
					 placeOfJ = j;
//					 System.out.println("ZeroPosition = " + placeOfI +"," + placeOfJ);
				 }
				 if(board[i][j] == tile){
					 tileToMoveI = i;
					 tileToMoveJ = j;
//					 System.out.println("TilePosition = " + tileToMoveI +"," + tileToMoveJ);
				 }
			 }
		 }
		 if(Math.abs(placeOfI-tileToMoveI)<=1 && Math.abs(placeOfJ-tileToMoveJ) <=1){
			 LegalMove = true;
//			 System.out.println("Legal Move");
			 board[placeOfI][placeOfJ] = tile;
			 board[tileToMoveI][tileToMoveJ] = 0;
			 
		 }
		 else{
			 LegalMove = false;
		 }
		 return LegalMove;
		 
		 
		 
		 
//		 for (int i = 0; i<= board.length-1; i++){
//				for(int j = 0; j<= board[i].length-1; j++){
////					if(i > 0 && i < board.length-1 && j > 0 && j < board.length-1){
//						//handles the middle buttons
//						//moves to the right
//						if(board[i][j] == 0){
//							placeOfI = i;
//							placeOfJ = j;
//							System.out.println(placeOfI +" "+placeOfJ);
//							otherCounter++;
//						}else if(board[i][j] == tile){
//							tileTotileToMoveI = i;
//							tileTotileToMoveJ = j;
//							System.out.println(tileTotileToMoveI +" "+tileTotileToMoveJ);
//							otherCounter++;
//						}
//						if(otherCounter == 2 && Math.abs(placeOfI - tileTotileToMoveI)<= 0 && Math.abs(placeOfJ - tileTotileToMoveJ)<=0){
//							valid = true;
//							board[placeOfI][placeOfJ] = board[tileTotileToMoveI][tileTotileToMoveJ];
//							board[tileTotileToMoveI][tileTotileToMoveJ] = empty;
//							break;
//						}
//						System.out.print(placeOfI + " " + placeOfJ);
//						System.out.print(tileTotileToMoveI + " " + tileTotileToMoveJ);
////					}
//				}
//		 }
//		return true;
	}

	/**
	 * Returns true if game is won (i.e., board is in winning configuration),
	 * else false.
	 */
	public boolean won() {
		//TODO: This part isn't right
		int counter = (board.length*board.length)-1;
		int otherCounter = 1;
		boolean didTheyWin = false;
		
		for( int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++){
				if(board[i][j] == 0){
					continue;
				}else{
					if(otherCounter == counter){
						didTheyWin = true;
						break;
					}else{
						if(board[i][j] == otherCounter){
							otherCounter++;
						}
					}
					
				}
			}
		}
		return didTheyWin;

	}

	/**
	 * Saves the current state of the board to disk (for testing).
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public void save() throws IOException {
		// log
		// String log = "log.txt";

		// delete existing log, if any, before first save
		// boolean saved = false;
		// BufferedWriter out = new BufferedWriter (new FileWriter(log));

		// log board
		out.write("{");
		for (int i = 0; i < d; i++) {
			out.write("{");
			for (int j = 0; j < d; j++) {
				out.write(String.valueOf(board[i][j]));
				if (j < d - 1) {
					out.write(",");
				}
			}
			out.write("}");
			if (i < d - 1) {
				out.write(",");
			}
		}
		out.write("}\n");
		out.flush();
	}

	public static void main(String[] args) throws InterruptedException,
			IOException {
		// TODO Auto-generated method stub

		// ensure proper usage
		if (args.length != 1) {
			System.out.printf("Usage: ./fifteen d\n");
			System.exit(-1);
		}

		int d = Integer.valueOf(args[0]);

		// ensure valid dimensions
		if (d < FifteenGame.MIN || d > FifteenGame.MAX) {
			System.out.printf(
					"Board must be between %i x %i and %i x %i, inclusive.\n",
					FifteenGame.MIN, FifteenGame.MIN, FifteenGame.MAX,
					FifteenGame.MAX);
			System.exit(-1);
		}

		FifteenGame myGame = new FifteenGame(d);

		// greet player
		myGame.greet();

		// initialize the board
		myGame.init();

		Scanner in = new Scanner(System.in);
		// accept moves until game is won
		while (true) {

			// draw the current state of the board
			myGame.draw();

			// saves the current state of the board (for testing)
			myGame.save();

			// check for win
			if (myGame.won()) {
				System.out.printf("ftw!\n");
				break;
			}

			// prompt for move
			System.out.printf("Tile to move: ");
			int tile = in.nextInt();

			// move if possible, else report illegality
			if (!myGame.move(tile)) {
				System.out.printf("\nIllegal move.\n");
				Thread.sleep(2000);
			}

			// sleep for animation's sake
			Thread.sleep(2000);
		}

		myGame.closeLog();
		// that's all folks

	}
}