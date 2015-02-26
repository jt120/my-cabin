package accp.javaSE.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShearData {

	/**
	 * @param args
	 */
	
	//private static  int data=0;
	//private static Map<String,Integer> map=new HashMap<String,Integer>();
	private static ThreadLocal<Integer> local=new ThreadLocal<Integer>();
	public static void main(String[] args) {
	for(int i=0;i<2;i++){
		new Thread(new Runnable(){
			@Override
			public void run() {
				int datas=new Random().nextInt();
				System.out.println(Thread.currentThread().getName()+"  放入数据是:"+datas);
				//map.put(Thread.currentThread().getName(),datas);
				local.set(datas);
			new A().get();
			new B().get();
			}
		}).start();
		}
	}
	
	static class A{
		static void get(){
			System.out.println("A:"+local.get());
		}
	}
	
	static class B{
		static void get(){//map.get(Thread.currentThread().getName())
			System.out.println("B:"+local.get());
		}
	}
	

}
