package org.garage48.travellinn;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class LocationPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("Photo");

        ImageView iv = findViewById(R.id.photo);

        final Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BitmapImage");

        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
            iv.setImageResource(getIntent().getIntExtra("RES", 0));
        }
    }
}
