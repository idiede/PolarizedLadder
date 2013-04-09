package polarizedladder;

import java.awt.Point;

public class Position {
	
	int i;
	int j;
	String mark; 
	Point point;
	
	
 public Position(int i, int j, String mark){
	 
	  this.point.x = i;
	  this.point.y = j;
	  this.mark = mark;
	  this.point = new Point(i, j);
 }

 public Position(Point p, String mark){
	  this.i =p.x;
	  this.j =p.y;
	  this.mark = mark;
	 
}
public int getI() {
	return i;
}


public void setI(int i) {
	this.i = i;
}


public int getJ() {
	return j;
}


public void setJ(int j) {
	this.j = j;
}


public String getMark() {
	return mark;
}


public void setMark(String mark) {
	this.mark = mark;
}

public Point getPoint() {
	return point;
}

public void setPoint(Point point) {
	this.point = point;
}
 
 
}
