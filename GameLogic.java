package application;

import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Character> gesuchtesWort = new ArrayList<Character>();
	static String searchedWord = Main.searchedWord;
	static int lengthOfTheWord ;
	// METHOD) MENU		
		public static void menu() {
			
			// Output: Explanation of the game rules
			
			// the first player enters the word that the others have to look for		
			searchedWord = scan.next().toLowerCase();
			lengthOfTheWord = searchedWord.length();
			
			// First Output as String AND just to fill the ArrayList for the first time with number of "_"
			// String "wordoutput" won't be needed again after this loop
			String wordOutput = "";
			for (int x = 0; x < lengthOfTheWord; x++) {
					wordOutput+="_ ";
					gesuchtesWort.add('_');
			}
			System.out.println(wordOutput +"\n");
			
			// ### -------------- You have 12 attempts (12 Loops) Call the Method -------------- ###
			twelveRunsLoop();
		}
		
		// METHOD) 12 LOOPS ,then GAME OVER
		public static boolean twelveRunsLoop() {
			// Counter-Variables to count up or down
			int fehlschlaege = 0;
			int durchlauefe = 1;
			int zaehler1 = 12;
			// Start of the Loop (12 Runs)
			for (int i = 0; i <= 12; i++) {
				
				System.out.println("Versuchen Sie in dem Wort vorkommende Buchstaben zu erraten, Sie können noch "+zaehler1+""
						+ " Mal falsch liegen");
				// Output: How many Loops do you have left
				System.out.println("Das ist Ihr " + durchlauefe + " Versuch:");
				System.out.println("--------------------------------------------------------");
				// User-Input of a char or the whole word
				String versuch = scan.next().toLowerCase();
				char zeichen = versuch.charAt(0);
				// Output Word 
				boolean richtigGeraten = false; // important for the failure-counter
				for (int x = 0; x < lengthOfTheWord; x++) {
					if (versuch.equals(searchedWord)) {
						// whole word guessed
						System.out.println("Sie haben gewonnen, das Wort ist RICHTIG!");
						return true;
					}
					else if (zeichen != searchedWord.charAt(x)) {
						// when char is incorrect = continue
						continue;
					}
					else if (zeichen == searchedWord.charAt(x)){
						// when char is correct = continue
						gesuchtesWort.set(x, zeichen); 
						i--; // go 1 loop back in for-loop
						richtigGeraten = true;
					}
				}
				if (richtigGeraten == false) {
					// Failure: Print GalgMan with switch-case(Index of failxounter)
					printGalgMan(fehlschlaege); 
					fehlschlaege++; // failure-counter
				}
				
				// checks whether there are any gaps, if not then Variable allLettersAreGuessesYouWIN == true
				boolean allLettersAreGuessesYouWIN = true;
				for (int y = 0; y < lengthOfTheWord; y++) {
					if (gesuchtesWort.get(y) == '_'){
						allLettersAreGuessesYouWIN = false;
					}
				}
				// when all the letters have been guessed an there are no more gaps = WIN
				if (allLettersAreGuessesYouWIN == true) {
					System.out.println("\nSie haben gewonnen, alle Buchstaben wurden erraten");
					// Ouput of the correct word
					for (char element : gesuchtesWort) {
						System.out.print(element + " ");
					}
					return true;
				}
				
				//Console-Output of the word (with gaps),if you did not win
				System.out.println("\nKonsolenausgabe des Wortes");
				for (char element : gesuchtesWort) {
					System.out.print(element + " ");
				}	
				zaehler1--;	// attempt-Counter counts down
				if (i == 11) {
					System.out.println("Sie haben keine Versuche mehr GAME OVER!");
					return false;
				}
			durchlauefe++;
			}	
			return false;	
			
		}
		
		// METHOD PRINT PIC (switch-case)
		public static void printGalgMan(int fehlversuche) {
			switch (fehlversuche) {
			case 11:
				// 12. Try  END!!
				System.out.print("\n       ________________"
					 			+"\n     |  /            |"
					 			+"\n     | /           __|__  "
					 			+"\n     |/           (°  °)|      "
					 			+"\n     |             \\_o_/  "
					 			+"\n     |               |"
					 			+"\n     |              /|\\"
					 			+"\n     |             / | \\"
					 			+"\n     |               |    "
					 			+"\n     |              /\\"
					 			+"\n    / \\            /  \\"
					 			+"\n   /   \\          /    \\"
					 			+"\n  /     \\");
			break;
			
			case 10:
				// 11. Try
				System.out.print("\n       ________________"
			 					+"\n     |  /            |"
			 					+"\n     | /           __|__  "
			 					+"\n     |/           (°  °)|      "
			 					+"\n     |             \\_o_/  "
			 					+"\n     |               |"
			 					+"\n     |              /|\\"
			 					+"\n     |             / | \\"
			 					+"\n     |               |    "
			 					+"\n     |              /\\"
			 					+"\n    / \\            /  "
			 					+"\n   /   \\          /    "
			 					+"\n  /     \\");
			break;
			
			case 9:
				// 10. Try
				System.out.print("\n       ________________"
			 					+"\n     |  /            |"
			 					+"\n     | /           __|__  "
			 					+"\n     |/           (°  °)|      "
			 					+"\n     |             \\_o_/  "
			 					+"\n     |               |"
			 					+"\n     |              /|\\"
			 					+"\n     |             / | \\"
			 					+"\n     |               |    "
			 					+"\n     |                "
			 					+"\n    / \\              "
			 					+"\n   /   \\             "
			 					+"\n  /     \\");
			break;
			
			case 8:
				// 9. Try
				System.out.print("\n       ________________"
			 					+"\n     |  /            |"
			 					+"\n     | /           __|__  "
			 					+"\n     |/           (°  °)|      "
			 					+"\n     |             \\_o_/  "
			 					+"\n     |               |"
			 					+"\n     |              /|"
			 					+"\n     |             / |"
			 					+"\n     |               |"
			 					+"\n     |                "
			 					+"\n    / \\              "
			 					+"\n   /   \\             "
			 					+"\n  /     \\");
			break;
			
			case 7:
				// 8. Try
				System.out.print("\n       ________________"
			 					+"\n     |  /            |"
			 					+"\n     | /           __|__  "
			 					+"\n     |/           (°  °)|      "
			 					+"\n     |             \\_o_/  "
			 					+"\n     |               |"
			 					+"\n     |               |"
			 					+"\n     |               |"
			 					+"\n     |               |"
			 					+"\n     |                "
			 					+"\n    / \\              "
			 					+"\n   /   \\             "
			 					+"\n  /     \\");
			break;
			
			case 6:
				// 7. Try
				System.out.print("\n       ________________"
			 					+"\n     |  /            |"
			 					+"\n     | /           __|__  "
			 					+"\n     |/           (°  °)|      "
			 					+"\n     |             \\_o_/  "
			 					+"\n     |               "
			 					+"\n     |               "
			 					+"\n     |               "
			 					+"\n     |               "
			 					+"\n     |               "
			 					+"\n    / \\             "
			 					+"\n   /   \\            "
			 					+"\n  /     \\");
			break;
			
			case 5:
				// 6. Try
				System.out.print("\n       ________________"
								+"\n     |  /            |"
								+"\n     | /             |"
								+"\n     |/          "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n    / \\         "
								+"\n   /   \\        "
								+"\n  /     \\");	
			break;
			
			case 4:
				// 5. Try
			    System.out.print("\n       ________________"
			    				+"\n     |  /        "
			    				+"\n     | /         "
			    				+"\n     |/          "
			    				+"\n     |           "
			    				+"\n     |           "
			    				+"\n     |           "
			    				+"\n     |           "
			    				+"\n     |           "
			    				+"\n     |           "
			    				+"\n    / \\         "
			    				+"\n   /   \\        "
			    				+"\n  /     \\");	
			break;
			
		    case 3:
			    // 4. Try
			    System.out.print("\n       ________________"
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n     |           "
								+"\n    / \\         "
								+"\n   /   \\        "
				 				+"\n  /     \\");	
		    break;
		    
		    case 2:
				// 3. Try	  
				System.out.print("\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n     |           "
			 					+"\n    / \\         "
			 					+"\n   /   \\        "
			 					+"\n  /     \\");
			break;
		  	  
			case 1:  
				// 2. Try
				System.out.print("\n   /\\  "
							   	+"\n  /  \\  ");
			break;
			
			case 0:
				// 1. Try	
				System.out.print("\n   /  "
				              	+"\n  /    ");
		    break;
			}
		}
		

	
}
