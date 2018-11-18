package itp341.lee.woonghee.finalproject.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itp341.lee.woonghee.finalproject.Model.Post;
import itp341.lee.woonghee.finalproject.Model.UserDbHelper;
import itp341.lee.woonghee.finalproject.R;

public class CreatePostActivity extends AppCompatActivity {

    private EditText title, month, detail;
    private Button save_post_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        title = (EditText) findViewById(R.id.post_title);
        month = (EditText) findViewById(R.id.post_month);
        detail = (EditText) findViewById(R.id.post_detail);
        save_post_button = (Button) findViewById(R.id.save_post_button);

        //when you save, store it to array and database
        save_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().isEmpty() ||
                        month.getText().toString().isEmpty()||
                        detail.getText().toString().isEmpty()){
                    return;
                }
                //save a post to the database
                String post_title = title.getText().toString();
                String post_detail = detail.getText().toString();
                String post_month = month.getText().toString();
                Post post = new Post(post_title, post_detail, post_month );
                //add to the database
                UserDbHelper.getInstance(getApplicationContext()).add_post_to_database(post);

                setResult(Activity.RESULT_OK);
                finish(); //close this page

            }
        });
    }
}
