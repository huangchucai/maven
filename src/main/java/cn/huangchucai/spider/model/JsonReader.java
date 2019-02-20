package cn.huangchucai.spider.model;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class JsonReader extends NewsReader {
    public JsonReader(File file) {
        super(file);
    }

    @Override
    public News newReader() {
        News news = null;
        try {
            String jsonString = FileUtils.readFileToString(file, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonString);
            String title = jsonObject.getString("title");
            String content = jsonObject.getString("content");
            news = new News(title, content);
        } catch (IOException e) {
            System.out.println("新闻读取错误");
        } catch (JSONException e) {
            System.out.println("JSON读取错误");
        }
        return news;
    }
}
