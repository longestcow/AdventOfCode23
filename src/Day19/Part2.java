package Day19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Part2 {
	static char[][] grid;
	static HashMap<String, List<String>> workflow = new HashMap<>();
	public static void main(String[] args) throws FileNotFoundException, IOException {	
		final long startTime = System.currentTimeMillis();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day19/input.txt")))) {
			String line;
			boolean part = false;
		    while ((line = br.readLine()) != null) {
		    	if(line.isEmpty())break;
	    		line=line.replace("}", "");
	    		workflow.put(line.split("\\{")[0], Arrays.asList(line.split("\\{")[1].split(",")));
		    }
	    }
		
		System.out.println(lookForA("in", new ArrayList<String>()));
	
	}
	
	static long lookForA(String wf, List<String> pstuff){
		List<String> stuff = new ArrayList<String>(),cstuff = new ArrayList<String>(), current = workflow.get(wf);
		cstuff.addAll(pstuff);
		boolean count = false;
		long sum = 0;
		if(current.stream().anyMatch(s -> s.contains("A")))
			count=true;//we should keep count of all ranges until a
		for(String work : current) {
			if(work.equals("A") || work.equals("R")) return 0;
			if(!work.contains(":")) sum+=lookForA(work, cstuff); // last one, dont have to worry about a as stuff should have all ranges by now
			else {
				if(work.contains("A")) {
					stuff.add(work.split(":")[0]); // add inverse range
					count=false;//stop counting now
				}
				else {
					cstuff.add(work.split(":")[0]);
					if(count)stuff.add(inverse(work.split(":")[0])); // add inverse range
					if(!work.split(":")[1].equals("R"))//if not r, go into branch
						sum+=lookForA(work.split(":")[1], cstuff); // look for branch
				}
			}
			
		}
		
		System.out.println(wf +": "+cstuff+","+stuff);
		//stuff -> sum
		
		return sum;

	}

	static String inverse(String s) {
		if(s.charAt(1)=='>')
			return s.replace(">", "<=");
		else
			return s.replace("<", ">=");
	}
	


}


