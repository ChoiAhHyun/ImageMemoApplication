package com.cah.android.memoapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cah.android.memoapp.R;
import com.cah.android.memoapp.adapter.ListAdapter;
import com.cah.android.memoapp.database.AppDatabase;
import com.cah.android.memoapp.model.Memo;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ListAdapter(this);
        recyclerView.setAdapter(adapter);

        getList();
    }

    private void getList() {
        AppDatabase database = AppDatabase.getInstance(getApplicationContext());
        LiveData<List<Memo>> memoList = database.memoDao().getAll();

        memoList.observe(this, new Observer<List<Memo>>() {
            @Override
            public void onChanged(@Nullable final List<Memo> memos) {
                adapter.setMemos(memos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
