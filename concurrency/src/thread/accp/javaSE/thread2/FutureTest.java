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
	 
	 
	 System.out.println("等待结果....");
	 try {
		System.out.println(fu.get());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 //线程池
	 ExecutorService executor=Executors.newFixedThreadPool(10);
	 //completion 返回多个
	 CompletionService<Integer> completionService=new  ExecutorCompletionService<Integer>(executor);
	 
	 //提交10个任务
	 for(int i=0;i<10;i++){
		 final int j=i;
	 //提交线程
	 completionService.submit(new Callable<Integer>(){
		@Override
		public Integer call() throws Exception {
			Thread.sleep(new Random().nextInt(5000));
			
			//完成一个任务就反回
			return j;
		}
		 
	 });
	 
	 
	 }
	 
	 //反回结果
	 for(int i=0;i<10;i++){
		 int result = 0;
		 try {
			 //得到一个线程执行的结果
			 result=completionService.take().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 System.out.println("返回的结果:"+result);
	 }
	 
	 
	 System.out.println("执行下面的了....");
	}

}
