package Day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Part2 {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
//		String dir = "LR";
		int count = 0;
		HashMap<String, String[]> map = new HashMap<>();
		List<String> currs = new ArrayList<>();
		List<Integer> counts = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day08/input.txt")))) {
			String dir = br.readLine(); br.readLine();
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] op = line.split(" = ")[1].replace("(", "").replace(")", "").split(", ");
		    	String ip = line.split(" = ")[0];
		    	map.put(ip, op);
		    	if(ip.contains("A")) 
		    		currs.add(ip);
		    	
		    	
		    }

    		for(String curr : currs) {
    			count=0;
    			p: while(true) {
    				for(char c : dir.toCharArray()) {
			    		count++;
			    		curr=map.get(curr)[(c=='R')?1:0];
			    		if(curr.endsWith("Z")) {
			    			counts.add(count);
			    			break p;
			    		}
    				}
		    	}
    		}
			System.out.println(findLCM(counts) + " ("+(System.currentTimeMillis()-startTime)+"ms)");
		    
	    }
	}

  
    static long findLCM(List<Integer> arr) 
    { 
    	long lcm = 0;
    	boolean pass = true;
    	Collections.sort(arr);
    	int biggest = arr.get(arr.size()-1);
        while(true) {
        	lcm+=biggest;
        	for(int i : arr) {
        		if(lcm%i!=0) {
        			pass=false;
        			break;
        		}
        	}
        	if(pass)break;
        	pass=true;
        }
        
        return lcm;
    } 

	

}