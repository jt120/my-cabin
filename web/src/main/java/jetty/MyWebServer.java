package jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by ze.liu on 2014/9/2.
 */
public class MyWebServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8888);

        //创建了WebAppContext必须设置resouseBase
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/hello");
        webAppContext.setResourceBase("d:/test/hello");
        webAppContext.addServlet(MyServlet.class,"/my");

        server.setHandler(webAppContext);
        server.start();
    }
}
