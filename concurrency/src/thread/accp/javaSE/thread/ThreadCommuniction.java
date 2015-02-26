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
		//加上同步锁，ThreadCommuniction在使用这个方法时进行锁    我在输出的时候，其它线程在这里等待
		
		private boolean bTagSub=true;//用于线程之间的切换
		
		public synchronized void subThread(int i){
			while(!bTagSub){
				try {
					this.wait();//做完10次之后，如果为flase的话就不做了，为等待状态，
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<20;j++){
				System.out.println("子线程里外循环i: "+i+"  内循环j:  "+j);
			}
			System.out.println("开始换醒主线程");
			this.notify();//10次之后 做完之后就换醒主线程
			bTagSub=false;
		}
		
		public synchronized void mianThread(int i){
		while(bTagSub){
				try {
					this.wait();//如是为true就开始等待
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int j=0;j<50;j++){
				System.out.println("main Thread 外循环i: "+i+"  内循环j:  "+j);
			}
			//做完之后，换醒子线程
			System.out.println("开始换醒子线程");
			bTagSub=true;
			this.notify();
			
		}
	}


