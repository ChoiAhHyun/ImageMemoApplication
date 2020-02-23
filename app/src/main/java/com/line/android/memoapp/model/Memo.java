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

    @ColumnInfo
    public String title;

    @ColumnInfo
    public String content;

    @ColumnInfo
    @TypeConverters(DataConverter.class)
    public List<String> image;

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getImage() {
        return image;
    }

    public Memo(int idx, String title, String content, List<String> image) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
