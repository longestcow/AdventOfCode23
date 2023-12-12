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
	static int[][] graph;
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
		    	if(!line.contains("#"))rows.add(i);
		    	
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
		
		for(int i = 0; i<galaxies.size(); i++) 
			for(int j = galaxies.size()-1; j>0+(i); j--) 
				if(i!=j)
					combinations.add(galaxies.get(i)+"-"+galaxies.get(j));
			

		

		sizeX=lines.size(); sizeY=lines.get(0).length();
		grid = new char[sizeX][sizeY];
		graph=new int[sizeX][sizeY];
		for(int i = 0; i<lines.size(); i++) {
			grid[i]=lines.get(i).toCharArray();
			for(int j = 0; j<graph.length; j++) {
				if(rows.contains(i) || cols.contains(j))
					graph[i][j]=part;
				else graph[i][j]=1;
			}
		}
		
		for(char[] arr : grid) {
			for(char ca : arr)
				System.out.print(ca);
			System.out.println();
		}
				
		System.out.println(rows);
		System.out.println(cols);
		int sum = 0;
		for(String comb : combinations) 
			sum+=getDist(comb.split("-")[0],comb.split("-")[1]);		
		System.out.println(sum);
	}
	private static int getDist(String c1, String c2) {
		int step = 0, x,y;
		List<String> visited = new ArrayList<>(), temp = new ArrayList<>();;
		visited.add(c1);
		
		while(!visited.contains(c2)) {
			for(String c : visited) {
				x = Integer.parseInt(c.split(",")[0]); y= Integer.parseInt(c.split(",")[1]);
				if(x+1<sizeX && (grid[x+1][y]=='.' || ((x+1)+","+y).equals(c2)) && !visited.contains((x+1)+","+y)) {
					temp.add((x+1)+","+y);
				}
				if(x-1>=0 && (grid[x-1][y]=='.' || ((x-1)+","+y).equals(c2)) && !visited.contains((x-1)+","+y)) {
					temp.add((x-1)+","+y);
				}
				if(y+1<sizeY && (grid[x][y+1]=='.' || (x+","+(y+1)).equals(c2)) && !visited.contains(x+","+(y+1))) {
					temp.add(x+","+(y+1));
				}
				if(y-1>=0 && (grid[x][y-1]=='.' || (x+","+(y-1)).equals(c2)) && !visited.contains(x+","+(y-1))) {
					temp.add(x+","+(y-1));
				}
			}
			visited.addAll(temp);
			temp.clear();

			step++;
		}
		
		return step;
	}
	static void printGrid(List<String> coords, String c1, String c2) {
		for(int i = 0; i<sizeX; i++) {
			for(int j = 0; j<sizeY; j++) {
				if((i+","+j).equals(c1) || (i+","+j).equals(c2)) 
					System.out.print("\u001B[32m#\u001B[0m");
				else if (coords.contains(i+","+j))
					System.out.print("\u001B[32m#\u001B[0m");
				else System.out.print(".");
				
			}
			System.out.println();
		}
	}
	
	

}