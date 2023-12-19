package Day18;

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


public class Part1 {
	static char[][] grid;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		int md=0,mr=0,ml=0,mu=0,d=0,r=0,l=0,u=0; 
		List<String> directions = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day18/input.txt")))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	line=((line.charAt(0)=='R')?'0':(line.charAt(0)=='D')?'1':(line.charAt(0)=='L')?'2':'3')+" "+line.split(" ")[1];
		    	System.out.println(line);
		    	if(line.charAt(0)=='0') {
		    		r+=Integer.parseInt(line.split(" ")[1]);
		    		l-=Integer.parseInt(line.split(" ")[1]);
		    		if(r>mr)mr=r;
		    	}
		    	
		    	else if(line.charAt(0)=='2') {
		    		r-=Integer.parseInt(line.split(" ")[1]);
		    		l+=Integer.parseInt(line.split(" ")[1]);
		    		if(l>ml)ml=l;
		    	}
		    	else if(line.charAt(0)=='3') {
		    		u+=Integer.parseInt(line.split(" ")[1]);
		    		d-=Integer.parseInt(line.split(" ")[1]);
		    		if(u>mu)mu=u;
		    	}
		    	
		    	else if(line.charAt(0)=='1') {
		    		u-=Integer.parseInt(line.split(" ")[1]);
		    		d+=Integer.parseInt(line.split(" ")[1]);
		    		if(d>md)md=d;
		    	}
		    	directions.add(line);
		    	
		    }
	    }
		int x=md,y=mr,cx = 1, cy = 1, n;
		if(mu-md>0) {
			x+=Math.abs(mu-md);
			cx=Math.abs(mu-md)+1;
		}
		if(ml-mr>0) {
			y+=Math.abs(ml-mr);
			cy=Math.abs(ml-mr)+1;
		}
		System.out.println(cx+","+cy);
		grid = new char[x+3][y+3];

		
		char dir;
		for(int i = 0 ;i<grid.length; i++) 
			for(int j = 0; j<grid[i].length; j++)
				grid[i][j]='.';
			
		for(String a : directions) {
			dir=a.split(" ")[0].charAt(0);
			n=Integer.parseInt(a.split(" ")[1]);
			for(int i = 0; i<n; i++) {
				cx+= (dir=='3')?-1:(dir=='1')?1:0;
				cy+= (dir=='2')?-1:(dir=='0')?1:0;
				grid[cx][cy]='#';
			}
		}

		floodFill();
		for(int i = 0 ;i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++)
				System.out.print(grid[i][j]+" ");
			System.out.println();
		}
		long sum = 0;
		for(int i = 0; i<grid.length; i++) 
			for(int j = 0; j<grid[i].length; j++)
				if(grid[i][j]!='=')sum++;

		System.out.println(sum);
		

	}
	
	
	static void floodFill() {
		Set<String> filled = new HashSet<>(), toAdd = new HashSet<>(),nextGen = new HashSet<>();
		int x,y,cx,cy;
		filled.add("0,0");
		nextGen.add("0,0");
		while(true) {
			for(String s : nextGen) {
				x = Integer.parseInt(s.split(",")[0]);
				y = Integer.parseInt(s.split(",")[1]);
				grid[x][y]='=';
				if((cx=x-1)>=0 && (cy=y-1)>=0 && !filled.contains(cx+","+cy) && (grid[cx][cy]=='.' || grid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				if((cx=x-1)>=0 && !filled.contains(cx+","+y) && (grid[cx][y]=='.' || grid[cx][y]==',')) toAdd.add((cx)+","+(y));
				if((cx=x-1)>=0 && (cy=y+1)<grid[0].length && !filled.contains(cx+","+cy) && (grid[cx][cy]=='.' || grid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				
				if((cx=x)>=0 && (cy=y-1)>=0  && !filled.contains(cx+","+cy) && (grid[cx][cy]=='.' || grid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				if((cx=x)>=0 && (cy=y+1)<grid[0].length && !filled.contains(cx+","+cy) && (grid[cx][cy]=='.' || grid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				
				if((cx=x+1)<grid.length && (cy=y-1)>=0 && !filled.contains(cx+","+cy) && (grid[cx][cy]=='.' || grid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
				if((cx=x+1)<grid.length && !filled.contains(cx+","+y) && (grid[cx][y]=='.' || grid[cx][y]==',')) toAdd.add((cx)+","+(y));
				if((cx=x+1)<grid.length && (cy=y+1)<grid[0].length  && !filled.contains(cx+","+cy) && (grid[cx][cy]=='.' || grid[cx][cy]==',')) toAdd.add((cx)+","+(cy));
			}
			if(toAdd.isEmpty())break;
			filled.addAll(toAdd);
			nextGen.clear();
			nextGen.addAll(toAdd);
			toAdd.clear();
//			printGrid(grid);

		}
		
		
	}

	

}
