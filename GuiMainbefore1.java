import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;

/* Chess
 * Version 1.1
 * Wade Huang
 * Mr. Roller
 * ICS3U
 * June 15th, 2018
 */

public class GuiMainbefore1 implements ActionListener {

	//declaring and initializing all instance and static variables/arrays
	String letters[] = { "a", "b", "c", "d", "e", "f", "g", "h" };
	String cases;
	int temp = 0;
	int tempC = 0;
	int tempPn = 0;
	int tempR = 0;
	int tempT = 0;
	static int turn = 1;
	int n = 0;
	int n2 = 0;
	int top = 0;
	int bot = 0;
	int right = 0;
	int left = 0;
	int diff = 0;
	static boolean first = true;
	boolean valid = false;
	int block = 0;
	boolean stop =  false;
	static String victory = "bw";
	boolean startCheck = false;
	static int nextTurn = 2;
	int occupied[] = new int[100];
	int occupiedW[] = new int[100];
	int occupiedB[] = new int[100];
	JFrame frame;
	JPanel main, game, side;
	static JButton[] pieces = new JButton[81];
	static Pieces[] ps = new Pieces[81];
	private ImageIcon bPawn = new ImageIcon("src/Pieces/bPawn.png");
	private ImageIcon bRook = new ImageIcon("src/Pieces/bRook.png");
	private ImageIcon bKnight = new ImageIcon("src/Pieces/bKnight.png");
	private ImageIcon bBishop = new ImageIcon("src/Pieces/bBishop.png");
	private ImageIcon bQueen = new ImageIcon("src/Pieces/bQueen.png");
	private ImageIcon bKing = new ImageIcon("src/Pieces/bKing.png");
	private ImageIcon wPawn = new ImageIcon("src/Pieces/wPawn.png");
	private ImageIcon wRook = new ImageIcon("src/Pieces/wRook.png");
	private ImageIcon wKnight = new ImageIcon("src/Pieces/wKnight.png");
	private ImageIcon wBishop = new ImageIcon("src/Pieces/wBishop.png");
	private ImageIcon wQueen = new ImageIcon("src/Pieces/wQueen.png");
	private ImageIcon wKing = new ImageIcon("src/Pieces/wKing.png");

	//Constructor
	public GuiMainbefore1() {

		//Resets static variables
		turn = 1;
		stop = false;
		nextTurn = 2;
		
		//initialize frame
		frame = new JFrame("Chess");
		frame.setSize(870, 670);
		frame.setResizable(false);
		frame.setVisible(true);

		//Initialize main panel and add it to the frame
		main = new JPanel(new BorderLayout());
		frame.add(main);

		//the chess board is initialied and added to the main panel
		game = new JPanel(new GridLayout(9, 9, 5, 5));
		main.add(game, BorderLayout.CENTER);
		game.setBackground(Color.WHITE);

		//The side panel is initialized and added to the main panel (this was for the timer method and the JLabels showing what pieces you've taken. 
		//I did not have time to replace it because I was stuck on the check method most of the time
		
		side = new JPanel();
		main.add(side, BorderLayout.EAST);

		
		//Initialize all JButtons and the piece class and adds it to their respective arrays
		for (int q = 0; q < pieces.length; q++) {

			ps[q] = new Pieces(q);
			pieces[q] = new JButton();
			pieces[q].addActionListener(this);
			pieces[q].setSize(50, 50);
			pieces[q].setFont(new Font("Helvetica", Font.BOLD, 36));

			//If it is a notation block (ie. the alphanumeric terms that notes the position of pieces like a4 or n6),
			//then it is set as darkgrey and a solid piece (ie. it is a piece that can't be taken to moved to)
			if (9 > q || q % 9 == 0) {

				pieces[q].setBackground(Color.DARK_GRAY);
				pieces[q].setForeground(Color.WHITE);
				occupied[q] = 1;
				occupiedW[q] = 1;
				occupiedB[q] = 1;		

			//Sets the two color themes of the chess board
			} else if (q % 2 == 0) {

				pieces[q].setBackground(new Color(139,69,19));

			} else {

				pieces[q].setBackground(new Color(205,170,125));

			}
			
			//Writes the letters on the letter notation pieces
			if (q > 0 && q < 10 && temp < 8) {

				pieces[q].setText(letters[temp]);
				temp++;
				pieces[q].setEnabled(false);

			}

			//Writes the number on the number notation pieces
			if (q % 9 == 0) {

				pieces[q].setText("" + ((81 - q) / 9));
				
				if (((81 - q) / 9) == 9) {
					
					pieces[q].setText("");
					
				}
				
				pieces[q].setEnabled(false);
				

			}

			//Add all the buttons
			pieces[q].setVisible(true);
			game.add(pieces[q]);

			//Set their attritbutes (if they are a white piece, black piece, or a space
			if (q > 63 && q < 81) {

				occupiedW[q] = 1;
				occupied[q] = 1;

			} else {

				occupiedW[q] = 0;

			}

			if (q > 9 && q < 27) {

				occupiedB[q] = 1;
				occupied[q] = 1;

			} else {

				occupiedB[q] = 0;

			}
			
			//Giving them icons to repersent what piece they are
			if (range(q, 64, 71) || range(q, 19, 26)) {

				if (q < 40) {

					pieces[q].setIcon(bPawn);

				} else {

					pieces[q].setIcon(wPawn);

				}

			} else if (q == 10 || q == 17 || q == 73 || q == 80) {

				if (q < 40) {

					pieces[q].setIcon(bRook);

				} else {

					pieces[q].setIcon(wRook);

				}

			} else if (q == 11 || q == 16 || q == 74 || q == 79) {

				if (q < 40) {

					pieces[q].setIcon(bKnight);

				} else {

					pieces[q].setIcon(wKnight);

				}

			} else if (q == 12 || q == 15 || q == 75 || q == 78) {

				if (q < 40) {

					pieces[q].setIcon(bBishop);

				} else {

					pieces[q].setIcon(wBishop);

				}

			} else if (q == 13 || q == 77) {

				if (q < 40) {

					pieces[q].setIcon(bQueen);

				} else {

					pieces[q].setIcon(wQueen);

				}

			} else if (q == 14 || q == 76) {

				if (q < 40) {

					pieces[q].setIcon(bKing);

				} else {

					pieces[q].setIcon(wKing);

				}

			}

		}

		temp = 0;

	}//end Costructor

