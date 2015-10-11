package hackathon.com.museuimpressoes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ImageDetailsActivity extends AppCompatActivity {

    PhotoViewAttacher iAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_details);

        Intent receiver = getIntent();
        int imageResource = receiver.getExtras().getInt("image");

        Bitmap bpIMage = BitmapFactory.decodeResource(getResources(),imageResource);

        ImageView image_details = (ImageView) findViewById(R.id.image_details);
        image_details.setImageBitmap(bpIMage);
        iAttacher = new PhotoViewAttacher(image_details);

    }

}
