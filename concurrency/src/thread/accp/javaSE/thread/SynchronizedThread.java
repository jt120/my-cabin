package accp.javaSE.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SynchronizedThread().init();
		
		
	}
	
	
	private void init(){
		final output out=new output();
		  new Thread(new Runnable() {
			  
			@Override
			public void run() {
				
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.outputs("刘备");
				}
				
			}
		}).start();
	
	//2
		  new Thread(new Runnable() {
				@Override
				public void run() {
					
			
					while(true){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.outputs3("诸葛孔明");
					}
					
				}
			}).start();
	
	}
	
	
	static class output{
		
	private final static  Lock lock=new ReentrantLock();
		
		public  void outputs(String name){
			//this这个
			//类同步机制
			//synchronized(output.class){
			lock.lock();
			try {
				
		
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
			
		} catch (Exception e) {
			// TODO: handle exception
		 }finally{
			lock.unlock();
			
		}
		}
		//}
		
		
		//2
		public synchronized void outputs2(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
			}
		
		
		//3 
		   
				public static void outputs3(String name){
				
					lock.lock();
					try {
					for(int i=0;i<name.length();i++){
						System.out.print(name.charAt(i));
					}
					System.out.println();
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
						lock.unlock();
					}
				     
				}
				
				 
		
		
	}

}
