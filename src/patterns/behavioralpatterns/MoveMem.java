package patterns.behavioralpatterns;

import java.awt.Point;

import patterns.structuralpatterns.CompositeParent;

public class MoveMem extends MementoPattern {
	
	private Point p;
	private CompositeParent cp;
	
	public MoveMem(int x, int y, CompositeParent _cp){
		p = new Point(x,y);
		cp = _cp;
	}
	
	public MoveMem(Point _p, CompositeParent _cp){
		p = _p;
		cp = _cp;
	}
	
	public Point getState(){
		return p;
	}
	
	public CompositeParent getComp(){
		return cp;
	}

}
