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

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<String> lines = new ArrayList<>();
		List<Integer> cols = new ArrayList<>(), rows = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day11/input.txt")))) {
			String line;
			int i = 0;
		    while ((line = br.readLine()) != null) {
		    	lines.add(line);
		    	if(!line.contains("#"))rows.add(i);
		    	i++;
		    }
	    }
		boolean s;
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


		
		char[][] grid = new char[lines.size()][lines.get(0).length()];
		for(int i = 0; i<lines.size(); i++)grid[i]=lines.get(i).toCharArray();
		System.out.println(rows);
		System.out.println(cols);
		//pathfind code begins here
		
	}
	
	

}