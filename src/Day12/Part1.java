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
		int sum = 0, curr=0;
		for(String key : map.keySet()) {
			curr=0;
			List<Integer> qs = new ArrayList<Integer>();
			for(int i = 0; i<key.length(); i++)
				if(key.charAt(i)=='?')qs.add(i);
			curr+=dfs("#", key, map.get(key), qs, 0);
			curr+=dfs(".", key, map.get(key), qs, 0);
			sum+=curr;
		}
		System.out.println(sum);
	}
	
	static int dfs(String pick, String s, List<Integer> i, List<Integer> qs, int ind) {
		s=s.substring(0,qs.get(ind))+pick+s.substring(qs.get(ind)+1);
		if(ind==qs.size()-1) {
			if(checkValid(s,i,s.length()-1))
				return 1;
			else return 0;
		}
		
		if(!checkValid(s,i,qs.get(ind)))
			return 0;
		
		int sum = 0;

		sum+=dfs("#", s, i, qs, ind+1);
		sum+=dfs(".", s, i, qs, ind+1);
		return sum;
	}
	
	static boolean checkValid(String s, List<Integer> i, int length) {
		s=s.substring(0, length);
		int c = 0;
		for(String a : s.split("\\.")) {
			if(a.isEmpty())continue;
			try {
				if(a.length()!=i.get(c))
					return false;
				c++;

			}catch(IndexOutOfBoundsException e) {return false;}
		}
		if(i.size()==c)
			return true;
		return false;
	

	}
	

}
