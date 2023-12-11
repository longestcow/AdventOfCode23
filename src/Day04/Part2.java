package Day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day04/input.txt")))) {
			String line,winner,own;
			int count = 0, sum = 0, cardNum;
			HashMap<Integer,Integer> map = new HashMap<>();
		    while ((line = br.readLine()) != null) {
		    	//each card
		    	cardNum=Integer.parseInt(line.split(":")[0].split("Card")[1].strip());
				map.put(cardNum, map.containsKey(cardNum)?map.get(cardNum)+1:1);
		    	winner=line.split("\\| ")[0].split(":")[1];
		    	own = line.split(" \\| ")[1];
		    	for(String o : own.split(" ")) {
		    		if(o.isEmpty())continue;
		    		if(winner.contains(" "+o+" ")) 
		    			count++;
		    	}
		    	for(int j = 0; j<map.get(cardNum); j++) {
			    	for(int i = cardNum+1; i<=cardNum+count;i++) {
			    		if(map.containsKey(i))
			    			map.put(i, map.get(i)+1);
			    		else map.put(i, 1);
			    	}
		    	}
		    	count=0;
		    	
		    }
		    for(Integer i : map.values())
		    	sum+=i;
    		System.out.println(sum + " ("+(System.currentTimeMillis()-startTime)+"ms)");
	    }
	}

}
