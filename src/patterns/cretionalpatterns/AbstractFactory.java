package patterns.cretionalpatterns;

import java.awt.*;

import patterns.structuralpatterns.CompositeParent;
import patterns.structuralpatterns.FlyWeightPattern;
import patterns.structuralpatterns.NonTerminal;
import patterns.structuralpatterns.Terminal;

public abstract class AbstractFactory {
		
	protected FlyWeightPattern fly;
	
	public abstract CompositeParent make(Point p, CompositeParent r);
	
	public abstract Terminal lastmake(Point p, CompositeParent r);
	
	public abstract Terminal maketerminal(Point p, CompositeParent r, int value);
	
	public abstract NonTerminal makenonterminal(Point p, CompositeParent r, int value);
	
	public abstract FlyWeightPattern makeFly();
	
}
