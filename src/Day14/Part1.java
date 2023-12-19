package Day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

	static int s;
	static List<String> ids = new ArrayList<>();
	static char[][] grid;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day14/input.txt")))) {
			String line = br.readLine();
			s=line.length();
			grid = new char[s][s];
			grid[0]=line.toCharArray();
			int row = 1;
		    while ((line = br.readLine()) != null) 
		    	grid[row++]=line.toCharArray();
		    
		    cycle();
		    System.out.println(calcNorthLoad());
		    
	    }

	}
	
	 static int calcNorthLoad() {
	    int sum = 0;
	    for(int i = 0; i<grid.length; i++) {
	    	int count = 0;
	    	for(int j = 0; j<grid[i].length; j++) if(grid[i][j]=='O')count++;
	    	sum+=(count*(s-i));
	    }
	    
		return sum;
	}

	
	static void cycle() { 
		for(int row = 0; row<grid.length;row++) {
	    	for(int col = 0; col<grid[row].length; col++) {
	    		if(grid[row][col]=='O') {
	    			int lowRow = row;
	    			for(int curr = row-1; curr>=0; curr--) {
	    				if(grid[curr][col]=='.')lowRow=curr;
	    				else break;
	    			}
	    			if(lowRow!=row) {
	    				grid[lowRow][col]='O';
	    				grid[row][col]='.';
	    			}
	    			
	    		}
	    		
	    	}
		}

	}
	
	static void printGrid() {
    	for(char[] arr : grid) {
    		for(char c : arr)System.out.print(c);
    		System.out.println();
    	}
    	System.out.println();
	}
	


	

}
