package Day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
	static int s1 = ".OO.O.#....O.O#......O#...#.##O#...O.#....#..O#O..#...#....O..OOO#.#.....O#.#O.##.#O#O.#.O....#.##O.".length();
	static int s2 = "O....#....".length();
	static char[][] grid = new char[s1][s1];
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day14/input.txt")))) {
			String line;
			int row = 0;
		    while ((line = br.readLine()) != null) {
		    	for(int col = 0; col<line.length(); col++) {
		    		if(line.charAt(col)=='O') {
		    			int lowRow = row;
		    			for(int curr = row; curr>=0; curr--) {
		    				if(grid[curr][col]=='.')lowRow=curr;
		    				else if(grid[curr][col]=='#' || grid[curr][col]=='O')break;
		    			}
		    			if(lowRow!=row) {
		    				grid[lowRow][col]='O';
		    				grid[row][col]='.';
		    			}
		    			else grid[row][col]='O';
		    			
		    		}
		    		else grid[row][col]=line.charAt(col);
		    		
		    	}
		    	row++;
		    }
		    int sum = 0;
		    for(int i = 0; i<grid.length; i++) {
		    	int count = 0;
		    	for(int j = 0; j<grid[i].length; j++) 
		    		if(grid[i][j]=='O')count++;
		    	sum+=(count*(s1-i));
		    }
		    System.out.println(sum);
	    }
		//use arrays.hashcode


	}
	


	

}
