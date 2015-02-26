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
	 * ���ų̹�������
	 * @param args
	 */

	public static void main(String[] args) {
	
	MyData mydata=new MyData();
    Thread tt=new Thread(new MyRunnable1(mydata));	
    tt.start();
	new Thread(new MyRunnable2(mydata)).start();//һ���̼߳�10
   
	try {
		block.take();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
    System.out.println("��ǰƱ��:"+mydata.getCount());
	
	
	}
	
}

 /**
  * ���ݹ������ݣ�����̲߳���
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
			myData.inc();//������
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
				myData.dec();//�ӷ���
				}
			
			
			
		}
	
	
}

//����������
class MyData{
 private boolean b=true;
		private int count=100;
		public int getCount() {
			return count;
		}

		
		public  synchronized void inc(){
			if(b){
			try {
				this.wait(); //�����̵߳ȴ�
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			count--;
			this.notify(); //���� 
			b=false;
			
		System.out.println("��ǰ�߳�:  "+Thread.currentThread().getName()+" ��ǰƱ��:"+count);
		}
		
		public  synchronized void dec(){

			if(!b){
			try {
				this.wait(); //�����̵߳ȴ�
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}	
				count++;
				this.notify(); //���� 
				b=true;
			System.out.println("��ǰ�߳�:  "+Thread.currentThread().getName()+" ��ǰƱ��:"+count);
		}
	}

