/**
 * 
 */
package patterns;
import javax.swing.*;

import patterns.behavioralpatterns.*;
import patterns.cretionalpatterns.SingletonPattern;
import patterns.structuralpatterns.CompositeParent;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.Vector;

/**
 * @author pettair
 *
 */
public class Window {
	
	private JFrame drawwindow;
	private JFrame controlwindow;
	protected int width;
	protected int height;
	private CompositeParent root;
	private int obs;
	private boolean open;
	private MemoryState mems;
	//private String filename;
	private MyListener myl;
	private final JTextField tf = new JTextField("Ha ide nem fer ki akkor sehova");
	private JTextField memtf;
	
	private Vector<ObserverPattern> observers;
	
	public class MyListener implements MouseListener, MouseMotionListener, WindowListener, KeyListener
	{	
		private CompositeParent root;
		private Graphics grap;
		private CompositeParent grab;
		private CompositeParent sel;
		private int position;
		private Point tmppoint;
		private Window win;
		
		
		private Vector<MementoPattern> mem;
		
		public MyListener(CompositeParent r, Graphics g, Window w)
		{
			mem = new Vector<MementoPattern>();
			tmppoint = null;
			position = -1;
			root = r; grap = g; grab = null; sel = null;
			win = w;
		}
		
		public void addMem(MementoPattern first, MementoPattern act){
			mem.add(first); mem.add(act); position = mem.size()-1;
			System.out.println(mem.size());
		}
		
		public void changeRoot(CompositeParent p){
			root = p;
		}
		
		public void undo(){
			if(position >= 1){
				position--;
				if(mem.elementAt(position) instanceof MoveMem){
					if(!((MoveMem)mem.elementAt(position)).getComp().getcenter().equals(((MoveMem)mem.elementAt(position)).getState())){
						((MoveMem)mem.elementAt(position)).getComp().setCenter(((MoveMem)mem.elementAt(position)).getState());
					}else{
						position--;
						((MoveMem)mem.elementAt(position)).getComp().setCenter(((MoveMem)mem.elementAt(position)).getState());
					}
				}
				else{
					System.out.println("inside undo");
					System.out.println(root.toString());
					if(mem.elementAt(position) instanceof RegenerateMem){
						if(!root.equals(((RegenerateMem)mem.elementAt(position)).getRoot())){
							root = ((RegenerateMem)mem.elementAt(position)).getRoot();
						}else{
							position--;
							root = ((RegenerateMem)mem.elementAt(position)).getRoot();
						}
						win.setRoot(root);
						System.out.println(root.toString());
					}
				}
				
				grap.clearRect(0, 0, Main.width, Main.height + Main.sortavolsag);
				root.draw(grap);
			}
		}
		
		public void redo(){
			if(position < mem.size() - 1){
				position++;
				if(mem.elementAt(position) instanceof MoveMem){
					if(!((MoveMem)mem.elementAt(position)).getComp().getcenter().equals(((MoveMem)mem.elementAt(position)).getState())){
						((MoveMem)mem.elementAt(position)).getComp().setCenter(((MoveMem)mem.elementAt(position)).getState());
					}else{
						position++;
						((MoveMem)mem.elementAt(position)).getComp().setCenter(((MoveMem)mem.elementAt(position)).getState());
					}
				}else{
					if(mem.elementAt(position) instanceof RegenerateMem){
						if(!root.equals(((RegenerateMem)mem.elementAt(position)).getRoot())){
							root = ((RegenerateMem)mem.elementAt(position)).getRoot();
						}else{
							System.out.println("inside redo");
							position++;
							root = ((RegenerateMem)mem.elementAt(position)).getRoot();
						}
						win.setRoot(root);
					}
				}
				grap.clearRect(0, 0, Main.width, Main.height + Main.sortavolsag);
				root.draw(grap);
				//prevstep = 1;
			}
		}
		
		public CompositeParent getSelected(){
			return sel;
		}
		
