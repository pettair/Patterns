package patterns.structuralpatterns;
import java.awt.*;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.*;

import patterns.behavioralpatterns.Observable;
import patterns.behavioralpatterns.ObserverPattern;

public abstract class CompositeParent implements Compositeinterface, Observable, Cloneable{

	protected Vector<CompositeParent> childs = new Vector<CompositeParent>();
	protected CompositeParent parent;
	protected int _diameter = 35;
	protected static Vector<ObserverPattern> observers = new Vector<ObserverPattern>();
	protected Color highlight = Color.GREEN;
	protected FlyWeightPattern fly;
	
	public void draw(Graphics g){}
	//public JButton draw(JFrame g){return null;}

	public abstract int getvalue();
	
	public abstract Point getcenter();
	
	public abstract boolean add(CompositeParent p);
	
	public abstract boolean setparent(CompositeParent p);
	
	public abstract CompositeParent inside(int x, int y);
	
	public abstract void setCenter(Point p);
	
	public abstract void set(Point p, FlyWeightPattern f);
	
	public abstract void set(Point center, Color color, Color color2);

	public abstract boolean move(CompositeParent p);
	
	public abstract void removeHighlight();
	
	public abstract void writer(PrintWriter pw, PrintWriter pw2);
	
	public abstract String getType();
	
	public Vector<CompositeParent> getChilds(){
		return childs;
	}
	
	public FlyWeightPattern getFyl(){
		return fly;
	}
	
	@Override
	public void attach(ObserverPattern o){
		observers.add(o);
	}

	@Override
	public void detach(ObserverPattern o){
		observers.remove(o);
		o.remove();
	}
	
	@Override
	public void notiFy(){
		for(ObserverPattern i : observers)
		{
			i.notifyme();
		}
	}

	@Override
	public abstract int getState();

	@Override
	public abstract void setState(int t);

}
