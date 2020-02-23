package com.line.android.memoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.line.android.memoapp.etc.DataConverter;

import java.util.List;

@Entity(tableName = "memo_table")
public class Memo {
    @PrimaryKey(autoGenerate = true)
    public int idx;

    public String title;

    public String content;

    @TypeConverters(DataConverter.class)
    public List<String> image;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public Memo(String title, String content, List<String> image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
