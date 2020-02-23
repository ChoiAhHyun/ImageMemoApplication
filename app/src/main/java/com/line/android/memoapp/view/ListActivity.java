package com.line.android.memoapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.line.android.memoapp.R;
import com.line.android.memoapp.adapter.ListAdapter;
import com.line.android.memoapp.model.Memo;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private MemoViewModel memoViewModel;

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
        memoViewModel = new ViewModelProvider(this).get(MemoViewModel.class);

        memoViewModel.getAll().observe(this, new Observer<List<Memo>>() {
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
}
