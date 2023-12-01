package Day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		int sum = 0, curr = 0, cind, ind, cNum=0;
		HashMap<String, Integer> nums = new HashMap<>();
        nums.put("one", 1);
        nums.put("two", 2);
        nums.put("three", 3);
        nums.put("four", 4);
        nums.put("five", 5);
        nums.put("six", 6);
        nums.put("seven", 7);
        nums.put("eight", 8);
        nums.put("nine", 9);
        for(int i = 1; i<10; i++) 
        	nums.put(""+i, i);
        
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day01/input.txt")))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	cind=10000;
	    		for(String i : nums.keySet()) {
	    			ind = line.indexOf(i);
	    			if(ind==-1)continue;
	    			if(ind<cind) {
	    				cind=ind;
	    				cNum=10*nums.get(i);
	    			}
	    		}
	    		curr+=cNum;
	    		cind=-10000;
	    		for(String i : nums.keySet()) {
	    			ind = line.lastIndexOf(i);
	    			if(ind==-1)continue;
	    			if(ind>cind) {
	    				cind=ind;
	    				cNum=nums.get(i);
	    			}
	    		}
	    		curr+=cNum;
		    	sum+=curr;
		    	curr=0;
		    }
		}
		System.out.println(sum);

	}

}
