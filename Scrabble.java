package scrabble;

public class Scrabble {

	private static int NUM_PLAYERS = 2; 
	private static int BONUS = 50;
	
	
	public static void main (String[] args) {
		
		Board board = new Board();
		Pool pool = new Pool();
		Dictionary dictionary = new Dictionary();
		Player[] players = new Player[NUM_PLAYERS];
		UI ui = new UI();
		int currentPlayerId = 0;
		Player currentPlayer,prevPlayer = null;
		Frame currentFrame,prevFrame = null;
		Word word;
		char lowestTile;
		boolean tileDraw = false;
		String letters;
		char[] tiles = new char[NUM_PLAYERS];
		int commandCode, checkCode, totalWordScore = 0;
		boolean frameWasFull, turnOver = false, gameOver = false, allOverPassLimit;
		int[] unusedScore = new int [NUM_PLAYERS];
		int totalUnused;
		
		// Initialize players
		ui.displayGameStart();
		for (int i=0; i<NUM_PLAYERS; i++) {
			players[i] = new Player();
			players[i].setName(ui.getName());
		}
		
		// Decide who starts
		do {			
			for (int i=0; i<NUM_PLAYERS; i++) {
				tiles[i] = pool.getRandomTile().getFace();
				ui.displayTile(players[i],tiles[i]);
			}
			lowestTile = tiles[0];
			currentPlayerId = 0;
			tileDraw = false;
			for (int i=1; i<NUM_PLAYERS; i++) {
				if (tiles[i] < lowestTile) {
					lowestTile = tiles[i];
					currentPlayerId = i;
					tileDraw = false;
				}
				else if (tiles[i] == lowestTile) {
					tileDraw = true;
				}
			}
			if (!tileDraw) {
				ui.displayStarter(players[currentPlayerId]);
			}
			else {
				ui.displayStarterDraw();
			}
		} while (tileDraw);

		// Play the game
		gameOver = false;
		do {
			currentPlayer = players[currentPlayerId];
			currentFrame = currentPlayer.getFrame();
			currentFrame.refill(pool);
			ui.displayBoard(board);
			ui.displayScores(players);
			ui.displayPoolSize(pool);
			do {
				commandCode = ui.getCommand(currentPlayer);
				switch (commandCode) {
					case UI.COMMAND_QUIT : 		
						turnOver = true;
						gameOver = true;
						break;
					case UI.COMMAND_PASS : 		
						turnOver = true;
						currentPlayer.pass();
						turnOver = true;
						allOverPassLimit = true;
						for (int i=0; i<NUM_PLAYERS; i++) {
							allOverPassLimit = allOverPassLimit && players[i].isOverPassLimit();
						}
						if (allOverPassLimit) {
							gameOver = true;
						}
						break;
					case UI.COMMAND_HELP : 	
						ui.displayHelp();
						turnOver = false;
						break;
					case UI.COMMAND_EXCHANGE :
						letters = ui.getLetters();
						if (!currentFrame.isAvailable(letters)) {
							ui.displayError(UI.EXCHANGE_NOT_AVAILABLE);
							turnOver = false;							
						} else if ( pool.size() < letters.length()) {
							ui.displayError(UI.EXCHANGE_NOT_ENOUGH_IN_POOL);
							turnOver = false;
						} else {
							currentFrame.exchange(letters, pool);
							turnOver = true;
						}
						break;
					case UI.COMMAND_PLAY :		
						word = ui.getWord();
						checkCode = board.checkWord(word, currentFrame);
						if (checkCode != UI.WORD_OK) {
							ui.displayError(checkCode);
							turnOver = false;
						}
						else {
							frameWasFull = currentFrame.isFull();
							totalWordScore = board.setWord(word, currentFrame);
							if (currentFrame.isEmpty() && frameWasFull) {
								totalWordScore = totalWordScore + BONUS;
							}
							ui.displayWordScore(totalWordScore);
							currentPlayer.addScore(totalWordScore);
							turnOver = true;
							if (currentFrame.isEmpty() && pool.isEmpty()) {
								gameOver = true;
							}
						}
						break;
					case UI.COMMAND_CHALLENGE :
						word = ui.getWord();
						if(board.isFirstPlay()){
							System.out.println("unable to make this move on first turn");
							turnOver = false;
						}
						else{
							if(!dictionary.checkWord(word)){
								System.out.println("Challenge Succesful");
								board.removeWord(word, prevFrame);
								totalWordScore = totalWordScore - (totalWordScore*2);
								prevPlayer.addScore(totalWordScore);
								ui.displayBoard(board);
								ui.displayScores(players);
							}
							else{
								System.out.println("Challenge failed");
							}
							turnOver = false;
						}
												
			
				}
			}	while (!turnOver);
			if (!gameOver) {
				currentPlayerId++;
				if (currentPlayerId > NUM_PLAYERS-1) {
					currentPlayerId = 0;
				}
			}
			prevFrame = currentFrame;
			prevPlayer = currentPlayer;
		} while (!gameOver);
		
		totalUnused = 0;
		for (int i=0; i<NUM_PLAYERS; i++) {
			unusedScore[i] = players[i].unusedLettersScore();
			players[i].addScore(-unusedScore[i]);
			totalUnused = totalUnused + unusedScore[i];
		}
		if (unusedScore[currentPlayerId] == 0) {
			players[currentPlayerId].addScore(totalUnused);
		}
		ui.displayResult(players);
	
		System.out.println("GAME OVER");
		
		return;
	}
	
 }
	 
