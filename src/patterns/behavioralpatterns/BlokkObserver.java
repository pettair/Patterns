package patterns.behavioralpatterns;
import javax.swing.*;

import patterns.structuralpatterns.CompositeParent;

import java.awt.*;
import java.math.*;


public class BlokkObserver extends ObserverPattern {
	
	int coll;
	double collwidth;
	int collheight;
	int distance = 30;
	
	public BlokkObserver(){
		coll = 5;
		collwidth = (width - (coll + 1) * distance)/( coll + 1 );
		collheight = height / 10;
	}
	
	public BlokkObserver(CompositeParent p){
		super(p);
		coll = p.getChilds().size();
		collheight = (int) ((height-(coll-1)*distance) /coll);//( coll + 1 );
		System.out.println("Sor: " + (coll + 1) + "; SorMagassag: " + collheight + " -> " + height + "/" + (coll + 1) * distance);
	}

	@Override
	protected void draw() {
		double max = 0;
		for(CompositeParent i : root.getChilds()){if( Math.abs(i.getState())>max){max = Math.abs(i.getState());}}
		if(max == 0){max = 1;}
		collwidth = width / max;
		collwidth = collwidth / 2;
		System.out.println("Max: " + max + "; Szelesseg: " + collwidth + " -> " + width / max + "/2");
		
		Graphics g = window.getGraphics();
		int j = 0;
		g.drawLine(width/2, 0, width/2, height);
		
		for(CompositeParent i :  root.getChilds()){
			double ert = i.getState();
			g.setColor(Color.BLUE);
			
			int y1 = j *collheight+distance * j;
			int x2 = (int)(collwidth * Math.abs(ert));
			int y2 = (j+1)*collheight+distance * j;
			int x1; if(ert < 0) {
				x1 = width / 2 - x2;
			}else{x1 = width/2;}
			g.fillRect( x1, y1, x2, collheight);
			//g.fillRect( j *collheight+distance * j, width/2, (j+1)*collheight+distance * j, width/2 + (int)collwidth * ert);
			System.out.println("kezdopontok: "+ x1 + " : " + y1 +"vegpontok: " + x2 + " : " + y2);
			g.setColor(Color.RED);
			//g.fi
			
			g.drawString( (new Integer( (int)(ert) )).toString(), x1, 10 + y1 + (y2 - y1)/2);
			j++;
			g.setColor(Color.BLACK);
			System.out.println(ert);
		}
	}
	
}