	public void actionPerformed(ActionEvent e) {

		//If it is the first click
		if (first == true) {

			temp = 0;
			n = 0;	

			//finds the Pieces object that is related to the JButton that is pressed
			while (e.getSource() != pieces[n]) {

				n += 1;

			}

			if (occupiedW[n] == 1 && turn % 2 != 0 || occupiedB[n] == 1 && turn % 2 == 0) {

				while (ps[temp].getPn() != n) {

					temp++;

				}

				n = temp;

				//Disable all irrelevant JButtons (ie. Enemy pieces) and enable all relevant ones
				if (turn % 2 != 0 && temp > 63 && temp < 81){
					
					for (int r = 0; r < 81; r++) {
						
						if (ps[r].getSn() < 81 && ps[r].getSn() > 63 && ps[r].getSn() != 72) {
							
							pieces[ps[r].getPn()].setEnabled(false);
							
						}
						
					}
					
				} else if (turn % 2 == 0  && temp > 9 && temp < 27){
					
					for (int r = 0; r < 81; r++) {
						
						if (ps[r].getSn() < 27 && ps[r].getSn() > 9 && ps[r].getSn() != 18) {
							
							pieces[ps[r].getPn()].setEnabled(false);
							
						}
						
					}
					
				}
				
				if (turn % 2 == 0  && temp > 9 && temp < 27) {
					
					for (int r = 0; r < 81; r++) {
						
						if (ps[r].getSn() < 81 && ps[r].getSn() > 63 && ps[r].getSn() != 72) {
							
							pieces[ps[r].getPn()].setEnabled(true);
							
						}
						
					}
					
				} else if (turn % 2 != 0 && temp > 63 && temp < 81) {
					
					for (int r = 0; r < 81; r++) {
						
						if (ps[r].getSn() < 27 && ps[r].getSn() > 9 && ps[r].getSn() != 18) {
							
							pieces[ps[r].getPn()].setEnabled(true);
							
						}
						
					}
					
				}
				
				//Will go to the check move method and set the stop boolean (which is related to buttons enable/disable) to true
				checkMove(n);
				stop = true;

			}

		} else {

			//If it is the second click, then it will find the Pieces object that is relate to that JButton
			temp = 0;
			n2 = 0;

			while (e.getSource() != pieces[n2]) {

				n2 += 1;

			}

			while (ps[temp].getPn() != n2) {

				temp++;

			}

			n2 = temp;

			//Disable all irrelevant JButtons (ie. allied pieces) and enable all relevant ones
			if (turn % 2 != 0){
				
				for (int r = 0; r < 81; r++) {
					
					if (ps[r].getSn() < 81 && ps[r].getSn() > 63 && ps[r].getSn() != 72) {
						
						pieces[ps[r].getPn()].setEnabled(true);
						
					}
					
				}
				
			} else {
				
				for (int r = 0; r < 81; r++) {
					
					if (ps[r].getSn() < 27 && ps[r].getSn() > 9 && ps[r].getSn() != 18) {
						
						pieces[ps[r].getPn()].setEnabled(true);
						
					}
					
				}
				
			}
			
			//Will go to the check move method and set the stop boolean (which is related to buttons enable/disable) to false
			checkMove(n2);
			stop = false;

		}

	}//end method

