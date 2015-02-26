package accp.javaSE.thread2;

import java.util.Random;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service=Executors.newCachedThreadPool();
		//一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点
		final CyclicBarrier cb=new CyclicBarrier(3);
		
	for(int i=0;i<3;i++){
		Runnable runnable=new Runnable(){
			@Override
			public void run() {
			try {
	
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("threadName:"+Thread.currentThread().getName()+
			"   即将到达集合地点,当前已有:"+(cb.getNumberWaiting()+1)+"个到达！"+
			(cb.getNumberWaiting()+1==3?"都到齐了继续走呀!":""));
			//等待所有线程完成之后才开始往下走
			cb.await();
			
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("threadName:"+Thread.currentThread().getName()+
					"   即将到达集合地点,当前已有:"+(cb.getNumberWaiting()+1)+"个到达！"+
					(cb.getNumberWaiting()+1==3?"都到齐了继续走呀!":""));
			//等待所有线程完成之后才开始往下走
			cb.await();
			
			//Thread.sleep((long)Math.random()*10000);
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("threadName:"+Thread.currentThread().getName()+
					"   即将到达集合地点,当前已有:"+(cb.getNumberWaiting()+1)+"个到达！"+
					(cb.getNumberWaiting()+1==3?"都到齐了继续走呀!":""));
			//等待所有线程完成之后才开始往下走
			cb.await();
			
			
			
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				
				}
			}
		};
		
		service.execute(runnable);
		}
	service.shutdown();
	}

}
