package com.example.justin.myscape;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    final static int PICK_IMAGE = 100;
    Button m_infoButton = null;
    Button m_photoGallaryButton = null;
    Resources m_Resources = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_Resources = getResources();
        m_infoButton = (Button) findViewById(R.id.information_button);
        m_photoGallaryButton = (Button) findViewById(R.id.access_photos_button);

        m_infoButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), Instructions.class);

                        try{
                            startActivity(i);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        m_photoGallaryButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Intent i = new Intent(getApplicationContext(), ImageEditActivity.class);
            Uri imageUri = data.getData();
            String stringUri;

            stringUri = imageUri.toString();
            i.putExtra(m_Resources.getResourceName(R.string.edit_background_image),stringUri);

            try{
                startActivity(i);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