	//Determines if int x is within int min and int max
	public static boolean range(int x, int min, int max) {

		return min <= x && x <= max;

	}//end method

	//determines what Piece the first Piece is
	public void getWho(int x) {

		//check their starting number (their sn) and see what piece they are (ex. if it is a rook, then it will set case to "r").
		//Based on that different methods will be executed in the checkMove method
		tempC = ps[x].getSn();

		if (range(tempC, 64, 71) || range(tempC, 19, 26)) {

			cases = "p";

		} else if (tempC == 10 || tempC == 17 || tempC == 73 || tempC == 80) {

			cases = "r";

		} else if (tempC == 11 || tempC == 16 || tempC == 74 || tempC == 79) {

			cases = "kn";

		} else if (tempC == 12 || tempC == 15 || tempC == 75 || tempC == 78) {

			cases = "b";

		} else if (tempC == 13 || tempC == 77) {

			cases = "q";

		} else if (tempC == 14 || tempC == 76) {

			cases = "k";

		} else {

			cases = "null";

		}

		tempC = 0;

	}//end Method

	//The first click will make it go to the getWho method to see what piece they are,
	//and the second click will make the checkMove method use the case variable and then dependent on what it is it will execute differnt logics.
	public void checkMove(int x) {

		if (first == true) {
			
			getWho(x);

			tempPn = ps[x].getPn();

			first = false;

		} else {

			//Get the difference (useful for rook logic) and uses a switch statement and see what case is to determine what logic to use.
			diff = ps[x].getPn() - tempPn;

			switch (cases) {

			//For instance, if the case is "r", then it will call the rookLogic method and insert x (piece number of the second piece), tempPn (the position number of the first piece),
			//and turn (which is just current turn. If the requirement fits (the if statement), the it will call in the finalize method to swap the two Jbuttons
			case "r":

				rookLogic(x, tempPn, turn);
				
				if ((top >= ps[x].getPn() && bot <= ps[x].getPn()) && diff % 9 == 0 || range(diff, left, right)) {
				
					finalize(ps[n], ps[n2]);

				} else {

					first = true;

				}

				break;

			//This one and all of it below uses the valid boolean to determine if the move is valid. 
			//The logic will automatically make valid as true if the move is valid
			case "kn":
				
				knightLogic(x, tempPn, turn);
				
				if (valid == true) {
				
					finalize(ps[n], ps[n2]);
					
				}
				
				first = true;
				
				break;

			case "b":
				
				bishopLogic(x, tempPn, turn);
				
				if (valid == true) {

					finalize(ps[n], ps[n2]);

				} else {
					
					first = true;
					
				}

				break;

			//The queen just uses both the rook and bishop logic instead of a separate one
			case "q":
					
				rookLogic(x, tempPn, turn);	
				bishopLogic(x, tempPn, turn);
				
				if (((top >= ps[x].getPn() && bot <= ps[x].getPn()) && diff % 9 == 0 || range(diff, left, right)|| valid == true)) {
					
					finalize(ps[n], ps[n2]);

				} else {

					first = true;
					
				}
				
				break;
				
			case "p":
				
				
				pawnLogic(x, tempPn, turn);
			
				if (valid == true) {

					finalize(ps[n], ps[n2]);

				}else {
					
					first = true;
					
				}
				
				break;
			
			case "k" :
				
				kingLogic(x, tempPn, turn);
				
				if (valid == true) {
					
					finalize(ps[n], ps[n2]);
					
				} else {
					
					first = true;
					
				}
				
				break;
			
			//If nothing is found, then it will go back to the beginning 
			case "null":
				
				first = true;
				
				break;
				
			}

		}

	}//end Method

