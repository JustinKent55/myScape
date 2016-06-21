package com.example.justin.myscape;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;


public class ImageEditActivity extends Activity {

    Resources m_Resources = null;
    String m_ImagePath = null;
    ImageEditView m_imageEditView = null;
    Button m_addImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);
        Intent intent = getIntent();
        m_Resources = getResources();

        //Retrieve intent resources and setup ImageEditView (custom view)
        m_ImagePath = intent.getStringExtra(m_Resources.getResourceName(R.string.edit_background_image));
        m_imageEditView = (ImageEditView) this.findViewById(R.id.customView);
        m_imageEditView.initialize(m_ImagePath, BitmapFactory.decodeResource(getResources(), R.drawable.tree_1));

        m_addImage = (Button) findViewById(R.id.addImageButton);

        m_addImage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), AddImage.class);
                        startActivityForResult(i, 0);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            switch(data.getIntExtra("selected", -1)) {
                case 0:
                    m_imageEditView.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.tree_1));
                    break;
                case 1:
                    m_imageEditView.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.white_flower_bush));
                    break;
                case 2:
                    m_imageEditView.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.palm_tree));
                    break;
            }
        }
    }

}
