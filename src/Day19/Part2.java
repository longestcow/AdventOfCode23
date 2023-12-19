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
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		List<Part> parts = new ArrayList<>(), accepted = new ArrayList<>();
		HashMap<String, List<String>> workflow = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day19/input.txt")))) {
			String line;
			boolean part = false;
		    while ((line = br.readLine()) != null) {
		    	if(line.isEmpty())part=true;
		    	else if(!part) {
		    		line=line.replace("}", "");
		    		workflow.put(line.split("\\{")[0], Arrays.asList(line.split("\\{")[1].split(",")));
		    	}
		    	else {
		    		line=line.replace("a=", "").replace("s=", "").replace("m=", "").replace("x=","").replace("{", "").replace("}","");
		    		parts.add(new Part(line.split(",")));
		    	}
		    }
	    }
		String condition,output;
		for(Part p : parts) {
			p: while(true) {
				for(String s : workflow.get(p.workflow)) {
					if(!s.contains(":")) { // no condition
						if(s.equals("A")) {
							accepted.add(p);
							break p;
						}
						else if(s.equals("R")) 
							break p;
						else {
							p.workflow=s;
							continue p;
						}
					}
					
					condition = s.split(":")[0];
					output = s.split(":")[1];
					if(p.check(condition)) {
						if(output.equals("A")) {
							accepted.add(p);
							break p;
						}
						else if(output.equals("R")) 
							break p;
						else {
							p.workflow=output;
							continue p;
						}
					}
					else continue;
					
				}
			}
		}
		int sum = 0;
		for(Part p : accepted)sum+=p.sum();
		System.out.println(sum);
	}
	


}
class Route{
	public HashMap<Character, Integer> map = new HashMap<>();
	public String workflow="in";
	
	Route(String[] ar){
		map.put('x', Integer.parseInt(ar[0]));
		map.put('m', Integer.parseInt(ar[1]));
		map.put('a', Integer.parseInt(ar[2]));
		map.put('s', Integer.parseInt(ar[3]));

	}

	public int sum() {
		int sum = 0;
		for(int v : map.values())sum+=v;
		return sum;
	}

	public boolean check(String condition) {
		if(condition.charAt(1)=='>')
			return map.get(condition.charAt(0))>Integer.parseInt(condition.split(">")[1]);
		return map.get(condition.charAt(0))<Integer.parseInt(condition.split("<")[1]);
	}
}

