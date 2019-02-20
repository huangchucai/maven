package cn.huangchucai.spider.model;

import java.io.File;

public abstract class NewsReader {
    protected File file;

    public NewsReader(File file) {
        this.file = file;
    }

    public abstract News newReader();
}
