package read;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class TestQueue {
	
		public static void main(String[] args) {
			
			
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			
			final SynchronousQueue<String> synchronous=new SynchronousQueue<String>();
			final Semaphore semaphore=new Semaphore(1);
			
			for(int i=0;i<10;i++){
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							
						semaphore.acquire(); //点然一支灯
						
						String input = synchronous.take();
						String output = TestDo.doSome(input); //10个线程同时进行的所以要加灯，一个一个的来
						System.out.println(Thread.currentThread().getName()+ ":" + output);

					   semaphore.release();//灭掉一支灯
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					
				}).start();
			}
			
			
			for(int i=0;i<10;i++){  //这行不能改动
				String input = i+"";  //这行不能改动
			//	String output = TestDo.doSome(input);
				
				try {
					synchronous.put(input);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(Thread.currentThread().getName()+ ":" + output);
			}
		}
	}
	
	//不能改动此TestDo类
	class TestDo {
		public static String doSome(String input){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String output = input + ":"+ (System.currentTimeMillis() / 1000);
			return output;
		}
	}