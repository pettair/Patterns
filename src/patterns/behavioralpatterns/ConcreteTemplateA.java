package patterns.behavioralpatterns;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import patterns.cretionalpatterns.*;
import patterns.structuralpatterns.*;

public class ConcreteTemplateA extends TemplateMethodPattern {

	public ConcreteTemplateA(String fn) {
		
		super(fn);
	}

	@Override
	public void handle(Scanner sc, CompositeParent p) {
		System.out.println("HANLDE STARTING");
		int count = 0;
		boolean canrun = true;
		for(int i = 0; i < 5 && canrun; i++){
			if (sc.hasNext()) {
				String s = sc.next();
				if (!s.equals(";")) {
					count = 0;
					CompositeParent temp = null;
					if(s.equals("N")){
						int x = Integer.parseInt(sc.next()); int y = Integer.parseInt(sc.next());
						temp = fact.makenonterminal(new Point(x,y), p, 0);
					}else{
						int x = Integer.parseInt(sc.next()); int y = Integer.parseInt(sc.next()); int v = Integer.parseInt(sc.next());
						temp = fact.maketerminal(new Point(x,y), p, v);
					}
					if(temp != null){p.add(temp); handle(sc,temp);}
				}else{
					count++;
					canrun = false;
				}
			}
			//if(count >= 2){canrun = false;}
		}
		
	}

}
