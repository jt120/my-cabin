package zookeeper;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ze.liu
 * @since 2014/6/9
 */
public class AddressTest {

    public static void main(String[] args) throws UnknownHostException {
        String localhost = InetAddress.getLocalHost().getHostAddress();
        System.out.println(localhost);
    }
}
