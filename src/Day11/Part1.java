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
	static int part = 2;
	static char[][] grid;
	static int sizeX, sizeY;
	static List<Integer> cols = new ArrayList<>(), rows = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<String> lines = new ArrayList<>(), galaxies = new ArrayList<>(), combinations = new ArrayList<>();
		HashMap<String, Integer> temp = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day11/input.txt")))) {
			String line;
			int i = 0;
		    while ((line = br.readLine()) != null) {
		    	lines.add(line);
		    	if(!line.contains("#"))lines.add(line);
		    	
		    	for(int j = 0; j<line.length(); j++)
		    		if(line.charAt(j)=='#') 
		    			galaxies.add(i+","+j);
		    		
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
		int c = 0;
		for(int i : cols) {
			for(int j = 0; j<lines.size(); j++) 
				lines.set(j, lines.get(j).substring(0,i+c)+"."+lines.get(j).substring(i+c));
			c++;
		}
		for(int i = 0; i<galaxies.size(); i++) 
			for(int j = galaxies.size()-1; j>0+(i); j--) 
				if(i!=j)
					combinations.add(galaxies.get(i)+"-"+galaxies.get(j));
			

		

		sizeX=lines.size(); sizeY=lines.get(0).length();
		grid = new char[sizeX][sizeY];
		for(int i = 0; i<lines.size(); i++)
			grid[i]=lines.get(i).toCharArray();
		
		System.out.println(rows);
		System.out.println(cols);
		int sum = 0;
		for(String comb : combinations) 
			sum+=getDist(comb.split("-")[0],comb.split("-")[1]);		
		System.out.println(sum);
	}
	private static int getDist(String c1, String c2) {
		int step = 0, x,y;
		List<String> visited = new ArrayList<>(), temp = new ArrayList<String>(), all = new ArrayList<String>();
		visited.add(c1);
		
		while(!visited.contains(c2)) {
			temp.clear();
			temp.addAll(visited);
			visited.clear();
			for(String c : temp) {
				x = Integer.parseInt(c.split(",")[0]); y= Integer.parseInt(c.split(",")[1]);
				if(x+1<sizeX && grid[x+1][y]=='.' ) {
					visited.add((x+1)+","+y);
				}
				if(x-1>=0 && grid[x-1][y]=='.' ) {
					visited.add((x-1)+","+y);
				}
				if(y+1<sizeY && grid[x][y+1]=='.' ) {
					visited.add(x+","+(y+1));
				}
				if(y-1>=0 && grid[x][y-1]=='.' ) {
					visited.add(x+","+(y-1));
				}
			}

			step++;
		}
		
		return step;
	}
	
	

}