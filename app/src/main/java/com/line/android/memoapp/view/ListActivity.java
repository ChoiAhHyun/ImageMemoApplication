package com.line.android.memoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.line.android.memoapp.R;
import com.line.android.memoapp.adapter.ListAdapter;
import com.line.android.memoapp.model.MemoModel;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList image = new ArrayList<>();
    ArrayList<MemoModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        addImage();

        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        ListAdapter adapter = new ListAdapter(this, list);
        recyclerView.setAdapter(adapter);
        list.clear();
        addList();
    }

    private void addList() {
        for(int i = 0; i < 10; i++){
            list.add(new MemoModel("가가가가가가가가가",
                    "가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가",
                    null));
            list.add(new MemoModel("가나다라마바사아자차카타파하",
                    "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                    image));
            list.add(new MemoModel("나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나",
                    "나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나",
                    null));
        }
        list.add(new MemoModel("가가가가가가가가가",
                "가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가",
                null));
        list.add(new MemoModel("나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나",
                "나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나나",
                null));
    }

    private void addImage() {
        image.add("https://i.dlpng.com/static/png/1489152-parkpng-park-png-1276_717_preview.png");
        image.add("https://purepng.com/public/uploads/large/purepng.com-fresh-applefoodsweettastyhealthyfruitappleleaf-981524677946vfurf.png");
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
