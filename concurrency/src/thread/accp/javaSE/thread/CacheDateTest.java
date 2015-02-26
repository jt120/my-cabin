package accp.javaSE.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDateTest {

	private Map<String,Object> map=new HashMap<String,Object>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					Object obj=new CacheDateTest().getMap("liang");
					System.out.println("threadName:"+Thread.currentThread().getName()+
							"\tvalue: "+obj.toString());
				}
				
			}
		}).start();
		
		
new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					Object obj=new CacheDateTest().getMap("rui");
					System.out.println("threadName:"+Thread.currentThread().getName()+
							"\tvalue: "+obj.toString());
				}
				
			}
		}).start();

	}
	
//��д��
private ReadWriteLock rwl=new ReentrantReadWriteLock();
	
private Object obj=null;
volatile boolean cacheValid;

 public Object getMap(String key){
	 rwl.readLock().lock();//����
	System.out.println("������key:"+key);
	 try {
		 if(!cacheValid){
			 System.out.println("������key�ǿյ�value:"+key);
			 // ����Ϊ�գ���ȥ������������д��
			 rwl.readLock().unlock();
			 rwl.writeLock().lock();
	try {	 
	if(!cacheValid){//�����ж���߳���д�Ļ�
		 //ȥ���ݿ����.......
		 System.out.println("��ʼ��:  "+key+"  д��ֵ");
		 map.put(key, "aaaa");
		 cacheValid=true;
		 }
	} catch (Exception e) {
				e.printStackTrace();
	}finally{
	//д��֮��ȥ��д�������϶���
	rwl.writeLock().unlock();
	}
    rwl.readLock().lock();
	}
	obj=map.get(key);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
	rwl.readLock().unlock();//�����
	}
	
	 
	 return obj;
 }

}