	//Check and see if the path between the 2 JButton is straight usin x (piece number of the second piece), tempPn (the position number of the first piece),
	//and turn (which is just current turn.
	public void rookLogic(int x, int y, int t) {

		left = 0;
		right = 0;
		bot = 0;
		top = 0;

		//If it is a vertical path
		if (diff % 9 == 0) {

			top = y;
			//Checks each Jbutton that is above the first Jbutton and stops if one is a solid piece
			while (top <= ps[x].getPn() && top >= y) {

				if (top < 81) {

					top += 9;

					if (block(top, t) == 1) {

						break;

					}

				}

			}

			bot = y;
			//Checks each Jbutton that is below the first Jbutton and stops if one is a solid piece
			while (bot > ps[x].getPn() && bot <= y) {

				if (bot > 9) {

					bot -= 9;

					if (block(bot, t) == 1) {

						break;

					}

				}

			}

		} else {

			tempR = y;
			//Checks each Jbutton that is to the right of the first Jbutton and stops if one is a solid piece
			while (tempR % 9 != 0) {

				right++;
				tempR++;

				if (block(tempR, t) == 1) {

					break;

				}

			}

			right--;//For some reason I need this. I honestly don't know how it works. It just works when I put this here

			tempR = y;
			
			//Checks each Jbutton that is to the left of the first Jbutton and stops if one is a solid piece
			while (tempR % 9 != 0) {

				left--;
				tempR--;

				if (block(tempR, t) == 1) {

					break;

				}

			}

			left++;

		}
		
		ps[y].moved();

	}//end Method

	//Same thing as rookLogic except this time it's L shaped paths
	public void knightLogic(int x, int y, int t) {

		valid = false;
		//Because knights cannot be blocked, there is no block method. It just checks to see if the path is L shaped. 
		//If it is, then the move is valid
		if ((ps[x].getPn() == y + 19 || ps[x].getPn() == y - 19 || ps[x].getPn() == y + 17
				|| ps[x].getPn() == y - 17 || ps[x].getPn() == y + 7 || ps[x].getPn() == y - 7
				|| ps[x].getPn() == y + 11 || ps[x].getPn() == y - 11)) {

			valid = true;

		}

	}

	//Same thing as rookLogic except this time it's diagonal paths
	public void bishopLogic(int x, int y, int t) {

		tempR = y;
		valid = false;
		//Checks the path of all the Jbuttons in the north west direction of the first Jbutton clicked
		//If a solid piece is found, it stops, and the move is not valid.
		while (ps[x].getPn() < tempR) {

			tempR -= 10;

			if (tempR > 0) {

				if (block(tempR, t) == 1) {

					break;

				}

				if (ps[x].getPn() == tempR) {

					valid = true;

				}

			}

		}

		tempR = y;

		//Checks the path of all the Jbuttons in the north east direction of the first Jbutton clicked
		//If a solid piece is found, it stops, and the move is not valid.
		while (ps[x].getPn() < tempR) {

			tempR -= 8;

			if (tempR > 0) {

				if (block(tempR, t) == 1) {

					break;

				}

				if (ps[x].getPn() == tempR) {

					valid = true;

				}

			}

		}

		tempR = y;

		//Checks the path of all the Jbuttons in the south west direction of the first Jbutton clicked
		//If a solid piece is found, it stops, and the move is not valid.
		while (ps[x].getPn() > tempR) {

			tempR += 8;

			if (tempR < 81) {

				if (block(tempR, t) == 1) {

					break;

				}

				if (ps[x].getPn() == tempR) {

					valid = true;

				}

			}

		}

		tempR = y;

		//Checks the path of all the Jbuttons in the south east direction of the first Jbutton clicked
		//If a solid piece is found, it stops, and the move is not valid.
		while (ps[x].getPn() > tempR) {

			tempR += 10;

			if (tempR < 81) {

				if (block(tempR, t) == 1) {

					break;

				}

				if (ps[x].getPn() == tempR) {

					valid = true;

				}
				

			}

		}

	}//end Method

