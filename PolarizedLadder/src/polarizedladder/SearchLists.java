package polarizedladder;

import java.awt.Point;
import java.util.Hashtable;

public class SearchLists extends GameHeuristics {
	
	public SearchLists(){
		
		super();
	}

	
	public Hashtable<Point, String> cloneOpenList( Hashtable<Point, String> openList, Point removePoint){
		Hashtable<Point, String> copy =	openList;
		if(copy.containsValue(removePoint))
		  copy.remove(removePoint);
		return copy;
	}
	
}
