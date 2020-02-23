package com.line.android.memoapp.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.line.android.memoapp.database.MemoRepository;
import com.line.android.memoapp.model.Memo;

import java.util.List;

public class MemoViewModel extends AndroidViewModel {

    private MemoRepository repository;

    private LiveData<List<Memo>> memoList;

    public MemoViewModel(Application application) {
        super(application);
        repository = new MemoRepository(application);
        memoList = repository.getAll();
    }

    LiveData<List<Memo>> getAll() { return memoList; }

    public void insert(Memo word) { repository.insert(word);}
}
