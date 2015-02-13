package scrabble;

public class player {


	static String playerName;
	int playerScore;
	Frame playerFrame;
			
	//constructor
	player(String playerNameIn, Frame frameIn){
		playerName = playerNameIn;
		playerScore = 0;
		playerFrame = frameIn;
		 System.out.println("Player created.");
	}
	//default constructor
	public player(){
		playerName = "Player";
		playerScore = 0;
		System.out.println("Player created.");
	}
	
	//Accessors
	//method to display name
	void displayName(){
		System.out.println(playerName);
	}
	
	//method to display score
	public int displayScore(){
		return playerScore;
	}
	
	//method to increase score depending on what int increase is...
	void increaseScore(int score){
		playerScore = playerScore + score;
	}

	//method to reset player's score back to 0
	void resetPlayerData(String playerNameIn,Frame frameIn){
		playerScore = 0;
		playerName = playerNameIn;
		playerFrame = frameIn;
	}
	
	//method to display the player's tiles
	public char[] displayPlayerTiles(){
		return playerFrame.getLetters();
	}
	
}