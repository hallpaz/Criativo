package hackathon.com.museuimpressoes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChooseGalleryActivity extends AppCompatActivity {

    TextView gallery1button, gallery2button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gallery);

        gallery1button = (TextView) findViewById(R.id.gallery1button);
        gallery1button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(ChooseGalleryActivity.this, PieceGalleryActivity.class);
                galleryIntent.putExtra("panelName", "Varejão");
                startActivity(galleryIntent);

            }
        });

        gallery2button = (TextView) findViewById(R.id.gallery2button);
        gallery2button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(ChooseGalleryActivity.this, PieceGalleryActivity.class);
                galleryIntent.putExtra("panelName", "São Sebastião");
                startActivity(galleryIntent);

            }
        });
    }
}
