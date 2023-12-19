package Day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
	static List<List<Long[]>> list = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		long time = 0, dist=0;
		String ds="",ts="";
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day06/input.txt")))) {
			String[] tim = br.readLine().split("Time:")[1].split(" "), dis = br.readLine().split("Distance:")[1].split(" ");
			for(String t : tim) 
				if(!t.isEmpty())ts+=t;
			for(String d : dis) 
				if(!d.isEmpty())ds+=d;
			time=Long.parseLong(ts);
			dist=Long.parseLong(ds);

			int i = 0;
			for(long t : findFactors(time)) {
				if(t>dist)i+=2;
			}
			System.out.println(i+" ("+(System.currentTimeMillis()-startTime)+"ms)");


	    }
	}
	public static ArrayList<Long> findFactors(long num) {        
		ArrayList<Long> factors = new ArrayList<Long>();
		long j = (num%2!=0)?1:0;
		for(long i = 1; i<num/2+j; i++) {
			factors.add((num-i) * i);
		}
	    return factors;
	}
	

}
