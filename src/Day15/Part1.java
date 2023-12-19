package Day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int sum = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day15/input.txt")))) {
			String line = br.readLine();
			for(String a : line.split(","))
				sum+=hash(a);
		    
	    }
		System.out.println(sum);

	}
	static int hash(String s) {
		int curr = 0;
		
		for(char c : s.toCharArray()) {
			curr+=(int)c;
			curr*=17;
			curr%=256;
		}
		return curr;
	}

}
