package Day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Part1 {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		String curr = "AAA";
		int count = 0;
		HashMap<String, String[]> map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day08/input.txt")))) {
			String dir = br.readLine(); br.readLine();
			String line;
		    while ((line = br.readLine()) != null) {
		    	map.put(line.split(" = ")[0], line.split(" = ")[1].replace("(", "").replace(")", "").split(", "));
		    }

		    while(true) {
		    	for(char c : dir.toCharArray()) {
		    		count++;
		    		curr=map.get(curr)[(c=='R')?1:0];
		    		if(curr.equals("ZZZ")) {
		    			System.out.println(count + " ("+(System.currentTimeMillis()-startTime)+"ms)");
		    			System.exit(0);
		    		}
		    		
		    	}
		    }
	    }
	}

	

}