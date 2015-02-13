package scrabble;

import java.util.Arrays;

// Represents the Frame each player has 
public class Frame {
	
	private int num_Tiles = 7;
	private char[] letter = new char[num_Tiles];
	public Pool pool;
	
	
	//Construct the Frame with initial letters
	public Frame(Pool poolIn) { 	
		//check if pool is empty
		if(poolIn == null)
			throw new IllegalArgumentException();
		
		pool = poolIn;
		for(int i = 0;i<7;i++){
			letter[i] = pool.tileDraw();
		}
	}
	
	//Accessors
	// Remove a letter from the Frame and returns it to the pool.
	public void removeLetter(char letter) {		 
		for(int i = 0; i< num_Tiles;i++){
			if(this.letter[i] == letter){
				this.letter[i] = ' ';
				break;
			}
		}
		
	}

	//Check for a letter in the Frame (True if it is, False if not)
	public boolean isLetterIn(char letter) {
		for(int i = 0; i<num_Tiles;i++){
			if(this.letter[i] == letter)
				return true;
		}
		
		
		return false;
	}

	
	public boolean isEmpty() {
		for(int i=0;i<num_Tiles;i++){
			if(letter[i] != ' '){
				return false;
			}
		}
		return true;
	}


	public char[] getLetters() {		//Access letters in the Frame
		return letter;
	}


	public void refill() {
		for (int i =0;i<num_Tiles;i++) {
			if(letter[i] == ' ' && pool.poolSize() != 0){
				letter[i] = pool.tileDraw();
			}
		}
	}


	public void displayFrame() { 		//Method to display the Frame
		System.out.print("Frame:");
		System.out.println(Arrays.toString(letter));
	}
}
