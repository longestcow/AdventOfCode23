package Day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Part1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		List<char[][]> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day13/input.txt")))) {
			String line = null;
			List<char[]> temp = new ArrayList<>();
		    while ((line = br.readLine()) != null) {
		    	if(line.isEmpty()) {
		    		char[][] hor = new char[temp.size()][];
		    		for(int i = 0; i<temp.size(); i++)
		    			hor[i]=temp.get(i);
		    		list.add(hor);
		    		list.add(rotateCW(hor));
		    		temp.clear();
		    	}
		    	else 
		    		temp.add(line.toCharArray());
		    	
		    }
	    }
		int h,v,sum=0;
		for(int i = 0; i<list.size(); i+=2) {
			h=calcMirror(list.get(i));
			v=calcMirror(list.get(i+1));
			sum+=h*100;
			sum+=v;
		}
		System.out.println(sum);
		
		

	}
	
	static char[][] rotateCW(char[][] grid) { // taken from SO
	    final int M = grid.length;
	    final int N = grid[0].length;
	    char[][] ret = new char[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = grid[r][c];
	        }
	    }
	    return Arrays.stream(ret).map(char[]::clone).toArray(char[][]::new);
	}
	
	static int calcMirror(char[][] c) {		
		int m=0,n=0,bd=0,ba=0;
		for(int i = 1; i<c.length-1; i++) {
			m=i;n=i+1;
			if(new String(c[m]).equals(new String(c[n]))) {
				int a = m, b = n, d = 0;
				while(a>=0 && b<c.length) {
					if(new String(c[a--]).equals(new String(c[b++]))) 
						d++;
					else break;
				}
				if(d>bd) {
					bd=d;
					ba=m;
				}
			}
		}
		
		if(bd==0)return 0;
		else return c.length-ba;
		

	}


}
