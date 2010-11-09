/**
 * 
 */
package patterns.structuralpatterns;
import java.awt.*;
import javax.swing.*;



import java.awt.geom.Ellipse2D;
import java.io.PrintWriter;

/**
 * @author pettair
 *
 */
public class NonTerminal extends CompositeParent implements Cloneable{
	
	private boolean inited;
	
	int   _x;
    int   _y;
    int nvalue;
    Color _color;
    Color _color2;
    boolean high;
   
	public NonTerminal(){
		nvalue = 0;
		parent = null;
		inited = false;
		high = false;
	}
	
	public NonTerminal(CompositeParent p){
		nvalue = 0;
		parent = p;
		high = false;
	}

	public boolean add(CompositeParent p) {
		if(childs.size() < 5){
			p.setparent(this); childs.add(p); return true;
		}
		return false;
	}
	
	public boolean setparent(CompositeParent p)
	{
		parent = p;
		return true;
	} 
	
	public boolean move(CompositeParent p) {
		CompositeParent temp = this.parent;
		if(p.add(this)){
			temp.childs.remove(this);
		}
		return false;
	}
	
	public int getvalue() {
		for(int i = 0; i < childs.size(); i++)
		{
			nvalue += childs.elementAt(i).getvalue();
		}
		inited = true;
		return nvalue;
	}
	
	public void init(){inited = true;}
	
	public void set(Point center, Color color, Color color2) {
        _x        = center.x - _diameter/2;
        _y        = center.y - _diameter/2;
        _color    = color;
        _color2 = color2;
    }
	
	public void set(Point center, FlyWeightPattern f) {
        _x        = center.x - _diameter/2;
        _y        = center.y - _diameter/2;
        fly = f;
    }
	
	public Point getcenter(){
		return new Point(_x, _y);
	}
	
	public void setCenter(Point p){
		_x = p.x;
		_y = p.y;
	}
	
	public CompositeParent inside(int x, int y){
		CompositeParent ret = null;
		if(_x < x && _y < y && x < (_x + _diameter) && y < (_y +  _diameter)) { high = true;  System.out.println(_x + _y);return this;}
		else{
			for(int i = 0; i < childs.size(); i++){
				ret = childs.elementAt(i).inside(x, y);
				if(ret != null){break;}
			}
		}
		return ret;
	}
	
	public void removeHighlight(){
		high = false;
		for(int i = 0; i < childs.size(); i++){childs.elementAt(i).removeHighlight();}
	}
    
	public void draw(Graphics g) {
        if(high){g.drawImage(fly.getNodehigh(), _x - _diameter  / 2, _y - _diameter  / 2, null);}
        else{
        	g.drawImage(fly.getNode(), _x - _diameter  / 2, _y - _diameter  / 2, null);
        }
        //Graphics2D g2 = (Graphics2D) g;
        //g.fillOval(_x - _diameter/2, _y - _diameter/2, _diameter, _diameter);
        //g.setColor(_color2);
		//g2.draw(new Ellipse2D.Double(_x - _diameter/2, _y - _diameter/2, _diameter, _diameter));
        //g.setColor(Color.BLACK);
        if(inited){
        	//g.drawString((new Integer(nvalue)).toString(), _x, _y);
        	fly.drawNumber(new String((new Integer(nvalue)).toString()), g, new Point(_x-_diameter,_y));
        }
        else{
        	g.drawString("?", _x, _y);
        }
        for(int i = 0; i < childs.size(); i++)
		{
				g.drawLine(_x, _y, childs.elementAt(i).getcenter().x, childs.elementAt(i).getcenter().y);
				childs.elementAt(i).draw(g);
		}
    }

	@Override
	public int getState() {
		int temp = 0;
		for(int i = 0; i < childs.size(); i++)
		{
			temp += childs.elementAt(i).getState();
		}
		return temp;
	}

	@Override
	public void setState(int t) {
		int n = t / childs.size();
		for(int i = 0; i < childs.size(); i++)
		{
			childs.elementAt(i).setState(n);
		}
		notiFy();
	}
	
	public String getType(){
		return fly.getType();
	}

	/*@Override
	public void writer(PrintWriter pw) {
		pw.println("N " + _x + " " + _y);
		for(CompositeParent i : childs){
			i.writer(pw);
		}
		pw.println(";");	
	}*/
	

    public void writer(PrintWriter pw, PrintWriter pw2)
    {
        pw.println("N " + _x + " " + _y);
        pw2.println("N " + _x + " " + _y);
        for(CompositeParent i : childs){
        	i.writer(pw,pw2);
        }
        pw.println(";"); pw2.println("*");
    }
	
	public Object clone() throws CloneNotSupportedException {
        NonTerminal copy = (NonTerminal) super.clone();
        return copy;
    }
	
}
