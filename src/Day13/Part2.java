package Day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class Part2 {
	static char[][] grid;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();

		List<Integer> energies = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day16/input.txt")))) {
			String line = br.readLine();
			grid = new char[line.length()][line.length()];
			grid[0]=line.toCharArray();
			int c = 1;
		    while ((line = br.readLine()) != null) 
		    	grid[c++]=line.toCharArray();
	    }
		


	}

}

