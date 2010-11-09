package patterns.behavioralpatterns;

import patterns.structuralpatterns.*;

public class RegenerateMem extends MementoPattern {
	
	private CompositeParent root;
	
	public RegenerateMem(CompositeParent p){
		root = p;
	}
	
	public CompositeParent getRoot(){
		return root;
	}

}
