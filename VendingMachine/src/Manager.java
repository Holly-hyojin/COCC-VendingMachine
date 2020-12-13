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

public class Manager {

	public static Stage window;
	
	public static void display() {
		
		window = new Stage(); //Setting new Stage
		window.setTitle("Manager Menu");
		Label welcome = new Label("This is the Manage Menu");
		
		
		// Add drinks 
		Button coke = new Button("Add all coke");
		coke.setOnAction(e -> {
			Drinks.coke = 20;
			System.out.println("Now coke is full stock!");
		});
		Button diet = new Button("Add all Diet coke");
		diet.setOnAction(e -> {
			Drinks.dietCoke = 20;
			System.out.println("Now Diet coke is full stock!");
		});
		Button fantaLemon = new Button("Add all Fanta Lemon");
		fantaLemon.setOnAction(e -> {
			Drinks.fantaLemon = 20;
			System.out.println("Now Fanta Lemon is full stock!");
		});
		Button fantaOrange = new Button("Add all Fanta Orange");
		fantaOrange.setOnAction(e -> {
			Drinks.fantaOrange = 20;
			System.out.println("Now Fanta Orange is full stock!");
		});
		

		
		// Take money
				Button takeMoney = new Button("Collect Money");
				takeMoney.setOnAction(e -> {
					System.out.printf("\n\nTotal money is €%.2f\n", Drinks.moneyToManager);
					System.out.println("You took all the money, Thank you!");
					Drinks.moneyToManager = 0;
				});
		
				
				
		// Reading a report
		Button checkReport = new Button("Check reports");
		checkReport.setOnAction(e -> {
			readReport();
		});
		
		
		VBox addBtn = new VBox();
		addBtn.getChildren().addAll(coke, diet, fantaLemon, fantaOrange, takeMoney, checkReport);
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
		takeMoney.setStyle("-fx-background-color: linen; -fx-spacing: 15;");
		checkReport.setStyle("-fx-background-color: honeydew; -fx-spacing: 15;");
		addBtn.setStyle("-fx-spacing: 15;");
		
	    

	    // Adding all to Scene
	    Scene scene = new Scene(root,500,480); //Setting the screen
		root.getChildren().addAll(welcome, addBtn);
		window.setScene(scene);
		window.show();

	}

	// Give an alert to manager when there's no report
	public static void readReport()  {
	
		Alert report = new Alert(AlertType.INFORMATION);
		report.setTitle("Report");
		report.setHeaderText("Report record");
		report.setContentText("There is no record from customer");
		report.showAndWait();
	}
}
