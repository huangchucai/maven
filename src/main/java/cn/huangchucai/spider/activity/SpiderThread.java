package cn.huangchucai.spider.activity;

import cn.huangchucai.spider.model.NewsWithRelated;
import cn.huangchucai.spider.model.SearchSate;
import cn.huangchucai.spider.model.UrlReader;

import java.io.IOException;

public class SpiderThread extends Thread {
    private String url;
    private SearchSate searchSate;

    public SpiderThread(SearchSate searchSate, String url) {
        this.searchSate = searchSate;
        this.url = url;
        start();
    }

    @Override
    public void run() {
        try {
            // 发送请求
            NewsWithRelated next = UrlReader.read(url);
            searchSate.visit(next);
        } catch (IOException e) {
            System.out.println("爬取错误: " + url);
        }
    }
}
