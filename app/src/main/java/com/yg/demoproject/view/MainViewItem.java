package com.yg.demoproject.view;

public class MainViewItem {
    public static final int TARGET_FILE = 0;
    public static final int TARGET_BINDER_TEST = 1;

    String title;
    String contents;
    int target;

    public MainViewItem(String title, String contents, int target) {
        this.target = target;
        this.title = title;
        this.contents = contents;
    }

    public MainViewItem(String title, String contents) {
        this(title, contents, -1);
    }

    public MainViewItem(String title) {
        this(title, "");
    }

    public int getTarget() {
        return target;
    }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public interface BaseItem {
        MainViewItem createItem();
    }
}
