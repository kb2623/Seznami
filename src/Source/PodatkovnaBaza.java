package Source;

import java.io.*;

public class PodatkovnaBaza {
	public static void main(String[] args) {
		SeznamiUV seznamiUV = new SeznamiUV();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input, output;        
        try {
            do {
                System.out.print("Enter command: ");
                input = br.readLine();
                output = seznamiUV.processInput(input);
                System.out.println(output);
            } while (!input.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.err.println("[Error]: Failed to retrieve the next command.");
            System.exit(1);
        }
	}
}
