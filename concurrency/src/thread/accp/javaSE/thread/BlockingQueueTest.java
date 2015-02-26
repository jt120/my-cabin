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
		//��������
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
				bloking.put(1);//�������һ�����ݣ����Եģ��Ϳ�����
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int j=0;j<10;j++){
				System.out.println("���߳�����ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			System.out.println("��ʼ�������߳�");
			
			try {
				
				bloking2.take(); //���������������֮�󣬾͸�2ȡ��һ�����ݣ�2�Ϳ�����������
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		public  void mianThread(int i) throws Exception{
		
			bloking2.put(1);//��������Ѿ��Ų����ˣ����������ˣ���������������
			
			for(int j=0;j<10;j++){
				System.out.println("main Thread ��ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			//����֮�󣬻������߳�
			System.out.println("��ʼ�������߳�");
			
			bloking.take();//��1��ȡ��һ�����ݣ� �����Ͳ��������������ˣ�
			
		}
	}
}

