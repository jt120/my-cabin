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
	
//读写锁
private ReadWriteLock rwl=new ReentrantReadWriteLock();
	
private Object obj=null;
volatile boolean cacheValid;

 public Object getMap(String key){
	 rwl.readLock().lock();//读锁
	System.out.println("进来的key:"+key);
	 try {
		 if(!cacheValid){
			 System.out.println("进来的key是空的value:"+key);
			 // 如是为空，则去掉读锁，上上写锁
			 rwl.readLock().unlock();
			 rwl.writeLock().lock();
	try {	 
	if(!cacheValid){//如是有多个线程来写的话
		 //去数据库查找.......
		 System.out.println("开始给:  "+key+"  写入值");
		 map.put(key, "aaaa");
		 cacheValid=true;
		 }
	} catch (Exception e) {
				e.printStackTrace();
	}finally{
	//写完之后去掉写锁，加上读锁
	rwl.writeLock().unlock();
	}
    rwl.readLock().lock();
	}
	obj=map.get(key);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
	rwl.readLock().unlock();//解读锁
	}
	
	 
	 return obj;
 }

}
