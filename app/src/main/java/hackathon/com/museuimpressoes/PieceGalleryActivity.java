package hackathon.com.museuimpressoes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class PieceGalleryActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "PieceGallery";

    private NestedScrollView imageScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_gallery);

        imageScrollView = (NestedScrollView) findViewById(R.id.imageScrollView);

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile("filename", bitmapOptions);

        ImageView image = new ImageView(this);
        image.setImageBitmap(bm);

        imageScrollView.addView(image);

        image.setOnClickListener(this);

    }

    public void onClick(View view){
        showPieceDetails();
    }

    public void showPieceDetails(){
        Log.d(TAG, "Should be showing details");
    }


}
