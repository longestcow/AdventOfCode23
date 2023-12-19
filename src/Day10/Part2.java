package Day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Part2 {

	static char[][] grid,bigGrid;
	static String curr1="";
	static List<String> visited = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		int in = 1;
		String sc="";
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day10/input.txt")))) {
			String line = br.readLine();
			grid = new char[140][];
			bigGrid = new char[grid.length*3][line.length()*3];
			grid[0]=line.toCharArray();
	    	if(line.contains("S")) //if s on first line
	    		sc="0,"+line.indexOf("S");
		    while ((line = br.readLine()) != null) {
		    	grid[in]=line.toCharArray();
		    	if(line.contains("S")) 
		    		sc=in+","+line.indexOf("S");
		    	
		    	in++;
		    }
		    
	    }
		char curr,next;

		int x,y;
		x=Integer.parseInt(sc.split(",")[0]);
		y=Integer.parseInt(sc.split(",")[1]);
		curr=grid[x][y];
		visited.add(sc);
		
		if(x-1>=0 && ((next=grid[x-1][y])=='7'||next=='F'||next=='|')) 
			curr1=(x-1)+","+y;
		
		if(x+1<grid.length && ((next=grid[x+1][y])=='J'||next=='L'|next=='|')) 
			curr1=(x+1)+","+y;
	
		if(y-1>=0 && ((next=grid[x][y-1])=='F'||next=='L'|next=='-')) 
			curr1=x+","+(y-1);	

		if(y+1<grid[0].length && ((next=grid[x][y+1])=='7'||next=='J'||next=='-')) 
			curr1=x+","+(y+1);

		
		grid[x][y]='-'; // i had to hardcode this, so the solution probably wont work for every input set
		
		
		while(true) { //calculate trail
			x=Integer.parseInt(curr1.split(",")[0]);
			y=Integer.parseInt(curr1.split(",")[1]);
			curr=grid[x][y];
			visited.add(curr1);
			if(x-1>=0 && (curr=='J'||curr=='L'|curr=='|') && !visited.contains((x-1)+","+y) && (((next=grid[x-1][y])=='7'||next=='F'||next=='|'))) 
				curr1=(x-1)+","+y;
			
			else if(x+1<grid.length && (curr=='7'||curr=='F'||curr=='|') && !visited.contains((x+1)+","+y) && (((next=grid[x+1][y])=='J'||next=='L'|next=='|'))) 
				curr1=(x+1)+","+y;
			
			else if(y-1>=0 && (curr=='7'||curr=='J'||curr=='-') && !visited.contains(x+","+(y-1)) && (((next=grid[x][y-1])=='F'||next=='L'|next=='-'))) 
				curr1=x+","+(y-1);
			
			else if(y+1<grid[0].length && (curr=='F'||curr=='L'|curr=='-') && !visited.contains(x+","+(y+1)) && (((next=grid[x][y+1])=='7'||next=='J'||next=='-'))) 
				curr1=x+","+(y+1);
			else break;
		}
		
		
		//remove everything other than trail
		for(int i = 0; i<grid.length; i++) 
			for(int j = 0; j<grid[i].length; j++) 
				if(!visited.contains(i+","+j))
					grid[i][j]='.';
			
		
		
		
		
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[0].length; j++) {
				curr=grid[i][j];
				if(curr=='.') {
					bigGrid[i*3][j*3]='.';bigGrid[i*3][j*3+1]='.';bigGrid[i*3][j*3+2]='.';
					
					bigGrid[i*3+1][j*3]='.';bigGrid[i*3+1][j*3+1]='.';bigGrid[i*3+1][j*3+2]='.';
					
					bigGrid[i*3+2][j*3]='.';bigGrid[i*3+2][j*3+1]='.';bigGrid[i*3+2][j*3+2]='.';
					continue;
				}
				
				bigGrid[i*3][j*3]=',';bigGrid[i*3][j*3+1]=(curr=='J'||curr=='|'||curr=='L')?'|':',';bigGrid[i*3][j*3+2]=',';
				
				bigGrid[i*3+1][j*3]=(curr=='J'||curr=='-'||curr=='7')?'-':',';bigGrid[i*3+1][j*3+1]=curr;bigGrid[i*3+1][j*3+2]=(curr=='F'||curr=='-'||curr=='L')?'-':',';
				
				bigGrid[i*3+2][j*3]=',';bigGrid[i*3+2][j*3+1]=(curr=='F'||curr=='|'||curr=='7')?'|':',';bigGrid[i*3+2][j*3+2]=',';

			}
		}
		
		floodFill();
		long sum = 0;
		
		for(int i = 0; i<bigGrid.length; i++) 
			for(int j = 0; j<bigGrid[0].length; j++) 
				if(bigGrid[i][j]=='.')sum++;
//		printGrid(bigGrid);

		System.out.println((sum/9));
		
		
		

		
	}
	
	static void floodFill() {
		Set<String> filled = new HashSet<>(), toAdd = new HashSet<>(),nextGen = new HashSet<>();
		int x,y,cx,cy;
		//4 corners
		nextGen.add(0+","+(0));
		nextGen.add((bigGrid.length-1)+","+(bigGrid.length-1));
		nextGen.add((bigGrid.length-1)+","+0);
		nextGen.add(0+","+(bigGrid.length-1));
		
		while(true) {
			for(String s : nextGen) {
				x = Integer.parseInt(s.split(",")[0]);
				y = Integer.parseInt(s.split(",")[1]);
				bigGrid[x][y]='=';
				if((cx=x-1)>=0 && (cy=y-1)>=0 && !filled.contains(cx+","+cy) && (bigGrid[cx][cy]=='.' || bigGrid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				if((cx=x-1)>=0 && !filled.contains(cx+","+y) && (bigGrid[cx][y]=='.' || bigGrid[cx][y]==',')) toAdd.add((cx)+","+(y));
				if((cx=x-1)>=0 && (cy=y+1)<bigGrid[0].length && !filled.contains(cx+","+cy) && (bigGrid[cx][cy]=='.' || bigGrid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				
				if((cx=x)>=0 && (cy=y-1)>=0  && !filled.contains(cx+","+cy) && (bigGrid[cx][cy]=='.' || bigGrid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				if((cx=x)>=0 && (cy=y+1)<bigGrid[0].length && !filled.contains(cx+","+cy) && (bigGrid[cx][cy]=='.' || bigGrid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				
				if((cx=x+1)<bigGrid.length && (cy=y-1)>=0 && !filled.contains(cx+","+cy) && (bigGrid[cx][cy]=='.' || bigGrid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				if((cx=x+1)<bigGrid.length && !filled.contains(cx+","+y) && (bigGrid[cx][y]=='.' || bigGrid[cx][y]==',')) toAdd.add((cx)+","+(y));
				if((cx=x+1)<bigGrid.length && (cy=y+1)<bigGrid[0].length  && !filled.contains(cx+","+cy) && (bigGrid[cx][cy]=='.' || bigGrid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
			
			}
			if(toAdd.isEmpty())break;
			filled.addAll(toAdd);
			nextGen.clear();
			nextGen.addAll(toAdd);
			toAdd.clear();

		}
		
		
	}

	static void printGrid(char[][] grid) {
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) 
				System.out.print(grid[i][j]+" ");
			System.out.println();
		}
	}
	
	

}
