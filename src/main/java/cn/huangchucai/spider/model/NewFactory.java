package cn.huangchucai.spider.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NewFactory {
    // 文件目录
    private File newsDir;

    public NewFactory(String dir) throws Exception {
        newsDir = new File(dir);

        if (!newsDir.exists()) {
            throw new Exception("文件不存在");
        }
        if (!newsDir.isDirectory()) {
            throw new Exception("输入路径不是一个合法目录！");
        }
    }

    // 请求，返回News列表
    public ArrayList<News> fetch() throws IOException {
        ArrayList<News> newsList = new ArrayList<>();
        File[] files = newsDir.listFiles();
        for (File file : files) {
            String suffix = file.getName();
            NewsReader reader = null;
            // 根据后缀名区分不同的reader
            if(suffix.endsWith(".txt")) {
                reader = new TextReader(file);
            } else if(suffix.endsWith(".json")) {
                reader = new JsonReader(file);
            }
            News news = reader.newReader();
            newsList.add(news);
        }
        return newsList;
    }
}
