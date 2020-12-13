import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

import com.sun.jdi.Field;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CreateAccount {
	
	private static TextField firstNameText;
	private static TextField lastNameText;
	private static TextField DOBText;
	private static TextField genderText;
	private static TextField emailText;
	private static TextField pinText;
	private static TextField mobileText;
	private static TextField addressText;
	private static TextField addressText2;
	private static TextField addressText3;
	private static ComboBox<String> countyCombo;  
	private static ComboBox<String> counties;
	public static CheckBox agree;
	
	public static void newAccount (String title, String message) {
		
		Stage window2 = new Stage();
		window2.setTitle(title);
		window2.initModality(Modality.APPLICATION_MODAL);
		Label welcome = new Label(message);
		
		HBox header = new HBox();
		header.getChildren().addAll(welcome);
		
		
		//Sign up form start, first name 
		Label firstName= new Label("Enter your first name:\t");
		firstNameText = new TextField();
		
		getWidth(firstNameText);  //Setting width of textfield
		Button validateFirstName = new Button();
		validateFirstName.setText("Validate");		
		validateFirstName.setOnAction(e -> {		 
			if(isAlphabetic(firstNameText)) { //If the first name is only alphabetic, it will be accepted				
				getConfirmationBox(); 
			}else 
				getErrorMessage(); 				  
		});
		HBox fname = new HBox();
		fname.getChildren().addAll(firstName, firstNameText, validateFirstName);
		fname.setAlignment(Pos.CENTER_LEFT);
		validateFirstName.setStyle("-fx-background-color: linen;");
		fname.setStyle("-fx-spacing: 7;");
		
		//last name	
		Label lastName = new Label("Enter your last name:\t"); 	
		lastNameText = new TextField();  
		getWidth(lastNameText); 
		
		Button validateLastName = new Button(); 
		validateLastName.setText("Validate");
		validateLastName.setOnAction(e -> {
			if(isAlphabetic(lastNameText)) { 
				getConfirmationBox();
			}else 
				getErrorMessage();
		});
		
		HBox lname = new HBox();
		lname.getChildren().addAll(lastName, lastNameText, validateLastName);
		lname.setAlignment(Pos.CENTER_LEFT);
		validateLastName.setStyle("-fx-background-color: linen;");
		lname.setStyle("-fx-spacing: 7;");
		
		
		//Date of Birth
		Label DOB = new Label("Date of Birth:\t\t\t"); 
		DOBText = new TextField(); 		
		DOBText.setPromptText("yyyy-mm-dd");
		Button validateDOB = new Button(); 	
		validateDOB.setText("Validate");		
		validateDOB.setOnAction(e -> 
			checkAge()
		); 
		validateDOB.setStyle("-fx-background-color: linen;");
		
		HBox birth = new HBox();
		birth.getChildren().addAll(DOB,DOBText, validateDOB);		
		birth.setAlignment(Pos.CENTER_LEFT);
//		validateDOB.setStyle("-fx-background-color: linen;");
		birth.setStyle("-fx-spacing: 7;");
		
		
		//Gender 
		Label askGender = new Label ("Please select your gender:");
		ToggleGroup group = new ToggleGroup();
		
	    RadioButton maleButton = new RadioButton("Male");
	    maleButton.setToggleGroup(group);
	    maleButton.setSelected(true);
	    
        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setToggleGroup(group);
        
        RadioButton notToSayButton = new RadioButton("I prefer not to say");
        notToSayButton.setToggleGroup(group);
        
        genderText =  new TextField();
        HBox gender = new HBox();
        gender.getChildren().addAll(askGender, maleButton, femaleButton, notToSayButton);
        gender.setAlignment(Pos.CENTER_LEFT);
        gender.setStyle("-fx-spacing: 13;");
        
        
        //Email
        Label email = new Label("Email:\t\t");
		emailText = new TextField();
		getWidth(emailText);
		HBox mail= new HBox();
		mail.getChildren().addAll(email, emailText);
		mail.setAlignment(Pos.CENTER_LEFT);
		mail.setStyle("-fx-spacing: 7;");
		
		
        //Pin
		Label pin = new Label("PIN number:\t");
		pinText = new TextField();
		pinText.setPromptText("4 digits");
		getWidth(pinText);
        
        Button validatePin = new Button(); 
        validatePin.setText("Validate");
        validatePin.setOnAction(e -> {
			if(isNumber(pinText)) { 
				getConfirmationBox();
			}else 
				getErrorMessage();
		});
        
        HBox pinNumber= new HBox();
		pinNumber.getChildren().addAll(pin,pinText,validatePin);
        pinNumber.setAlignment(Pos.CENTER_LEFT);
        pinNumber.setStyle("-fx-spacing: 7;");
        validatePin.setStyle("-fx-background-color: linen;");
        
        
		// Mobile number
    	Label mobile = new Label("Mobile number:");
		mobileText = new TextField();
		getWidth(mobileText);
		HBox phone= new HBox();
		phone.getChildren().addAll(mobile,mobileText);
        phone.setAlignment(Pos.CENTER_LEFT);
        phone.setStyle("-fx-spacing: 7;");
        
        
        
        //Address line 1
        Label address = new Label("Address Line 1:\t   ");
		addressText = new TextField(); 
		getWidth(addressText); 
		HBox a1 = new HBox();
		a1.getChildren().addAll(address,addressText);
	    a1.setAlignment(Pos.CENTER_LEFT);
	    a1.setStyle("-fx-spacing: 7;");
		
	    
	    //Address line 2
		Label address2 = new Label("Address Line 2:\t   ");
		addressText2 = new TextField(); 
		getWidth(addressText2);
		HBox a2 = new HBox();
		a2.getChildren().addAll(address2,addressText2);
		a2.setAlignment(Pos.CENTER_LEFT);
		a2.setStyle("-fx-spacing: 7;");

		
		//Address line3
		Label address3 = new Label("Address Line 3:\t   "); 
		addressText3 = new TextField(); 
		getWidth(addressText3);
		HBox a3 = new HBox();
		a3.getChildren().addAll(address3,addressText3);
		a3.setAlignment(Pos.CENTER_LEFT);
		a3.setStyle("-fx-spacing: 7;");
        
		
		//County
		Label ireland = new Label("County:\t\t\t   ");
		countyCombo = new ComboBox<>();
		countyCombo.getItems().addAll("antrim","armagh","carlow","cavan","clare","cork","derry","donegal","down","dublin","fermanagh","galway","kerry","kildare","kilkenny","limerick","longford","louth","mayo","meath","sligo","tipperary","tyrone","waterford","wexford","wicklow");
		HBox county = new HBox();
		county.getChildren().addAll(ireland, countyCombo);
		county.setAlignment(Pos.CENTER_LEFT);
		county.setStyle("-fx-spacing: 7;");
        
		
        //Country
		Label abroad = new Label("If your address is abroad, Please write Country Name:");
		counties = new ComboBox<>();
		counties.getItems().addAll("AF|Afghanistan","AL|Albania","DZ|Algeria","AS|American Samoa","AD|Andorra","AO|Angola","AI|Anguilla","AQ|Antarctica","AG|Antigua And Barbuda","AR|Argentina","AM|Armenia","AW|Aruba","AU|Australia","AT|Austria","AZ|Azerbaijan","BS|Bahamas","BH|Bahrain","BD|Bangladesh","BB|Barbados","BY|Belarus","BE|Belgium","BZ|Belize","BJ|Benin","BM|Bermuda","BT|Bhutan","BO|Bolivia","BA|Bosnia And Herzegovina","BW|Botswana","BV|Bouvet Island","BR|Brazil","IO|British Indian Ocean Territory","BN|Brunei Darussalam","BG|Bulgaria","BF|Burkina Faso","BI|Burundi","KH|Cambodia","CM|Cameroon","CA|Canada","CV|Cape Verde","KY|Cayman Islands","CF|Central African Republic","TD|Chad","CL|Chile","CN|China","CX|Christmas Island","CC|Cocos (keeling) Islands","CO|Colombia","KM|Comoros","CG|Congo","CD|Congo, The Democratic Republic Of The","CK|Cook Islands","CR|Costa Rica","CI|Cote D'ivoire","HR|Croatia","CU|Cuba","CY|Cyprus","CZ|Czech Republic","DK|Denmark","DJ|Djibouti","DM|Dominica","DO|Dominican Republic","TP|East Timor","EC|Ecuador","EG|Egypt","SV|El Salvador","GQ|Equatorial Guinea","ER|Eritrea","EE|Estonia","ET|Ethiopia","FK|Falkland Islands (malvinas)","FO|Faroe Islands","FJ|Fiji","FI|Finland","FR|France","GF|French Guiana","PF|French Polynesia","TF|French Southern Territories","GA|Gabon","GM|Gambia","GE|Georgia","DE|Germany","GH|Ghana","GI|Gibraltar","GR|Greece","GL|Greenland","GD|Grenada","GP|Guadeloupe","GU|Guam","GT|Guatemala","GN|Guinea","GW|Guinea-bissau","GY|Guyana","HT|Haiti","HM|Heard Island And Mcdonald Islands","VA|Holy See (vatican City State)","HN|Honduras","HK|Hong Kong","HU|Hungary","IS|Iceland","IN|India","ID|Indonesia","IR|Iran, Islamic Republic Of","IQ|Iraq","IE|Ireland","IL|Israel","IT|Italy","JM|Jamaica","JP|Japan","JO|Jordan","KZ|Kazakstan","KE|Kenya","KI|Kiribati","KP|Korea, Democratic People's Republic Of","KR|Korea, Republic Of","KV|Kosovo","KW|Kuwait","KG|Kyrgyzstan","LA|Lao People's Democratic Republic","LV|Latvia","LB|Lebanon","LS|Lesotho","LR|Liberia","LY|Libyan Arab Jamahiriya","LI|Liechtenstein","LT|Lithuania","LU|Luxembourg","MO|Macau","MK|Macedonia, The Former Yugoslav Republic Of","MG|Madagascar","MW|Malawi","MY|Malaysia","MV|Maldives","ML|Mali","MT|Malta","MH|Marshall Islands","MQ|Martinique","MR|Mauritania","MU|Mauritius","YT|Mayotte","MX|Mexico","FM|Micronesia, Federated States Of","MD|Moldova, Republic Of","MC|Monaco","MN|Mongolia","MS|Montserrat","ME|Montenegro","MA|Morocco","MZ|Mozambique","MM|Myanmar","NA|Namibia","NR|Nauru","NP|Nepal","NL|Netherlands","AN|Netherlands Antilles","NC|New Caledonia","NZ|New Zealand","NI|Nicaragua","NE|Niger","NG|Nigeria","NU|Niue","NF|Norfolk Island","MP|Northern Mariana Islands","NO|Norway","OM|Oman","PK|Pakistan","PW|Palau","PS|Palestinian Territory, Occupied","PA|Panama","PG|Papua New Guinea","PY|Paraguay","PE|Peru","PH|Philippines","PN|Pitcairn","PL|Poland","PT|Portugal","PR|Puerto Rico","QA|Qatar","RE|Reunion","RO|Romania","RU|Russian Federation","RW|Rwanda","SH|Saint Helena","KN|Saint Kitts And Nevis","LC|Saint Lucia","PM|Saint Pierre And Miquelon","VC|Saint Vincent And The Grenadines","WS|Samoa","SM|San Marino","ST|Sao Tome And Principe","SA|Saudi Arabia","SN|Senegal","RS|Serbia","SC|Seychelles","SL|Sierra Leone","SG|Singapore","SK|Slovakia","SI|Slovenia","SB|Solomon Islands","SO|Somalia","ZA|South Africa","GS|South Georgia And The South Sandwich Islands","ES|Spain","LK|Sri Lanka","SD|Sudan","SR|Suriname","SJ|Svalbard And Jan Mayen","SZ|Swaziland","SE|Sweden","CH|Switzerland","SY|Syrian Arab Republic","TW|Taiwan, Province Of China","TJ|Tajikistan","TZ|Tanzania, United Republic Of","TH|Thailand","TG|Togo","TK|Tokelau","TO|Tonga","TT|Trinidad And Tobago","TN|Tunisia","TR|Turkey","TM|Turkmenistan","TC|Turks And Caicos Islands","TV|Tuvalu","UG|Uganda","UA|Ukraine","AE|United Arab Emirates","GB|United Kingdom","US|United States","UM|United States Minor Outlying Islands","UY|Uruguay","UZ|Uzbekistan","VU|Vanuatu","VE|Venezuela","VN|Viet Nam","VG|Virgin Islands, British","VI|Virgin Islands, U.s.","WF|Wallis And Futuna","EH|Western Sahara","YE|Yemen","ZM|Zambia","ZW|Zimbabwe");
		getWidth(counties);
		HBox country = new HBox();
		country.getChildren().addAll(abroad, counties);
		country.setAlignment(Pos.CENTER_LEFT);
		country.setStyle("-fx-spacing: 7;");
        
		// ask to agree the terms
		agree = new CheckBox("I accept to the Terms of Service and Privacy Policy");
		HBox agreement = new HBox();
		agreement.getChildren().addAll(agree);
		agreement.setAlignment(Pos.CENTER);
		agreement.setStyle("-fx-padding: 10;");
		
		//Submit button to create new account
		Button submit = new Button("Submit"); 
		submit.setStyle("-fx-background-color: linen;");
		
		//checking user name and pin number is correct to sign up
		submit.setOnAction(e -> {
			if(isNumber(pinText) ) { //	If email and Pin is correct, create new account
				getConfirmationBox();
				CreditCard.display("Top up with your credit card");
				try {
					pw.writeTofile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}  //Write user info into the file. User will be able to find their details(Username and password)
				window2.close();    
			}
		});
			
		//submit button
		HBox submitBtn = new HBox();
		submitBtn.setAlignment(Pos.CENTER); 
		submitBtn.getChildren().addAll(submit);
		submitBtn.setStyle("-fx-spacing: 2;");
		
		
		
		//help button
		Button help = new Button("Help");
		help.setOnAction(e -> { // if help button is clicked, open Alert Box
			getHelp();
		});
		help.setStyle("-fx-background-color:lightcyan;");
		help.setShape(new Circle(5));
		
		HBox helpBtn = new HBox();
		helpBtn.setAlignment(Pos.BOTTOM_RIGHT); // Alignment of the button
		helpBtn.getChildren().addAll(help);
		
		
		//Put everything into VBox
		VBox signup = new VBox(20);
		signup.getChildren().addAll(header,fname,lname,birth,gender,mail,pinNumber, phone, a1,a2,a3, county ,country, agreement,submitBtn, helpBtn);
		signup.setPadding(new Insets(10,10,10,10));
	
				
		
		//Styling nodes 
		signup.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px; -fx-spacing: 5;");
		welcome.setStyle("-fx-font: normal bold 25px 'serif'; -fx-padding: 10");
		header.setAlignment(Pos.TOP_CENTER);
			
		//
		Scene scene2 = new Scene(signup,650,610);
		window2.setScene(scene2);
		window2.show(); 
	
	}


	//Method to setting width
	public static void getWidth(TextField Text) { 
		Text.setPrefWidth(200);
		Text.setMaxWidth(200);
	}
	
	public static void getWidth(ComboBox<String>Text) { 
		Text.setPrefWidth(200);
		Text.setMaxWidth(200);
	}
	
	// Method for validation, accepting only letter
	public static boolean isAlphabetic(TextField lastNameText2) { 
		String field = lastNameText2.getText();
		
		if(!field.matches("[a-zA-Z\\s']+")) { 
			return false; 
			
		}else 
			System.out.println("Correct Input"); 
			return true;
	}

	
	// Method to check age is over 18 
	public static void checkAge() {
	
		LocalDate currentDate = LocalDate.now();
		LocalDate birthDate = LocalDate.parse(DOBText.getText());
		
		Period period = Period.between(birthDate, currentDate);
		if (period.getYears() >= 18) {
			System.out.println("18 or over. You can sign up!");
			getConfirmationBox();
		} else {
			System.out.println("To sign up, You need to be more then 18 years.");
			getErrorMessage();
		}
		
	}
	
	
	//Checking PIN number, only number and 4 digits
	public static boolean isNumber(TextField pinNumber) {
		int pinLength = pinNumber.getLength();
		String pinField = pinNumber.getText();
		
		if(pinLength == 4){
			if(!pinField.matches("[a-zA-Z\\s']+"))			
			return true;}
		
		else 
			System.out.println("Pin number has to be 4 digits");
			return false;
		
	}
	
	
	//Getter methods for FirstName
	public static TextField FirstNameTextField() { 
		return firstNameText;
	}
	
	//Getter methods for email
	public static TextField getEmail() { 
		return emailText;
	}
	
	//Getter methods for pin
	public static TextField Pin() { 
		return pinText;
	}
	
	//Getter for the mobile number
	public static TextField getMobile() { 
		return mobileText;
	}
	
	public static TextField getLastName() { //getter for the last name 
		return lastNameText;
	}
	
	
	public static TextField getDob() { //getter for the DOB 
		return DOBText;
	}
	
	public static TextField getGender(){ //getter for the gender
		return genderText;
	}
	
	public static Alert getLoginConfirmation() { //Method what will give you alert box(information)
		Alert alert1 = new Alert(AlertType.INFORMATION);
		alert1.setTitle("Login Details");
		alert1.setHeaderText("Login Information");
		alert1.setContentText("Username and Password are correct!");
		alert1.showAndWait();
		
		return alert1;
	}
	
	public static void getErrorMessage() { //Method what will give you alert box(confirmation)
		
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Validation Box");
		alert1.setHeaderText("Validation result!");
		alert1.setContentText("Validation is not correct! ");
		
		alert1.showAndWait();
	}
	
	public static void getConfirmationBox() { // Method what will give you alert box(error)
		
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Validation Box");
		alert1.setHeaderText("Validation result: ");
		alert1.setContentText("Correct!");
		alert1.showAndWait();
		
	}
	
	public static Alert getLoginError() { // Method what will give you alert box(error)
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Validation Box");
		alert1.setHeaderText("Validation result: ");
		alert1.setContentText("Not correct!");
		alert1.showAndWait();
		
		return alert1;
	}
	
	// Method what will create alert box
		public static Alert getHelp() {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Help Box");
			alert.setHeaderText("Login Help");
			alert.setContentText(
					"Create new account!"
					+ "\n\nYour Eamil and PIN number will be your username and password");
			alert.showAndWait();

			return alert;

		}
}
