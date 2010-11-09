/**
 * 
 */
package patterns.structuralpatterns;
import java.awt.*;
import javax.swing.*;

/**
 * @author pettair
 *
 */
public interface Compositeinterface {
		
	public Color higlight = Color.GREEN;
	public int getvalue();
	public boolean add(CompositeParent p);
	public boolean move(CompositeParent p);
	public void draw(Graphics g);
	//public JButton draw(JFrame g);
}
