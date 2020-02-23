package com.line.android.memoapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.line.android.memoapp.R;
import com.line.android.memoapp.adapter.ImageAdapter;
import com.line.android.memoapp.database.AppDatabase;
import com.line.android.memoapp.database.MemoDao;
import com.line.android.memoapp.etc.CirclePagerIndicatorDecoration;
import com.line.android.memoapp.model.Memo;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView tv_title, tv_content;
    private RecyclerView recyclerView;

    private AppDatabase database;

    private int idx;
    private String title, content;
    private List<String> image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();
        getDetail();
    }

    private void initialize() {
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        recyclerView = findViewById(R.id.pager);
    }

    private void getDetail() {
        Intent intent = getIntent();
        idx = intent.getIntExtra("idx", 0);
        if (idx != 0) {
            database = AppDatabase.getInstance(getApplicationContext());
            LiveData<Memo> memo = database.memoDao().getByIdx(idx);

            memo.observe(this, new Observer<Memo>() {
                @Override
                public void onChanged(@Nullable final Memo memo) {
                    setDetail(memo);
                }
            });
        }
    }

    private void setDetail(Memo memo){
        title = memo.title;
        content = memo.content;
        image = memo.image;

        tv_title.setText(title);
        tv_content.setText(content);
        setImage(image);
    }

    private void setImage(List<String> image) {
        if (image == null){
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            ImageAdapter adapter = new ImageAdapter(this);
            recyclerView.setAdapter(adapter);
            adapter.setImages(image);

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
            recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.delete:
                new DeleteAsyncTask(database.memoDao()).execute(idx);
                return true;
            case R.id.edit:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("idx", idx);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("image", (ArrayList) image);
                startActivityForResult(intent, 200);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private MemoDao mMemoDao;

        private DeleteAsyncTask(MemoDao memoDao){
            this.mMemoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            mMemoDao.deleteByIdx(integers[0]);
            return null;
        }
    }
}
