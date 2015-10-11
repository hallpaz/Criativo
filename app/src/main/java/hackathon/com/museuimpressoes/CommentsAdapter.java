package hackathon.com.museuimpressoes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Vector;

/**
 * Created by hallpaz on 11/10/2015.
 */
public class CommentsAdapter extends BaseAdapter {
    private Context myContext;
    private ParseQuery<ParseObject> query;
    private Vector<ParseObject> commentsObjects;

    public CommentsAdapter(Context context, String workname){
        myContext = context;

        commentsObjects = new Vector<ParseObject>();

        query = ParseQuery.getQuery("Comments");
        query.whereEqualTo("work", workname);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                commentsObjects.addAll(list);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getCount() {
        return commentsObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParseObject currentCommentObject = commentsObjects.get(position);

        String username = currentCommentObject.getString("username");
        String text = currentCommentObject.getString("text");

        TextView comment = new TextView(myContext);

        comment.setText(username + "\n" + text);
        return comment;

    }
}
