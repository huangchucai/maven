package cn.huangchucai.spider.view;

import cn.huangchucai.spider.model.Viewable;

import java.util.ArrayList;

public class ViewList {
    private ArrayList<Viewable> viewList;

    public ViewList(ArrayList<Viewable> viewList) {
        this.viewList = viewList;
    }

    public void displayNews() {
        for(Viewable view: viewList) {
            // 输出相关信息
            view.display();
        }
    }
}
