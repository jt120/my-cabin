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
				System.out.println(Thread.currentThread().getName()+"  ����������:"+datas);
				//��ȡ��ǰ�ֳ�ʵ�����󲢱���  ��һ��Ϊ�ն����ȥʵ��new��
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
			//���ﵱǰ�߳������������ˣ����ͻ����ȡ�����󣬲�����
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
	 * ��ȡ��ǰ�̵߳����ݶ���ʵ����
	 * */
	 class GetThreadLocalInstans{
		
	private GetThreadLocalInstans(){}	
	public static /*synchronized ���ﲻ��Ҫ������Ϊÿһ���̰߳󶨣������ݲ�ͬ*/ MyThreadData getLocalInstans()
	{
		MyThreadData instans=map.get();//��ȡ��ǰ�̵߳����ݶ���ʵ��
		if(instans==null){
			//�����ǰ����ʵ��Ϊ�յĻ�����newһ��,������
			instans=new MyThreadData();
			map.set(instans);
		}
		return instans;
	}
	
	//��ǰ�̰߳󶨵����ݼ���
	private static ThreadLocal<MyThreadData> map=new ThreadLocal<MyThreadData>();

}
	 /*�̰߳󶨵Ķ���*/
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
