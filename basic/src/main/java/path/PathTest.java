package path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ze.liu on 2014/7/11.
 */
public class PathTest {


    public static void main(String[] args) throws Exception {
        // 使用相对路径,正常读取b.jar中的文件
        showContent(PathTest.class.getResource("b.txt"));

        // 使用相对路径,正常读取b.jar中的文件
        showContent(PathTest.class.getResource("test/c.txt"));

        // 使用绝对对路径,正常读取b.jar中的文件
        showContent(PathTest.class.getResource("/net/a.txt"));

        // 使用绝对对路径,正常读取b.jar中的文件
        showContent(PathTest.class.getClassLoader().getResource("net/aty/test/c.txt"));

        // 使用相对路径,正常读取本jar中的same.txt
        showContent(PathTest.class.getResource("../same.txt"));

        // 错误，不能再不同的lib中
        showContent(PathTest.class.getResource("../LICENSE.txt"));
    }

    public static void showContent(URL url) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                url.openStream()));

        StringBuilder contentHolder = new StringBuilder();

        String lineContent = null;

        while ((lineContent = br.readLine()) != null) {
            contentHolder.append(lineContent);
        }

        br.close();

        System.out.println("content=" + contentHolder);

    }
}
