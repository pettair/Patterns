package patterns.structuralpatterns;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class FlyWeightPattern {
	
	BufferedImage nodehigh;
	BufferedImage leafhigh;
	BufferedImage node;
	BufferedImage leaf;
	Vector<BufferedImage> nums;
	String type;
	int  charwidth = 7;
	
	protected FlyWeightPattern(){
		nodehigh = null;
		leafhigh = null;
		nums = new  Vector<BufferedImage>();
    	try {
    	    nodehigh = ImageIO.read(new File("node_high.png"));
    	    leafhigh = ImageIO.read(new File("leaf_high.png"));
    	} catch (IOException e) {
    	}
	}

	public synchronized BufferedImage getNodehigh() {
		return nodehigh;
	}

	public synchronized BufferedImage getLeafhigh() {
		return leafhigh;
	}

	public synchronized BufferedImage getNode() {
		return node;
	}

	public synchronized BufferedImage getLeaf() {
		return leaf;
	}
	
	public synchronized String getType(){
		return type;
	}
	
	public synchronized void drawNumber(String s, Graphics g, Point p) {
		int i = 0;
		if(s.charAt(i) == '-'){g.drawImage(nums.elementAt(nums.size()-1),p.x  + (i-1) * charwidth, p.y, null);i++; }
		for(; i < s.length(); i++){
			g.drawImage(nums.elementAt( Integer.parseInt(Character.toString(s.charAt(i)))), p.x  + (i-1) * charwidth, p.y, null);
		}
	}
	
	

}
