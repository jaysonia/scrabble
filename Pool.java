package scrabble;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;


public class Pool {
	//initialize variables
	private Vector<Character> tiles = new Vector<Character>();
	private int ascii = (int) 'a';
	private static final HashMap<Character, Integer> tileValue;
	static{
		tileValue = new HashMap<Character, Integer>();
		tileValue.put('#', 0);
		tileValue.put('a', 1);
		tileValue.put('b', 3);
		tileValue.put('c', 3);
		tileValue.put('d', 2);
		tileValue.put('e', 1);
		tileValue.put('f', 4);
		tileValue.put('g', 2);
		tileValue.put('h', 4);
		tileValue.put('i', 1);
		tileValue.put('j', 8);
		tileValue.put('k', 5);
		tileValue.put('l', 4);
		tileValue.put('m', 3);
		tileValue.put('n', 1);
		tileValue.put('o', 1);
		tileValue.put('p', 3);
		tileValue.put('q', 10);
		tileValue.put('r', 1);
		tileValue.put('s', 1);
		tileValue.put('t', 1);
		tileValue.put('u', 1);
		tileValue.put('v', 4);
		tileValue.put('w', 4);
		tileValue.put('x', 8);
		tileValue.put('y', 4);
		tileValue.put('z', 10);
	}
	
	private static final HashMap<Character, Integer> tileQuant;
	static{
		tileQuant = new HashMap<Character, Integer>();
		tileQuant.put('#', 2); 
		tileQuant.put('a', 9); 
		tileQuant.put('b', 2); 
		tileQuant.put('c', 2); 
		tileQuant.put('d', 4); 
		tileQuant.put('e', 12);
		tileQuant.put('f', 2);
		tileQuant.put('g', 3);
		tileQuant.put('h', 2);
		tileQuant.put('i', 9);
		tileQuant.put('j', 1);
		tileQuant.put('k', 1);
		tileQuant.put('l', 4);
		tileQuant.put('m', 2);
		tileQuant.put('n', 6);
		tileQuant.put('o', 8);
		tileQuant.put('p', 2);
		tileQuant.put('q', 1);
		tileQuant.put('r', 6);
		tileQuant.put('s', 4);
		tileQuant.put('t', 6);
		tileQuant.put('u', 4);
		tileQuant.put('v', 2);
		tileQuant.put('w', 2);
		tileQuant.put('x', 1);
		tileQuant.put('y', 2);
		tileQuant.put('z', 1);
	}
	
	//Constructor
	public Pool(){
		//add the blank tile value
		for(int i=0;i< tileQuant.get('#');i++){
			tiles.add('#');
		}
		
		//add all the tiles
		for(int i = ascii;i<ascii+26;i++){
			for(int j = 0;j<tileQuant.get((char) i);j++){
				tiles.add((char) i);
			}
		}
				
	}
	
	public void reset(){
		//reset the size of the pool to 0 and reset the Vector
		tiles.clear();
		
		//add the blank tile value
		for(int i=0;i< tileQuant.get('#');i++){
			tiles.add('#');
		}
		
		//add all the tiles
		for(int i = ascii;i<ascii+26;i++){
			for(int j = 0;j<tileQuant.get((char) i);j++){
				tiles.add((char) i);
			}
		}
	}
	
	//Check if the pool is empty
	public boolean isEmpty(){
		if(tiles.isEmpty())
			return true;
		else
			return false;
	}
	
	//returns the size of the pool
	public int poolSize(){
		return tiles.size();
	}
	
	//draw a random tile
	public char tileDraw(){
		Random random = new Random();
		char temp;
		
		//test if stack is empty before drawing a tile
		if(tiles.isEmpty()){
			throw new EmptyStackException();
		}
		
		else{
			int randInt = random.nextInt(tiles.size());
			temp = tiles.get(randInt);
			tiles.remove(randInt);
			return temp;
		}
	}
	
	//return the tile value for a given tile
	public int tileVal(char tile){		
		return tileValue.get(tile);
	}
	
	//returns a tile to the pool
	public void returnTile(char tile){
		tiles.add(tile);
	}

}
