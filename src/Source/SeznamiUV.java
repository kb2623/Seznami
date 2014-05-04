package Source;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.HashMap;

public class SeznamiUV {
	private final HashMap<String, Seznam<String>> seznami;
	private Seznam<String> seznam;

	public SeznamiUV() {
		this.seznami = new HashMap<>();
		this.seznami.put("pv", new PrioritetnaVrsta<String>());
		this.seznami.put("sk", new Sklad<String>());
		this.seznami.put("bst", new Bst<String>());
		this.seznami.put("bk", new BinomskaKopica<String>());
		this.seznam = null;
	}

	@SuppressWarnings("resource")
	public String processInput(String input) {
		if(input == null) {
			return "Error: Bad arguments";
		}
		Scanner scan = new Scanner(input);
		if(!scan.hasNext()) {
			return "Error: No arguments";
		}
		String res = "OK", token;
		switch((token = scan.next())) {
			case "use":
				if(null != (token = this.getNextString(scan))) {
					if(null == (this.seznam = this.seznami.get(token))) {
						res = "Error: please specify a data structure type (pv, sk, bst, bk)";
					}
				} else {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				}
				break;
			case "add":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else if(null != (token = this.getNextString(scan))) {
					try {
						this.seznam.add(token);
					} catch(IllegalArgumentException e) {
						res = "Error: element exists";
					}
				} else {
					res = "Error: please specify a string";
				}
				break;
			case "removefirst":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else if(!this.seznam.isEmpty()) {
					res = String.valueOf(this.seznam.removeFirst());
				} else if(this.seznam instanceof Sklad) {
					res = "Error: stack is empty";
				} else if(this.seznam instanceof PrioritetnaVrsta) {
					res = "Error: priority queue is empty";
				} else if(this.seznam instanceof Bst){
					res = "Error: bst tree is empty";
				} else {
					res = "Error: bk queue is empty";
				}
				break;
			case "getfirst":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else if(!this.seznam.isEmpty()) {
					res = String.valueOf(this.seznam.getFirst());
				} else if(this.seznam instanceof Sklad) {
					res = "Error: stack is empty";
				} else if(this.seznam instanceof PrioritetnaVrsta) {
					res = "Error: priority queue is empty";
				} else if(this.seznam instanceof Bst){
					res = "Error: bst tree is empty";
				} else {
					res = "Error: bk queue is empty";
				}
				break;
			case "size":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else {
					res = String.valueOf(this.seznam.size());
				}
				break;
			case "depth":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else {
					res = String.valueOf(this.seznam.depth());
				}
				break;
			case "isempty":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else {
					if(this.seznam.isEmpty()) {
						res = "Yes";
					} else {
						res = "Not";
					}
				}
				break;
			case "exists":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else if(null != (token = this.getNextString(scan))) {
					if(!this.seznam.isEmpty()) {
						if(this.seznam.exists(token)) {
							res = "Yes";
						} else {
							res = "Not";
						}
					} else if(this.seznam instanceof Sklad) {
						res = "Error: stack is empty";
					} else if(this.seznam instanceof PrioritetnaVrsta) {
						res = "Error: priority queue is empty";
					} else if(this.seznam instanceof Bst){
						res = "Error: bst tree is empty";
					} else {
						res = "Error: bk queue is empty";
					}
				} else {
					res = "Error: please specify a string";
				}
				break;
			case "remove":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else if(null != (token = this.getNextString(scan))) {
					if(!this.seznam.isEmpty()) {
						try {
							res = String.valueOf(this.seznam.remove(token));
						} catch(NoSuchElementException e) {
							res = "Error: element does not exists";
						}
					} else if(this.seznam instanceof Sklad) {
						res = "Error: stack is empty";
					} else if(this.seznam instanceof PrioritetnaVrsta) {
						res = "Error: priority queue is empty";
					} else if(this.seznam instanceof Bst){
						res = "Error: bst tree is empty";
					} else {
						res = "Error: bk queue is empty";
					}
				} else {
					res = "Error: please specify a string";
				}
				break;
			case "reset":
				if(this.seznam == null) {
					res = "Error: please specify a data structure type (pv, sk, bst, bk)";
				} else {
					while(!this.seznam.isEmpty()) {
						this.seznam.removeFirst();
					}
				}
				break;
			default:
				res = "Error: bad operation";
				break;
		}
		return res;
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