package accp.javaSE.thread2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	/**
	 * �̳߳�
	 * @param args
	 */
	public static void main(String[] args) {
		
	ExecutorService  service=Executors.newFixedThreadPool(3);
	//���̳߳�����֮�����һ���̼߳���
	//ExecutorService  service=Executors.newFixedThreadPool();
	
	//�ӵ�10������
	for(int i=0;i<10;i++){
		final int task=i;
	//3���̳߳ؿ�ʼ����
	service.execute(new Runnable(){
		public void run() {
				for(int j=0;j<10;j++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("�߳�:"+Thread.currentThread().getName()+" "+"�� "+task+" ���������j��: "+j+" ��");
				}
			}
			
		
	});
	}
	
	System.out.println("ȫ������ 10 �����.....");
	//service.shutdown();//�ر��̳߳ص��߳�
	
	//�̳߳� ��ʱ��
	//scheduleһ��
	/*Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable(){
		@Override
		public void run() {
			System.out.println("bombing......��ը.....");
			
		}
	}, 6, 5,TimeUnit.SECONDS);
	*/
	}
}
