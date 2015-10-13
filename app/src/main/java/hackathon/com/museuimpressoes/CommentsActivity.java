package hackathon.com.museuimpressoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    private List<User> users;
    private RecyclerView commentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comments);

        commentsView =(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        commentsView.setLayoutManager(llm);
        commentsView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        users = new ArrayList<>();
        users.add(new User("Hallison da Paz", "24 anos", R.drawable.hallison, getString(R.string.hallpaz_comment) ));
        users.add(new User("Kizzy dos reis", "23 anos", R.drawable.kizzy, getString(R.string.kizzy_comment)));
        users.add(new User("Rubens Ramos", "25 anos", R.drawable.rubens, getString(R.string.rubens_comment)));
        users.add(new User("Tatiana Silva", "25 years old", R.drawable.tatiana, getString(R.string.tatiana_comment)));
        users.add(new User("Patrícia Licio", "20 anos", R.drawable.patricia, getString(R.string.patricia_comment)));
    }

    private void initializeAdapter(){
        CommentsAdapter adapter = new CommentsAdapter(users);
        commentsView.setAdapter(adapter);
    }
}
