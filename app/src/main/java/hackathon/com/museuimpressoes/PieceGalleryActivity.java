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

public class PieceGalleryActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "PieceGallery";

    private GridView imageGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_gallery);

        imageGrid = (GridView) findViewById(R.id.imageGrid);
        imageGrid.setAdapter(new ImageAdapter(this, "test_panel"));

        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(PieceGalleryActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                Intent detailsIntent = new Intent(PieceGalleryActivity.this, DetailsActivity.class);
                startActivity(detailsIntent);
            }
        });

    }

    public void onClick(View view){
        showPieceDetails();
    }

    public void showPieceDetails(){
        Log.d(TAG, "Should be showing details");
    }

}
