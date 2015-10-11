package hackathon.com.museuimpressoes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;
import java.util.Vector;

/**
 * Created by hallpaz on 10/10/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private final String TAG = "IMAGE_RET";
    private Context myContext;
    private int squareSize = 250;
    private ParseQuery<ParseObject> query;
    private Vector<ParseFile> imageList;
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


    public ImageAdapter(Context c, String panelName) {
        myContext = c;
        imageList = new Vector<ParseFile>();
        query = ParseQuery.getQuery("WorkThumb");
        query.whereEqualTo("panel", panelName);
        /*try {
            List<ParseObject> list = query.find();
            for (int i = 0; i < list.size(); ++i){
                imageList.add(list.get(i).getParseFile("image")) ;
                Log.d(TAG, "opa");
            }

        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }*/
        
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    //imageList = (list.get(0)).getParseFile();
                    for (int i = 0; i < list.size(); ++i){
                        imageList.add(list.get(i).getParseFile("image")) ;
                        notifyDataSetChanged();
                        Log.d(TAG, "opa");
                    }
                    Log.d("score", "Retrieved " + list.size() + " IMAGES");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }


    @Override
    public int getCount() {
        return imageList.size();
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


        //imageView.setImageResource(mThumbIds[position]);

        try {
            byte[] imageData = imageList.get(position).getData();
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bm;
            bm = BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);
            imageView.setImageBitmap(bm);
        } catch (com.parse.ParseException e) {
            Log.e(TAG, "Deu RUIM!");
        }

        //imageView.setImageBitmap(bm);
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
