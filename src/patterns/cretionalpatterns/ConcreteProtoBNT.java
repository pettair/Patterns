package patterns.cretionalpatterns;

import java.awt.Point;

import patterns.structuralpatterns.CompositeParent;
import patterns.structuralpatterns.NonTerminal;

public class ConcreteProtoBNT extends PrototypePattern {

	private NonTerminal nt;
	
	public ConcreteProtoBNT(){
		nt = (new ConcreteFactoryB()).makenonterminal(new Point(), null, 0);
	}
	
	@Override
	public void set(Point point, CompositeParent root, int v) {
		nt.setparent(root); nt.setCenter(point);
	}
	
	public Object clone() throws CloneNotSupportedException {
        return nt.clone();
    }

}
