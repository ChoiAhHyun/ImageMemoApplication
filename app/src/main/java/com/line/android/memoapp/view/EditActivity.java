package com.line.android.memoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.line.android.memoapp.R;
import com.line.android.memoapp.database.AppDatabase;
import com.line.android.memoapp.database.MemoDao;
import com.line.android.memoapp.model.Memo;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    private boolean EDIT_MODE;

    private EditText et_title, et_content;
    private RecyclerView recyclerView;
    private int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();
        setDetail();
    }

    private void initialize() {
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
//        recyclerView = findViewById(R.id.pager);
    }

    private void setDetail() {
        Intent intent = getIntent();
        idx = intent.getIntExtra("idx", 0);
        if (idx != 0){
            EDIT_MODE = true;

            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            List<String> image = intent.getStringArrayListExtra("image");

            et_title.setText(title);
            et_content.setText(content);
//            setImage(image);
        } else {
            EDIT_MODE = false;
        }
    }
/*
    private void setImage(Memo memo) {
        if (memo.image == null){
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            ImageAdapter adapter = new ImageAdapter(this);
            recyclerView.setAdapter(adapter);
            adapter.setImages(memo.image);

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
            recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); //TODO override해서 dialog로 확인
                return true;
            case R.id.upload:
                return true;
            case R.id.save:
                Memo memo = new Memo(et_title.getText().toString(), et_content.getText().toString(), null);
                if (EDIT_MODE) {
                    memo.setIdx(idx);
                    new UpdateAsyncTask(AppDatabase.getInstance(getApplicationContext()).memoDao()).execute(memo);
                } else {
                    new InsertAsyncTask(AppDatabase.getInstance(getApplicationContext()).memoDao()).execute(memo);
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Memo, Void, Void> {
        private MemoDao mMemoDao;

        private InsertAsyncTask(MemoDao memoDao){
            this.mMemoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            mMemoDao.insert(memos[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Memo, Void, Void> {
        private MemoDao mMemoDao;

        private UpdateAsyncTask(MemoDao memoDao){
            this.mMemoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            mMemoDao.update(memos[0]);
            return null;
        }
    }
}
