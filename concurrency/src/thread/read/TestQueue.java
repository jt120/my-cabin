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
							
						semaphore.acquire(); //��Ȼһ֧��
						
						String input = synchronous.take();
						String output = TestDo.doSome(input); //10���߳�ͬʱ���е�����Ҫ�ӵƣ�һ��һ������
						System.out.println(Thread.currentThread().getName()+ ":" + output);

					   semaphore.release();//���һ֧��
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					
				}).start();
			}
			
			
			for(int i=0;i<10;i++){  //���в��ܸĶ�
				String input = i+"";  //���в��ܸĶ�
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
	
	//���ܸĶ���TestDo��
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