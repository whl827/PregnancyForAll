package itp341.lee.woonghee.finalproject.View;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import itp341.lee.woonghee.finalproject.Controller.PostAdapter;
import itp341.lee.woonghee.finalproject.Model.Post;
import itp341.lee.woonghee.finalproject.Model.UserDbHelper;
import itp341.lee.woonghee.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserPostFragment extends Fragment {

    private ListView listView;
    private Button add_post_button;
    private ArrayList<Post> posts;
    private PostAdapter mAdapter;

    public UserPostFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_post, container, false);
        listView = (ListView) v.findViewById(R.id.post_listView);
        add_post_button = (Button) v.findViewById(R.id.add_post_button);
        posts = UserDbHelper.getInstance(getActivity().getApplicationContext()).getPostList();
        mAdapter = new PostAdapter(getActivity(), 0, posts);
        listView.setAdapter(mAdapter);
        //when button is clicked, open new page where you can add a post
        add_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open a page to create a new post
                Intent i = new Intent(getActivity().getApplicationContext(), CreatePostActivity.class);
                startActivityForResult(i,1);
            }
        });

        //when one post is clicked, open a new page with details
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post p = posts.get(i);
                Intent intent = new Intent(getActivity().getApplicationContext(), PostDetailActivity.class);
                intent.putExtra("Selected Post",p);
                startActivity(intent);
            }
        });
        return v;
    }
    //when coming back from creating post, update the data
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listView.invalidate();
        mAdapter.notifyDataSetChanged();
    }
}
