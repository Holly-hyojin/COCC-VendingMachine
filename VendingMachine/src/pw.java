import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

public class pw {

    public static void writeTofile() throws IOException { 
    	
        FileWriter file = new FileWriter ("Registration.txt"); 
        PrintWriter text = new PrintWriter(file);
        System.out.println("File path is: " + new File("Registration.txt").getAbsolutePath());

            text.println("guest@gmail.com:1234"); //set the guest details
            text.println("manager@gmail.com:1234"); //set the manager details
            text.println(CreateAccount.getEmail().getText() + ":" + CreateAccount.Pin().getText()); //set new user details            
			text.close();                                
        }
    

    public static void getFile() { //Check if the file exists

        File file = new File("Registration.txt");
        System.out.println("File path is: " + new File("Registration.txt").getAbsolutePath());

        if (file.exists())
            System.out.println("Found the file");
        else
            System.out.println("Did not find the file.");


    }

}