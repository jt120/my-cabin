package syn;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

	//不能改动此Test类	
	public class Test extends Thread{
		
		private TestDo testDo;
		private String key;
		private String value;
		
		public Test(String key,String key2,String value){
			this.testDo = TestDo.getInstance();
			/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
			以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
			this.key = key+key2; 
			//this.key=key;
			this.value = value;
		}


		public static void main(String[] args) throws InterruptedException{
			Test a = new Test("1","","1");
			Test b = new Test("1","","2");
			Test c = new Test("3","","3");
			Test d = new Test("4","","4");
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			a.start();
			b.start();
			c.start();
			d.start();
		}
		
		public void run(){
			testDo.doSome(key, value);
		}
	}

	class TestDo {
		private TestDo() {}
		private static TestDo _instance = new TestDo();	
		public static TestDo getInstance() {
			return _instance;
		}

		//ArrayList copyOnList=new ArrayList();
		//这个集合在多线程中每一个方法都加上了同步机制
		CopyOnWriteArrayList copyOnList=new CopyOnWriteArrayList();
		public void doSome(Object key, String value) {
			//如有有相同的key,则用之前的key,而不是新建一个
			if(!copyOnList.contains(key)){
				copyOnList.add(key);//如果没有则添加
			}else{
				//如果有则取出再用
				Iterator itr=copyOnList.iterator();
				while(itr.hasNext()){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Object oo=itr.next();
					key=oo;
				}
			}
			
			// 以大括号内的是需要局部同步的代码，不能改动!
			synchronized(key)//对key加上同步锁,key必须是同一个相同对象
			{
				try {
					Thread.sleep(1000);
					System.out.println(key+":"+value + ":"
							+ (System.currentTimeMillis() / 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}