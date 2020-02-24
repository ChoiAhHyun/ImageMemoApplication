package com.line.android.memoapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.line.android.memoapp.R;
import com.line.android.memoapp.adapter.ImageBoxAdapter;
import com.line.android.memoapp.database.AppDatabase;
import com.line.android.memoapp.database.MemoDao;
import com.line.android.memoapp.model.Memo;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    private boolean EDIT_MODE;

    private EditText et_title, et_content;
    private RecyclerView recyclerView;
    private ImageBoxAdapter adapter;

    private int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();
        setRecyclerView();
        setDetail();
    }

    private void initialize() {
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        recyclerView = findViewById(R.id.rv_image);
    }

    private void setRecyclerView() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        adapter = new ImageBoxAdapter(this);
        recyclerView.setAdapter(adapter);
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
            adapter.setImages(image);
        } else {
            EDIT_MODE = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.camera:
                takePhoto();
                return true;
            case R.id.album:
                selectAlbum();
                return true;
            case R.id.url:

                return true;
            case R.id.save:
                saveMemo();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void takePhoto() {
        Album.camera(this)
                .image()
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        adapter.addImage(result);
                    }
                })
                .start();
    }

    private void selectAlbum() {
        Album.image(this)
                .singleChoice()
                .camera(false)
                .columnCount(3)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        adapter.addImage(result.get(0).getPath());
                    }
                })
                .start();
    }

    private void saveMemo() {
        Memo memo = new Memo(et_title.getText().toString(), et_content.getText().toString(), adapter.getImages());
        if (EDIT_MODE) {
            memo.setIdx(idx);
            new UpdateAsyncTask(AppDatabase.getInstance(this).memoDao()).execute(memo);
        } else {
            new InsertAsyncTask(AppDatabase.getInstance(this).memoDao()).execute(memo);
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
