
public class Pieces {
	
	//declaring and initializing all instance and static variables/arrays
	private int pn;
	private int sn;
	private int bAn;
	private int wAn;
	private int moved;
	
	//Constructor
	public Pieces (int x) {
		
		setPn(x);
		setSn(x);
		
		if (x == 80 || x == 73 || x == 10 || x == 17 || x == 14 || x == 76 || x > 18 && x < 27 || x > 63 ) {
			
			setMoved();//If it is a piece that has methods related to whether or not it moved or not, then it has moved set to 0
			
		} else { 
			
			moved();//Otherwiese, it's just set to 1
			
		}
		
		setAn(x);
		
		
	}//end constructor
	
	//Sets position number as the inputed number
	public void setPn(int x) {
		
		pn = x;
		
	}//end method
	
	//Sets starting number as the inputed number
	public void setSn(int x) {
		
		sn = x;
		
	}//end method
	
	//The three methods below (and their respective get methods) are useless methods ( I didn't delete them cause I was afraid that something might mess up again),
	//but it basically functions as the occupied arrays to see what space is occupied
	public void setAn(int x) {
		
		if (x > 9 && x < 27) {
			
			bAn = 1;
			wAn = -1;
			
		}else if (x > 72 && x < 81 || x > 63 && x < 72) {
			
			bAn = -1;
			wAn = 1;
			
		}else {
			
			bAn = 0;
			wAn = 0;
			
		}
	
	}//end method
	
	public void setBAn(int x) {
		
		bAn = x;
		
	}//end method
	
	public void setWAn(int x) {
		
		wAn = x;
		
	}//end method
	
	//Returns the position number of the piece
	public int getPn() {
		
		return pn;
		
	}//end method
	
	//Returns the starting number of the piece
	public int getSn() {
		
		return sn;
		
	}//end method
	
	public int getBAn() {
		
		return bAn;
		
	}//end method
	
	public int getWAn() {
		
		return wAn;
		
	}//end method
	
	//sets moved to 0
	public void setMoved() {
		
		moved = 0;
		
	}//end method
	
	//Sets moved to 1
	public void moved() {

		moved = 1;
		
	}//end method
	
	//returns moved
	public int getMoved() {
		
		return moved;
		
	}//end method

}
