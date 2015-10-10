package hackathon.com.museuimpressoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetailsActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar);
        bar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      goToGallery(view);
                    }
                }
        );

    }



    public void goToGallery(View view){
        Intent galleryIntent = new Intent(this, PieceGalleryActivity.class);
        startActivity(galleryIntent);
    }
}
