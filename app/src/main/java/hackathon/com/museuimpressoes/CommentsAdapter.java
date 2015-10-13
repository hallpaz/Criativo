package hackathon.com.museuimpressoes;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.UserViewHolder> {

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView userName;
        //TextView userAge;
        TextView userComment;
        ImageView userPhoto;

        UserViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            userName = (TextView)itemView.findViewById(R.id.user_name);
            //userAge = (TextView)itemView.findViewById(R.id.person_age);
            userPhoto = (ImageView)itemView.findViewById(R.id.user_photo);
            userComment = (TextView)itemView.findViewById(R.id.user_comment);
        }
    }

    List<User> users;

    CommentsAdapter(List<User> users){
        this.users = users;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        UserViewHolder pvh = new UserViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int position) {
        userViewHolder.userName.setText(users.get(position).name);
        //userViewHolder.userAge.setText(users.get(position).age);
        userViewHolder.userPhoto.setImageResource(users.get(position).photoId);
        userViewHolder.userComment.setText(users.get(position).comment);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