	//Same thing as previous logic except this method includes the promote method 
	//and the fact that pawns can only move in 1 direction
	public void pawnLogic(int x, int y, int t) throws NullPointerException {

		String s = "";
		valid = false;
		tempR = y;

		//If the piece selected is directly diagonally in front of the pawn, then the move is valid.
		if (t % 2 == 0) {
			
			if ((ps[x].getPn() == tempR + 8 || ps[x].getPn() == tempR + 10)
					&& occupiedW[ps[x].getPn()] == 1) {
				
				valid = true;

			}

			//If the pawn did not move, then it can move 2 spaces, otherwise, it will only move once
			//Obviously, it will also check the path of solid pieces in front of them.
			//When the pawn first moves, it will check off a move integer so the program knows they moved
			if (ps[n].getMoved() == 0 && valid == false) {

				while (y + 18 > tempR) {

					tempR += 9;

					if (occupied[tempR] == 1 || tempR == ps[x].getPn()) {

						break;

					}

				}

				if (tempR == ps[x].getPn()) {

					valid = true;
					ps[n].moved();

				}

			} else if (y + 9 == ps[x].getPn()) {

				if (occupied[y + 9] == 0) {

					valid = true;

				}

			}

		} else {

			if ((ps[x].getPn() == tempR - 8 || ps[x].getPn() == tempR - 10)
					&& occupiedB[ps[x].getPn()] == 1) {

				valid = true;

			}

			if (ps[n].getMoved() == 0 && valid == false) {

				while (y - 18 < tempR) {

					tempR -= 9;

					if (occupied[tempR] == 1 || tempR == ps[x].getPn()) {

						break;

					}

				}

				if (tempR == ps[x].getPn()) {

					valid = true;
					ps[n].moved();

				}

			} else if (y - 9 == ps[x].getPn()) {

				if (occupied[y - 9] == 0) {

					valid = true;

				}

			}

		}
		
		//When a pawn enters the opposite endzone, a pop up will come up and ask the player what piece they want the pawn to transform to
		//Depending on their input, the pawn will change its attribute and icon to fit the selected piece
		if (valid == true && (turn % 2 == 0 && ps[x].getPn() > 72 && ps[x].getPn() < 81 || turn % 2 != 0 && ps[x].getPn() > 9 && ps[x].getPn() < 18)) {	

			Pattern pattern = Pattern.compile("(Queen|Bishop|Rook|Knight)");
			Matcher matcher = pattern.matcher(s);
			while (!matcher.find()) {
				
				s = JOptionPane.showInputDialog(frame,"Unit conversion (Queen/Bishop/Rook/Knight"); 
				
				if(s != null) {
					
				matcher = pattern.matcher(s);
				
				}
			
			}
			
			switch (s) {
			
			case "Queen":
				
				if (turn % 2 != 0) {
					
					pieces[ps[n].getPn()].setIcon(wQueen);
					ps[n].setSn(77);
					
				} else {
					
					pieces[ps[n].getPn()].setIcon(bQueen);
					ps[n].setSn(13);
					
				}
				
				break;
				
			case "Bishop":
				
				if (turn % 2 != 0) {
					
					pieces[ps[n].getPn()].setIcon(wBishop);
					ps[n].setSn(78);
					
				} else {
					
					pieces[ps[n].getPn()].setIcon(bBishop);
					ps[n].setSn(12);
					
				}
				
				break;
				
			case "Knight":
				
				if (turn % 2 != 0) {
					
					pieces[ps[n].getPn()].setIcon(wKnight);
					ps[n].setSn(79);
					
				} else {			
					pieces[ps[n].getPn()].setIcon(bKnight);
					ps[n].setSn(11);
					
				}
				
				break;
				
			case "Rook":
				
				if (turn % 2 != 0) {
					
					pieces[ps[n].getPn()].setIcon(wRook);
					ps[n].setSn(80);
					
				} else {
					
					pieces[ps[n].getPn()].setIcon(bRook);
					ps[n].setSn(10);
					
				}
				
				break;
				
			
			}
			
		}	

	}

