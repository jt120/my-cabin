package down.html;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class UrlTest {

    public static void main(String[] args) {
        String s = "http://update.123juzi.net/dl.php?edition=hao123_juzi_canal&amp;cid=h2";
        try {
            URL url = new URL(s);

            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            urlConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
