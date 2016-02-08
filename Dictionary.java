package scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
	
	private static ArrayList<String> words;
	private Scanner input;
	private static final String fileName = "sowpods.txt";
	
	//Constructor
	public Dictionary(){
		words = new ArrayList<String>();
		File myFile = new File(fileName);
		try {
			input = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error with file reader!");
		}
		
		do{
			words.add(input.nextLine());
		}while(input.hasNextLine());
	}
	
	//Check if work is valid
	public Boolean checkWord(Word word){
		String checkword = word.getLetters();
		
		checkword = checkword.toLowerCase();
		
		if(words.contains(checkword)){
			return true;
		}
		else
			return false;
		
	}
	
}
