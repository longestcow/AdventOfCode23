package Day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int red = 12, blue = 14, green = 13, sum=0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day02/input.txt")))) {
		    String line, stuff;
		    outer: while ((line = br.readLine()) != null) {
		    	stuff=line.split(": ")[1];
		    	for(String st : stuff.split("; ")) {
		    		for(String item : st.split(", ")) {
		    			String i = item.split(" ")[1];
		    			int j = Integer.parseInt(item.split(" ")[0]);
		    			if(i.equals("red") && j>red)continue outer;
		    			if(i.equals("green") && j>green)continue outer;
		    			if(i.equals("blue") && j>blue)continue outer;
		    		}
		    	}
		    	sum+=Integer.parseInt(line.split(": ")[0].split(" ")[1]);
		    }
		    System.out.println(sum);
		}
	}
		
}
