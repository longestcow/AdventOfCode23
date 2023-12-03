package Day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int sum=0, r=0,g=0,b=0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day02/input.txt")))) {
		    String line, stuff;
		    while ((line = br.readLine()) != null) {
		    	stuff=line.split(": ")[1];
		    	for(String st : stuff.split("; ")) {
		    		for(String item : st.split(", ")) {
		    			String i = item.split(" ")[1];
		    			int j = Integer.parseInt(item.split(" ")[0]);
		    			if(i.equals("red") && j>r)r=j;
		    			else if(i.equals("green") && j>g)g=j;
		    			else if(i.equals("blue") && j>b)b=j;
		    		}
		    	}
		    	sum+=r*g*b;
		    	r=0;g=0;b=0;
		    }
		    System.out.println(sum);
		}
	}
		
}
