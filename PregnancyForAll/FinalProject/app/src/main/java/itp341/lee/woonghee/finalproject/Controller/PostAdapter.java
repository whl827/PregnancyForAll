package itp341.lee.woonghee.finalproject.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import itp341.lee.woonghee.finalproject.Model.Post;
import itp341.lee.woonghee.finalproject.R;

/**
 * Created by WoongHee on 12/4/2016.
 */
//custom adapter
public class PostAdapter extends ArrayAdapter<Post> {
    //store posts and get context
    private ArrayList<Post> posts;
    private Context context;
    //overloaded constructor
    public PostAdapter(Context context,int resourceId, ArrayList<Post> posts) {
        super(context, resourceId , posts);
        this.posts = posts;
        this.context = context;
    }
    //build a custom list view for potsts/tips section
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            LayoutInflater li = LayoutInflater.from(context);
            convertView = li.inflate(R.layout.fragment_custom_post, null);
        }
        Post post = posts.get(position);

        TextView month = (TextView) convertView.findViewById(R.id.post_month);
        TextView title = (TextView) convertView.findViewById(R.id.post_title);

        month.setText("Month " + post.getMonth());
        title.setText(post.getTitle());

        return convertView;
    }
}