	//Same as all other logics except It just looks at if the second Jbutton is adjacent to the first Jbuttons and the castle method included
	public void kingLogic(int x, int y, int t) {

		valid = false;
		tempR = y;
		
		//If the king and the rook hasn't moved
		if (ps[n].getMoved() == 0 && ps[x].getPn() == 11 && ps[10].getSn() == 10 && ps[x].getPn() == 16 && ps[17].getSn() == 17) {
		
			int te = 0;
			//and the second button that's pressed is the space beside the rook
			if (turn % 2 == 0 && (ps[x].getPn() == 11 && ps[10].getSn() == 10 &&
				ps[10].getMoved() == 0)) {
				
					valid = true;
				//Sees if the path is clear, if it is not, then it's not a valid move	
				for (te = y - 1; te > 17; te--) {
					
					if(occupied[te] != 0) {
						
						valid = false;
						break;
						
					}
					
				}
				
				
			}
			
			if (turn % 2 == 0 && ( ps[x].getPn() == 16 && ps[17].getSn() == 17 &&
				ps[17].getMoved() == 0)) {
					
				valid = true;
				
				for (te = y + 1; te < 17; te++) {
					
					if(occupied[te] != 0) {
					
						valid = false;
						break;
					
					}
				
				}
					
			}
			
			if (turn % 2 != 0 && (ps[x].getPn() == 74 && ps[73].getSn() == 73 &&
					ps[73].getMoved() == 0)) {
						
				valid = true;
					
				for (te = y - 1; te > 73; te--) {
					
					if(occupied[te] != 0) {
						
						valid = false;
						break;
						
					}
					
				}
						
			}
			
			if (turn % 2 != 0 && (ps[x].getPn() == 79 && ps[80].getSn() == 80 &&
					ps[80].getMoved() == 0)) {
						
				valid = true;
					
				for (te = y + 1; te < 80; te++) {
					
					if(occupied[te] != 0) {
						
						valid = false;
						break;
						
					}
					
				}
						
			}
			
			if (valid == true) {
				
				int ter = 0;
						
				if (turn % 2 == 0 && te > y) {
					
					for (ter = 0; ps[ter].getPn() != te - 2; ter++ ) {
						
					}
					
					move(ps[17],ps[ter], turn);
				
				}
				
				if (turn % 2 == 0 && te < y) {
					
					for (ter = 0; ps[ter].getPn() != te + 2; ter++ ) {
						
					}
					
					move(ps[11],ps[ter], turn);
				
				}
				
				if (turn % 2 != 0 && te > y) {
					
					for (ter = 0; ps[ter].getPn() != te - 2; ter++ ) {
						
					}
					
					move(ps[80],ps[ter], turn);
				
				}
				
				if (turn % 2 != 0 && te < y) {
					
					for (ter = 0; ps[ter].getPn() != te + 2; ter++ ) {
						
					}
					
					move(ps[73],ps[ter], turn);
				
				}
				
			}
					
		}
		
		valid = false;
		
		//If the button pressed is adjacent to the king, then the move is valid
		if(t % 2 == 0 && ( occupiedB[y + 8] == 0 && ps[x].getPn() == y + 8 || occupiedB[y - 8] ==  0 &&
				ps[x].getPn() == y - 8 || occupiedB[y + 10] ==  0  && ps[x].getPn() ==  y + 10 ||
				occupiedB[y - 10] == 0  && ps[x].getPn() == y - 10 || occupiedB[y + 9] ==  0 &&
				ps[x].getPn() == y + 9 || occupiedB[y - 9] ==  0  && ps[x].getPn() == y - 9 ||
				occupiedB[y + 1] ==  0 && ps[x].getPn() == y + 1 || occupiedB[y + -1] ==  0 && ps[x].getPn() == y - 1 )) {
			
			valid = true;
			
		} else if (t % 2 != 0 && ( occupiedW[y + 8] == 0 && ps[x].getPn() == y + 8 || occupiedW[y - 8] ==  0 &&
				ps[x].getPn() == y - 8 || occupiedW[y + 10] ==  0  && ps[x].getPn() ==  y + 10 ||
				occupiedW[y - 10] == 0  && ps[x].getPn() == y - 10 || occupiedW[y + 9] ==  0 &&
				ps[x].getPn() == y + 9 || occupiedW[y - 9] ==  0  && ps[x].getPn() == y - 9 ||
				occupiedW[y + 1] ==  0 && ps[x].getPn() == y + 1 || occupiedW[y + -1] ==  0 && ps[x].getPn() == y - 1 )) {
			
			valid = true;
			
		}
		
		ps[y].moved();

	}

	//Checks one last time to see if the move is valid
	public void finalize(Pieces p, Pieces p2) {
	
		if (turn % 2 == 0 && occupiedB[p2.getPn()] == 0) {
			
			take(p, p2);
			first = true;

		} else if (turn % 2 != 0 && occupiedW[p2.getPn()] == 0) {
			
			take(p, p2);
			first = true;
		}

	}//end Method

