package accp.javaSE.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest3 {
	
	/**
	 *@param args
	 */
	public static void main(String[] args) {
		final SubMethod subm=new SubMethod();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					subm.subThread2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					subm.subThread3(i);
				}
			}
		}).start();
		
		for(int i=0;i<20;i++){
			subm.mianThread(i);
	    }
		
		
		
		
}



	static class SubMethod{
		
		final Lock lcok=new ReentrantLock();
		//当前状况
		final Condition condition1=lcok.newCondition();
		final Condition condition2=lcok.newCondition();
		final Condition condition3=lcok.newCondition();
		
		//加上同步锁 synchornized，ThreadCommuniction在使用这个方法时进行锁    我在输出的时候，其它线程在这里等待
		
		private int bTagSub=1;//用于线程之间的切换
		
		public  void mianThread(int i){
		
		lcok.lock();
		try {
			while(bTagSub!=1){
					try {
						//如果不等等1的话，我就休息在这里,等待换醒
						condition1.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int j=0;j<10;j++){
					System.out.println("main Thread 外循环i: "+i+"  内循环j:  "+j);
				}
				//做完之后，换醒子线程
				//System.out.println("开始换醒子线程222222222");
				bTagSub=2;
				//this.notify();
				condition2.signal();
			} catch (Exception e) {
			
			}finally{
			lcok.unlock();
			}
				
			}
		
		public  void subThread2(int i){
		lcok.lock();
		try {
			while(2!=bTagSub){
				try {
					condition2.await();
					//this.wait();//做完100次之后，如果为flase的话就不做了，为等待状态，
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<10;j++){
				System.out.println("子线程2里外循环i: "+i+"  内循环j:  "+j);
			}
			//System.out.println("开始换醒线程33333");
			//this.notify();//100次之后 做完之后就换醒主线程
			
			bTagSub=3;
			condition3.signal();
			
			} catch (Exception e) {
				
			}finally{
				lcok.unlock();
			}
		}
		
		
		
		public  void subThread3(int i){
			
			lcok.lock();
			try {
				while(3!=bTagSub){
					try {
						condition3.await();
						//this.wait();//做完100次之后，如果为flase的话就不做了，为等待状态，
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int j=0;j<10;j++){
					System.out.println("子线程3里外循环i: "+i+"  内循环j:  "+j);
				}
				//System.out.println("开始换醒主线程111111");
				//this.notify();//100次之后 做完之后就换醒主线程
				
				bTagSub=1;
				condition1.signal();
				
				} catch (Exception e) {
					
				}finally{
					lcok.unlock();
				}
			}
			
		
	
	}
}

