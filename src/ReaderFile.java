import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Cork College of Commerce 2020/2021 Hyojin_Kim

public class ReaderFile {

	public static String[] users = new String[4];
	public static String user, pin;
	
	public static void readTheFile(String title) { //Method to read from the file
		
		//creating userId and password instances
	
		try {		
			BufferedReader br = new BufferedReader(new FileReader("Registration.txt")); 
			String line =" ";
			
			//Read through the file			
			int counter = 0, length = 0;
			while (line != null) {
				line = br.readLine();
				users[counter] = line;
				
				if(line != null)
					counter += 1;
			}
			
			//to show what is saved user name and pin number in Console
			System.out.println(users[0] + " (First user saved)");
			System.out.println(users[1] + " (Second user saved)");
			System.out.println(users[2] + " (Third user saved)\n\n");
			 
			for (int i = 0; i < counter; i++) {
			
			int findIndex = users[i].indexOf(":");	
			length = users[i].length();
			pin = (users[i].substring(length - 4));
			user = (users[i].substring(0, findIndex));
			
			}	
				
		}
		
		catch (IOException e) { //Throw exception if the userId and password is not found
			e.printStackTrace();
			}

	}
	}