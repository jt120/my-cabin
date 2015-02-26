package accp.javaSE.thread2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadShareData {

	 static BlockingQueue<Integer> block=new ArrayBlockingQueue<Integer>(10);
	
	 
	/**
	 * 多张程共享数据
	 * @param args
	 */

	public static void main(String[] args) {
	
	MyData mydata=new MyData();
    Thread tt=new Thread(new MyRunnable1(mydata));	
    tt.start();
	new Thread(new MyRunnable2(mydata)).start();//一个线程加10
   
	try {
		block.take();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
    System.out.println("当前票数:"+mydata.getCount());
	
	
	}
	
}

 /**
  * 传递共享数据，多个线程操作
  * @author liangrui
  *
  */

class MyRunnable1 implements Runnable{
	private MyData myData;
	
	public MyRunnable1(MyData myData){
	this.myData=myData;
	}
	
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			myData.inc();//减方法
		}
		try {
			MultiThreadShareData.block.put(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	MultiThreadShareData.b=false;
		MultiThreadShareData.notFull.signal();*/
	
	}
	}
	
class MyRunnable2 implements Runnable{
		private MyData myData;
		public MyRunnable2(MyData myData){
			this.myData=myData;
			
		}
		
		@Override
		public void run() {
			for(int i=0;i<10;i++){
				myData.dec();//加方法
				}
			
			
			
		}
	
	
}

//共享数据类
class MyData{
 private boolean b=true;
		private int count=100;
		public int getCount() {
			return count;
		}

		
		public  synchronized void inc(){
			if(b){
			try {
				this.wait(); //其它线程等待
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			count--;
			this.notify(); //唤醒 
			b=false;
			
		System.out.println("当前线程:  "+Thread.currentThread().getName()+" 当前票数:"+count);
		}
		
		public  synchronized void dec(){

			if(!b){
			try {
				this.wait(); //其它线程等待
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}	
				count++;
				this.notify(); //唤醒 
				b=true;
			System.out.println("当前线程:  "+Thread.currentThread().getName()+" 当前票数:"+count);
		}
	}

