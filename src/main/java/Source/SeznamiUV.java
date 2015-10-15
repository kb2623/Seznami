package Source;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.HashMap;

import UserInterface.Seznam;
import UserInterface.AbstractFacrory;

public class SeznamiUV<Element> {

	private AbstractFacrory<Element> facrory;
	private HashMap<String, Seznam<Element>> seznami;
	private Seznam<Element> seznam;
	private String seznamKey;

	public SeznamiUV(AbstractFacrory<Element> facrory) { 
		this.seznami = new HashMap<>(); 
		this.facrory = facrory;
	}	
	
	public void addImplementations(String name, Seznam seznam) {
		this.seznami.put(name, seznam);
	}

	public String processInput(String input) {
		if(input == null) {
			return "Error: Bad arguments";
		}
		String token;
		try (Scanner scan = new Scanner(input)) {
			if(!scan.hasNext()) {
				scan.close();
				return "Error: No arguments";
			}
			token = scan.next();
			if(this.seznam == null && !token.equals("use")) {
				StringBuilder sb = new StringBuilder();
				sb.append("Error: please specify a data structure (use {");
				for(String s : this.seznami.keySet()) {
					sb.append(s).append('|');
				}
				sb.append("..})");
				return sb.toString();
			}
			switch(token) {
				case "use":
					if(null != (token = this.getNextString(scan)) && null != (this.seznam = this.seznami.get(token))) {
						this.seznamKey = token;
						return "OK";
					} else {
						StringBuilder sb = new StringBuilder();
						sb.append("Error: please specify a data structure (use {");
						for(String s : this.seznami.keySet()) {
							sb.append(s).append('|');
						}
						sb.append("..})");
						return sb.toString();
					}
				case "add":
					if(null != (token = this.getNextString(scan))) {
						try {
							this.seznam.add(this.facrory.makeElement(token));
						} catch(IllegalArgumentException e) {
							return "Error: element exists";
						} catch(AbstractMethodError e) {
							System.out.println(token);
							e.printStackTrace();
						} catch(Exception e) {
							return "Error: "+e.getMessage();
						}
						return "OK";
					} else {
						return "Error: please specify a string";
					}
				case "removefirst":
					if(!this.seznam.isEmpty()) {
						return this.seznam.removeFirst().toString();
					} else {
						return "Error: "+this.seznamKey+" is empty";
					}
				case "getfirst":
					if(!seznam.isEmpty()) {
						return this.seznam.getFirst().toString();
					} else {
						return "Error: "+this.seznamKey+" is empty";
					}
				case "count":
					return Integer.toString(this.seznam.size());
				case "depth":
					return Integer.toString(this.seznam.depth());
				case "isempty":
					if(this.seznam.isEmpty()) {
						return "Yes";
					} else {
						return "Not";
					}
				case "exists":
					if(null != (token = this.getNextString(scan))) {
						if(!this.seznam.isEmpty()) {
							if(this.seznam.exists(this.facrory.makeElement(token))) {
								return "Yes";
							} else {
								return "Not";
							}
						} else {
							return "Error: "+this.seznamKey+" is empty";
						}
					} else {
						return "Error: please specify a string";
					}
				case "remove":
					if(null != (token = this.getNextString(scan))) {
						if(!this.seznam.isEmpty()) {
							try {
								return this.seznam.remove(this.facrory.makeElement(token)).toString();
							} catch(NoSuchElementException e) {
								return "Error: element does not exists";
							} catch(Exception e) {
								return e.getMessage();
							}
						} else {
							return "Error: "+this.seznamKey+" is empty";
						}
					} else {
						return "Error: please specify a string";
					}
				case "reset":
					while(!this.seznam.isEmpty()) {
						this.seznam.removeFirst();
					}
					return "OK";
				case "print":
					return this.seznam.print()+"\nOK";
				case "save":
					if(null != (token = this.getNextString(scan))) {
						try {
							this.seznam.save(new FileOutputStream(token));
						} catch(IOException e) {
							return "I/O Error "+e.getMessage();
						}
						return "OK";
					} else {
						return "Error: please specify a file name";
					}
				case "restore":
					if(null != (token = this.getNextString(scan))) {
						try {
							this.seznam.restore(new FileInputStream(token));
						} catch (IOException e) {
							return "Error: IO error "+e.getMessage();
						} catch(ClassNotFoundException e) {
							return "Error: Unknown format";
						}
						return "OK";
					} else {
						return "Error: please specify a file name";
					}
				case "assess":
					if(null != (token = this.getNextString(scan))) {
						this.seznam.setComparator(this.facrory.makeComparator(token));
						return "OK";
					} else {
						return "Error: need more args";
					}
				case "exit":
					return "Have a nice day!!!";
				default: return "Error: bad operation";
			}
		}
	}

	private String getNextString(Scanner scan) {
		if(!scan.hasNext()) {
			return null;
		} else {
			String tmp = scan.next();
			StringBuilder builder = new StringBuilder();
			if(tmp.charAt(0) == '"') {
				if(tmp.charAt(tmp.length()-1) == '"') {
					return tmp.substring(1, tmp.length()-1);
				} else {
					builder.append(tmp.substring(1));
					while(scan.hasNext()) {
						tmp = scan.next();
						builder.append(' ');
						for(int i = 0; i < tmp.length(); i++) {
							if(tmp.charAt(i) == '"') {
								return builder.toString();
							} else {
								builder.append(tmp.charAt(i));
							}
						}
					}
				}
				return null;
			} else {
				for(int i = 1; i < tmp.length(); i++) {
					if(tmp.charAt(i) == '"') {
						return tmp.substring(0, i);
					}
				}
				return tmp;
			}
		}
	}
}
