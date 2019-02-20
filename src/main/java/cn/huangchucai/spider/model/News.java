package cn.huangchucai.spider.model;

public class News implements Viewable {
    private String title;
    private String content;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }
    public void display() {
        System.out.println("News display");
        System.out.println("| title |" + this.getTitle());
        System.out.println("| content | " + this.getContent());
    }

}
