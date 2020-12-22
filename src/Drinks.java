import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
// Cork College of Commerce 2020/2021 Hyojin_Kim

public class Drinks {

	public static Stage window;
	public static ComboBox<String> menu;  
	public static ComboBox<String> number;
	public static CheckBox agreeCheck;
	public static CheckBox reportCheck;
	public static TextField report;
	public static ListView<String> listview;
	
	static int coke = 20;
	static int dietCoke = 20;
	static int fantaLemon = 20;
	static int fantaOrange = 20;
	
	static boolean cokeOutOfStock = false;
	static boolean dietCokeOutOfStock = false;
	static boolean fantaLemonOutOfStock = false;
	static boolean fantaOrangeOutOfStock  = false;
	
	static double moneyToManager = 0;
	static int countUser = 0;

  
	public static void display() {
				
		//First message
		window = new Stage(); //Setting new Stage
		window.setTitle("Drinks Menu");
		Label welcome = new Label("Welcome to Holly's Vending Machine");
		Label message = new Label("Please select your Drink and Numbers");
		Label message2 = new Label("All of drinks is €1.20 each");
		
		
		//Drinks part
		Label drinksList = new Label("Drinks: ");
		listview =new ListView<>();
		listview.getItems().addAll("Coke","Diet Coke","Fanta Lemon","Fanta Oragne");
		listview.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

		HBox drinksMenu = new HBox();
		drinksMenu.getChildren().addAll(drinksList, listview);
		drinksMenu.setAlignment(Pos.CENTER);
		
		
		//Number part
		Label numbers = new Label("Numbers: ");
		number = new ComboBox<>();
		number.getItems().addAll("1", "2", "3", "4", "5", "10");
		
		HBox drinksNumber = new HBox();
		drinksNumber.getChildren().addAll(numbers, number);
		drinksNumber.setAlignment(Pos.CENTER);
		
		
		// no change agreement check box
		agreeCheck = new CheckBox("I understand that This vending machine will NOT dispense any change");
		agreeCheck.setStyle("-fx-background-color: azure; -fx-padding: 10;");
		agreeCheck.setAlignment(Pos.CENTER);
		
		
		// Cash payment button
		Button cash = new Button(" Cash ");
		cash.setOnAction(e -> {
			try {
				show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		});
		
		// Card payment button
		Button card = new Button(" Card ");
		card.setOnAction( e -> {
			try {
				show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		cash.setStyle("-fx-background-color: ghostwhite;");
		card.setStyle("-fx-background-color: lavender; ");
		HBox payment = new HBox();
		payment.getChildren().addAll(cash, card);
		payment.setAlignment(Pos.CENTER);
		payment.setStyle("-fx-spacing: 12; -fx-padding: 6;");
		
				
		//report part		
		Label reportCheck = new Label("Write your report if there's any problem");
		report = new TextField();
		CreateAccount.getWidth(report); 
		report.setPromptText("You can reprot 'Out Of Order' or any problem" );
		report.setMaxWidth(350);
		Button reportBtn = new Button("Report");
		reportBtn.setOnAction(e -> { // Send the report to manager
			try {
				reportToManager();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}); 		
		reportBtn.setStyle("-fx-background-color: paleturquoise;");
		reportBtn.setAlignment(Pos.CENTER);
		VBox reportPart = new VBox();
		reportPart.getChildren().addAll(reportCheck, report,reportBtn);
		reportPart.setPadding(new Insets(5,5,5,5));
		reportPart.setAlignment(Pos.BOTTOM_CENTER);
		reportPart.setStyle("-fx-spacing: 15; -fx-background-color:white; -fx-padding:10");
		
		
		// Help button		
		Button helpBtn = new Button("Help");
		helpBtn.setOnAction(e -> { 
			getHelp();
		});
		helpBtn.setShape(new Circle(5));
		HBox help = new HBox();
		help.getChildren().addAll(helpBtn);
		help.setAlignment(Pos.BOTTOM_RIGHT);
		helpBtn.setStyle("-fx-background-color: lightcyan;");
		
		// Put everything into VBox
		VBox root = new VBox();
		root.setPadding(new Insets(10,10,5,10));
	    root.setAlignment(Pos.TOP_CENTER);
		
		// Styling nodes
		root.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px; -fx-spacing: 10;"); 
		welcome.setStyle("-fx-font: normal bold 25px 'serif'");
		message.setStyle("-fx-text-fill: black");
		message2.setStyle("-fx-font-weight: 700; -fx-text-fill: orangered;");
			
		//Put everything into VBox
		Scene scene = new Scene(root,590,550); //Setting the screen
		root.getChildren().addAll(welcome, message, message2, drinksMenu, drinksNumber, agreeCheck, payment,  reportPart, help); 
		
		window.setScene(scene);
		window.show();
	}
	
	
	// Print customer's choice and price
    public static void show() throws IOException{ 

        ObservableList<String> list;
        list= listview.getSelectionModel().getSelectedItems();
        
        String numbers;
    	numbers = number.getSelectionModel().getSelectedItem();
      	
    	// If user didn't select drinks or number, show the error message 
    	if(numbers == null) {
    		System.out.println("Please select number of drink");
    		CreateAccount.getLoginError();
    	} else if (list.isEmpty()) {
    		System.out.println("Please select a drink");
    		CreateAccount.getLoginError();
    	}
    	
    	float price = (float) (Integer.parseInt(numbers) * 1.2);  // calculate the drinks price
    	
        for( String ab : list ){  // check which drink is it and there is enough stock        
        	System.out.println("\n------- Your order -------");
        	switch(ab) {
         
	         case "Coke": 
	        	 if(coke >= (Integer.parseInt(numbers))) {
		        	 coke -= (Integer.parseInt(numbers));
		             System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
		             cash();
		        	 System.out.println("coke left: " + coke);
	        	 } else {
	         		 System.out.println("\nSorry, Out of Stock!");
	        	 	 cokeOutOfStock = true; 
	        	 	 reportOutOfStock(); // send a report to manager if drinks are out of stock
	        	 	 oufOfStock(); // alert message for out of stock
	        	 }	
	        	 break;
	        	 
	         case "Diet Coke":
	        	 if(dietCoke >= (Integer.parseInt(numbers))) {
	        	 dietCoke -= (Integer.parseInt(numbers));
	        	 System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
	        	 cash();
	        	 System.out.println("Dite coke left: " + dietCoke);
	        	 } else {
	         		 System.out.println("\nSorry, Out of Stock!");
	        	 	 dietCokeOutOfStock = true; 
	        	 	 reportOutOfStock();
	        	 	 oufOfStock();
	        	 }
	        	 break;
	        	 
	         case "Fanta Lemon":
	        	 if(fantaLemon >= (Integer.parseInt(numbers))) {
	        	 fantaLemon -= (Integer.parseInt(numbers));
	        	 System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
	        	 cash();
	        	 System.out.println("Fanta Lemon left: " + fantaLemon);
	        	 } else {
	         		 System.out.println("\nSorry, Out of Stock!");
	         		fantaLemonOutOfStock = true; 
	         		reportOutOfStock();
	        	 	oufOfStock();
	        	 }
	        	 break;
	        	 
	         case "Fanta Oragne":
	        	 if(fantaOrange >= (Integer.parseInt(numbers))) {
	        	 fantaOrange-= (Integer.parseInt(numbers));
	        	 System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
	        	 cash();
	        	 System.out.println("Fanta Orange left: " + fantaOrange);
	        	 } else {
	         		 System.out.println("\nSorry, Out of Stock!");
	         		 fantaOrangeOutOfStock = true; 
	         		 reportOutOfStock();
	         		 oufOfStock();
	        	 }
	        	 break;
	         }   // switch close           
	        } //for loop end 

    }

    
    // when user choose pay by cash
    public static void cash() {
    	
    	String  numbers = number.getSelectionModel().getSelectedItem();
    	boolean loopEnd = false; 
    	
    	float price = (float) (Integer.parseInt(numbers) * 1.2); 
    	
    	// Ask to user to insert money using Scanner
    	Scanner sc = new Scanner(System.in);
    	System.out.println("\nYou select pay by Cash, Please insert €" + price + ": ");
    	float userInput = sc.nextFloat();
    	
    	
    	// if user put correct money, print 'Enjoy' message
    	if(userInput == price) {
    		System.out.println("\nEnjoy your drink!");
    		moneyToManager += userInput;
    		countUser++;
    		enjoy(); // order confirm message
    	}
    	
    	
    	// if user put extra money, give a warning and ask do they want to keep ordering
    	if(userInput > price) { 
    		
    		float difference = userInput - price; 
    		short userAgree = 9;
    		while(!(userAgree == 0 || userAgree == 1)) { // ask to user agreement that there's no change back 
	    		System.out.printf("Your total price is €%.2f, We dont' give you back the chagne €%.2f%n", price, difference);
	    		System.out.println("If it is ok, Please enter 1, if you want to cancle the order enter 0: "); // get input between 1 or 0
	    		userAgree = sc.nextShort();
    		}
    		
				switch (userAgree) {
				case 1: 
					System.out.println("Enjoy your drink!\n");
					moneyToManager += userInput;
					countUser++;
					enjoy(); // order confirm message
					break;
					
				case 0:
					System.out.println("Your order has been canceled\n");
					break;
					
				default: // made a default just for in case while loop is not working
					System.out.println("Please select between 0 and 1\n"); 
					break;
				}
    		}

    	
    	// if user put lower money, ask to put the difference
    	if (userInput < price) { 
    		
    		float diff = price - userInput;   		
    		System.out.printf("Your total price is €%.2f, Please insert €%.2f more : ", price, diff);
    		float rest = sc.nextFloat();
    		userInput += rest;
    		
    		while(!(userInput == price)) {	
    				diff = price - userInput; 
	    			System.out.printf("Not correct money, Please insert €%.2f more : ", diff);
	    			rest = sc.nextFloat();
	        		userInput += rest;
	        		countUser++;
    			}
	    		
    		if(userInput == price) {
		    	loopEnd = true;
		        System.out.println("Enjoy your drink!\n");
		    	moneyToManager += userInput;
		    	enjoy();
		    	}    			  			    	
    		}
    	}
    
    
    // Method to write a report which drink is out of stock
    @SuppressWarnings("resource")
	public static void reportOutOfStock() throws IOException { 

    	FileWriter file = new FileWriter ("Report.txt"); 
    	
    	if (cokeOutOfStock == true) {
    		file.write("Coke is Out of Stock");
    		file.close();
    		System.out.println("Successfully Out of stock report wrote to the file.\n");
    	}
    	if (dietCokeOutOfStock == true) {
    		file.write("Diet Cork is Out of Stock");
    		file.close();
    		System.out.println("Successfully Out of stock report wrote to the file.\n");
    	}
    	if (fantaLemonOutOfStock == true) {
    		file.write("Fanta Lemon is Out of Stock");
    		file.close();
    		System.out.println("Successfully Out of stock report wrote to the file.\n");
    	}
    	if (fantaOrangeOutOfStock == true) {
    		file.write("Fanta Orange is Out of Stock");  
    		file.close();
    		System.out.println("Successfully Out of stock report wrote to the file.\n");
    	}
    }
    
  

    // pay by card option, have a problem from line 376
    public static void card() {
    	
    	String numbers = number.getSelectionModel().getSelectedItem();
    	float price = (float) (Integer.parseInt(numbers) * 1.2); 
    	System.out.println("\nYou select pay by Card, Total Price is €" + price);
    	
    	// read 'Topup.txt' file to check a credit		
    	String[] readCredit = new String[3];
    	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Topup.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String line =" ";
		
		//Read through the file			
		int counter = 0, length = 0;
		while (line != null) {
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			readCredit[counter] = line;
			
			if(line != null)
				counter += 1;
		}
		System.out.println("\nYour credit is " + readCredit[0]);
		String s = readCredit[0];
		float f = Float.parseFloat(s);
		System.out.println(f);
    }
    

    // when user write a report, it will be saved to Report.tet file and manager can see the user's report
	public static void reportToManager() throws IOException { 
    	
        FileWriter file = new FileWriter ("Report.txt"); 
        PrintWriter text = new PrintWriter(file);
        System.out.println("File path is: " + new File("Report.txt").getAbsolutePath());

            text.println(report.getText()); //set new user details            
            Alert alert1 = new Alert(AlertType.CONFIRMATION);
            alert1.setTitle("Report success");
    		alert1.setHeaderText("Thank you for your report");
    		alert1.setContentText("Your report has been send to manager successfully");
    		alert1.showAndWait();

            text.close();  
        }

	
	// help message for user 
    public static Alert getHelp() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Help");
    	alert.setHeaderText("Choose the drink and number of drink");
    	alert.setContentText("If you pay by cash, please insert correnct amount"
    			+ "\nThis machine does not give chagne");
    	alert.showAndWait();

    	return alert;
    }
    
    // if order went through order confirm message will pop up
    public static Alert enjoy() {
    	Alert enjoy= new Alert(AlertType.CONFIRMATION);
    	enjoy.setTitle("Order Success!");
    	enjoy.setContentText("Enjoy your drink :)");
    	enjoy.showAndWait();
    	return enjoy;
    }
    
 // if the drink is out of stock, sold out message will pop up to customer
    public static Alert oufOfStock() {
    	Alert out = new Alert(AlertType.WARNING);
    	out.setTitle("Out Of Stock!");
    	out.setContentText("Sorry, your drink is out of stock \nThe report has been send to manager successfully");
    	out.showAndWait();
    	return out;
    }
}
