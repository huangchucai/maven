package cn.huangchucai.spider;

import cn.huangchucai.spider.model.NewsWithRelated;
import cn.huangchucai.spider.model.UrlReader;
import cn.huangchucai.spider.model.Viewable;
import cn.huangchucai.spider.view.ViewList;

import java.util.*;

public class Main {
    private static final int MAX_NUM = 100;

    public static void main(String[] args) throws Exception {
        // // 地址
        // String resources_path = Main.class.getClassLoader().getResource("read_hub").getPath();
        //
        // NewFactory newsReader = new NewFactory(resources_path);
        // // 获取新闻
        // ArrayList<News> news = newsReader.fetch();
        // ArrayList<Viewable> viewables = new ArrayList<>();
        // viewables.addAll(news);
        // // 展示
        // ViewList views = new ViewList(viewables);
        // views.displayNews();

        long startTime = System.currentTimeMillis();

        // URL获取
        String startUrl = "https://readhub.cn/topic/5bMmlAm75lD";
        // String startUrl = "https://readhub.cn/topic/J7JAsosg7I";
        int count = 0;
        // 根据related的URL继续获取
        // 广度优先搜索
        Queue<NewsWithRelated> newsQueue = new LinkedList<>();

        // 标记已经访问过的URL
        Set<String> visited = new HashSet<>();

        // 存放所有的展示内容
        ArrayList<Viewable> arrayList = new ArrayList<>();

        NewsWithRelated startNews = UrlReader.read(startUrl);
        newsQueue.add(startNews);
        visited.add(startUrl);

        while (!newsQueue.isEmpty() && count <= MAX_NUM) {
            NewsWithRelated currentNews = newsQueue.poll();
            arrayList.add(currentNews);
            count += 1;
            for (Map.Entry<String, String> related : currentNews.getRelated().entrySet()) {
                String url = related.getValue();
                if (!visited.contains(url)) {
                    NewsWithRelated nextNews = UrlReader.read(url);
                    newsQueue.add(nextNews);
                    visited.add(url);
                }
            }
        }
        ViewList viewList = new ViewList(arrayList);
        viewList.displayNews();
        long endTime = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}
