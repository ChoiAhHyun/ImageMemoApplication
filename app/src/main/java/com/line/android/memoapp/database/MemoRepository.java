package com.line.android.memoapp.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.line.android.memoapp.model.Memo;

import java.util.List;

public class MemoRepository {
    private MemoDao memoDao;
    private LiveData<List<Memo>> memoList;

    public MemoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        memoDao = database.memoDao();
        memoList = memoDao.getAll();
    }

    public LiveData<List<Memo>> getAll() {
        return memoList;
    }

    public void insert(Memo memo) {
        new insertAsyncTask(memoDao).execute(memo);
    }

    private static class insertAsyncTask extends AsyncTask<Memo, Void, Void> {
        private MemoDao mMemoDao;

        insertAsyncTask(MemoDao dao) {
            mMemoDao = dao;
        }

        @Override
        protected Void doInBackground(final Memo... memos) {
            mMemoDao.insert(memos[0]);
            return null;
        }
    }
}
