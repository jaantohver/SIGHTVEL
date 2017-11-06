package org.garage48.travellinn;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class LocationPhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location_photos);

        setTitle(getIntent().getStringExtra("LOC"));

        final Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BitmapImage");

        GridView grid = findViewById(R.id.photo_grid);
        grid.setAdapter(new ImageAdapter(this, getIntent().getStringExtra("LOC"), bitmap));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), LocationPhotoActivity.class);

                if (getIntent().getStringExtra("LOC").equals("Zavood")) {
                    if (i == 0) {
                        intent.putExtra("RES", R.drawable.zav1);
                    } else if (i == 1) {
                        intent.putExtra("RES", R.drawable.zav2);
                    }
                } else if (getIntent().getStringExtra("LOC").equals( "Physicum")) {
                    if (i == 0) {
                        intent.putExtra("RES", R.drawable.phys1);
                    } else if (i == 1) {
                        intent.putExtra("RES", R.drawable.phys2);
                    } else {
                        intent.putExtra("BitmapImage", bitmap);
                    }
                } else if (getIntent().getStringExtra("LOC").equals("Keller")) {
                    intent.putExtra("RES", R.drawable.keller);
                }

                startActivity(intent);
            }
        });
    }
}
