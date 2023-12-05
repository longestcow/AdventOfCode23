package Day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class Part2 {
	static List<List<Long[]>> list = new ArrayList<>(6);

	public static void main(String[] args) throws FileNotFoundException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Long>> futures = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day05/input.txt")))) {
			String line;
			String[] temp = null;
			long a,b,c;
			int index = -1;
		    while ((line = br.readLine()) != null) {
		    	if(line.isEmpty())continue;
		    	else if(line.startsWith("seeds:")) {
		    		temp=line.split("seeds: ")[1].split(" ");
		    		for(int i = 0; i<temp.length; i+=2) {
		    			String s=temp[i], r=temp[i+1];
		                Future<Long> future = executorService.submit(() -> processRange(s,r));
		                futures.add(future);
		    		}	
		    		executorService.shutdown();
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
		    
	    	try {
	            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

	            long min = Long.MAX_VALUE;
	            for (Future<Long> future : futures) min = Math.min(min, future.get());
	            
	            System.out.println(min);
	            
	        } catch (InterruptedException | ExecutionException e) {
	            e.printStackTrace();
	        }
		    

		    	
	    }
	}
	
	static long map(long x, long in_min, long in_max, long out_min, long out_max) {
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	static long seedToLocation(long i) {
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
	static long processRange(String s, String r) {
		long min=Long.MAX_VALUE, curr=0;
		for(long j = Long.parseLong(s); j<Long.parseLong(s)+Long.parseLong(r)-1; j++) {
			curr=seedToLocation(j);
			if(curr<min)min=curr;
		}
		return min;
	}

}
