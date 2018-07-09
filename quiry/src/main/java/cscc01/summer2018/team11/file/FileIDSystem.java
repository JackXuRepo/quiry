package cscc01.summer2018.team11.file;

import java.util.ArrayList;
import java.util.Random;


public class FileIDSystem {
	public static ArrayList<String> num = new ArrayList<String>();
	public static String generate() {
		Random rand = new Random();
		int n;
		do {
			n = rand.nextInt(89999) + 10000;
		}while(num.indexOf(Integer.toString(n)) != -1);
		num.add(Integer.toString(n));
		return Integer.toString(n);
	}
	
	public static void deleteID(String id) {
		try {
			num.remove(num.indexOf(id));
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("ID does not exist.");
		}
	}

}
