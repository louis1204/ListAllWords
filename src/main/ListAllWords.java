package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class ListAllWords {
	private String in = null;
	private static HashSet<String> set = new HashSet<String>();
	private static HashSet<String>dictionary = new HashSet<String>();
	
	// Constructor
	// initiates the dictionary and the word
	public ListAllWords(String in)
	{
		this.in = in;	//initiate word
		
		//initiate dictionary
		try{
	      	  FileInputStream fstream = new FileInputStream("corncob_lowercase.txt");
	      	  DataInputStream input = new DataInputStream(fstream);
	      	  BufferedReader br = new BufferedReader(new InputStreamReader(input));
	      	  String strLine;
	      	  
	      	  //Read File Line By Line
	      	  while ((strLine = br.readLine()) != null)   {
		      	  // add each word to the dictionary hashset
		      	  dictionary.add(strLine.trim());
	      	  }
	      	  
	      	  //Close the input stream
	      	  input.close();
	    }
		catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }
	}
	
	//Clears the current set and gets the words
	public void getWords()
	{
		set.clear();
		getWords(new StringBuilder(""), new StringBuilder(in));
	}
	
	//Gets the current count of set. Testing purposes.
	public int getSetCount()
	{
		return set.size();
	}
	
	//Adds every combination of the letters to a hashset
	private void getWords(StringBuilder string, StringBuilder append)
	{
		//Base Case
		if(append.toString().isEmpty())
		{
			return;
		}
		
		//Recursive case
		//For each letter in append, append to string then recurse with string and append
		//without the appended letter
		for(int i = 0; i < append.length(); i++)
		{
			string.append(append.charAt(i));
			set.add(string.toString());
			getWords(string, new StringBuilder(append).deleteCharAt(i));
			string.deleteCharAt(string.length()-1);
		}
	}
	
	//Used to check if the word is an actual English word
	public boolean checkDictionary(String word)
	{
		return dictionary.contains(word);
	}
	
	//Sets the current word
	public void setWord(String word)
	{
		this.in = word;
	}
	
	//Gets the current word
	public String getWord()
	{
		return this.in;
	}
	
	//Prompts the user to enter a word
	public static void main(String[] args)
    {     
		boolean prompt = true;
        Scanner keyboard = new Scanner(System.in);
        ListAllWords a = new ListAllWords("");
        String input;
        String cont;

        while(prompt)
        {
	        System.out.print("Enter letters followed by a return: ");
	        input = keyboard.next();
	        input = input.toLowerCase();
	        input = input.trim();

	        if(!input.matches("[a-zA-Z]*"))	// Check if input is valid
	        {
	        	System.out.println("Only input A-Z or a-z\n");
	        	prompt = true;
	        }
	        else
	        {
		        a.setWord(input);
		        a.getWords();
		        for(String item : set)	//Check if it is a valid word and print if is
		        {
		        	if(a.checkDictionary(item))
		        		System.out.println(item);
		        }
		        System.out.println("\nDone! ");
		        
		        do{
		        	System.out.print("Would you like to continue? (y/n) ");
		        	cont = keyboard.next().toLowerCase().trim();
		        }while(!cont.matches("[y|n]"));
		        if(cont.matches("n"))
		        	prompt = false;
	        }
        }
        
        System.out.println("Goodbye");
        keyboard.close();
    }
}
