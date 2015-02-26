package accp.javaSE.thread;

public class ThreadCommuniction {
	
	/**
	 *@param args
	 *liangrui
	 */
	public static void main(String[] args) {
		final SubMethod subm=new SubMethod();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					subm.subThread(i);
				}
			}
		}).start();
		
		for(int i=0;i<10;i++){
			subm.mianThread(i);
			
	}
}
}


	class SubMethod{
		//����ͬ������ThreadCommuniction��ʹ���������ʱ������    ���������ʱ�������߳�������ȴ�
		
		private boolean bTagSub=true;//�����߳�֮����л�
		
		public synchronized void subThread(int i){
			while(!bTagSub){
				try {
					this.wait();//����10��֮�����Ϊflase�Ļ��Ͳ����ˣ�Ϊ�ȴ�״̬��
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<20;j++){
				System.out.println("���߳�����ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			System.out.println("��ʼ�������߳�");
			this.notify();//10��֮�� ����֮��ͻ������߳�
			bTagSub=false;
		}
		
		public synchronized void mianThread(int i){
		while(bTagSub){
				try {
					this.wait();//����Ϊtrue�Ϳ�ʼ�ȴ�
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<50;j++){
				System.out.println("main Thread ��ѭ��i: "+i+"  ��ѭ��j:  "+j);
			}
			//����֮�󣬻������߳�
			System.out.println("��ʼ�������߳�");
			bTagSub=true;
			this.notify();
			
		}
	}


