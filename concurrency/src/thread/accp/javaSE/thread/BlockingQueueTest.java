package accp.javaSE.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	
	/**
	 *@param args
	 *liangrui
	 */
	public static void main(String[] args) {
		final SubMethod subm=new SubMethod();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					subm.subThread(i);
				}
			}
		}).start();
		
		for(int i=0;i<50;i++){
			try {
				subm.mianThread(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}



	static class SubMethod{
		//队列组赛
		BlockingQueue<Integer> bloking=new ArrayBlockingQueue<Integer>(1);		
		BlockingQueue<Integer> bloking2=new ArrayBlockingQueue<Integer>(1);

		{
			try {
				bloking2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	
		
		public  void subThread(int i){
			try {
				bloking.put(1);//队列里放一个数据，可以的，就可以走
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int j=0;j<10;j++){
				System.out.println("子线程里外循环i: "+i+"  内循环j:  "+j);
			}
			System.out.println("开始换醒主线程");
			
			try {
				
				bloking2.take(); //如是这个运行完了之后，就给2取出一个数据，2就可以往下走了
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		public  void mianThread(int i) throws Exception{
		
			bloking2.put(1);//这个里面已经放不下了，不能再走了，就阻赛在这里了
			
			for(int j=0;j<10;j++){
				System.out.println("main Thread 外循环i: "+i+"  内循环j:  "+j);
			}
			//做完之后，换醒子线程
			System.out.println("开始换醒子线程");
			
			bloking.take();//从1里取出一个数据， 这样就不会组赛在那里了，
			
		}
	}
}

