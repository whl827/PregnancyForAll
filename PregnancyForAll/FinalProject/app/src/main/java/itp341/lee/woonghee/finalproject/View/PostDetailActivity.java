package itp341.lee.woonghee.finalproject.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import itp341.lee.woonghee.finalproject.Model.Post;
import itp341.lee.woonghee.finalproject.R;

public class PostDetailActivity extends AppCompatActivity {


    private TextView title,month,detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        //variables to display the detail of each post
        title = (TextView) findViewById(R.id.post_title);
        month = (TextView) findViewById(R.id.post_month);
        detail = (TextView) findViewById(R.id.post_detail);

        //get selected Post from intent and display the detail
        Intent i = getIntent();
        Post p = (Post) i.getSerializableExtra("Selected Post");

        title.setText(p.getTitle());
        month.setText(p.getMonth());
        detail.setText(p.getDetail());
        detail.setText(p.getDetail());
    }
}
