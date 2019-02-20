package cn.huangchucai.spider;

import cn.huangchucai.spider.activity.SpiderThread;
import cn.huangchucai.spider.model.NewsWithRelated;
import cn.huangchucai.spider.model.SearchSate;
import cn.huangchucai.spider.model.UrlReader;
import cn.huangchucai.spider.view.ViewList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Multithread {
    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();

        // 广度优先搜索
        SearchSate searchSate = new SearchSate(100);

        // URL获取
        String startUrl = "https://readhub.cn/topic/5bMmlAm75lD";
        NewsWithRelated startsNews = UrlReader.read(startUrl);
        searchSate.visit(startsNews);

        while (searchSate.hasTarget()) {
            NewsWithRelated current = searchSate.poll();
            searchSate.addResult(current);
            List<SpiderThread> spiders = new ArrayList<>();


            // 发送相关页面的请求数据
            for (Map.Entry<String, String> entry : current.getRelated().entrySet()) {
                String url = entry.getValue();
                spiders.add(new SpiderThread(searchSate, url));
            }

            // 等待所有的线程执行完成
            for(SpiderThread spider: spiders) {
                spider.join(); // 等待这个线程运行完成
            }
        }

        long endTime = System.currentTimeMillis();

        new ViewList(searchSate.getResults()).displayNews();
        System.out.println("总数量：" + searchSate.getCount());
        System.out.println("程序运行时间：" + (endTime - startTime)+ "ms");

    }
}
