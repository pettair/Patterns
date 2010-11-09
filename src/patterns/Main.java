/**
 * 
 */
package patterns;
import java.util.Random;
import java.awt.*;
import java.util.Vector;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import patterns.behavioralpatterns.*;
import patterns.cretionalpatterns.*;
import patterns.structuralpatterns.*;

/**
 * @author pettair
 *
 */
public class Main {
	
	private static Window win;
	
	private static CompositeParent root;
	private static JFrame starter;
	
	private static String file1 = "fileA";
	private static String file2 = "fileB";
	
	public static int width = 1500;
	public static int height = 400;
	public static int sortavolsag = height / 6;
	
	public static int koz = 40;
	
	public static AbstractFactory factory; 
	
	public static String fn;
	
	private static void refresh()
	{
		root.draw(win.getgraphics());
		win.ref();
	}
	
	public static CompositeParent regenerate(){
		if(SingletonPattern.getFactoryType()){factory = new ConcreteFactoryA(); fn = file1;}else{factory = new ConcreteFactoryB();fn = file2;}
		root = factory.make(new Point(600,50),null);
		//int sortavolsag = height / 6;
		
		Vector<Vector<CompositeParent>> tree = new Vector<Vector<CompositeParent>>();
		
		for(int i=0;i<4;i++)
		{
			tree.add(new Vector<CompositeParent>());
		}
		tree.elementAt(0).add(root);
		
		CompositeParent t;
		
		for(int level=0; level<3;level++){
			
			int szelesseg = width / (level + 1 + ((level+1)*5 )* koz);
			int initpoint = (width - szelesseg*(level+1)) / 2 ;
			
			for(int elements=0; elements < tree.elementAt(level).size();elements++){
				int childnum=SingletonPattern.numOfChildren();
				for(int i=0; i<childnum;i++){
					t = factory.make(new Point((elements + i) * 40 * (3 - level)*2 + 35,(level + 2 )*sortavolsag),tree.elementAt(level).elementAt(elements));
					tree.elementAt(level + 1).add(t);
				}
			}
		}
		
		int szelesseg = width / (125);
		koz = szelesseg / 5;
		if(tree.elementAt(3) != null){
			for(int i = 0; i < tree.elementAt(3).size(); i++){
				int chldsnmb=SingletonPattern.numOfChildren();
				for(int j = 0; j < chldsnmb; j++){
					t= factory.lastmake(new Point((j+i) * 40 ,5*sortavolsag), 
							tree.elementAt(3)
							.elementAt(i));
					tree.elementAt(3).elementAt(i).add(t);
				}
			}
		}
		
		return root;
	}
	
	public static void build(){
		
		String fn;
		String modif = "";
		TemplateMethodPattern builder;
		if(SingletonPattern.getFactoryType()){fn = file1;}else{fn = file2;}
		if(SingletonPattern.getFactoryType()){
			System.out.println("ALTER BUILDER");
			modif = "_alt";
			builder = new ConcreteTemplateB(fn + modif +".txt");
		}else{
			builder = new ConcreteTemplateA(fn + ".txt");
		}
		
		root = builder.BulidTree();
	}
	
	public static void init(){
		System.out.println("init finish");
		BlokkObserver ob = new BlokkObserver(root);
		root.attach(ob);
		ChartObserver ob2 = new ChartObserver(root);
		root.attach(ob2);
		win = new Window(width, height + sortavolsag, root);
		win.addObserver(ob);
		win.addObserver(ob2);
		refresh();
		starter.setVisible(false);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SingletonPattern.getSingletonObject();
		
		try 
		{ 
			int mode = Math.abs(SingletonPattern.getleafValue());
			UIManager.LookAndFeelInfo[] LF = UIManager.getInstalledLookAndFeels();
			mode = mode % (LF.length + 2);
			if(mode >= LF.length){
				mode = mode - LF.length;
				String st = "";
				switch(mode){
				case 0: st = "com.seaglasslookandfeel.SeaGlassLookAndFeel";break;
				case 1: st = "napkin.NapkinLookAndFeel"; break;
				}
				UIManager.setLookAndFeel(st); 
			}
			else{
				UIManager.setLookAndFeel(LF[mode].getClassName());
			}
			
			System.out.println(UIManager.getLookAndFeel().toString() + UIManager.getLookAndFeel());
		} 
		catch (Exception ex) 
		{ 
			System.out.println("Failed loading L&F: "); 
			System.out.println(ex); 
		} 
		
		starter = new JFrame("Mod valasztas");
		starter.setLayout(new FlowLayout());
		JButton gen = new JButton("Generalas");
		JButton bet = new JButton("Filebol betoltes");
		
		gen.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				regenerate();
				init();
			}
		} );
		
		bet.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				build();
				init();
			}
		} );
		
		starter.add(gen);
		starter.add(bet);
		
		starter.pack();
		starter.setVisible(true);

		
	}

}
