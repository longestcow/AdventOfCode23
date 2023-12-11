package Day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Part2 {
	static int size=10;

	static char[][] grid = new char[size][];
	static String curr1="", curr2="";
	static HashMap<String,Integer> visited = new HashMap<>();
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		int in = 0;
		String sc = "";
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day10/input.txt")))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	grid[in]=line.toCharArray();
		    	if(line.contains("S")) 
		    		sc=in+","+line.indexOf("S");
		    	
		    	in++;
		    }
		    
	    }
		//add both streams from s here
		int moves = 0,max=0,x,y, count = 0;
		char curr,next;
		x=Integer.parseInt(sc.split(",")[0]);
		y=Integer.parseInt(sc.split(",")[1]);
		curr=grid[x][y];
		visited.put(sc,0);
		try {
			//up
			next=grid[x-1][y];
			if(next=='7'||next=='F'||next=='|') 
				curr1=(x-1)+","+y;
			
		} catch(Exception e) {}
		
		try {
			//down
			next=grid[x+1][y];
			if(next=='J'||next=='L'|next=='|') {
				if(curr1.isBlank())
					curr1=(x+1)+","+y;
				else curr2=(x+1)+","+y;
			}
		}catch(Exception e) {}
		try {
			//left
			next=grid[x][y-1];
			if(next=='F'||next=='L'|next=='-') {
				if(curr1.isBlank())
					curr1=x+","+(y-1);
				curr2=x+","+(y-1);

			}
		}catch(Exception e) {}
		try {
			//right
			next=grid[x][y+1];
			if(next=='7'||next=='J'||next=='-') {
				curr2=x+","+(y+1);
			}
		}catch(Exception e) {}
		
		
		while(true) {
			count++;
			moves = 0;
			x=Integer.parseInt(curr1.split(",")[0]);
			y=Integer.parseInt(curr1.split(",")[1]);
			curr=grid[x][y];
			visited.put(curr1, count);
			try {
				//up
				next=grid[x-1][y];
				if((curr=='J'||curr=='L'|curr=='|') && !visited.containsKey((x-1)+","+y) && ((next=='7'||next=='F'||next=='|'))) {
					curr1=(x-1)+","+y;
					moves++;
				}
			} catch(Exception e) {}
			try {
				//down
				next=grid[x+1][y];
				if((curr=='7'||curr=='F'||curr=='|') && !visited.containsKey((x+1)+","+y) && ((next=='J'||next=='L'|next=='|'))) {
					curr1=(x+1)+","+y;
					moves++;
				}
			}catch(Exception e) {}
			try {
				//left
				next=grid[x][y-1];
				if((curr=='7'||curr=='J'||curr=='-') && !visited.containsKey(x+","+(y-1)) && ((next=='F'||next=='L'|next=='-'))) {
					curr1=x+","+(y-1);
					moves++;
				}
			}catch(Exception e) {}
			try {
				//right
				next=grid[x][y+1];
				if((curr=='F'||curr=='L'|curr=='-') && !visited.containsKey(x+","+(y+1)) && ((next=='7'||next=='J'||next=='-'))) {
					curr1=x+","+(y+1);
					moves++;
				}
			}catch(Exception e) {}
			
			x=Integer.parseInt(curr2.split(",")[0]);
			y=Integer.parseInt(curr2.split(",")[1]);
			curr=grid[x][y];
			visited.put(curr2, count);
			try {
				//up
				next=grid[x-1][y];
				if((curr=='J'||curr=='L'|curr=='|') && !visited.containsKey((x-1)+","+y) && ((next=='7'||next=='F'||next=='|'))) {
					curr2=(x-1)+","+y;
					moves++;
				}
			} catch(Exception e) {}
			try {
				//down
				next=grid[x+1][y];
				if((curr=='7'||curr=='F'||curr=='|') && !visited.containsKey((x+1)+","+y) && ((next=='J'||next=='L'|next=='|'))) {
					curr2=(x+1)+","+y;
					moves++;
				}
			}catch(Exception e) {}
			try {
				//left
				next=grid[x][y-1];
				if((curr=='7'||curr=='J'||curr=='-') && !visited.containsKey(x+","+(y-1)) && ((next=='F'||next=='L'|next=='-'))) {
					curr2=x+","+(y-1);
					moves++;
				}
			}catch(Exception e) {}
			try {
				//right
				next=grid[x][y+1];
				if((curr=='F'||curr=='L'|curr=='-') && !visited.containsKey(x+","+(y+1)) && ((next=='7'||next=='J'||next=='-'))) {
					curr2=x+","+(y+1);
					moves++;
				}
			}catch(Exception e) {}

			if(moves==0)break;
		}
		
		for(int c : visited.values()) 
			if(c>max)max=c;
		
		System.out.println(max + " ("+(System.currentTimeMillis()-startTime)+"ms)");
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				if(visited.containsKey(i+","+j))System.out.print("|");
				else System.out.print(".");
			}
			System.out.println();
		}
	}
	


}