package Day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
	static HashMap<String,List<Integer>> map = new HashMap<>(); 

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day12/input.txt")))) {
			String line;
			int i = 0;
		    while ((line = br.readLine()) != null) {
		    	List<Integer> currI = Arrays.asList(line.split(" ")[1].split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());

		    	map.put(line.split(" ")[0], currI);
		    }
	    }
		int sum = 0;
		for(String key : map.keySet()) {
			List<Integer> qs = new ArrayList<Integer>();
			for(int i = 0; i<key.length(); i++)
				if(key.charAt(i)=='?')qs.add(i);
			sum+=dfs("#", key, map.get(key), qs, 0);
			sum+=dfs(".", key, map.get(key), qs, 0);
		}
		System.out.println(sum);
	}
	
	static int dfs(String pick, String s, List<Integer> i, List<Integer> qs, int ind) {
		if(ind==qs.size()-1)
			return checkValid(s,i,qs.get(ind))?1:0;
		s=s.substring(0,qs.get(ind))+pick+s.substring(qs.get(ind)+1);
		int sum = 0;
		if(!checkValid(s,i,qs.get(ind)))
			return 0;

		sum+=dfs("#", s, i, qs, ind+1);
		sum+=dfs(".", s, i, qs, ind+1);
		return sum;
	}
	
	static boolean checkValid(String s, List<Integer> i, int length) {
		s=s.substring(0, length+1);
		if(!s.contains("?")) {
			int c = 0;
			for(String a : s.split("\\.")) {
				if(a.isEmpty())continue;
				try {
					if(a.length()!=i.get(c))
						return false;
				}catch(IndexOutOfBoundsException e) {return false;}
				c++;
			}
			if(i.size()==c+1);
			return true;
		}
		return false;
	}
	

}
