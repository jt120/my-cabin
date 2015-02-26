package accp.javaSE.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest3 {
	
	/**
	 *@param args
	 */
	public static void main(String[] args) {
		final SubMethod subm=new SubMethod();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					subm.subThread2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					subm.subThread3(i);
				}
			}
		}).start();
		
		for(int i=0;i<20;i++){
			subm.mianThread(i);
	    }
		
		
		
		
}



	static class SubMethod{
		
		final Lock lcok=new ReentrantLock();
		//��ǰ״��
		final Condition condition1=lcok.newCondition();
		final Condition condition2=lcok.newCondition();
		final Condition condition3=lcok.newCondition();
		
		//����ͬ���� synchornized��ThreadCommuniction��ʹ���������ʱ������    ���������ʱ�������߳�������ȴ�
		
		private int bTagSub=1;//�����߳�֮����л�
		
		public  void mianThread(int i){
		
		lcok.lock();
		try {
			while(bTagSub!=1){
					try {
						//������ȵ�1�Ļ����Ҿ���Ϣ������,�ȴ�����
						condition1.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int j=0;j<10;j++){
					System.out.println("main Thread ��ѭ��i: "+i+"  ��ѭ��j:  "+j);
				}
				//����֮�󣬻������߳�
				//System.out.println("��ʼ�������߳�222222222");
				bTagSub=2;
				//this.notify();
				condition2.signal();
			} catch (Exception e) {
			
			}finally{
			lcok.unlock();
			}
				
			}
		
		public  void subThread2(int i){
		lcok.lock();
		try {
			while(2!=bTagSub){
				try {
					condition2.await();
					//this.wait();//����100��֮�����Ϊflase�Ļ��Ͳ����ˣ�Ϊ�ȴ�״̬��
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<10;j++){
				System.out.println("���߳�2����ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			//System.out.println("��ʼ�����߳�33333");
			//this.notify();//100��֮�� ����֮��ͻ������߳�
			
			bTagSub=3;
			condition3.signal();
			
			} catch (Exception e) {
				
			}finally{
				lcok.unlock();
			}
		}
		
		
		
		public  void subThread3(int i){
			
			lcok.lock();
			try {
				while(3!=bTagSub){
					try {
						condition3.await();
						//this.wait();//����100��֮�����Ϊflase�Ļ��Ͳ����ˣ�Ϊ�ȴ�״̬��
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int j=0;j<10;j++){
					System.out.println("���߳�3����ѭ��i: "+i+"  ��ѭ��j:  "+j);
				}
				//System.out.println("��ʼ�������߳�111111");
				//this.notify();//100��֮�� ����֮��ͻ������߳�
				
				bTagSub=1;
				condition1.signal();
				
				} catch (Exception e) {
					
				}finally{
					lcok.unlock();
				}
			}
			
		
	
	}
}

