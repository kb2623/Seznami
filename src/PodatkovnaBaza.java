import java.io.*;
import Source.SeznamiUV;

public class PodatkovnaBaza {
	public static void main(String[] args) {
		SeznamiUV seznamiUV = new SeznamiUV();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;        
		try {
		    while(true) {
		        System.out.print("Enter command: ");
		        input = br.readLine();
		        if(input.equalsIgnoreCase("exit")) {
		        	System.out.println("Have a nice day.");
		        	break;
		        }
		        System.out.println(seznamiUV.processInput(input));
		    }
		} catch (IOException e) {
		    System.err.println("[Error]: Failed to retrieve the next command.");
		    System.exit(1);
		}
	}
}
