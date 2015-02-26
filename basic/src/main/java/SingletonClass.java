/**
 * Created by ze.liu on 2014/8/19.
 */
public class SingletonClass {

    private static SingletonClass _instance = new SingletonClass();

    private SingletonClass() {}

    public static SingletonClass getInstance() {
        if (_instance == null) {
            synchronized (SingletonClass.class) {
                if (_instance == null) {
                    _instance = new SingletonClass();
                }
            }
        }
        return _instance;
    }
}
