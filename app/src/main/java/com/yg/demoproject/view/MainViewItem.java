package com.yg.demoproject.view;

import android.content.Intent;
import android.os.Bundle;

public class MainViewItem {
    String title;
    String contents;
    Intent targetIntent;
    int id = -1;
    Bundle bundle;

    public MainViewItem(String title, String contents, Intent target) {
        this.targetIntent = target;
        this.title = title;
        this.contents = contents;
    }

    public MainViewItem(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public MainViewItem(String title, String contents, int id) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public MainViewItem(String title, String contents, Bundle bundle) {
        this.bundle = bundle;
        this.title = title;
        this.contents = contents;
    }

    public MainViewItem(String title) {
        this(title, "");
    }

    public Intent getTarget() {
        return targetIntent;
    }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public Bundle getBundle() {
        return bundle;
    }
}
