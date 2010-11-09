package patterns.cretionalpatterns;

import java.awt.Point;

import patterns.structuralpatterns.CompositeParent;
import patterns.structuralpatterns.Terminal;

public class ConcreteProtoBT extends PrototypePattern {

	private Terminal nt;
	
	public ConcreteProtoBT(){
		nt = (new ConcreteFactoryA()).maketerminal(new Point(), null, 0);
	}
	
	@Override
	public void set(Point point, CompositeParent root, int v) {
		nt.setparent(root); nt.setCenter(point); nt.setvalue(v);
	}
	
	public Object clone() throws CloneNotSupportedException {
        return nt.clone();
    }

}
