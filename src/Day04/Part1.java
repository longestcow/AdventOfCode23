package Day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day04/input.txt")))) {
			String line,winner,own;
			boolean started = false;
			int count = 0, sum = 0;
		    while ((line = br.readLine()) != null) {
		    	//each card
		    	winner=line.split("\\| ")[0].split(":")[1];
		    	own = line.split(" \\| ")[1];
		    	for(String o : own.split(" ")) {
		    		if(o.isEmpty())continue;
		    		if(winner.contains(" "+o+" ")) {
		    			if(!started) {
		    				count=1;
		    				started=true;
		    			}
		    			else count*=2;
		    		}
		    	}
		    	sum+=count;
		    	count=0;
		    	started=false;
		    	
		    }
    		System.out.println(sum + " ("+(System.currentTimeMillis()-startTime)+"ms)");
	    }
	}

}
