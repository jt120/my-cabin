package accp.javaSE.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class TrditionalTimerTest {

	/**
	 * ��ͳ��ʱ��
	 * @param args
	 */
	
	private static int conut=0;
	public static void main(String[] args) {
		
		class myTimerTask extends TimerTask{
			@Override
			public void run() {
				
			conut= (conut+1)%2;
			//���ﶨʱ��ʲô
			System.out.println("����ը����.....");	 
			new Timer().schedule(new myTimerTask(), 2000+3000*conut);
			}
		}
		
		
		new Timer().schedule(new myTimerTask(), 2000);
		
		
		
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	

}

 