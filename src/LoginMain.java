import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//Cork College of Commerce 2020/2021 Hyojin_Kim

public class LoginMain extends Application {

	static Stage window;
	public static int findIndex;
	public static int length;
	public static String user;
	public static String pin;
	
	public static TextField userIdText;
	public static TextField enterPassword;

	
public static void main(String[] args) {
	
	launch(args);	
	}

@Override
public void start(Stage primaryStage) {
	
	//main page
	window = primaryStage;
	
	primaryStage.setTitle("First page to log-in");
	Label welcome = new Label("Welcome to Holly's Vending Machine\n");
	Label message = new Label("Please log in or sign up"
			+ "\nIf you are manager, Please login with manager account\n\n\n\n\n\n");
	
	
	// Creating userId part to login
	Label userId = new Label();
	userId.setText("ID: \n"); 
	userIdText = new TextField(); 										
	userIdText.setPromptText("Email address");
	userIdText.setPrefWidth(180);
	userIdText.setMaxWidth(180);

	// Creating password part to login
	Label password = new Label();
	password.setText("PW: \n"); 
	enterPassword = new PasswordField(); 
	enterPassword.setPromptText("Pin number");
	enterPassword.setMaxWidth(180);

	// Creating button which will have function to check if login details are correct
	Button confirm = new Button();
	confirm.setText("Log in");
	
	confirm.setOnAction(e -> {
		ReaderFile.readTheFile("Registration.txt"); // Reading from the file
		checkUser(); // Check user name and password
		});

		
	// Create new account part
	Button signup = new Button("Sign Up"); 
	signup.setOnAction(e -> {
		CreateAccount.newAccount("Create New Account", "Enter your details");
		window.close();
	});
	
	// Help part, if help button is clicked, show the introduction of vending machine
	HBox hbox3 = new HBox();
	Button help = new Button("Help");
	help.setOnAction(e -> { // open the help window 
		getHelp();
	});
	help.setShape(new Circle(5)); // make a button circle shape
	VBox root = new VBox();
	root.setPadding(new Insets(10,10,10,10));
    root.setAlignment(Pos.CENTER);
   
    
    // Arrange
    HBox hbox1 = new HBox();
    HBox hbox2 = new HBox();
    hbox1.getChildren().addAll(userId, userIdText);
    hbox1.setAlignment(Pos.CENTER);
    
    hbox2.getChildren().addAll(password, enterPassword);
    hbox2.setAlignment(Pos.CENTER);
    
    hbox3.setAlignment(Pos.BOTTOM_RIGHT); 
	hbox3.getChildren().addAll(help); 
	
	
	//Styling nodes  
	root.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px; -fx-spacing: 5;"); 
	welcome.setStyle("-fx-font: normal bold 25px 'serif'; fx-spacing: 7;");
	message.setStyle("-fx-text-fill: black; fx-spacing: 7;");
	hbox1.setStyle("-fx-background-color:white; -fx-padding:10;");
	hbox2.setStyle("-fx-background-color:white; -fx-padding:10;");
	userId.setStyle("-fx-background-color:white; -fx-padding:10;");
	password.setStyle("-fx-background-color:white; -fx-padding:10;");
	confirm.setStyle("-fx-background-color:mintcream; fx-spacing: 10;");
	signup.setStyle("-fx-background-color:paleturquoise; fx-spacing: 14;");
	help.setStyle("-fx-background-color: lightcyan;"); //

	Scene scene = new Scene(root, 550, 400);
	root.getChildren().addAll(welcome, message, hbox1, hbox2, confirm, signup, hbox3);

	primaryStage.setScene(scene);
	primaryStage.show();

}

public static Alert getHelp() { // help button will show this message.

	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Help");
	alert.setHeaderText("Email address is your ID and pin number is PW");
	alert.setContentText(
			"If you don't want to create an account, \nyou can login with ID: 'guest@gmail.com'"
			+ " PW: '1234' \nGuest account only accept cash\n");
	alert.showAndWait();

	return alert;

}

public static void checkUser() { //Check the user name and show different display. 

	 for(int i = 0; i < ReaderFile.users.length ; i++){
		 
		 	// Get user id and pin number from ReaderFile class
		 	findIndex = ReaderFile.users[i].indexOf(":");	
			length = ReaderFile.users[i].length();
			user = (ReaderFile.users[i].substring(0, findIndex));
			pin = (ReaderFile.users[i].substring(length - 4));	

			// Check the Id and password
			if (userIdText.getText().equals(user) && enterPassword.getText().equals(pin)) {			
				 // Check if user id is a manager account  
				if(user.equals("manager@gmail.com")) {
					System.out.println("\nYou logged in with Manager account");			
					window.close();
					Manager.display();
				}
				 else {
					CreateAccount.getLoginConfirmation();
					System.out.println("\nYou logged in with User Name: " + user + "\n"); 																																	
					// Move to drink order scene
					Drinks.display(); 		
					}
				} 
			} // Loop end
			 if (!((userIdText.getText().equals(user) && enterPassword.getText().equals(pin))))
				 CreateAccount.getLoginError(); // Show the error message if information are incorrect	

		}
}

