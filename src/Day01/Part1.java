package Day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Part1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		int sum = 0, curr = 0, c = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day01/input.txt")))) {
		    String line;
		    while ((line = br.readLine()) != null) {
	    		for(int i = 0; i<=line.length()-1; i++) {
	    			if(Character.isDigit(line.charAt(i))) {
	    				curr+=10*Integer.parseInt(line.charAt(i)+"");
	    				break;
	    			}
		    	}
	    		for(int i = line.length()-1; i>=0; i--) {
	    			if(Character.isDigit(line.charAt(i))) {
	    				curr+=Integer.parseInt(line.charAt(i)+"");
	    				break;
	    			}
	    		}
		    	sum+=curr;
		    	curr=0;
		    }
		}
		System.out.println(sum + " ("+(System.currentTimeMillis()-startTime)+"ms)");

	}

}
