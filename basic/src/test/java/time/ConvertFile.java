package time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author ze.liu
 * @since 2014/6/23
 */
public class ConvertFile {

    public static void main(String[] args) throws Exception {
        File f = new File("D:\\qunar\\数据\\毕业\\zhuanti_qt_uniq.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String s = "";
        StringBuilder stringBuilder = new StringBuilder();
        while((s=bufferedReader.readLine())!=null) {
            stringBuilder.append("'" + s + "'," );
        }
        System.out.println(stringBuilder.toString());

        bufferedReader.close();
    }
}
