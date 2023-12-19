package Day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Part2 {

	static HashMap<Integer, LinkedHashMap<String,Integer>> map = new HashMap<>();// {id, {name,focalLen}}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int sum = 0, id;
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day15/input.txt")))) {
			String line = br.readLine();
			for(String a : line.split(",")) {
				if(a.contains("=")) {
					id=hash(a.split("=")[0]);
					if(!map.containsKey(id)) 
						map.put(id, new LinkedHashMap<String,Integer>());
					map.get(id).put(a.split("=")[0], Integer.parseInt(a.split("=")[1]));
				}
				else {
					id=hash(a.substring(0, a.length()-1));
					if(map.containsKey(id))
						map.get(id).remove(a.substring(0, a.length()-1));
				}
			}
	    }
		for(int key : map.keySet()) {
			int i = 1, current;
			for(String m : map.get(key).keySet()) {
				current=((key+1)*i*map.get(key).get(m));
				System.out.println(m+": "+current);
				sum+= current;
				i++;
			}
		}
		System.out.println(sum);

	}
	static int hash(String s) {
		int curr = 0;
		
		for(char c : s.toCharArray()) {
			curr+=(int)c;
			curr*=17;
			curr%=256;
		}
		return curr;
	}

}