		public void saveTree(){
			FileOutputStream fs;
			FileOutputStream fs2;
			try {
				fs = new FileOutputStream("file" + root.getFyl().getType() + ".txt");
				fs2 = new FileOutputStream("file" + root.getFyl().getType() + "_alt.txt");
				PrintWriter pw = new PrintWriter(fs, true);
				PrintWriter pw2 = new PrintWriter(fs2, true);
				pw.println(root.getType());
				pw2.println(root.getType());
				root.writer(pw,pw2);
				pw.close();
				pw2.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public void mousePressed(MouseEvent e) {
			root.removeHighlight();
			grab = root.inside(e.getX(), e.getY());
			sel = grab;
			if(grab != null) { root.draw(grap); tmppoint = grab.getcenter();}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {
			if(grab != null ){
				//root.removeHighlight();
				Point p = new Point(e.getX(), e.getY());
				mem.add(new MoveMem(tmppoint,grab));  mem.add(new MoveMem(p,grab)); position = mem.size()-1;
				grab.setCenter(p); grap.clearRect(0, 0, Main.width, Main.height + Main.sortavolsag);
				tmppoint = null;
				root.draw(grap);
			}
		}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {
			if(grab != null ){
				grab.setCenter(new Point(e.getX(), e.getY())); grap.clearRect(0, 0, Main.width, Main.height + Main.sortavolsag);
				root.draw(grap);
			}
		}
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowClosed(WindowEvent arg0) {
			System.out.println("CLOSED");
		}
		@Override
		public void windowClosing(WindowEvent arg0) {
			System.out.println("CLOSING");
			open = false;
			saveTree();
			
		}
		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			System.out.println(e.getKeyChar() + " Modifier: " + e.getModifiers() + " KeyChar: " + e.getKeyChar() +" KeyCode: " + e.getKeyCode());
			
			if(e.getKeyChar() == 'z' && e.getModifiers() == 4){
				undo();
			}
			if(e.getKeyChar() == 'y' && e.getModifiers() == 4){
				redo();
			}
			if(e.getKeyChar() == 'r' && e.getModifiers() == 4){
				win.regen();
			}
			if(e.getKeyChar() == 's' && e.getModifiers() == 4){
				System.out.println("SAVE");
				saveTree();
			}
			if(e.getKeyCode() == 27){
				System.out.println("ESC");
				root.removeHighlight();
				sel = null;
				root.draw(grap);
			}
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void clearCache(){
			mem = new Vector<MementoPattern>();
			position = -1;
		}
	}
	
	public Window(int x, int y, CompositeParent r){
		root = r;
		observers = new Vector<ObserverPattern>();
		final MyListener m;
		final JButton b = new JButton("kiertekel");
		final JButton reg = new JButton("Ujra general");
		final JButton undobutton = new JButton("Undo");
		final JButton redobutton = new JButton("Redo");
		final JButton tfbtn = new JButton("OK");
		JPanel jPanel1 = new JPanel();
		JLabel label = new JLabel("Memory felhasznalas: ");
		memtf = new JTextField();
		//memtf.setEditable(false);
		memtf.setPreferredSize(new Dimension(100,30));
		 
		
		 System.out.println(UIManager.getLookAndFeel().getName());
		 int pic = Math.abs(SingletonPattern.getleafValue());
		 String st = "1.png";
		 pic = pic % 4;
		 switch(pic){
		 case 0: st = "2.jpg"; break;
		 case 1: st = "3.jpg"; break;
		 case 2: st = "4,gif"; break;
		 case 3: break;
		 }
		 
		 ImageIcon imageBack = new ImageIcon(st);
		 
		 JLabel background = new JLabel(imageBack);
        
		width = x;
		height = y;
		drawwindow = new JFrame("Draw Window");
		controlwindow = new JFrame("Controll Window");
		
		drawwindow.setLayout(new FlowLayout());
		controlwindow.setLayout(new FlowLayout());
		
		//drawwindow.s
		
		//obs = 0;

		controlwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		drawwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		b.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
					//controlwindow.removeAll();
					root.getvalue();
					root.draw(getgraphics());
					controlwindow.update(controlwindow.getGraphics());
				}
			} );
		
		controlwindow.add(b);
		controlwindow.add(reg);
		controlwindow.add(tf);
		controlwindow.add(tfbtn);
		controlwindow.setVisible(true);
		controlwindow.pack();
		tf.setText("Ertek");
		drawwindow.setSize(width, height);
		drawwindow.setVisible(true);
		
		m = new MyListener(root,drawwindow.getGraphics(), this);
		myl = m;
		
		reg.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
					regen();
				}
			} );
		
		tfbtn.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
					setSelectedValue();
				}
			} );
		
		drawwindow.addMouseListener(m); drawwindow.addMouseMotionListener(m);
		drawwindow.addWindowListener(m);
		drawwindow.addKeyListener(m);
		open = true;
		mems = new MemoryState(this);
		redobutton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				m.redo();
			}
		} );
		undobutton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				m.undo();
			}
		} );
		
		/*background.setBounds(0, 0, imageBack.getIconWidth(), imageBack.getIconHeight());
        drawwindow.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        jPanel1.setOpaque(false);
        drawwindow.setContentPane(jPanel1);*/
		
		controlwindow.add(b);
		controlwindow.add(undobutton);
		controlwindow.add(redobutton);
		controlwindow.add(label);
		controlwindow.add(memtf);
		controlwindow.setVisible(true);
		controlwindow.pack();
		
	}
	
	public void regen(){
		CompositeParent temp = root;
		root = Main.regenerate();
		myl.addMem(new RegenerateMem(temp),new RegenerateMem(root));
		getgraphics().clearRect(0, 0, Main.width, Main.height + Main.sortavolsag);
		root.draw(getgraphics());
		myl.changeRoot(root);
	}
	
	public void setSelectedValue(){
		CompositeParent tem = myl.getSelected();
		if(tem != null){
			int t = -10000;
			try{
				t = Integer.parseInt(tf.getText());
			}
			catch(Exception e){tf.setText("-100 <= x <= 100");}
			
			if(t<=100 && t>=-100){tem.setState(t); root.getvalue(); }
			else{tf.setText("-100 <= x <= 100");}
		}else{
			tf.setText("Nincs kivalasztott elem");
		}
		controlwindow.pack();
	}
	
	public void setRoot(CompositeParent p){
		root = p;
		for(ObserverPattern i : observers){i.setRoot(p);}
	}
	
	public void addObserver(ObserverPattern o)
	{
		final ObserverPattern ot = o; obs++;
		JButton bt = new JButton("OB" + obs);
		bt.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
					//controlwindow.removeAll();
					ot.upDate();
				}
			} );
		observers.add(o);
		controlwindow.add(bt);
		controlwindow.pack();
	}
	
	public synchronized boolean getState(){
		return open;
	}
	
	public synchronized void setMemTF(double d){
		memtf.setText(new String((new Double(d)).toString()));
	}
	
	public synchronized void setMemTF(String d){
		memtf.setText(d);
	}
	
	public void ref(){
		root.draw(getgraphics());
	}
	
	public synchronized void clearCache(){
		myl.clearCache();
	}
	
	public synchronized Graphics getgraphics(){
		return drawwindow.getGraphics();
	}

}
