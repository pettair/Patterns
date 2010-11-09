/**
 * 
 */
package patterns.behavioralpatterns;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import patterns.structuralpatterns.CompositeParent;

/**
 * @author pettair
 *
 */
public abstract class ObserverPattern {
	
	protected CompositeParent root;
	protected JFrame window;
	//protected JFrame controllwin;
	protected JTextField tf;
	protected int width;
	protected int height; 
	
	public ObserverPattern(){
		root = null;
		//window = new JFrame();
		width = 400;
		height = 400;
		
		window = new JFrame("New Window");
		//controllwin =  new JFrame("Controll");
		//controllwin.setLayout(new FlowLayout());
		JButton b = new JButton("Értéket ad");
		tf = new JTextField();
		
		b.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
					//controlwindow.removeAll();
					if(!ertad()){tf.setText("Nem megfelelo parameter!!");}
					
				}
			} );
		
		/*controllwin.add(tf);
		controllwin.add(b);
		controllwin.setSize(100,50);
		controllwin.setVisible(true);
		controllwin.pack();*/
		
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(width, height);
		window.setVisible(true);
	}
	
	public ObserverPattern(CompositeParent r){
		root = r;
		window = new JFrame();
		width = 400;
		height = 400;
		
		if(root != null){
			window = new JFrame(root.toString());
		}else{window = new JFrame();}
		
		//controllwin =  new JFrame("Controll");
		//controllwin.setLayout(new FlowLayout());
		JButton b = new JButton("Értéket ad");
		tf = new JTextField();
		
		b.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
					//controlwindow.removeAll();
					if(!ertad()){tf.setText("Nem megfelelo parameter!!");}
					
				}
			} );
		
		/*controllwin.add(tf);
		controllwin.add(b);
		controllwin.setVisible(true);*/
		
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(width, height);
		window.setVisible(true);
	}
	
	public boolean ertad(){
		int t = -10000;
		try{
			t = Integer.parseInt(tf.getText());
		}
		catch(Exception e){return false;}
		
		if(t<=100 && t>=-100){root.setState(t); root.getvalue(); //notifyme();
		return true;}
		return false;
	}
	
	public void setRoot(CompositeParent p){
		root = p;
		upDate();
	}
	
	public void remove(){window.setVisible(false); root = null;}//controllwin.setVisible(false); }
	
	public void notifyme(){draw();}
	
	public void upDate(){draw();}
	
	protected abstract void draw();

}
