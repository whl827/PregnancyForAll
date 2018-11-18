package itp341.lee.woonghee.finalproject.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import itp341.lee.woonghee.finalproject.R;

public class TermsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    //display the temrs of service
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //set the title of tool bar
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setTitle(getResources().getString(R.string.terms_of_service));
        setSupportActionBar(mToolbar);
    }
}
