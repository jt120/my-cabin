package accp.javaSE.thread;

public class Thread1{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
	Thread tread=new Thread(){
		@Override
		public void run() {
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(true){
				System.out.println("��ǰ�߳�1:"+Thread.currentThread().getName());
				System.out.println("��ǰ�߳�2:"+this.getName());
			}
		}
		
	};
	
	//tread.start();
	
	
	Thread trhed2=new Thread(new Runnable(){
	
		public void run() {
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(true){
				System.out.println("Runnable�߳�:"+Thread.currentThread().getName());
			}
		
		
		}
		
	});
	
	
	//trhed2.start();
	
	//3
	new Thread(new Runnable(){
		@Override
		public void run() {
			while(true){
				System.out.println("�߳�3 Runnable�ӿ���run:"+Thread.currentThread().getName());
			}
			
		}
	}){
		//����Thread������
		@Override
		public void run() {
			while(true){
				System.out.println("�߳�3  Thread����run����:"+Thread.currentThread().getName());
			}
		
		}
	}.start();
	
	
	
	}

}
