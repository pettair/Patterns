package patterns.structuralpatterns;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConcreteFlyWeightA extends FlyWeightPattern {
	
	public ConcreteFlyWeightA(){
		super();
		node = null;
		leaf = null;
		type="A";
    	try {
    	    node = ImageIO.read(new File("red.png"));
    	    leaf = ImageIO.read(new File("green_leaf.png"));
    	    
    	    for(int i = 0 ; i < 10; i++){
    	    	nums.add(ImageIO.read(new File((new Integer(i)).toString()+"_blue.png")));
    	    }
    	    nums.add(ImageIO.read(new File("10_blue.png")));
    	    
    	} catch (IOException e) {
    	}
	}

}
