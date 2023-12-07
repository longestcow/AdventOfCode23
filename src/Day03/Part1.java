package Day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    static List<List<Character>> grid = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();

	    String line;
	    int a=0, sum=0;
	    String symbols = "*-#/=&%$&@+";
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day03/input.txt")))) {

		    while ((line = br.readLine()) != null) {
		    	grid.add(new ArrayList<Character>());
		    	for(String c : line.split(""))
		    		grid.get(a).add(c.charAt(0));
		    	a++;
		    }
		}
		for(int i = 0; i<grid.size(); i++) {
			for(int j = 0; j<grid.get(i).size(); j++) {
				if(symbols.contains(grid.get(i).get(j)+""))//is symbol
					sum+=neighbourChecks(i,j);
			}
		}
		System.out.println(sum + " ("+(System.currentTimeMillis()-startTime)+"ms)");
	}

	static int neighbourChecks(int i, int j) {
		int[] pNum;
		int num=0;
		for(int x = -1; x<=1; x++) {
			for(int y = -1; y<=1; y++) {
				if(x==0 && y==0) continue;
				pNum=checkPartNumber(i+x, y+j);
				if(pNum[0]!=-1) {
					num+=pNum[0];
					for(int k = 0; k<pNum[2]; k++) 
						grid.get(i+x).set(pNum[1]+k,'.');
				}
			}
		}
		return num;
		
	}

	static int[] checkPartNumber(int i, int j) {
		String num="";
		int  s = 0, l = 0;
		boolean started = false;
		try {
			if(Character.isDigit(grid.get(i).get(j))) {
				for(int a = -2; a<=2; a++) {
					if(j+a>grid.get(0).size()-1)continue;
					if(Character.isDigit(grid.get(i).get(j+a))) {
						num+=grid.get(i).get(j+a);
						if(!started)s=j+a;
						started=true;
						l++;
					}
					else if(started) {
						if(a<0) {
							num="";
							started=false;
							s=0;
							l=0;
						}
						else
							break;
					}
				}
			}
			
			return new int[] {Integer.parseInt(num), s, l};
		}
		catch(Exception e) {return new int[] {-1};}
		
		
	}


}
