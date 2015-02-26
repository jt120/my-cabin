package accp.javaSE.thread;

import java.util.Random;


public class ThreadLocalTest {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
	for(int i=0;i<2;i++){
		new Thread(new Runnable(){
			@Override
			public void run() {
				int datas=new Random().nextInt();
				System.out.println(Thread.currentThread().getName()+"  放入数据是:"+datas);
				//获取当前现程实例对象并保存  第一次为空对象会去实列new的
			MyThreadData instans=GetThreadLocalInstans.getLocalInstans();
			instans.setName("name"+datas);
			instans.setAge(datas);
			new A().get();
			new B().get();
			}
		}).start();
		}
	}
	
	static class A{
		static void get(){
			//这里当前线程里已有数据了，它就会真接取出对象，并返回
			MyThreadData instans=GetThreadLocalInstans.getLocalInstans();
			System.out.println("A:thread:"+Thread.currentThread().getName()+"  "
					+instans.getName()+"  age: "+instans.getAge());
		}
	}
	
	static class B{
		static void get(){//map.get(Thread.currentThread().getName())
			//System.out.println("B:"+local.get());
			MyThreadData instans=GetThreadLocalInstans.getLocalInstans();
			System.out.println("b:Thrad: "+Thread.currentThread().getName()+"  "
			+instans.getName()+"  age: "+instans.getAge());
		}
	}
	
}
	
	/*
	 * 获取当前线程的数据对象实列类
	 * */
	 class GetThreadLocalInstans{
		
	private GetThreadLocalInstans(){}	
	public static /*synchronized 这里不需要，而是为每一个线程绑定，但数据不同*/ MyThreadData getLocalInstans()
	{
		MyThreadData instans=map.get();//获取当前线程的数据对象实列
		if(instans==null){
			//如果当前对象实例为空的话，就new一个,并保存
			instans=new MyThreadData();
			map.set(instans);
		}
		return instans;
	}
	
	//当前线程绑定的数据集合
	private static ThreadLocal<MyThreadData> map=new ThreadLocal<MyThreadData>();

}
	 /*线程绑定的对象*/
	 class MyThreadData{
		private String name;
		private int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	 }
