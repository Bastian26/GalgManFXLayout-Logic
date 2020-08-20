package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Character> gesuchtesWort = new ArrayList<Character>();
	static String searchedWord;
	static int lengthOfTheWord;
	
	static String letterFX;
	static String tryCompleteWordFX;
	
	static int fehlschlaege = 0;
	static int durchlauefe = 1;
	static int zaehler1 = 12;
	static char zeichen;
	
	static HBox hBox0 = new HBox();
	
	static Image imgWhite = new Image (Main.class.getResource("GalgPic0.png").toExternalForm());
	static Image imgStart = new Image (Main.class.getResource("GalgPic1.png").toExternalForm());
	//GalgeMännchen-Bilder
	static Image imgCase0 = new Image (Main.class.getResource("GalgPicCase0.png").toExternalForm());
	static Image imgCase1 = new Image (Main.class.getResource("GalgPicCase1.png").toExternalForm());
	static Image imgCase2 = new Image (Main.class.getResource("GalgPicCase2.png").toExternalForm());
	static Image imgCase3 = new Image (Main.class.getResource("GalgPicCase3.png").toExternalForm());
	static Image imgCase4 = new Image (Main.class.getResource("GalgPicCase4.png").toExternalForm());
	static Image imgCase5 = new Image (Main.class.getResource("GalgPicCase5.png").toExternalForm());
	static Image imgCase6 = new Image (Main.class.getResource("GalgPicCase6.png").toExternalForm());
	static Image imgCase7 = new Image (Main.class.getResource("GalgPicCase7.png").toExternalForm());
	static Image imgCase8 = new Image (Main.class.getResource("GalgPicCase8.png").toExternalForm());
	static Image imgCase9 = new Image (Main.class.getResource("GalgPicCase9.png").toExternalForm());
	static Image imgCase10 = new Image (Main.class.getResource("GalgPicCase10.png").toExternalForm());
	static Image imgCase11 = new Image (Main.class.getResource("GalgPicCase11.png").toExternalForm());
	
	

	
    static ImageView imgView1 = new ImageView(imgWhite);
	
    @Override
    public void start(Stage stage) {
    	
		try {
			stage.setTitle("Galg-Man-Game");		
			VBox root = new VBox();
			root.setAlignment(Pos.TOP_CENTER);
			root.setStyle("-fx-background-color: #FAEBD7;");
			
			//String javaVersion = System.getProperty("java.version");
	        //String javafxVersion = System.getProperty("javafx.version");
			
			// Game-Info und Spielregeln Ausgabe/Überschrift
	        Label label = new Label("Willkommen zu GalgMan!"
					+ "\n-----------------------------------------------------------------------------------------"
					+ "\n--# SPIELREGELN (2-Spieler) #--"
					+ "\nDer 1 Spieler muss ein zu suchendes Wort für die Mitspieler eingeben (je länger, umso schwieriger!)."
					+ "\nDer 2 Spieler kann entweder einen Buchstaben erraten oder direkt versuchen, das gesamte gesuchte Wort einzugeben."
					+ "\nSollte eines der beiden Möglichkeiten nicht zutreffen, wird das Galgenmännchen aufgebaut, sobald dieses vollständig"
					+ "\nzu sehen ist, haben Sie verloren! Viel Spaß"
					+ "\n-----------------------------------------------------------------------------------------"
					+ "\nErster Spieler jetzt bitte ein zu suchendes Wort eingeben!");  
	        label.setStyle("-fx-font-weight: bold");
	        StackPane sp1 = new StackPane(label);
	        root.getChildren().add(sp1);
	        
	        // Bild einfügen (Später 12 Bilder für alle Galgenmännchen-Schritte)
//	        Image img1 = new Image (Main.class.getResource("Probe.png").toExternalForm());
//	        ImageView imgView1 = new ImageView(img1);
	       
	        hBox0.getChildren().add(imgView1);
	        hBox0.setAlignment(Pos.CENTER);
	        root.getChildren().add(hBox0);
	        imgView1.setFitHeight(350);
	        imgView1.setFitWidth(350);
	        
	        // HBox anlegen ### (Darin befinden sich ein label(Später taucht es aus und zeigt das Wort mit Leerstellen "_" und ein TEXTFIELD für
	        // die erste Worteingabe (Das zu Suchende Wort) + Button zum Bestätigen
	        HBox hBox1 = new HBox();
	        Label label1 = new Label("Show Word"); // Hier wird Wort später angezeigt
	        label1.setFont(Font.font ("Verdana", 30));
	        label1.setVisible(false); //VERBERGEN
	        
	        TextField wordFirstInput = new TextField(""); 
	        wordFirstInput.setPromptText("Hier zu suchendes Wort eingeben");
	        wordFirstInput.setMinWidth(200);
	        hBox1.getChildren().add(wordFirstInput);
	        Button bt1 = new Button();
	        bt1.setText("Bestätigen");
	        bt1.setMinWidth(100);
	        hBox1.getChildren().add(bt1);
	        hBox1.setAlignment(Pos.CENTER);
	        
	        root.getChildren().add(label1);
	        root.getChildren().add(hBox1);
	        
	        // ENDE von Hbox ###
	        //----------------------------------------------------------------------------------------
	        //--- Grid View (Tabellenartiges Layout)
	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(10, 10, 10, 10)); // Stelle Padding für Grid ein
	        grid.setVgap(3); // Grid hat 2 Zeilen
	        grid.setHgap(3); // Und 3 Spalten
	       
	        //GRID-LAYOUT ###
	        //Labelfelder für Eingabefelder (sozusagen Überschriften) (1.Spalte)
	        Label inputCompleteWordLabel = new Label("Komplettes Wort eingeben:");
	        GridPane.setConstraints(inputCompleteWordLabel, 0, 0);
	        
	        Label inputLetterLabel = new Label("Buchstaben eingeben:");
	        GridPane.setConstraints(inputLetterLabel, 0, 1); //1 rechts
	        
	        //Eingabefelder (2. Spalte
	        TextField inputCompleteWordTF = new TextField();
	        GridPane.setConstraints(inputCompleteWordTF, 1, 0);
	        
	        TextField inputLetterTF = new TextField();
	        GridPane.setConstraints(inputLetterTF, 1, 1);
	        
	        //Buttons (3. Spalte)
	        Button inputCompleteWordBTN = new Button();
	        inputCompleteWordBTN.setText("Bestätige");
	        GridPane.setConstraints(inputCompleteWordBTN, 2, 0);
	        inputCompleteWordBTN.setMinWidth(100);
	        
	        Button inputLetterBTN = new Button();
	        inputLetterBTN.setText("Bestätige");
	        GridPane.setConstraints(inputLetterBTN, 2, 1);
	        inputLetterBTN.setMinWidth(100);
	      
	        // Füge Elemente dem GRID hinzu und Zentriere diese
	        grid.getChildren().addAll(inputCompleteWordLabel, inputLetterLabel, inputCompleteWordTF, inputLetterTF, inputCompleteWordBTN, inputLetterBTN);
	        //grid.setGridLinesVisible(true);
	        grid.setAlignment(Pos.CENTER);
	        
	        //Grid-Spaltenbreite
	        grid.getColumnConstraints().add(new ColumnConstraints(180)); // column 0 is 200 wide
	        grid.getColumnConstraints().add(new ColumnConstraints(220)); // column 1 is 200 wide
	        grid.getColumnConstraints().add(new ColumnConstraints(180)); // column 2 is 200 wide
	        grid.setVisible(false); //VERBERGEN
	       
	        root.getChildren().add(grid);
	        // ENDE GRID ###
	        
	        imgView1.requestFocus(); //Cursor soll nicht in Feld sein beim Start //TODO funzt nicht (Initial focus)
	        Scene scene = new Scene(root, 670, 700);       
	        stage.setScene(scene);
	        stage.show();
	        
//	        GameLogic gL = new GameLogic();
//	        GameLogic.menu();
//	        GameLogic.twelveRunsLoop();
	        
	        
	        // EventHandler:
	        bt1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                //label.setText(new Date().toString());
	            	
	            	// Verborgene Elemente anzeigen
	                label1.setVisible(true);
	                grid.setVisible(true);
	                // Eingegebenes Suchwort aus dem Textelement speichern und dieses verbergen
	                searchedWord = wordFirstInput.getText().toLowerCase();
	                wordFirstInput.setVisible(false);
	                bt1.setVisible(false);
	                lengthOfTheWord = searchedWord.length();
	                
	                String wordOutput = "";
	    			for (int x = 0; x < lengthOfTheWord; x++) {
	    					wordOutput+="_ ";
	    					gesuchtesWort.add('_');
	    			}
	    			label1.setText(wordOutput);
	                
	                // Alter Window
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Information");
	                alert.setHeaderText(null);
	                String alertText = "das gesuchte Wort hat " + lengthOfTheWord + " Buchtstaben";
	                alert.setContentText(alertText);
	                alert.showAndWait();
	                
	                //Startbild anzeigen
	                hBox0.getChildren().remove(imgView1);
	                imgView1 = new ImageView(imgStart);
	                imgView1.setFitHeight(350);
	    	        imgView1.setFitWidth(350);
	                hBox0.getChildren().add(imgView1);
	                
	                
	                
	                
	            }
	        });
	        
	        inputCompleteWordBTN.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                // Eingegebenes Suchwort aus dem Textelement speichern und dieses verbergen
	                tryCompleteWordFX = inputCompleteWordTF.getText().toLowerCase();
					zeichen = tryCompleteWordFX.charAt(0);
	                
					
						
						if (fehlschlaege == 11) {
							printGalgMan(fehlschlaege);
							Alert alert = new Alert(AlertType.INFORMATION);
			                alert.setTitle("Information");
			                alert.setHeaderText(null);
			                String alertText = "Sie haben keine Versuche mehr, VERLOREN!";
			                alert.setContentText(alertText);
			                alert.showAndWait();
			                System. exit(0);
						}
						else if (tryCompleteWordFX.equals(searchedWord)) {
							// whole word guessed
							// Alter Window
			                Alert alert = new Alert(AlertType.INFORMATION);
			                alert.setTitle("Information");
			                alert.setHeaderText(null);
			                String alertText = "Sie haben das richtige Wort erraten, GEWONNEN!";
			                alert.setContentText(alertText);
			                alert.showAndWait();
			                System. exit(0);
						}
						else {
							printGalgMan(fehlschlaege); 
							fehlschlaege++; // failure-counter
							inputCompleteWordTF.setText("");
						}
	            }
	        });
	        inputLetterBTN.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                //label.setText(new Date().toString());
	            	letterFX = inputLetterTF.getText().toLowerCase();
	            	zeichen = letterFX.charAt(0);
	            	
	            	boolean richtigGeraten = false; // important for the failure-counter
					for (int x = 0; x < lengthOfTheWord; x++) {
						boolean buchstabenNichtMehrfachVorhanden = true;
						if (zeichen != searchedWord.charAt(x)) {
							// when char is incorrect = continue
							continue;
						}
						else if (zeichen == searchedWord.charAt(x)){
							// when char is correct = continue
							
							gesuchtesWort.set(x, zeichen);
							String zwischenText = "";
							for (int i = 0; i < gesuchtesWort.size(); i++) {
								zwischenText += gesuchtesWort.get(i);
							}
							label1.setText(zwischenText);
							richtigGeraten = true;
							
				            Alert alert = new Alert(AlertType.INFORMATION);
					        alert.setTitle("Information");
					        alert.setHeaderText(null);
					        String alertText = "Sie haben einen richtigen Buchstaben erraten";
					        alert.setContentText(alertText);
					        alert.showAndWait();
					        inputLetterTF.setText("");
				            
				            
				         // checks whether there are any gaps, if not then Variable allLettersAreGuessesYouWIN == true
							boolean allLettersAreGuessesYouWIN = true;
							for (int y = 0; y < lengthOfTheWord; y++) {
								if (gesuchtesWort.get(y) == '_'){
									allLettersAreGuessesYouWIN = false;
								}
							}
							// when all the letters have been guessed an there are no more gaps = WIN
							if (allLettersAreGuessesYouWIN == true) {
								Alert alert1 = new Alert(AlertType.INFORMATION);
				                alert.setTitle("Information");
				                alert.setHeaderText(null);
				                String alertText1 = "Sie haben alle Buchstaben erraten erraten, GEWONNEN!";
				                alert.setContentText(alertText1);
				                alert.showAndWait();
				                System. exit(0);
								// Ouput of the correct word
								
							}
						}
					}
					//------------------------------------
					if (richtigGeraten == false) {
						// Failure: Print GalgMan with switch-case(Index of failcounter)
						printGalgMan(fehlschlaege); 
						fehlschlaege++; // failure-counter
						inputLetterTF.setText("");
					}
					
					
	            }
	        });
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
    }
    
    //MAIN-Methode
    public static void main(String[] args) {
        launch(); 
        
    }
 // METHOD Verändert das Bild je nach übergebenen Fehlversuchen (switch-case)
 		public static void printGalgMan(int fehlversuche) {
 			switch (fehlversuche) {
 			case 11:
 				// 12. Try  END!!
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase11);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 10:
 				// 11. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase10);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 9:
 				// 10. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase9);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 8:
 				// 9. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase8);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 7:
 				// 8. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase7);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 6:
 				// 7. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase6);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 5:
 				// 6. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase5);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);	
 			break;
 			
 			case 4:
 				// 5. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase4);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);	
 			break;
 			
 		    case 3:
 			    // 4. Try
 		    	hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase3);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			    
 		    break;
 		    
 		    case 2:
 				// 3. Try	  
 		    	hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase2);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 		  	  
 			case 1:  
 				// 2. Try
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase1);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 			break;
 			
 			case 0:
 				// 1. Try	
 				hBox0.getChildren().remove(imgView1);
                imgView1 = new ImageView(imgCase0);
                imgView1.setFitHeight(350);
    	        imgView1.setFitWidth(350);
                hBox0.getChildren().add(imgView1);
 		    break;
 			}
 		}
   
    
 

}

// https://www.youtube.com/watch?v=9R_JDwHikrU
//https://www.youtube.com/watch?v=GLrHqBEcBWE
//--module-path "C:\Java(Fx)\JavaFXPog\javafx-sdk-14.0.1\lib" --add-modules javafx.controls,javafx.fxml