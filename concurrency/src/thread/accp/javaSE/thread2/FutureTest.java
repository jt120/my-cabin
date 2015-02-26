package accp.javaSE.thread2;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	ExecutorService  singlePool	=Executors.newSingleThreadExecutor();
	 Future<String> fu=singlePool.submit(new Callable<String>() {
		@Override
		public String call() throws Exception {
			Thread.sleep(3000);
		return "hello world";
		}
	});
	 
	 
	 System.out.println("�ȴ����....");
	 try {
		System.out.println(fu.get());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 //�̳߳�
	 ExecutorService executor=Executors.newFixedThreadPool(10);
	 //completion ���ض��
	 CompletionService<Integer> completionService=new  ExecutorCompletionService<Integer>(executor);
	 
	 //�ύ10������
	 for(int i=0;i<10;i++){
		 final int j=i;
	 //�ύ�߳�
	 completionService.submit(new Callable<Integer>(){
		@Override
		public Integer call() throws Exception {
			Thread.sleep(new Random().nextInt(5000));
			
			//���һ������ͷ���
			return j;
		}
		 
	 });
	 
	 
	 }
	 
	 //���ؽ��
	 for(int i=0;i<10;i++){
		 int result = 0;
		 try {
			 //�õ�һ���߳�ִ�еĽ��
			 result=completionService.take().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 System.out.println("���صĽ��:"+result);
	 }
	 
	 
	 System.out.println("ִ���������....");
	}

}
