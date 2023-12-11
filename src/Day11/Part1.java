package Day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part1 {
	static int size = 140;

	static char[][] grid = new char[size][];
	static String curr1="", curr2="";
	static HashMap<String,Integer> visited = new HashMap<>();
	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day11/input.txt")))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	lines.add(line);
		    	if(!line.contains("#"))lines.add(line);
		    }
	    }
		boolean s;
		List<Integer> cols = new ArrayList<>();
		for(int i = 1; i<lines.get(0).length()-1; i++) {
			s=true;
			for(String line : lines) {
				if(line.charAt(i)=='#') {
					s=false;
					break;
				}
			}
			if(s)cols.add(i);
		}
		int c = 0;
		for(int i : cols) {
			for(int j = 0; j<lines.size(); j++) 
				lines.set(j, lines.get(j).substring(0,i+c)+"."+lines.get(j).substring(i+c));
			c++;
		}
		
		char[][] grid = new char[lines.size()][lines.get(0).length()];
		for(int i = 0; i<lines.size(); i++)grid[i]=lines.get(i).toCharArray();
		
		//pathfind code begins here
		
	}
	
	

}