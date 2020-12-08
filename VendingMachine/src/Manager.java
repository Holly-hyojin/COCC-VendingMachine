import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Manager {

	public static Stage window;
	
	public static void display() {
		
		window = new Stage(); //Setting new Stage
		window.setTitle("Manager Menu");
		Label welcome = new Label("This is the Manage Menu");
		
		VBox root = new VBox();
		root.setPadding(new Insets(10,10,5,10));
	    root.setAlignment(Pos.TOP_CENTER);
	    root.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px; -fx-spacing: 10;"); 
	    welcome.setStyle("-fx-font: normal bold 25px 'serif'");
	
	    Scene scene = new Scene(root,500,480); //Setting the screen
		root.getChildren().addAll(welcome);
		window.setScene(scene);
		window.show();
		
	}

}
