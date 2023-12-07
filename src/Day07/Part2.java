package Day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Part2 {
	static String order = "AKQT98765432J";
	static List<String> ranked = new ArrayList<String>();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final long startTime = System.currentTimeMillis();
		HashMap<String, Integer> hands = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("src/Day07/input.txt")))) {
			String line;
		    while ((line = br.readLine()) != null) 
		    	hands.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
	    }
		ranked.addAll(hands.keySet());
		Collections.sort(ranked, new Comparator<String>() {
		    @Override
		    public int compare(String h1, String h2) {
		        return compareHands(h1,h2);
		    }
		});
		
		int sum = 0;
		for(int i = 0; i<ranked.size(); i++) 
			sum+=hands.get(ranked.get(i))*(i+1);
		
		System.out.println(sum + " ("+(System.currentTimeMillis()-startTime)+"ms)");
	}
	
	static int compareHands(String a, String b) {
		int aT = getType(a), bT=getType(b);
		if(aT>bT)return 1;
		else if(aT<bT)return -1;
		else {
			for(int i = 0; i<5; i++) {
				if(order.indexOf(a.charAt(i))>order.indexOf(b.charAt(i))) return -1;
				else if(order.indexOf(a.charAt(i))<order.indexOf(b.charAt(i))) return 1;
			}
		}
		return 0;//shouldnt ever get here, cards cant be equal
	}
	static int getType(String hand) {
		int a = 0, b = 0;
		String tHand = hand;
		int max = 0;
		char bestChar = 'z';
		while(!hand.isEmpty()) {
			b = countOccurence(hand, hand.charAt(0));
			if(b==2)a+=1;
			else if(b==3)a+=3;
			else if(b==4)a+=5;
			else if(b==5)a+=6;
			if(b>max && hand.charAt(0)!='J') {
				max=b;
				bestChar=hand.charAt(0);
			}
			
			hand=hand.replaceAll(hand.charAt(0)+"", "");
		}
		if(tHand.contains("J")) {
			a = 0;
			if(bestChar=='z')bestChar='A';
			tHand=tHand.replaceAll("J", bestChar+"");
			while(!tHand.isEmpty()) {
				b = countOccurence(tHand, tHand.charAt(0));
				if(b==2)a+=1;
				else if(b==3)a+=3;
				else if(b==4)a+=5;
				else if(b==5)a+=6;
				tHand=tHand.replaceAll(tHand.charAt(0)+"", "");
			}
		}
		return a;
	}
	static int countOccurence(String a, char b) {
		int count = 0;
		for (int i = 0; i < a.length(); i++) 
		    if (a.charAt(i) == b) 
		        count++;
		return count;
	}

	

}
