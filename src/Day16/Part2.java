package Day16;

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
		
		for(int y = 0; y<grid.length; y++) 
			energies.add(getEnergy(0,y,1,0));
		for(int y = 0; y<grid.length; y++) 
			energies.add(getEnergy(grid.length-1,y,-1,0));
		for(int x = 0; x<grid.length; x++) 
			energies.add(getEnergy(x,0,0,1));
		for(int x = 0; x<grid.length; x++) 
			energies.add(getEnergy(x,grid.length-1,0,-1));
		
		System.out.println(Collections.max(energies) + " ("+(System.currentTimeMillis()-startTime)+"ms)");
		

	}

	static int getEnergy(int x, int y, int dx, int dy) {
		HashSet<String> set = new HashSet<>();
		List<String> splits = new ArrayList<>();
		List<Beam> beams = new ArrayList<>(),toAdd=new ArrayList<>(),toRemove=new ArrayList<>();
		beams.add(new Beam(x,y,dx,dy));
		while(!beams.isEmpty()){
			for(Beam beam : beams) {
				if(!(beam.x<0 || beam.x>grid.length-1 || beam.y<0 || beam.y>grid.length-1)) {
					set.add(beam.x+","+beam.y);
					if(grid[beam.x][beam.y]=='|') {
						if(beam.dy!=0) {
							if(splits.contains(beam.x+","+beam.y))toRemove.add(beam);
							else {
								toRemove.add(beam);
								toAdd.add(new Beam(beam.x, beam.y, 1, 0));
								toAdd.add(new Beam(beam.x, beam.y, -1, 0));
								splits.add(beam.x+","+beam.y);
							}
						}
					}
					else if(grid[beam.x][beam.y]=='-') {
						if(beam.dx!=0) {
							if(splits.contains(beam.x+","+beam.y))toRemove.add(beam);
							else {
								toRemove.add(beam);
								toAdd.add(new Beam(beam.x, beam.y, 0, 1));
								toAdd.add(new Beam(beam.x, beam.y, 0, -1));
								splits.add(beam.x+","+beam.y);
							}
						}
					}
					else if(grid[beam.x][beam.y]=='/') {
						int tx = beam.dx, ty=beam.dy;
						beam.dx=-ty;
						beam.dy=-tx;
					}
					else if(grid[beam.x][beam.y]=='\\') {
						int tx = beam.dx, ty=beam.dy;
						beam.dx=ty;
						beam.dy=tx;
					}
					
					beam.x+=beam.dx;
					beam.y+=beam.dy;
				}
				else toRemove.add(beam);
			}
			beams.addAll(toAdd);
			beams.removeAll(toRemove);
			
			toAdd.clear();
			toRemove.clear();
		}
		
		return set.size();
	}

}

