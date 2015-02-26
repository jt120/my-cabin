package accp.javaSE.thread2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	/**
	 * 线程池
	 * @param args
	 */
	public static void main(String[] args) {
		
	ExecutorService  service=Executors.newFixedThreadPool(3);
	//单线程池死了之后会找一个线程继续
	//ExecutorService  service=Executors.newFixedThreadPool();
	
	//接到10个任务
	for(int i=0;i<10;i++){
		final int task=i;
	//3个线程池开始工作
	service.execute(new Runnable(){
		public void run() {
				for(int j=0;j<10;j++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程:"+Thread.currentThread().getName()+" "+"第 "+task+" 个任务输出j第: "+j+" 次");
				}
			}
			
		
	});
	}
	
	System.out.println("全部任务 10 个完成.....");
	//service.shutdown();//关闭线程池的线程
	
	//线程池 定时器
	//schedule一次
	/*Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable(){
		@Override
		public void run() {
			System.out.println("bombing......爆炸.....");
			
		}
	}, 6, 5,TimeUnit.SECONDS);
	*/
	}
}
