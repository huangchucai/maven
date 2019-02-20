package cn.huangchucai.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SaveUserInput {
    public static void main(String[] args) throws IOException {
        InputStream ipt = System.in;
        InputStreamReader iptReader = new InputStreamReader(ipt);
        BufferedReader buffer = new BufferedReader(iptReader);
        String text;
        int count = 1;
        // 读取不是回车的字符串
        while((text = buffer.readLine()) != null) {
            // 子线程存放输入
            new Save("Child Thread", text, count++);
        }
    }
}

