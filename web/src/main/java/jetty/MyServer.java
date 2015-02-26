package jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by ze.liu on 2014/9/2.
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8888);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(MyServlet.class, "/my");
        servletContextHandler.setContextPath("/hello");

        server.setHandler(servletContextHandler);
        server.start();
    }
}
