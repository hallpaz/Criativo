package hackathon.com.museuimpressoes;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PieceGalleryActivity extends AppCompatActivity  {
    private final String TAG = "PieceGallery";
    public static final String PANELExtra = "panelName";

    private GridView imageGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_gallery);

        Intent receiver = getIntent();

        String panelName = receiver.getStringExtra(PANELExtra);


        imageGrid = (GridView) findViewById(R.id.imageGrid);
        imageGrid.setAdapter(new ImageAdapter(this, panelName));

        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent detailsIntent = new Intent(PieceGalleryActivity.this, DetailsActivity.class);
                startActivity(detailsIntent);
            }
        });

    }


}
