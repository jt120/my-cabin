package accp.javaSE.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	
	/**
	 *@param args
	 */
	public static void main(String[] args) {
		final SubMethod subm=new SubMethod();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					subm.subThread(i);
				}
			}
		}).start();
		
		
		
		for(int i=0;i<20;i++){
			subm.mianThread(i);
	    }
		
}



	static class SubMethod{
		//ReentrantLock n. 凹角；再进入
		final Lock lcok=new ReentrantLock();
		//当前状况
		final Condition notFull=lcok.newCondition();
		final Condition notEmpty=lcok.newCondition();
		
		//加上同步锁 synchornized，ThreadCommuniction在使用这个方法时进行锁    我在输出的时候，其它线程在这里等待
		
		private boolean bTagSub=true;//用于线程之间的切换
		public  void subThread(int i){
		
		lcok.lock();
		try {
			while(!bTagSub){
				try {
					notFull.await();
					//this.wait();//做完100次之后，如果为flase的话就不做了，为等待状态，
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<10;j++){
				System.out.println("子线程里外循环i: "+i+"  内循环j:  "+j);
			}
			System.out.println("开始换醒主线程");
			//this.notify();//100次之后 做完之后就换醒主线程
			
			bTagSub=false;
			notEmpty.signal();
			
			} catch (Exception e) {
				
			}finally{
				lcok.unlock();
			}
		}
		
	public  void mianThread(int i){
	
	     lcok.lock();
	try {
		while(bTagSub){
				try {
					notEmpty.await();
				//	this.wait();//如是为true就开始等待
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<10;j++){
				System.out.println("main Thread 外循环i: "+i+"  内循环j:  "+j);
			}
			//做完之后，换醒子线程
			System.out.println("开始换醒子线程");
			bTagSub=true;
			//this.notify();
			notFull.signal();
		} catch (Exception e) {
		
		}finally{
		lcok.unlock();
		}
			
		}
	}
}

