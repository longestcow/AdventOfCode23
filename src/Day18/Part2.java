package Day18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day18/input.txt")))) {
			String line;
			long maxL=0, maxU=0,l=0,u=0;
		    long direction, dist, currx=0, curry=0;
			int c=1;

			List<String> dirs = new ArrayList<>();
		    while ((line = br.readLine()) != null) {
		    	line = line.split(" ")[2].replace("(#", "").replace(")", "");
		    	line = line.charAt(line.length()-1)+" "+line.substring(0,5);
		    	
//		    	line=((line.charAt(0)=='R')?'0':(line.charAt(0)=='D')?'1':(line.charAt(0)=='L')?'2':'3')+" "+line.split(" ")[1];
		    	direction = Long.parseLong(line.split(" ")[0]);
		    	dist = Long.parseLong(line.split(" ")[1],16);
		    	if(direction==0) {
		    		l-=dist;
		    		if(maxL>l)maxL=l;
		    	}
		    	else if(direction==2)l+=dist;
		    	else if(direction==3) {
		    		u-=dist;
		    		if(maxU>u)maxU=u;
		    	}
		    	else if(direction==1)u+=dist;
		    	dirs.add(line);
		    }
		    long sum = 0, peri = 0;
		    long[] x = new long[dirs.size()+2], y = new long[dirs.size()+2];
//		    currx=-maxU;curry=-maxL;
		    x[0]=currx;y[0]=curry;
		    x[x.length-1]=currx;y[y.length-1]=curry;

		    for(String dir : dirs) {
		    	System.out.println(currx+","+curry);
		    	direction = Long.parseLong(dir.split(" ")[0]);
		    	dist = Long.parseLong(dir.split(" ")[1],16);
		    	currx+=(direction==3)?-dist:(direction==1)?dist:0;
		    	curry+=(direction==2)?-dist:(direction==0)?dist:0;
		    	peri+=dist;
		    	x[c]=currx;y[c]=curry;
		    	c++;
		    }
		    
		    
		    for(int i = 0; i<x.length-1; i++) 
	    		sum+= (x[i]*y[i+1] - x[i+1]*y[i]);
		    
		    sum=Math.abs(sum);
		    System.out.println((sum/2) + (peri/2)+1);
	    }


	}
	



	

}
