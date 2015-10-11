package hackathon.com.museuimpressoes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity  {
    TextView descTextArtistBiography;
    ImageButton showArtistBiography, hideArtistBiography;

    TextView descTextProductionContext;
    ImageButton showProductionContext, hideProductionContext;


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
                        goToImageDetails(view);
                    }
                }
        );

        String sectionTitle = "Santos Dummont";
        TextView section_title = (TextView) findViewById(R.id.section_title);
        section_title.setText(sectionTitle);
        section_title.setContentDescription(sectionTitle);



        final String artistBiographyText = " \"A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                "        \"technologically advanced and open to imagination and magic.\\n\"\n" +
                "        \"Surfaces and edges of the material provide visual cues that are grounded in reality. The \"\n" +
                "        \"use of familiar tactile attributes helps users quickly understand affordances. Yet the \"\n" +
                "        \"flexibility of the material creates new affordances that supercede those in the physical \"\n" +
                "        \"world, without breaking the rules of physics.\\n\"\n" +
                "        \"The fundamentals of light, surface, and movement are key to conveying how objects move, \"\n" +
                "        \"interact, and exist in space and in relation to each other. Realistic lighting shows \"\n" +
                "        \"seams, divides space, and indicates moving parts.\\n\\n\"\n";


        descTextArtistBiography = (TextView) findViewById(R.id.artist_biography_text);
        showArtistBiography = (ImageButton) findViewById(R.id.show_artist_biography_text);
        showArtistBiography.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                showArtistBiography.setVisibility(View.INVISIBLE);
                hideArtistBiography.setVisibility(View.VISIBLE);
                descTextArtistBiography.setMaxLines(Integer.MAX_VALUE);
                descTextArtistBiography.setText(artistBiographyText);
                descTextArtistBiography.setAllCaps(false);
                descTextArtistBiography.setTextSize(16);
                descTextArtistBiography.setTypeface(null, Typeface.NORMAL);


            }
        });
        hideArtistBiography = (ImageButton) findViewById(R.id.hide_artist_biography_text);

        hideArtistBiography.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hideArtistBiography.setVisibility(View.INVISIBLE);
                showArtistBiography.setVisibility(View.VISIBLE);
                descTextArtistBiography.setMaxLines(1);
                descTextArtistBiography.setText(R.string.artist_biography_title);
                descTextArtistBiography.setAllCaps(true);
                descTextArtistBiography.setTextSize(20);
                descTextArtistBiography.setTypeface(null, Typeface.BOLD);


            }
        });



        final String productionContextText = " \"A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                "        \"technologically advanced and open to imagination and magic.\\n\"\n" +
                "        \"Surfaces and edges of the material provide visual cues that are grounded in reality. The \"\n" +
                "        \"use of familiar tactile attributes helps users quickly understand affordances. Yet the \"\n" +
                "        \"flexibility of the material creates new affordances that supercede those in the physical \"\n" +
                "        \"world, without breaking the rules of physics.\\n\"\n" +
                "        \"The fundamentals of light, surface, and movement are key to conveying how objects move, \"\n" +
                "        \"interact, and exist in space and in relation to each other. Realistic lighting shows \"\n" +
                "        \"seams, divides space, and indicates moving parts.\\n\\n\"\n";

        descTextProductionContext = (TextView) findViewById(R.id.production_context_text);
        showProductionContext = (ImageButton) findViewById(R.id.show_production_context_text);
        showProductionContext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                showProductionContext.setVisibility(View.INVISIBLE);
                hideProductionContext.setVisibility(View.VISIBLE);
                descTextProductionContext.setMaxLines(Integer.MAX_VALUE);
                descTextProductionContext.setText(productionContextText);
                descTextProductionContext.setAllCaps(false);
                descTextProductionContext.setTextSize(16);
                descTextProductionContext.setTypeface(null, Typeface.NORMAL);


            }
        });
        hideProductionContext = (ImageButton) findViewById(R.id.hide_production_context_text);

        hideProductionContext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hideProductionContext.setVisibility(View.INVISIBLE);
                showProductionContext.setVisibility(View.VISIBLE);
                descTextProductionContext.setMaxLines(1);
                descTextProductionContext.setText(R.string.production_context_title);
                descTextProductionContext.setAllCaps(true);
                descTextProductionContext.setTextSize(20);
                descTextProductionContext.setTypeface(null, Typeface.BOLD);



            }
        });
    }



    public void goToImageDetails(View view){
        Intent imageDetailsIntent = new Intent(this, ImageDetailsActivity.class);
        imageDetailsIntent.putExtra("image", R.drawable.catorzebis);
        startActivity(imageDetailsIntent);
    }
}
