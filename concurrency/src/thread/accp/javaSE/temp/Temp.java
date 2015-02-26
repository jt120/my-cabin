package accp.javaSE.temp;

public class Temp {
	public static void main(String[] args) {
		 final  threadTest therd=new threadTest();
		 
		new Thread(new Runnable(){
					@Override
					public void run() {
						 for(int i=0;i<50;i++){
							therd.sub(i);
							}
					}
					}).start();
		
		
		 
		 for(int i=0;i<50;i++){
			therd.main(i);
		 }
	
		
	}
	
	

}


class threadTest{
	
	boolean b=true;
	public synchronized void sub(int j){
		
		if(!b){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<20;i++){
			System.out.println(j+"==line:"+"==sub:"+i);
			}
		b=false;
		this.notify();
	}
	
	public synchronized void  main(int j){
		if(b){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<10;i++){
		System.out.println(j+"==line:"+"==main:"+i);
		}
		
		b=true;
		this.notify();
}
}
