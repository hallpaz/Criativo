package hackathon.com.museuimpressoes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by hallpaz on 10/10/2015.
 */
public class ImageAdapter extends BaseAdapter {

    private Context myContext;
    private int squareSize = 250;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature,
            R.drawable.sepia_nature, R.drawable.sepia_nature
    };


    public ImageAdapter(Context c) {
        myContext = c;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(myContext);
            imageView.setLayoutParams(new GridView.LayoutParams(squareSize, squareSize));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;

    }
    /*
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageButton = new ImageButton(myContext);
            imageButton.setLayoutParams(new GridView.LayoutParams(squareSize, squareSize));
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageButton.setPadding(8, 8, 8, 8);
        } else {
            imageButton = (imageButton) convertView;
        }

        imageButton.setImageResource(mThumbIds[position]);
        return imageButton;
     */
}
