package scrabble;

public class Board {
	
	//variables
	private char[][] board;
	int moveCount;
	
	//board layout for double, triple word and letter scores
	private int[][] BOARDLAYOUT = {
			{4,0,0,1,0,0,0,4,0,0,0,1,0,0,4},
			{0,3,0,0,0,2,0,0,0,2,0,0,0,3,0},
			{0,0,3,0,0,0,1,0,1,0,0,0,3,0,0},
			{1,0,0,3,0,0,0,1,0,0,0,3,0,0,0},
			{0,0,0,0,3,0,0,0,0,0,3,0,0,0,0},
			{0,2,0,0,0,2,0,0,0,2,0,0,0,2,0},
			{0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
			{4,0,0,1,0,0,0,3,0,0,0,1,0,0,4},
			{0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
			{0,2,0,0,0,2,0,0,0,2,0,0,0,2,0},
			{0,0,0,0,3,0,0,0,0,0,3,0,0,0,0},
			{1,0,0,3,0,0,0,1,0,0,0,3,0,0,1},
			{0,0,3,0,0,0,1,0,1,0,0,0,3,0,0},
			{0,3,0,0,0,2,0,0,0,2,0,0,0,3,0},
			{4,0,0,1,0,0,0,4,0,0,0,1,0,0,4},
	};
	
	//Constructor
	public Board(){
		board = new char[15][15];
		moveCount=0;
		
		for(int i=0;i<board[i].length;i++){
			for(int j=0;j<board[j].length;j++)
				board[i][j] = '-';
		}
	}
	
	//accessors
	//reset the board
	public void reset(){
		board = new char[15][15];
		moveCount= 0;
		
		for(int i=0;i<board[i].length;i++){
			for(int j=0;j<board[j].length;j++)
				board[i][j] = '-';
		}
	}
	
	//checks a word to see if it is a legal move
	private boolean wordCheck(char[] word,int x, int y,char direction,Frame frame){
		//players rack has tiles
		char[] tiles = frame.getLetters();
		boolean valid = false;
		for(int i = 0; i<word.length;i++){
			for(int j=0;j<tiles.length;j++){
				if(word[i] == tiles[j])
					valid =true;
				else
					valid = false;
			}
		}
		
		//tiles end up in the bounds of the board
		if(direction == 'v' && (y + word.length)<15){
			
		}
		
		//placement must use 1 letter from the rack
		
		//first word must touch middle of the board
		
		//if it's not he first word must touch another tile
		
		return valid;
	}
	
	//place a character on the board
	public void place(char[] word, int x, int y,char direction,Frame frame){
		if(direction == 'v'){
			for(int i=0;i<word.length;i++){
				board[x][y+i] = word[i];
				frame.removeLetter(word[i]);
			}
				
		}
		else if (direction == 'h'){
			for(int i=0;i<word.length;i++){
				board[x=i][y] = word[i];
			}
		}
		
	}
	
	//prints out an ASCII representation of the board
	public void display(){
		System.out.println(" \t 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
		for(int i=0;i<board[i].length;i++){
			System.out.print(i+"\t");
			for(int j=0;j<board[j].length;j++)
				System.out.print(board[i][j] );
		}
			
		
	}

}
