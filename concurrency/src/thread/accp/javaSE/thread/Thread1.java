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
				System.out.println("当前线程1:"+Thread.currentThread().getName());
				System.out.println("当前线程2:"+this.getName());
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
				System.out.println("Runnable线程:"+Thread.currentThread().getName());
			}
		
		
		}
		
	});
	
	
	//trhed2.start();
	
	//3
	new Thread(new Runnable(){
		@Override
		public void run() {
			while(true){
				System.out.println("线程3 Runnable接口下run:"+Thread.currentThread().getName());
			}
			
		}
	}){
		//子类Thread被调用
		@Override
		public void run() {
			while(true){
				System.out.println("线程3  Thread子类run方法:"+Thread.currentThread().getName());
			}
		
		}
	}.start();
	
	
	
	}

}
