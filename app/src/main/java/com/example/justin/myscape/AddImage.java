package com.example.justin.myscape;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddImage extends ListActivity {

    private static String[] mListofImages = null;
    private static ArrayAdapter<String> myImages = null;
    private static ListView listView = null;
    private static Resources mRes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        mRes = getResources();

        mListofImages = mRes.getStringArray(R.array.images);
        myImages = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mListofImages);

        getListView().setAdapter(myImages);
        listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selected", position);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
