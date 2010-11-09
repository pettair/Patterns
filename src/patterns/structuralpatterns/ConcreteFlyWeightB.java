package patterns.structuralpatterns;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConcreteFlyWeightB extends FlyWeightPattern {
	public ConcreteFlyWeightB(){
		super();
		node = null;
		leaf = null;
		type = "B";
    	try {
    	    node = ImageIO.read(new File("blue.png"));
    	    leaf = ImageIO.read(new File("red_leaf.png"));
    	    
    	    for(int i = 0 ; i < 10; i++){
    	    	nums.add(ImageIO.read(new File((new Integer(i)).toString()+"_red.png")));
    	    }
    	    nums.add(ImageIO.read(new File("10_red.png")));
    	} catch (IOException e) {
    	}
	}
}
