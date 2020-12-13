import java.io.File;
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
import javafx.stage.Stage;

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
	
	static double moneyToManager= 0;
  
	public static void display() {
				
		//First message
		window = new Stage(); //Setting new Stage
		window.setTitle("Drinks Menu");
		Label welcome = new Label("Welcome to Holly's Vending Machine");
		Label message = new Label("Please select your Drink and Numbers, All of drinks is €1.20 each");
		
		//Drinks part
		Label drinksList = new Label("Drinks: ");
		listview =new ListView<>();
		listview.getItems().addAll("Coke","Diet Coke","Fanta Lemon","Fanta Oragne");
		listview.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

		
//		menu = new ComboBox<>();
//		menu.getItems().addAll("Coke","Diet Coke","7Up","Fanta Oragne");
		HBox drinksMenu = new HBox();
		drinksMenu.getChildren().addAll(drinksList, listview);
		drinksMenu.setAlignment(Pos.CENTER);
		
		//Number part
		Label numbers = new Label("Numbers: ");
		number = new ComboBox<>();
		number.getItems().addAll("1", "2", "3", "4", "5");
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
			cash();			
		});
		
		// Card payment button
		Button card = new Button(" Card ");
		card.setOnAction( e -> {
			try {
				show();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
		reportPart.setStyle("-fx-spacing: 12;");
		
		
		
		// Put everything into VBox
		VBox root = new VBox();
		root.setPadding(new Insets(10,10,5,10));
	    root.setAlignment(Pos.TOP_CENTER);
		
		// Styling nodes
		root.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px; -fx-spacing: 10;"); 
		welcome.setStyle("-fx-font: normal bold 25px 'serif'");
		message.setStyle("-fx-text-fill: black");
		
		Scene scene = new Scene(root,540,510); //Setting the screen
		root.getChildren().addAll(welcome, message, drinksMenu, drinksNumber, agreeCheck, payment,  reportPart); //Put everything into VBox
		
		window.setScene(scene);
		window.show();
	}
	

    public static void show() throws IOException{ // Print customer's choice with price

        ObservableList<String> list;
        list= listview.getSelectionModel().getSelectedItems();
        
        String numbers;
    	numbers = number.getSelectionModel().getSelectedItem();
      	
    	float price = (float) (Integer.parseInt(numbers) * 1.2);  
    	
        for( String ab : list ){  // check which drink is it and there is enough stock        
        	
        	switch(ab) {
         
	         case "Coke": 
	        	 if(coke >= (Integer.parseInt(numbers))) {
		        	 coke -= (Integer.parseInt(numbers));
		             System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
		        	 System.out.println("coke left: " + coke);
	        	 } else {
	         		 System.out.println("Sorry, Out of Stock!");
	        	 	 cokeOutOfStock = true; 
	        	 }
	        	 break;
	        	 
	         case "Diet Coke":
	        	 if(dietCoke >= (Integer.parseInt(numbers))) {
	        	 dietCoke -= (Integer.parseInt(numbers));
	        	 System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
	        	 System.out.println("Dite coke left: " + dietCoke);
	        	 } else {
	         		 System.out.println("Sorry, Out of Stock!");
	        	 	 dietCokeOutOfStock = true; 
	        	 }
	        	 break;
	        	 
	         case "Fanta Lemon":
	        	 if(fantaLemon >= (Integer.parseInt(numbers))) {
	        	 fantaLemon -= (Integer.parseInt(numbers));
	        	 System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
	        	 System.out.println("Fanta Lemon left: " + fantaLemon);
	        	 } else {
	         		 System.out.println("Sorry, Out of Stock!");
	         		fantaLemonOutOfStock = true; 
	        	 }
	        	 break;
	        	 
	         case "Fanta Oragne":
	        	 if(fantaOrange >= (Integer.parseInt(numbers))) {
	        	 fantaOrange-= (Integer.parseInt(numbers));
	        	 System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
	        	 System.out.println("Fanta Orange left: " + fantaOrange);
	        	 } else {
	         		 System.out.println("\n\nSorry, Out of Stock!");
	         		fantaOrangeOutOfStock = true; 
	        	 }
	        	 break;
	         }   // switch close           
	        } //for loop end 
        
        reportOutOfStock(); // send a report to manager if drinks are out of stock
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
    
    public static void cash() {
    	
    	String numbers;
    	numbers = number.getSelectionModel().getSelectedItem();
    	boolean loopEnd = false;
    	float price = (float) (Integer.parseInt(numbers) * 1.2); 
    	
    	// Ask to user to insert money using Scanner
    	Scanner sc = new Scanner(System.in);
    	System.out.println("\n\nYou select pay by Cash, Please insert €" + price + ": ");
    	float userInput = sc.nextFloat();
    	
    	
    	// if user put correct money, print 'Enjoy' message
    	if(userInput == price) {
    		System.out.println("Enjoy your drink!");
    		moneyToManager += userInput;
    	}
    	
    	
    	// if user put extra money, give a warning and ask do they want to keep ordering
    	if(userInput > price) { 
    		
    		float difference = userInput - price; 
    		short userAgree = 9;
    		while(!(userAgree == 0 || userAgree == 1)) {
	    		System.out.printf("Your total price is €%.2f, We dont' give you back the chagne €%.2f%n", price, difference);
	    		System.out.println("If it is ok, Please enter 1, if you want to cancle the order enter 0: ");
	    		userAgree = sc.nextShort();
    		}
    		
				switch (userAgree) {
				case 1: 
					System.out.println("Enjoy your drink!\n");
					moneyToManager += userInput;
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
    			}
	    		
    		if(userInput == price) {
		    	loopEnd = true;
		        System.out.println("Enjoy your drink!\n");
		    	moneyToManager += userInput;	    			
		    	}    			  			    	
    		}
    	}
    
    
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
    	

}
