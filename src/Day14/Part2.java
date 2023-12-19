package Day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

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
		    
		    String curr;
		    int a = 0; // a = number of total cycles before it starts looping
		    while(!ids.contains((curr=cycle()))) {
		    	ids.add(curr);
			    a++;
		    }
		    int b = ids.indexOf(curr);//starting pos of loop
		    //a-b = length of the loop
		    
		    int c=(1_000_000_000-(b+1))%(a-b); //number of steps to go backward to get to the point of 1bil from loop start
		    //idea here is we remove the first few steps not in the loop from the billion
		    //and then we just see how many times our loop length fits into that billion-b
		    //and whatever the remainder is, we are supposed to go "back" to that point in the loop
		    //or since its just a loop, we just do the length of the loop - that remainder, and go forward by that many steps
		    for(int e = 0; e<c; e++) 
		    	cycle();
		    
		    //check duplicates in loop

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

	static void rotateCW() { // taken from SO
	    final int M = grid.length;
	    final int N = grid[0].length;
	    char[][] ret = new char[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = grid[r][c];
	        }
	    }
	    grid = Arrays.stream(ret).map(char[]::clone).toArray(char[][]::new);
	}
	
	static String cycle() { 
		for(int i = 0; i<4; i++) {
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
			rotateCW();

		}
		return Arrays.deepToString(grid); // basically the id of the current status of the grid
	}
	
	static void printGrid() {
    	for(char[] arr : grid) {
    		for(char c : arr)System.out.print(c);
    		System.out.println();
    	}
    	System.out.println();
	}
	


	

}
