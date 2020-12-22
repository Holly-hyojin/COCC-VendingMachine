import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.crypto.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//Cork College of Commerce 2020/2021 Hyojin_Kim

public class Manager {

	public static Stage window;
	
	public static void display() {
		
		window = new Stage(); //Setting new Stage
		window.setTitle("Manager Menu");
		Label welcome = new Label("This is the Manage Menu");
		
		// Display a report		
		Button displayReport = new Button("Display a Report");
		displayReport.setOnAction(e -> {
			System.out.println("\n------- Sales report -------");
			System.out.println("\nTotal Coke Sold: " + (20 - Drinks.coke));
			System.out.println("Total Diet Coke Sold: " + (20 - Drinks.dietCoke));
			System.out.println("Total Fanta Lemon Sold: " + (20 - Drinks.fantaLemon));
			System.out.println("Total Fanta Orange Sold: " + (20 - Drinks.fantaOrange));
			
			System.out.println("\nTotal user: " + Drinks.countUser);
			System.out.printf("Total earnings %.2f%n", Drinks.moneyToManager);
		});

		
		// Take money
				Button takeMoney = new Button("Collect Money");
				takeMoney.setOnAction(e -> {
					System.out.printf("%n------- Take money -------%nTotal money is €%.2f\n", Drinks.moneyToManager);
					System.out.println("You took all the money, Thank you!");
					Drinks.moneyToManager = 0;
				});
				
		// Add drinks 
		Button coke = new Button("Add all coke");
		coke.setOnAction(e -> {
			Drinks.coke = 20;
			System.out.println("\nNow coke is full stock!");
			addDrink();
		});
		Button diet = new Button("Add all Diet coke");
		diet.setOnAction(e -> {
			Drinks.dietCoke = 20;
			System.out.println("\nNow Diet coke is full stock!");
			addDrink();
		});
		Button fantaLemon = new Button("Add all Fanta Lemon");
		fantaLemon.setOnAction(e -> {
			Drinks.fantaLemon = 20;
			System.out.println("\nNow Fanta Lemon is full stock!");
			addDrink();
		});
		Button fantaOrange = new Button("Add all Fanta Orange");
		fantaOrange.setOnAction(e -> {
			Drinks.fantaOrange = 20;
			System.out.println("\nNow Fanta Orange is full stock!");
			addDrink();
		});
		
		
				
		// Reading a report
		Button checkReport = new Button("Check reports");
		checkReport.setOnAction(e -> {
			try {
				readReport();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		
		VBox addBtn = new VBox();
		addBtn.getChildren().addAll(displayReport,takeMoney, coke, diet, fantaLemon, fantaOrange, checkReport);
		addBtn.setAlignment(Pos.CENTER);

		
		// Styling 
		VBox root = new VBox();
		root.setPadding(new Insets(10,10,5,10));
	    root.setAlignment(Pos.TOP_CENTER);
		
	    root.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px; -fx-spacing: 40; "); 
	    welcome.setStyle("-fx-font: normal bold 25px 'serif';-fx-padding:10; -fx-spacing: 15;");
	    coke.setStyle("-fx-background-color: tomato; -fx-spacing: 15;");
	    diet.setStyle("-fx-background-color: silver; -fx-spacing: 15;");
	    fantaLemon.setStyle("-fx-background-color: lemonchiffon; -fx-spacing: 15;");
		fantaOrange.setStyle("-fx-background-color: orange; -fx-spacing: 15;");
		displayReport.setStyle("-fx-background-color: linen");
		takeMoney.setStyle("-fx-background-color: lightpink; -fx-spacing: 15;");
		checkReport.setStyle("-fx-background-color: honeydew; ");
		addBtn.setStyle("-fx-spacing: 15;");
		
		// Styling - set the all button size same
		getWidth(coke);
		getWidth(diet);
		getWidth(fantaLemon);
		getWidth(fantaOrange);
		getWidth(displayReport);
		getWidth(takeMoney);
		getWidth(checkReport);
	    

	    // Adding all to Scene
	    Scene scene = new Scene(root,500,480); //Setting the screen
		root.getChildren().addAll(welcome, addBtn);
		window.setScene(scene);
		window.show();

	}
	
	// add drinks alert message
    public static Alert addDrink() {
    	Alert add = new Alert(AlertType.CONFIRMATION);
    	add.setTitle("Add Drinks");
    	add.setHeaderText("The Drinks has been fully added!");
    	add.showAndWait();

    	return add;
    }

	// Give an alert to manager when there's no report
	public static void readReport() throws IOException  {
	
		String[] readReport = new String[2];
				
			BufferedReader br = new BufferedReader(new FileReader("Report.txt")); 
			String line =" ";
			
			//Read through the file			
			int counter = 0, length = 0;
			while (line != null) {
				line = br.readLine();
				readReport[counter] = line;
				
				if(line != null)
					counter += 1;
			}
			System.out.println("\n" + readReport[0]);
			
			if(readReport[0] == null) { // if there's no record from user
				Alert report = new Alert(AlertType.INFORMATION);
				report.setTitle("Report");
				report.setHeaderText("Report record");
				report.setContentText("There is no record from customer");
				report.showAndWait();
			}
		}

	public static void getWidth(Button btn) { // set buttons size
		btn.setPrefWidth(180);
		btn.setMaxWidth(180);
	}


}
		

	

