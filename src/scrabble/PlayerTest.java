package scrabble;

import java.util.Scanner;

public class PlayerTest {

	public static void main(String[] args) {
		
		//variables
		Pool gamePool = new Pool();
		Frame gameFrame;
		String playerName;
		char remove;
		int score=5;
		Scanner myScan = new Scanner(System.in);
		char[] playerTiles;
		
		//initialize the frame for use
		gameFrame = new Frame(gamePool);
		
		//take in users name for game
		System.out.println("Enter player name:");
		playerName = myScan.nextLine();
		
		//initilize and test the First player
		player firstPlayer  = new player(playerName, gameFrame);
		firstPlayer.displayName();
		//print current score
		System.out.println("Current Score "+firstPlayer.displayScore());
		
		//increment score and display score
		firstPlayer.increaseScore(score);
		System.out.println("Current Score "+firstPlayer.displayScore());
		
		//display current tiles in users Frame
		firstPlayer.playerFrame.displayFrame();
		System.out.println("Tiles remaining in pool: "+gamePool.poolSize());
		
		//read in a character to be removed from the array
		System.out.print("Choose a tile to be removed: ");
		remove = myScan.next().charAt(0);
		firstPlayer.playerFrame.removeLetter(remove);
		
		//refill the game users frame
		firstPlayer.playerFrame.refill();
		firstPlayer.playerFrame.displayFrame();
		
		//increase player score by the tiles currently in hand
		playerTiles = firstPlayer.displayPlayerTiles();
		score =0;
		for(int i=0;i<playerTiles.length;i++){
			score = score+ gamePool.tileVal(playerTiles[i]);
		}
		
		firstPlayer.increaseScore(score);
		System.out.println("Current Score "+firstPlayer.displayScore());
		myScan.nextLine();
		
		
		//reset player and pool data
		System.out.println("Enter player name:");
		playerName = myScan.nextLine();
		
		gamePool.reset();
		gameFrame = new Frame(gamePool);
		firstPlayer.resetPlayerData(playerName,gameFrame);
		firstPlayer.playerFrame.displayFrame();
		System.out.println("Pool size after reset: "+gamePool.poolSize());
		
		
		
		myScan.close();

	}

}
