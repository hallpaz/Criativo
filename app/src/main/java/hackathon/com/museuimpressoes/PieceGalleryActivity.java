package hackathon.com.museuimpressoes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PieceGalleryActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "PieceGallery";

    private GridLayout imageGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_gallery);

        imageGrid = (GridLayout) findViewById(R.id.imageGrid);

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

        for(int i = 0; i < 7; ++i){
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.sepia_nature, bitmapOptions);
            ImageView image = new ImageView(this);
            image.setImageBitmap(bm);
            

            imageGrid.addView(image);

            image.setOnClickListener(this);
        }

    }

    public void onClick(View view){
        showPieceDetails();
    }

    public void showPieceDetails(){
        Log.d(TAG, "Should be showing details");
    }


}
