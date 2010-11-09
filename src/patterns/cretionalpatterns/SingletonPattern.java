package patterns.cretionalpatterns;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class SingletonPattern {
	
		private static int numOfChilds;
		private static int maxValue;
		private static int childRate;
		private static Random rand;

		private static SingletonPattern singletonObject;
		
		private SingletonPattern() {
			numOfChilds = 5;
			maxValue = 100;
			childRate = 10;
			rand = new Random();
		}
		
		public static synchronized SingletonPattern getSingletonObject() {
			if (singletonObject == null) {
				singletonObject = new SingletonPattern();
			}
			return singletonObject;
		}
		
		public static synchronized void setParameters(int num, int max, int rate){
			numOfChilds = num;
			maxValue = max;
			childRate = rate;
		}
		
		public Object clone() throws CloneNotSupportedException {
			throw new CloneNotSupportedException();
		}
		
		public static synchronized boolean getnodType(){
			return (rand.nextInt(100) < childRate);
		}
		
		public static synchronized boolean getFactoryType(){
			return rand.nextBoolean();
		}
		
		public static synchronized int getleafValue(){
			return rand.nextInt(2*maxValue) - maxValue + 1;
		}
		
		public static synchronized int numOfChildren(){
			return rand.nextInt(numOfChilds) + 1;
		}
		
		public static Color getMainColor(boolean b){if(b){return Color.RED;}else return Color.BLUE;}
		
		public static Color getSecondColor(boolean b){if(b){return Color.BLUE;}else return Color.RED;}
		
}
