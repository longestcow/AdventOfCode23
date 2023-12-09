package Day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Part1 {
	static List<List<Long[]>> list = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day05/input.txt")))) {
			String line = null;
			List<Long> seeds = new ArrayList<>();
			for(int i = 0; i<7; i++)list.add(new ArrayList<>());
			int index = -1;
			long a,b,c,min=Long.MAX_VALUE;
		    while ((line = br.readLine()) != null) {
		    	if(line.isEmpty())continue;
		    	else if(line.startsWith("seeds:")) {
		    		for(String t : line.split("seeds: ")[1].split(" ")) 
		    			seeds.add(Long.parseLong(t));
		    	}
		    	else if(line.contains("map"))
		    		index++;
		    	else {
		    		a=Long.parseLong(line.split(" ")[0]);
		    		b=Long.parseLong(line.split(" ")[1]);
		    		c=Long.parseLong(line.split(" ")[2]);
		    		list.get(index).add(new Long[] {b,b+c-1,a,a+c-1});
		    	}

		    }
		    
		    for(Long i: seeds) { // for each seed
		    	i=seedToLocation(i);
		    	if(i<min)min=i;
		    }
		    	
			System.out.println(min + " ("+(System.currentTimeMillis()-startTime)+"ms)");

	    }
	}
	
	static Long seedToLocation(Long i) {
    	for(int j = 0; j<7; j++) { //goes through all conversions in order
	    	for(Long[] t : list.get(j)) { //for each range in the current conversion
	    		if(i>=t[0] && i<=t[1]) {
	    			i=map(i,t[0],t[1],t[2],t[3]);
	    			break;
	    		}
	    	}
    	}
    	return i;
	}

	static long map(long x, long in_min, long in_max, long out_min, long out_max) {
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

}
