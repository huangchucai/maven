package cn.huangchucai.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Save implements Runnable {
    private int count;
    private String content;
    public String name;
    public Thread t;
    public Save(String name, String content, int count) {
        this.count = count;
        this.content = content;
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        String pathName = "res/" + count + ".txt";
        File file = new File(pathName);
        try {
            FileUtils.writeStringToFile(file, content, "UTF-8");
        } catch (IOException e) {
            System.out.println("读取文件错误");
        }
    }
}
