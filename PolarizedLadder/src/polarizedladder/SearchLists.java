package polarizedladder;

import java.awt.Point;
import java.util.Hashtable;
import java.util.Iterator;

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
	
	public void removePoint(Point point, SearchLists openList){
		
		Iterator<Point> it = openList.getIterator();
		while(it.hasNext()){
			if(it.next().equals(point)){
				it.remove();
				System.out.println(point);
			}
		}
	}
}