	//Make the the Second Jbutton and the Piece object related into a space, and then puts them into the move method
	public void take(Pieces p, Pieces p2) {

		//Clears icon
		if (startCheck != true) {
			
			pieces[p2.getPn()].setIcon(null);
			
		}
		
		//Clears all values
		occupiedB[p2.getPn()] = 0;

		occupiedW[p2.getPn()] = 0;

		occupied[p2.getPn()] = 0;

		p2.setSn(40);

		p2.setWAn(0);

		p2.setBAn(0);

		move(p, p2, turn);

		//increases turn number
		if(startCheck != true) {
			
		turn++;
		
		}
		
		//Checks if any sides had won.
		victory = "";
		
		//Checks the sn of all the pieces, if 14, or 76 is missing, theat respective side doesn't now print their side into the victory String
		//Then, it checks to see which side remains, if both side remains, nothing happens. If one side remains, that side wins
		//and the game restarts.
		for (int n = 0; n < 81; n ++) {
			
			if (ps[n].getSn() == 76 ) {
				
				victory += "w";
				
			}
			
			if (ps[n].getSn() == 14) {
				
				victory += "b";
				
			}
			
		}
		
		if (victory.equals("b")) {
			
			JOptionPane.showMessageDialog(frame,"Black Victory");
			frame.setVisible(false); 
			frame.dispose(); 
			new GuiMainbefore1();
			
		}
		
		if (victory.equals("w")) {
			
			JOptionPane.showMessageDialog(frame,"White Victory");
			frame.setVisible(false); 
			frame.dispose();
			new GuiMainbefore1();
			
		}
		
		first = true;

	}

	//Swaps all the values of the two piece values inputed
	public void move(Pieces p, Pieces p2, int t) {

		//Swaps icon
		if (startCheck != true) {
			
			ImageIcon tempS = (ImageIcon) pieces[p2.getPn()].getIcon();
			pieces[p2.getPn()].setIcon(pieces[p.getPn()].getIcon());
			pieces[p.getPn()].setIcon(tempS);
		
		}
		
		//Swaps all occupied array values and Piece object values
		tempT = occupiedB[p2.getPn()];
		occupiedB[p2.getPn()] = occupiedB[p.getPn()];
		occupiedB[p.getPn()] = tempT;


		tempT = occupiedW[p2.getPn()];
		occupiedW[p2.getPn()] = occupiedW[p.getPn()];
		occupiedW[p.getPn()] = tempT;
		
		tempT = occupied[p2.getPn()];
		occupied[p2.getPn()] = occupied[p.getPn()];
		occupied[p.getPn()] = tempT;

		tempT = p2.getPn();
		p2.setPn(p.getPn());
		p.setPn(tempT);
		
		tempT = p2.getWAn();
		p2.setWAn(p.getWAn());
		p.setWAn(tempT);

		tempT = p2.getBAn();
		p2.setBAn(p.getBAn());
		p.setBAn(tempT);
		
		first = true;

	}//end Method

	//Takes the positon number and sees if there are any pieces (and if there is, is it an enemy piece or an allied piece)
	public int block(int x, int t) {
		
		boolean oneMore = false;

		if (x > 81) {

			x -= 9;

		}

		if (occupied[x] == 1) {

			if (t % 2 == 0) {

				if (occupiedB[x] == 1) {

					return 1;

				}

				if (occupiedW[x] == 1) {

					if (oneMore == false) {

						return 1;

					}

				}

			} else {

				if (occupiedW[x] == 1) {

					return 1;

				}

				if (occupiedB[x] == 1) {


					if (oneMore == false) {

						return 1;

					}

				}

			}

		}

		return 0;

	}//end Method
	
