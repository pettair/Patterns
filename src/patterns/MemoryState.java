package patterns;

public class MemoryState extends Thread {
	
	private Window win;
	
	public MemoryState(Window _w){
		win = _w;
		start();
	}
	
	public long getAllMemory(){
		return Runtime.getRuntime().totalMemory();
	}
	
	public long getFreeMemory(){
		return Runtime.getRuntime().freeMemory();
	}
	
	public long getAvaliableMemory(){
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}
	
	public void forceFreeUpMemory(){
		System.gc();
	}
	
	public void run() {
		while(win.getState()){
			try {
				double ratio =  (double)getFreeMemory() / (double)getAllMemory();
				//System.out.println("ALL: " + getAllMemory() + "; Free: " + getFreeMemory() + "; Avaliable: " + getAvaliableMemory() + "; Ratio: " + ratio);
				
				win.setMemTF(ratio);
				if(ratio < 0.3){
					System.out.println("Ratio under 0.3 -> force Garbage Collector");
					win.setMemTF("Ratio under 0.3 -> force Garbage Collector");
					forceFreeUpMemory();
					
					sleep(500);
					ratio =  (double)getFreeMemory() / (double)getAllMemory();
					
					if(ratio < 0.3){
						System.out.println("force Garbage Collector FAILED!\nClearing previous States;");
						win.setMemTF("force Garbage Collector FAILED!\nClearing previous States;");
						win.clearCache();
						forceFreeUpMemory();
						
						sleep(500);
						ratio =  (double)getFreeMemory() / (double)getAllMemory();
						
						if(ratio < 0.3){
							System.out.println("force Garbage Collector FAILED AGAIN!");
							win.setMemTF("force Garbage Collector FAILED AGAIN!");
						}else{
							System.out.println("force Garbage Collector Succeded");
							win.setMemTF("force Garbage Collector Succeded");
						}
					}else{
						System.out.println("force Garbage Collector Succeded");
						win.setMemTF("force Garbage Collector Succeded");
					}
				}
				sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				
			}
		}
		System.out.println("MemoryState Exited!");
	}

}
