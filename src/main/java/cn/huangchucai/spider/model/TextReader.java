package cn.huangchucai.spider.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextReader extends NewsReader {
    public TextReader(File file) {
        super(file);
    }

    @Override
    public News newReader() {
        News news = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String title = bufferedReader.readLine();
            // 读取空行
            bufferedReader.readLine();

            String content = bufferedReader.readLine();
            bufferedReader.readLine();

            String date = bufferedReader.readLine();
            String related = bufferedReader.readLine();

            if (title != null && content != null) {
                // News news = new News(title, content);
                news = new NewsWithRelated("", title, content);
                // 添加相关的新闻
                ((NewsWithRelated) news).addRelated(date, related);
            }
        } catch (IOException e) {
            System.out.println("新闻文本错误");
        }
        return news;
    }
}
