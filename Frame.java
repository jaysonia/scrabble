package scrabble;

import java.util.ArrayList;

public class Frame {

	public static final int MAX_TILES = 7;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	Frame() {
		tiles.clear();
		return;
	}
	
	Frame (Frame originalFrame) {
		this.tiles = new ArrayList<Tile> (originalFrame.getAllTiles());
		return;
	}
	
	public int size () {
		return(tiles.size());
	}
	
	public boolean isEmpty () {
		return(tiles.isEmpty());
	}
	
	public boolean isFull () {
		return(tiles.size() == MAX_TILES);
	}
	
	public int find (char letter) {
		boolean found = false;
		int index = 0;
		while ((index < tiles.size()) && !found) {
			if (tiles.get(index).matches(letter)) {
				found = true;
			}
			else {
				index++;
			}
		}		
		if (!found) {
			index = -1;
		}
		return(index);
	}

	public void removeAt (int index) {
		tiles.remove(index);
		return;
	}
	
	public void removeChar (char letter) {
		// precondition: isAvailable(letter) is true
		// postcondition: updates undoTiles so that undo can be performed
		int index = find(letter);
		tiles.remove(index);
		return;
	}
	
	public boolean isAvailable (char letter) {
		boolean found;
		if (find(letter) == -1) {
			found = false;
		}
		else {
			found = true;
		}
		return(found);
	}
	
	public boolean isAvailable (String letters) {
		Frame copyFrame = new Frame(this);
		boolean found = true;
		int indexLetters = 0, indexTiles;
		
		while ( (indexLetters<letters.length()) && found) {
			indexTiles = copyFrame.find(letters.charAt(indexLetters));
			if (indexTiles == -1) {
				found = false;
			}
			else {
				copyFrame.removeAt(indexTiles);
			}
			indexLetters++;
		}
		return(found);
	}
	
	public Tile getTile (int index) {
		return(tiles.get(index));
	}

	public ArrayList<Tile> getAllTiles () {
		return(tiles);
	}	
	
	public void refill (Pool pool) {
		int numTilesToDraw;
		ArrayList<Tile> newTiles;
		numTilesToDraw = MAX_TILES - tiles.size();
		newTiles = pool.draw(numTilesToDraw);
		tiles.addAll(newTiles);
		return;
	}
	
	public void exchange (String letters, Pool pool) {
		// precondition: the letters are available in the frame & there are sufficient tiles in the pool
		int index;
		Tile tile;
		
		for (int i=0; i<letters.length(); i++) {
			index = find(letters.charAt(i));
			tile = tiles.get(index);
			pool.add(tile);
			tiles.remove(index);
		}		
		refill(pool);
		return;
	}
	
	public void add (char letter) {
		Tile tile;
		
		tile = new Tile (letter);
		tiles.add(tile);
	}
	
	
}
