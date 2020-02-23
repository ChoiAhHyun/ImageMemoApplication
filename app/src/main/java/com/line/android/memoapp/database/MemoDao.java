package com.line.android.memoapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.line.android.memoapp.model.Memo;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM memo_table ORDER BY idx DESC")
    LiveData<List<Memo>> getAll();

//    @Query("SELECT * FROM memo_table WHERE idx  idx ")
//    Memo getByIdx(int idx);

    @Update
    void update(Memo memo);

    @Insert
    void insert(Memo memo);

    @Delete
    void delete(Memo memo);
}
