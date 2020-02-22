package com.line.android.memoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.line.android.memoapp.R;
import com.line.android.memoapp.adapter.ImageAdapter;
import com.line.android.memoapp.etc.CirclePagerIndicatorDecoration;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(intent.getStringExtra("title"));
        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(intent.getStringExtra("content"));

        RecyclerView recyclerView = findViewById(R.id.pager);
        ArrayList list = intent.getParcelableArrayListExtra("image");
        if (list == null){
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            ImageAdapter adapter = new ImageAdapter(this, list);
            recyclerView.setAdapter(adapter);

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
            recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        }
    }
}
