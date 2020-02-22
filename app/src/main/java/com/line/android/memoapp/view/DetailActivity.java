package com.line.android.memoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.line.android.memoapp.R;
import com.line.android.memoapp.adapter.ImageAdapter;
import com.line.android.memoapp.etc.CirclePagerIndicatorDecoration;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private String title, content;
    private ArrayList image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        image = intent.getParcelableArrayListExtra("image");

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(content);

        RecyclerView recyclerView = findViewById(R.id.pager);
        if (image == null){
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            ImageAdapter adapter = new ImageAdapter(this, image);
            recyclerView.setAdapter(adapter);

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
                //TODO 데이터베이스 연결한 후
                return true;
            case R.id.edit:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
