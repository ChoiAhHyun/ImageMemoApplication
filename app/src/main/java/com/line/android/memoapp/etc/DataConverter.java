package com.line.android.memoapp.etc;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    @TypeConverter
    public static String fromStringList(List<String> list) {
        if (list == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.toJson(list, type);
    }

    @TypeConverter
    public static List<String> toStringList(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List>() {}.getType();
        return gson.fromJson(value, type);
    }
}