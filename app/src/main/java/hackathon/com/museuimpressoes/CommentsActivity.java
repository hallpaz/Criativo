package hackathon.com.museuimpressoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CommentsActivity extends AppCompatActivity {
    public static final String COMMENTWork = "work";
    private ListView commentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent receiver = getIntent();
        String currentWork  = receiver.getStringExtra(COMMENTWork);

        commentsView = (ListView) findViewById( R.id.commentsList);
        commentsView.setAdapter(new CommentsAdapter(this, currentWork) );

    }
}
