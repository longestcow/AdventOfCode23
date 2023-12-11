package Day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day09/input.txt")))) {
			String line;
			int sum = 0;
		    while ((line = br.readLine()) != null) 
		    	sum+=getNext(sToIList(line.split(" ")));
		    
			System.out.println(sum + " ("+(System.currentTimeMillis()-startTime)+"ms)");
	    }
	}
	static List<Integer> sToIList(String[] split) {
    	List<Integer> l = new ArrayList<>();
    	for(String i : split)
    		l.add(Integer.parseInt(i));
    	return l;
	}
	static int getNext(List<Integer> list){
		List<Integer> l = new ArrayList<>();
		boolean zero = true;
		for(int i = 0; i<list.size()-1; i++) {
			int val = list.get(i+1)-list.get(i);
			l.add(val);
			if(val!=0)zero=false;
		}
		if(zero) 
			return list.get(0); //no need to go next gen, its all 0s
		
		else 
			return list.get(0)-getNext(l);
		
	}

	

}