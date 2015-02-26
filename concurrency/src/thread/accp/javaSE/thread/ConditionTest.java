package accp.javaSE.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	
	/**
	 *@param args
	 */
	public static void main(String[] args) {
		final SubMethod subm=new SubMethod();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<20;i++){
					subm.subThread(i);
				}
			}
		}).start();
		
		
		
		for(int i=0;i<20;i++){
			subm.mianThread(i);
	    }
		
}



	static class SubMethod{
		//ReentrantLock n. ���ǣ��ٽ���
		final Lock lcok=new ReentrantLock();
		//��ǰ״��
		final Condition notFull=lcok.newCondition();
		final Condition notEmpty=lcok.newCondition();
		
		//����ͬ���� synchornized��ThreadCommuniction��ʹ���������ʱ������    ���������ʱ�������߳�������ȴ�
		
		private boolean bTagSub=true;//�����߳�֮����л�
		public  void subThread(int i){
		
		lcok.lock();
		try {
			while(!bTagSub){
				try {
					notFull.await();
					//this.wait();//����100��֮�����Ϊflase�Ļ��Ͳ����ˣ�Ϊ�ȴ�״̬��
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<10;j++){
				System.out.println("���߳�����ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			System.out.println("��ʼ�������߳�");
			//this.notify();//100��֮�� ����֮��ͻ������߳�
			
			bTagSub=false;
			notEmpty.signal();
			
			} catch (Exception e) {
				
			}finally{
				lcok.unlock();
			}
		}
		
	public  void mianThread(int i){
	
	     lcok.lock();
	try {
		while(bTagSub){
				try {
					notEmpty.await();
				//	this.wait();//����Ϊtrue�Ϳ�ʼ�ȴ�
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<10;j++){
				System.out.println("main Thread ��ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			//����֮�󣬻������߳�
			System.out.println("��ʼ�������߳�");
			bTagSub=true;
			//this.notify();
			notFull.signal();
		} catch (Exception e) {
		
		}finally{
		lcok.unlock();
		}
			
		}
	}
}

