package patterns.behavioralpatterns;

import java.awt.Point;
import java.io.*;
import java.util.Scanner;

import patterns.cretionalpatterns.*;
import patterns.structuralpatterns.*;

public abstract class TemplateMethodPattern {
	
	private String filename;
	private BufferedReader br;
	private DataInputStream in;
	protected PrototypePattern protoNT;
	protected PrototypePattern protoT;
	protected CompositeParent root;
	protected Scanner sc;
	protected String type;
	protected AbstractFactory fact;
	
	public TemplateMethodPattern(String fn){
		FileInputStream fstream;
		root = null;
		filename = fn;
		if(filename.contains("A")){}
		try{
			fstream = new FileInputStream(filename);
			sc = new Scanner(fstream);
			if(sc.hasNext()){type = sc.next(); setProto(type);}
			if(sc.hasNext()){setRoot(sc);}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setProto(String s) {
		if(s.equals("A")){
			protoNT = new ConcreteProtoANT();
			protoT = new ConcreteProtoAT();
			fact = new ConcreteFactoryA();
		}else{
			fact = new ConcreteFactoryB();
			if(s.equals("B")){
				protoNT = new ConcreteProtoBNT();
				protoT = new ConcreteProtoBT(); 
			}
		}

	}
	
	public void setRoot(Scanner s){
		if(s.next().equals("N")){
			int x = Integer.parseInt(s.next()); int y = Integer.parseInt(s.next());
				root = 
					fact.makenonterminal(
							new Point(x,y), null, 0);

		}else{
			int x = Integer.parseInt(s.next()); int y = Integer.parseInt(s.next()); int v = Integer.parseInt(s.next());
			root = fact.maketerminal(new Point(x,y), null, v);
		}
			
		System.out.println(root.toString());
	}

	//public abstract void handle(BufferedReader br, CompositeParent p);
	public abstract void handle(Scanner sc, CompositeParent p);
	
	public CompositeParent BulidTree(){
		System.out.println("BuildStarted");
		try{
			handle(sc,root);
		}catch (Exception e){
		    System.err.println("Error: " + e.getMessage());
		}
		
		return root;
	}

}
