package Day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part2 {
	static int part = 1000000;
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
			

		

//		sizeX=lines.size(); sizeY=lines.get(0).length();
//		grid = new char[sizeX][sizeY];
////		graph=new int[sizeX][sizeY];
////		for(int i = 0; i<lines.size(); i++) {
////			grid[i]=lines.get(i).toCharArray();
////			for(int j = 0; j<graph.length; j++) {
////				if(rows.contains(i) || cols.contains(j))
////					graph[i][j]=part;
////				else graph[i][j]=1;
////			}
////		}
		
//		for(char[] arr : grid) {
//			for(char ca : arr)
//				System.out.print(ca);
//			System.out.println();
//		}
				
		System.out.println(rows);
		System.out.println(cols);
		long sum = 0;
		for(String comb : combinations) 
			sum+=getDist(comb.split("-")[0],comb.split("-")[1]);		
		System.out.println(sum);
	}
	private static long getDist(String c1, String c2) {
		int x0 = Integer.parseInt(c1.split(",")[0]), y0 = Integer.parseInt(c1.split(",")[1]);
		int x1 = Integer.parseInt(c2.split(",")[0]), y1 = Integer.parseInt(c2.split(",")[1]);

		long distance = Math.abs(x1-x0) + Math.abs(y1-y0);
		
		for(int row : rows) {
			if((x0<row && x1>row) || (x0>row && x1<row))
				distance+=part-1;
		}
		for(int col : cols) {
			if((y0<col&& y1>col) || (y0>col && y1<col))
				distance+=part-1;
		}
		return distance;
	}

	
	

}