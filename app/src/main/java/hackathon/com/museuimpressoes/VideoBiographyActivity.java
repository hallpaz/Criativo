package hackathon.com.museuimpressoes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoBiographyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String uriPath = "android.resource://"+ getPackageName() + "/" + R.raw.biography_video;
        Uri uri = Uri.parse(uriPath);


        VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
        myVideoView.setVideoURI(uri);
        myVideoView.start();
    }

}