	//The incomplete check and checkLogic method...
	/*public void check (int t) {
		
		startCheck = true;
		int x;
		
		if (t % 2 == 0) {
			
			x = ps[14].getPn();
			
		} else {
			
			
			x = ps[76].getPn();
			
		}
		
		if (occupied[ps[n2].getPn()] == 0) {
			
			move(ps[n],ps[n2],t);	
			checkLogic(x, t);
			move(ps[n],ps[n2],t);
			
			
		} else {
			
			int temp1 = occupiedB[ps[n2].getPn()];
			int temp2 = occupiedW[ps[n2].getPn()];
			int temp3 = occupied[ps[n2].getPn()];
			int temp4 = ps[n2].getSn();
			int temp5 = ps[n2].getPn();
			System.out.println(occupiedB[ps[n].getPn()]);
			
			occupiedB[ps[n2].getPn()] = 0;
			occupiedW[ps[n2].getPn()] = 0;
			occupied[ps[n2].getPn()] = 0;
			ps[n2].setSn(0);
			ps[n2].setPn(99);
			
			
			move(ps[n],ps[n2],t);	
			checkLogic(x, t);
			move(ps[n],ps[n2],t);
			
			ps[n2].setSn(temp4);
			ps[n2].setPn(temp5);
			occupiedB[ps[n2].getPn()] = temp1;
			occupiedW[ps[n2].getPn()] = temp2;
			occupied[ps[n2].getPn()] = temp3;
			System.out.println(occupiedB[ps[n].getPn()]);
			
		}
		
		startCheck = false;
			
	}

	public void checkLogic(int x, int t) {

		int tempx = 0;
		
		if(turn % 2 == 0) {
			
			for (int l = 0; l < 81; l++ ){
				
				if (range(ps[l].getSn(), 64, 71)) {
					
					tempx = ps[l].getPn();
					pawnLogic(x,tempx,t);

				} else if (ps[l].getSn() == 73 || ps[l].getSn() == 80) {

					tempx = ps[l].getPn();
					rookLogic(x,tempx,t);

				} else if (ps[l].getSn() == 74 || ps[l].getSn() == 79) {

					tempx = ps[l].getPn();
					knightLogic(x,tempx,t);

				} else if (ps[l].getSn() == 75 || ps[l].getSn() == 78) {

					tempx = ps[l].getPn();
					bishopLogic(x,tempx,t);

				} else if (ps[l].getSn() == 77) {

					tempx = ps[l].getPn();
					bishopLogic(x,tempx,t);
					knightLogic(x,tempx,t);

				} else if (ps[l].getSn() == 76) {

					tempx = ps[l].getPn();
					kingLogic(x,tempx,t);

				} 
				
			}
			
			if (valid == true) {
				
				check = true;
				
			}			
			
		} else {
			
			for (int l = 0; l < 81; l++ ){
				
				if (range(ps[l].getSn(), 19, 26)) {
					
					tempx = ps[l].getPn();
					pawnLogic(x,tempx,t);

				} else if (ps[l].getSn() == 10 || ps[l].getSn() == 17) {

					tempx = ps[l].getPn();
					rookLogic(x,tempx,t);
					
					if (((top >= ps[x].getPn() && bot <= ps[x].getPn()) && diff % 9 == 0 || range(diff, left, right)|| valid == true) && check == false) {

						valid = true; 

					} 

				} else if (ps[l].getSn() == 11 || ps[l].getSn() == 16) {

					tempx = ps[l].getPn();
					knightLogic(x,tempx,t);

				} else if (ps[l].getSn() == 12 || ps[l].getSn() == 15) {

					tempx = ps[l].getPn();
					bishopLogic(x,tempx,t);

				} else if (ps[l].getSn() == 13) {

					tempx = ps[l].getPn();
					bishopLogic(x,tempx,t);
					knightLogic(x,tempx,t);

				} else if (ps[l].getSn() == 14) {

					tempx = ps[l].getPn();
					kingLogic(x,tempx,t);

				} 
				
			}
			
			if (valid == true) {
				
				check = true;
				
			}
			
		}

	}*/

	public static void main(String[] args) {

		new GuiMainbefore1();//Intializes gui
		
		while (victory == "bw") {//When the game is still going, it enables the pieces that should move on that turn and disables everything else
	
			while (first == true && nextTurn == turn) {
				
				if (turn % 2 == 0){
					
					for (int r = 0; r < 81; r++) {
						
						if (ps[r].getSn() < 81 && ps[r].getSn() > 63 && ps[r].getSn() != 72) {
							
							pieces[ps[r].getPn()].setEnabled(false);
							
						}
						
						if (ps[r].getSn() < 27 && ps[r].getSn() > 9 && ps[r].getSn() != 18) {
							
							pieces[ps[r].getPn()].setEnabled(true);
							
						}
						
					}
					
				} else {
					
					for (int r = 0; r < 81; r++) {
						
						if (ps[r].getPn() < 27 && ps[r].getPn() > 9 && ps[r].getSn() != 18) {
							
							pieces[ps[r].getPn()].setEnabled(false);
							
						}
						
						if (ps[r].getPn() < 81 && ps[r].getPn() > 63 && ps[r].getSn() != 72) {
							
							pieces[ps[r].getPn()].setEnabled(true);
							
						}
						
					}
					
				}
				
				nextTurn++;

			}

		}
		
	}

}
