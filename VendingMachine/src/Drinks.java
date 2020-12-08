import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		
		
		// payment button
		Button cash = new Button(" Cash ");
		cash.setOnAction( e -> show());
		
		Button card = new Button(" Card ");
		card.setOnAction( e -> show());
		
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
		reportPart.setStyle("-fx-spacing: 12; -fx-background-color: mintcream;");
		
		
		
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
	

    public static void show(){ // Print customer's choice with price

        ObservableList<String> list;
        list= listview.getSelectionModel().getSelectedItems();
        
        String numbers;
    	numbers = number.getSelectionModel().getSelectedItem();

    	float price = (float) (Integer.parseInt(numbers) * 1.2);  
    	
        for( String ab : list ){
            System.out.println("You select " + numbers + " of " + ab + "\nTotal Price is €" + price);
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
