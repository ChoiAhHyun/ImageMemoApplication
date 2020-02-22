package com.line.android.memoapp.model;

import java.util.ArrayList;

public class MemoModel {
    private String title, content;
    private ArrayList image;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList getImage() {
        return image;
    }

    public MemoModel(String title, String content, ArrayList image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
