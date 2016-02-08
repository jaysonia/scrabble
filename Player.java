package scrabble;



public class Player {
	
	private static int NUM_PASSES_LIMIT = 3;
	
	private String name;
	private int score; 
	private int numPasses;   // consecutive passes
	public Frame frame = new Frame();
	
	Player () {
		name = "";
		score = 0;
		numPasses = 0;
	}
	
	public void setName (String text) {
		name = text;
	}
	
	public String getName () {
		return(name);
	}
	
	
	public void addScore (int increment) {
		score = score + increment;
		numPasses = 0; // count consecutive passes
		return;
	}

	public void pass() {
		numPasses++;
		return;
	}
	
	public boolean isOverPassLimit () {
		return(numPasses >= NUM_PASSES_LIMIT);
	}
	
	public int getScore() {
		return(score);
	}
		
	public Frame getFrame() {
		return(frame);
	}
	
	public int getNumPasses () {
		return(numPasses);
	}
	
	public int unusedLettersScore () {
		int unused = 0;
		for (Tile tile:frame.getAllTiles()) {
			unused = unused + tile.getValue();
		}
		return(unused);
	}	
	

}


