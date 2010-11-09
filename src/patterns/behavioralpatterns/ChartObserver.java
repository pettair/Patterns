/**
 * 
 */
package patterns.behavioralpatterns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.math.*;

import patterns.structuralpatterns.CompositeParent;

/**
 * @author pettair
 *
 */
public class ChartObserver extends ObserverPattern {

	int x;
	int y;
	int all;
	
	public ChartObserver(){
		
	}
	
	public ChartObserver(CompositeParent p){
		super(p);
	}

	@Override
	protected void draw() {
		all = 0;
		for(CompositeParent i : root.getChilds()){all += Math.abs(i.getState());}
		if(all == 0){all = 1;}
		Graphics g = window.getGraphics();
		double curValue = 0.0D;
	    int startAngle = 0;
	    for (int i=0; i<root.getChilds().size(); i++) {

	        startAngle = (int)(curValue * 360 / all);
	        int arcAngle = Math.abs((int)(root.getChilds().elementAt(i).getState()) * 360 / all);

	        if (i == root.getChilds().size()-1) {
	            arcAngle = 360 - startAngle;
	        }

	        Random r = new Random();
	        Color  c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	        g.setColor(c);
	        g.fillArc(x, y, width, height, startAngle, arcAngle);

	        curValue += Math.abs(root.getChilds().elementAt(i).getState());
	    }
	}

}
