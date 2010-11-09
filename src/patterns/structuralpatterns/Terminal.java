/**
 * 
 */
package patterns.structuralpatterns;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.imageio.ImageIO;



/**
 * @author pettair
 *
 */
public class Terminal extends CompositeParent implements Cloneable {
	
	private int value; 
	Color _color;
	Color _color2;
	
	Polygon myTri;
	int _x;
	int _y;
	boolean inited;
	boolean high;
	
	
	public Terminal(int ert, CompositeParent p){
		myTri = new Polygon();
		parent = p;
		value = ert;
		high = false;
	}
	
	public boolean move(CompositeParent p) {
		CompositeParent temp = this.parent;
		if(p.add(this)){
			temp.childs.remove(this);
		}
		return false;
	}
	
	public boolean setparent(CompositeParent p)
	{
		parent = p;
		return true;
	}
	
	public boolean add(CompositeParent p) {
		return false;
	}
	
	public void setvalue(int ert){
		value = ert;
	}
	
	public int getvalue(){
		return value;
	}
	
	public void set(Point center, Color color, Color color2){
		inited = false;
		myTri.addPoint(center.x, center.y - _diameter / 2); _x = center.x; _y = center.y;
		myTri.addPoint(center.x + _diameter, center.y + _diameter); _x += center.x + _diameter; _y += center.y + _diameter;
		myTri.addPoint(center.x - _diameter, center.y + _diameter); _x += center.x - _diameter; _y += center.y + _diameter;
		_color    = color;
		_color2 = color2;
		_x = _x / 3; _y = _y / 3;
		inited = true;
	}
	
	public void set(Point center, FlyWeightPattern f){
		inited = false;
		/*myTri.addPoint(center.x, center.y - _diameter / 2); _x = center.x; _y = center.y;
		myTri.addPoint(center.x + _diameter, center.y + _diameter); _x += center.x + _diameter; _y += center.y + _diameter;
		myTri.addPoint(center.x - _diameter, center.y + _diameter); _x += center.x - _diameter; _y += center.y + _diameter;
		_color    = color;
		_color2 = color2;*/
		fly = f;
		_x        = center.x - _diameter/2;
        _y        = center.y - _diameter/2;
		inited = true;
	}
	
	public Point getcenter(){
		return new Point(_x, _y);
	}
	
	public void setCenter(Point center){
		/*myTri.reset();
		myTri.addPoint(center.x, center.y - _diameter / 2); _x = center.x; _y = center.y;
		myTri.addPoint(center.x + _diameter, center.y + _diameter); _x += center.x + _diameter; _y += center.y + _diameter;
		myTri.addPoint(center.x - _diameter, center.y + _diameter); _x += center.x - _diameter; _y += center.y + _diameter;
		_x = _x / 3; _y = _y / 3;*/
		_x = center.x; _y = center.y;
		System.out.println("Seting center to : " + center.toString());
		inited = true;
	}
	
	public void draw(Graphics g) {
	    if(inited){
	    	  // int w = img.getWidth(null);
	    	   //int h = img.getHeight(null);
	    	   //g.drawImage(img, 0, 0, null);
	    	   
	    	if(high){
	    		g.drawImage(fly.getLeafhigh(), _x - _diameter / 2, _y - _diameter  / 2, null);
	    		//g.setColor(higlight);
	    	}else{
	    		g.drawImage(fly.getLeaf(), _x - _diameter  / 2, _y - _diameter  / 2, null);
	    		//g.setColor(_color);
	    	}
	    	//g.fillPolygon(myTri);
	    	//g.setColor(_color2);
	    	//g.drawPolygon(myTri);
	    	//g.drawImage(img, _x, _y, null);
	    	g.setColor(Color.BLACK);
	    	fly.drawNumber(new String((new Integer(getvalue())).toString()), g, new Point(_x,_y));
	    	//g.drawString((new Integer(getvalue())).toString(), _x, _y);
	    }
	}
	
	public CompositeParent inside(int x, int y){
		if(_x < x && _y < y && x < (_x + _diameter) && y < (_y +  _diameter)){high = true; return this;}
		return null;
	}
	
	public void removeHighlight(){
		high = false;
	}

	@Override
	public int getState() {
		return value;
	}

	@Override
	public void setState(int t) {
		value = t;
		notiFy();
	}
	
	public String getType(){
		return fly.getType();
	}
	
	public void writer(PrintWriter pw, PrintWriter pw2) {
		pw.println("T " + _x + " " + _y + " " + value);
		pw.println(";");
		pw2.println("T " + _x + " " + _y + " " + value);
		pw2.println("*");
	}
	
	public Object clone() throws CloneNotSupportedException {
        Terminal copy = (Terminal) super.clone();
        return copy;
    }

}
