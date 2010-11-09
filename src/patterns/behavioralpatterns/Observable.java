package patterns.behavioralpatterns;

public interface Observable {
	
	public void attach(ObserverPattern o);
	public void detach(ObserverPattern o);
	public int getState();
	public void setState(int t);
	public void notiFy();

}
