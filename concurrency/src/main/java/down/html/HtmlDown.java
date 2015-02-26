package down.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ze.liu
 * @since 2014/5/22
 */
public class HtmlDown implements Runnable {

    private URLConnection connection;

    private FileChannel fileChannel;

    private static volatile int count;

    public HtmlDown(URLConnection connection, FileChannel fileChannel) {
        this.connection = connection;
        this.fileChannel = fileChannel;
        count ++;
    }


    @Override
    public void run() {
        try {
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
            fileChannel.transferFrom(readableByteChannel, 0, Integer.MAX_VALUE);
            inputStream.close();
            fileChannel.close();
            count --;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String url = "http://www.sohu.com";
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddmmss");
        String time = simpleDateFormat.format(calendar.getTime());
        System.out.println(time);
        String suffix = ".txt";
        String fileName = time + suffix;
        File file = new File("d:/test/" + fileName);
        try {
            executorService.submit(new HtmlDown(new URL(url).openConnection(), new FileOutputStream(file).getChannel
                        ()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        while(!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("count num: " + count);
        }
    }
}
