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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreditCard {
	
	public static Stage window1;
	public static TextField cardNumText;
	public static TextField expireDate;
	public static TextField cvcCode;
	public static ComboBox<String> topup;
	

	public static void display(String title) {
		
		window1 = new Stage(); //Setting new Stage
		window1.setTitle(title);
		
		BorderPane p = new BorderPane(); //Setting BorderPane in order to position items easier on the screen
		p.setCenter(getVBox());//Calling the method and placing them on the center of the screen
	
		Scene scene = new Scene(p,500,430); //Setting the screen
		window1.setScene(scene);
		window1.show();
		
	}

	public static VBox getVBox() { //Method for the center layout of the screen
		
		Label message = new Label("Your Card Details");
		message.setStyle("-fx-font: normal bold 25px 'serif'; -fx-padding:12;  ");
		message.setAlignment(Pos.CENTER);
		
		VBox v = new VBox();
		v.setPadding(new Insets(10,10,10,10));
		v.setSpacing(10);
		
		
		// ask card number
		Label cardNum = new Label("Credit Card Number: ");
		cardNumText = new TextField();
		CreateAccount.getWidth(cardNumText); //set a size of text field, reuse the method from CreateAccount class
		cardNumText.setPromptText("16 digits only");
		Button validateCardNum = new Button(); 
		validateCardNum.setText("Validate");
		validateCardNum.setOnAction(e -> {
			if(isNumber(cardNumText)) {  //if card number is 16 digits and number, validate
				CreateAccount.getConfirmationBox();
			}else 
				CreateAccount.getErrorMessage();
		});
		HBox cardNumber = new HBox();
		cardNumber.getChildren().addAll(cardNum , cardNumText, validateCardNum );
		cardNumber.setStyle("-fx-padding:12; -fx-spacing: 7; -fx-background-color: lightblue; -fx-font-size: 15px;");
		validateCardNum.setStyle("-fx-background-color:floralwhite; -fx-padding:7; -fx-spacing: 7;");
		cardNumber.setAlignment(Pos.CENTER);
		
		// ask expire date
		Label expire = new Label("Expire Date: \t\t ");
		expireDate = new TextField();
		CreateAccount.getWidth(expireDate);//set a size of text field, reuse the method from CreateAccount class
		expireDate.setPromptText("mm/yy");
		Button validateExpire = new Button(); 
		validateExpire.setText("Validate");
		validateExpire.setOnAction(e -> {
			if(isExpire(expireDate)) {  //if card number is 16 digits and number, validate
				CreateAccount.getConfirmationBox();
			}else 
				CreateAccount.getErrorMessage();
		});
		HBox expireCard = new HBox();
		expireCard.getChildren().addAll(expire, expireDate, validateExpire);
		expireCard.setStyle("-fx-padding:12; -fx-spacing: 7; -fx-background-color: lightblue; -fx-font-size: 15px;");
		validateExpire.setStyle("-fx-background-color:floralwhite; -fx-padding:7; -fx-spacing: 7;");
		expireCard.setAlignment(Pos.CENTER);
		
		
		// ask cvc number
		Label cvc = new Label("CVC: \t\t\t");
		cvcCode = new TextField();
		CreateAccount.getWidth(cvcCode); //set a size of text field, reuse the method from CreateAccount class
		cvcCode.setPromptText("3 digits only");
		Button validateCvc= new Button(); 
		validateCvc.setText("Validate");
		validateCvc.setOnAction(e -> {
			if(isCvc(cvcCode)) {  //if card number is 16 digits and number, validate
				CreateAccount.getConfirmationBox();
			}else 
				CreateAccount.getErrorMessage();
		});
		HBox expireCvc= new HBox();
		expireCvc.getChildren().addAll(cvc,cvcCode, validateCvc);
		expireCvc.setStyle("-fx-padding:12; -fx-spacing: 7; -fx-background-color: lightblue; -fx-font-size: 15px;");
		validateCvc.setStyle("-fx-background-color:floralwhite; -fx-padding:7; -fx-spacing: 7;");
		expireCvc.setAlignment(Pos.CENTER);	
		
		// ask how much customer want to top up
		Label askTopup = new Label("\tTop Up Amount: ");
		topup = new ComboBox<>();
		topup.getItems().addAll("€10","€15","€20","€25");
		HBox topupCard = new HBox();
		topupCard.getChildren().addAll(askTopup, topup);
		topupCard.setStyle(" -fx-background-color: lightblue; -fx-font-size: 15px;");
		topupCard.setAlignment(Pos.CENTER_LEFT);	
		
		// submit button
		Button button = new Button("Submit");
		button.setStyle("-fx-background-color:floralwhite; -fx-padding:12; -fx-spacing: 17;");	
		button.setOnAction(e -> {
			CreateAccount.getConfirmationBox();
			try {
				saveTopup();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			window1.close();
			Drinks.display(); // move to drink order scene
		}); 
		
		HBox submit = new HBox();
		submit.getChildren().addAll(button);
		submit.setAlignment(Pos.CENTER);
		
		// put everything in V Box
	//	v.setAlignment(Pos.CENTER_LEFT);
		v.setStyle("-fx-background-color: lightblue; -fx-spacing: 15; -fx-font-size: 15px; -fx-padding:10;");
		v.getChildren().addAll(message, cardNumber, expireCard, expireCvc, topupCard, submit);
		
		return v;
	}
	
	//checking card number is 16digits
	public static boolean isNumber(TextField cardNumText) {
		int pinLength = cardNumText.getLength();
		String pinField = cardNumText.getText();
		
		if(pinLength == 16){
			if(!pinField.matches("[a-zA-Z\\s']+"))			
			return true;}
		
		else 
			System.out.println("Card Number has to be 16 digits and only Number");
			return false;
	}
	
	public static boolean isExpire(TextField expire ) {
		int expireLength = expireDate.getLength();
		String expireField = expireDate.getText();
		
		if(expireLength == 5){
			if(!expireField.matches("[a-zA-Z\\s']+"))			
			return true;}
		
		else 
			System.out.println("Expire date has to be mm/yy");
			return false;
	}
	
	public static boolean isCvc(TextField cvc) {
		int cvcLength = cvcCode.getLength();
		String cvcField = cvcCode.getText();
		
		if(cvcLength == 3){
			if(!cvcField.matches("[a-zA-Z\\s']+"))			
			return true;}
		
		else 
			System.out.println("Card Number has to be 16 digits and only Number");
			return false;
	}
	
	public static void saveTopup() throws IOException {
		String topupAmount;
		topupAmount = topup.getSelectionModel().getSelectedItem();		
		System.out.println("You top up " + topupAmount);
		
		FileWriter file = new FileWriter ("Topup.txt"); 
        PrintWriter text = new PrintWriter(file);
        text.println("Credit: "+ topupAmount + "\nUser: " +  CreateAccount.getEmail().getText());
        text.close();
        System.out.println("File path is: " + new File("Topup.txt").getAbsolutePath());
        
	}
}




