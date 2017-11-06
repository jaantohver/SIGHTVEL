package org.garage48.travellinn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // references to our images
    private Integer[] mThumbIds;

    Bitmap bmp;

    public ImageAdapter(Context c, String name, Bitmap bmp) {
        mContext = c;
        this.bmp = bmp;

        if (name.equals("Zavood")) {
            mThumbIds = new Integer[]{
                    R.drawable.zav1, R.drawable.zav2
            };
        } else if (name.equals( "Physicum")) {
            mThumbIds = new Integer[]{
                    R.drawable.phys1, R.drawable.phys2
            };
        } else if (name.equals("Keller")) {
            mThumbIds = new Integer[]{
                    R.drawable.keller
            };
        }
    }

    public int getCount() {
        if (bmp == null) {
            return mThumbIds.length;
        }

        return mThumbIds.length + 1;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(320, 320));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        if (position == mThumbIds.length) {
            imageView.setImageBitmap(bmp);
        } else {
            imageView.setImageResource(mThumbIds[position]);
        }
        return imageView;
    }
}