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
		//һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ�
		final CyclicBarrier cb=new CyclicBarrier(3);
		
	for(int i=0;i<3;i++){
		Runnable runnable=new Runnable(){
			@Override
			public void run() {
			try {
	
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("threadName:"+Thread.currentThread().getName()+
			"   �������Ｏ�ϵص�,��ǰ����:"+(cb.getNumberWaiting()+1)+"�����"+
			(cb.getNumberWaiting()+1==3?"�������˼�����ѽ!":""));
			//�ȴ������߳����֮��ſ�ʼ������
			cb.await();
			
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("threadName:"+Thread.currentThread().getName()+
					"   �������Ｏ�ϵص�,��ǰ����:"+(cb.getNumberWaiting()+1)+"�����"+
					(cb.getNumberWaiting()+1==3?"�������˼�����ѽ!":""));
			//�ȴ������߳����֮��ſ�ʼ������
			cb.await();
			
			//Thread.sleep((long)Math.random()*10000);
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("threadName:"+Thread.currentThread().getName()+
					"   �������Ｏ�ϵص�,��ǰ����:"+(cb.getNumberWaiting()+1)+"�����"+
					(cb.getNumberWaiting()+1==3?"�������˼�����ѽ!":""));
			//�ȴ������߳����֮��ſ�ʼ������
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
