/**
 * 
 */
package patterns.cretionalpatterns;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import patterns.cretionalpatterns.*;

import patterns.structuralpatterns.CompositeParent;
import patterns.structuralpatterns.ConcreteFlyWeightA;
import patterns.structuralpatterns.FlyWeightPattern;
import patterns.structuralpatterns.NonTerminal;
import patterns.structuralpatterns.Terminal;

/**
 * @author pettair
 *
 */
public class ConcreteFactoryA extends AbstractFactory {
	
	private Color _color;
	private Color _color2;
	//private ConcreteFlyWeightA fly;
	
	public ConcreteFactoryA(){
		_color = SingletonPattern.getMainColor(true);
		_color2 =SingletonPattern.getSecondColor(true);
		fly = new ConcreteFlyWeightA();
	}
	
	public CompositeParent make(Point p, CompositeParent r){
		CompositeParent t;
		if(SingletonPattern.getnodType() && r != null){
			t = new Terminal(SingletonPattern.getleafValue(), r);
			//t.set(new Point(initpoint + elements*szelesseg + i * koz ,(level + 2 )*sortavolsag), Color.RED);
			t.set(p, fly);
		}
		else{
			t = new NonTerminal(r);
			//t.set(new Point(initpoint + elements*szelesseg + i * koz ,(level + 2 )*sortavolsag), Color.RED);
			t.set(p,fly);
		}
		if(r != null){r.add(t);}
		
		return t;
	}
	
	public Terminal lastmake(Point p, CompositeParent r){
		Terminal t;
		t = new Terminal(SingletonPattern.getleafValue(),
				r);
		//t.set(new Point(i*szelesseg + j * koz ,5*sortavolsag), Color.RED);
		t.set(p, fly);
		//t.set(p, _color, _color2);
		return t;
	}

	@Override
	public NonTerminal makenonterminal(Point p, CompositeParent r, int value) {
		NonTerminal ret = new NonTerminal(r);
		ret.set(p, fly);
		return ret;
	}

	@Override
	public Terminal maketerminal(Point p, CompositeParent r, int value) {
		Terminal ret = new Terminal(value,r);
		ret.set(p, fly);
		return ret;
	}

	@Override
	public  FlyWeightPattern makeFly(){
		return null;
	}

	
}
