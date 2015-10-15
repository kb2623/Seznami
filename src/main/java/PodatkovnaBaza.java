import Source.*;
import UserInterface.AbstractFacrory;

import java.io.*;
import java.util.Comparator;

public class PodatkovnaBaza {
	public static void main(String[] args) {
		//Ustvari objekt SeznamiUV
		SeznamiUV<String> seznamiUV = new SeznamiUV<>(new AbstractFacrory<String>() {
			@Override
			public String makeElement(String string) {
				return String.valueOf(string);
			}
			@Override
			public Comparator<String> makeComparator(String string) {
				switch(string) {
					case "-":
						return (String t, String t1) -> -(t.compareTo(t1));
					case "+":
						return String::compareTo;
					default: return null;
				}
			}			
		});
		//Dodaj podatkovne strukture, ki implementirajo vmesnik Seznam
		seznamiUV.addImplementations("sk", new Sklad<>());
		seznamiUV.addImplementations("pv", new PrioritetnaVrsta<>());
		seznamiUV.addImplementations("bst", new Bst<>());
		seznamiUV.addImplementations("bk", new BinomskaKopica<>());
		//Od tukaj naprej je ukazna vrstica
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;        
		try {
		    do {
				System.out.print("Enter command: ");
				input = br.readLine();
				System.out.println(seznamiUV.processInput(input));
			} while(!input.equalsIgnoreCase("exit"));
		} catch (IOException e) {
		    System.err.println("Error: Failed to retrieve the next command.");
		    System.exit(1);
		}
	}
}
