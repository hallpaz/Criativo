package hackathon.com.museuimpressoes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class DetailsActivity extends AppCompatActivity  {
    TextView descTextArtistBiography, videoArtistBiography, videoProductionContext, videoDescription;
    ImageButton showArtistBiography, hideArtistBiography;

    TextView descTextProductionContext;
    ImageButton showProductionContext, hideProductionContext, playProductionContext, stopProductionContext, playArtistBiographyText, stopArtistBiographyText;
    ImageButton fab, fab_stop, playBasicData, stopBasicData;
    MediaPlayer description_audio, productionContext_audio, artistBiographyText_audio, basicData_audio;

    public void showComments(View view){
        Intent commentsIntent = new Intent(this, CommentsActivity.class);
        startActivity(commentsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        basicData_audio = MediaPlayer.create(DetailsActivity.this, R.raw.baisc_data);
        description_audio = MediaPlayer.create(DetailsActivity.this, R.raw.description_audio);
        productionContext_audio = MediaPlayer.create(DetailsActivity.this, R.raw.context_audio);
        artistBiographyText_audio = MediaPlayer.create(DetailsActivity.this, R.raw.biography_audio);

        fab = (ImageButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description_audio.start();

                fab.setVisibility(View.INVISIBLE);
                fab_stop.setVisibility(View.VISIBLE);
            }
        });

        fab_stop = (ImageButton) findViewById(R.id.fab_stop);
        fab_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description_audio.pause();  //>>pause current sound
                fab.setVisibility(View.VISIBLE);
                fab_stop.setVisibility(View.INVISIBLE);
            }
        });

        videoArtistBiography = (TextView) findViewById(R.id.artist_biography_video);
        videoArtistBiography.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent videoIntent = new Intent(DetailsActivity.this, VideoBiographyActivity.class);
                        startActivity(videoIntent);

                    }
                }
        );

        videoProductionContext = (TextView) findViewById(R.id.production_context_video);
        videoProductionContext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent videoIntent = new Intent(DetailsActivity.this, VideoContextActivity.class);
                        startActivity(videoIntent);

                    }
                }
        );

        videoDescription = (TextView) findViewById(R.id.description_video);
        videoDescription.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent videoIntent = new Intent(DetailsActivity.this, VideoDescriptionActivity.class);
                        startActivity(videoIntent);

                    }
                }
        );

        playBasicData = (ImageButton) findViewById(R.id.play_basic_data);
        playBasicData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        basicData_audio.start();
                        playBasicData.setVisibility(View.INVISIBLE);
                        stopBasicData.setVisibility(View.VISIBLE);
                    }
                }
        );

        stopBasicData = (ImageButton) findViewById(R.id.stop_basic_data);
        stopBasicData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        basicData_audio.pause();
                        playBasicData.setVisibility(View.VISIBLE);
                        stopBasicData.setVisibility(View.INVISIBLE);
                    }
                }
        );

        AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar);
        bar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToImageDetails(view);
                    }
                }
        );

        String sectionTitle = "Mapa de Lopo Homem II - Série Terra Incógnita";
        TextView section_title = (TextView) findViewById(R.id.section_title);
        section_title.setText(sectionTitle);
        section_title.setContentDescription(sectionTitle);



        final String artistBiographyText = " Adriana Varejão é uma das artistas brasileiras de mais destaque na cena contemporânea, no Brasil e exterior. Suas obras integram as coleções dos principais museus do mundo e tem alcançado recordes de preço em casas de leilão de Londres e Nova York, atestando o reconhecimento internacional. No Brasil, Adriana ganhou um pavilhão inteiramente dedicado a seu trabalho no Instituto Inhotim, em Minas Gerais.\n" +
                "\n" +
                "Em sua produção, evoca repertório de imagens associadas à história do período colonial brasileiro, como azulejos e mapas. Apesar de remeter ao barroco, adquire forte contemporaneidade em decorrência do acúmulo excessivo de materiais, camadas de tinta e informações. Em obras que se situam entre a pintura e o relevo, freqüentemente emprega cortes e suturas em telas e outros suportes que permitem entrever materiais internos que imitam o aspecto de carne. ";


        descTextArtistBiography = (TextView) findViewById(R.id.artist_biography_text);
        showArtistBiography = (ImageButton) findViewById(R.id.show_artist_biography_text);
        showArtistBiography.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                showArtistBiography.setVisibility(View.INVISIBLE);
                hideArtistBiography.setVisibility(View.VISIBLE);
                playArtistBiographyText.setVisibility(View.VISIBLE);
                videoArtistBiography.setVisibility(View.VISIBLE);
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
                playArtistBiographyText.setVisibility(View.INVISIBLE);
                showArtistBiography.setVisibility(View.VISIBLE);
                descTextArtistBiography.setMaxLines(1);
                descTextArtistBiography.setText(R.string.artist_biography_title);
                descTextArtistBiography.setAllCaps(true);
                descTextArtistBiography.setTextSize(20);
                descTextArtistBiography.setTypeface(null, Typeface.BOLD);

            }
        });



        final String productionContextText = " A obra Mapa de Lopo Homem II faz parte da série Terra Incógnita, iniciada em 1992. Ela representa um mapa baseado nos estudos do cartógrafo português Lopo Homem. Ele acreditava existir um pedaço de terra que unia as Américas e a Ásia, que ele denominou como Mundus Novus. O Brasil foi parte do que ele traçou em seu mapa de 1519 como Mundus Novus Brasil.\n" +
                "\n" +
                "Por meio de materiais e narrativa histórica, o corte sangrento que atravessa o centro da pintura Mapa de Lopo Homem II representam a violência que se esconde entre as versões da colonização da América.";

        descTextProductionContext = (TextView) findViewById(R.id.production_context_text);
        showProductionContext = (ImageButton) findViewById(R.id.show_production_context_text);
        showProductionContext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                showProductionContext.setVisibility(View.INVISIBLE);
                hideProductionContext.setVisibility(View.VISIBLE);
                videoProductionContext.setVisibility(View.VISIBLE);
                playProductionContext.setVisibility(View.VISIBLE);
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
                playProductionContext.setVisibility(View.INVISIBLE);
                showProductionContext.setVisibility(View.VISIBLE);
                descTextProductionContext.setMaxLines(1);
                descTextProductionContext.setText(R.string.production_context_title);
                descTextProductionContext.setAllCaps(true);
                descTextProductionContext.setTextSize(20);
                descTextProductionContext.setTypeface(null, Typeface.BOLD);

            }
        });

        playProductionContext = (ImageButton) findViewById(R.id.play_production_context_text);
        playProductionContext.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        productionContext_audio.start();
                        playProductionContext.setVisibility(View.INVISIBLE);
                        stopProductionContext.setVisibility(View.VISIBLE);


                    }
                }
        );

        stopProductionContext = (ImageButton) findViewById(R.id.stop_production_context_text);
        stopProductionContext.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        productionContext_audio.pause();  //>>pause current sound
                        playProductionContext.setVisibility(View.VISIBLE);
                        stopProductionContext.setVisibility(View.INVISIBLE);
                    }
                }
        );



        playArtistBiographyText = (ImageButton) findViewById(R.id.play_artist_biography_text);
        playArtistBiographyText.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        artistBiographyText_audio.start();
                        playArtistBiographyText.setVisibility(View.INVISIBLE);
                        stopArtistBiographyText.setVisibility(View.VISIBLE);


                    }
                }
        );

        stopArtistBiographyText = (ImageButton) findViewById(R.id.stop_artist_biography_text);
        stopArtistBiographyText.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        artistBiographyText_audio.pause();  //>>pause current sound
                        playArtistBiographyText.setVisibility(View.VISIBLE);
                        stopArtistBiographyText.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }

    public void goToImageDetails(View view){
        Intent imageDetailsIntent = new Intent(this, ImageDetailsActivity.class);
        imageDetailsIntent.putExtra("image", R.drawable.varejao);
        startActivity(imageDetailsIntent);
    }
    public static MediaPlayer getMediaPlayer(Context context){

        MediaPlayer mediaplayer = new MediaPlayer();

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return mediaplayer;
        }

        try {
            Class<?> cMediaTimeProvider = Class.forName( "android.media.MediaTimeProvider" );
            Class<?> cSubtitleController = Class.forName( "android.media.SubtitleController" );
            Class<?> iSubtitleControllerAnchor = Class.forName( "android.media.SubtitleController$Anchor" );
            Class<?> iSubtitleControllerListener = Class.forName( "android.media.SubtitleController$Listener" );

            Constructor constructor = cSubtitleController.getConstructor(new Class[]{Context.class, cMediaTimeProvider, iSubtitleControllerListener});

            Object subtitleInstance = constructor.newInstance(context, null, null);

            Field f = cSubtitleController.getDeclaredField("mHandler");

            f.setAccessible(true);
            try {
                f.set(subtitleInstance, new Handler() {
                    @Override
                    public void close() {

                    }

                    @Override
                    public void flush() {

                    }

                    @Override
                    public void publish(LogRecord record) {

                    }
                });
            }
            catch (IllegalAccessException e) {return mediaplayer;}
            finally {
                f.setAccessible(false);
            }

            Method setsubtitleanchor = mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor);

            setsubtitleanchor.invoke(mediaplayer, subtitleInstance, null);
            //Log.e("", "subtitle is setted :p");
        } catch (Exception e) {}

        return mediaplayer;
    }



}
