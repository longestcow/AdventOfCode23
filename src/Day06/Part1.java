package Day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
	static List<List<Long[]>> list = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day06/input.txt")))) {
			String[] tim = br.readLine().split("Time:")[1].split(" "), dis = br.readLine().split("Distance:")[1].split(" ");
			List<Long> time = new ArrayList<>(), dist = new ArrayList<>();
			for(String t : tim) 
				if(!t.isEmpty())time.add(Long.parseLong(t));
			
			for(String t : dis) 
				if(!t.isEmpty())dist.add(Long.parseLong(t));
			
			long a = 1;
			for(int j = 0; j<dist.size(); j++) {
				int i = 0;
				for(long t : findFactors(time.get(j))) {
					if(t>dist.get(j))i+=2;
				}
				if(time.get(j)%2==0)i+=1;
				a*=i;
				
			}
			System.out.println(a+" ("+(System.currentTimeMillis()-startTime)+"ms)");


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
