package patterns.cretionalpatterns;

import java.awt.Point;

import patterns.structuralpatterns.CompositeParent;

public abstract class PrototypePattern implements Cloneable {
	
    public Object clone() throws CloneNotSupportedException {
        PrototypePattern copy = (PrototypePattern) super.clone();
        return copy;
    }
 
    public abstract void set(Point point, CompositeParent root, int v);
}
  
