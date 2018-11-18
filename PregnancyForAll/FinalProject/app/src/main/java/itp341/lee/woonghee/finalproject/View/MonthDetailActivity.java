package itp341.lee.woonghee.finalproject.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import itp341.lee.woonghee.finalproject.Model.MonthDetail;
import itp341.lee.woonghee.finalproject.R;

public class MonthDetailActivity extends AppCompatActivity {

    //variables to display the details of each month
    private TextView body_detail;
    private TextView exercise_detail;
    private TextView diet_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_detail);

        body_detail = (TextView) findViewById(R.id.body_detail) ;
        exercise_detail = (TextView) findViewById(R.id.exercise_detail) ;
        diet_detail = (TextView) findViewById(R.id.diet_detail) ;

        //get data from intent and display it
        Intent i = getIntent();
        MonthDetail monthDetail = (MonthDetail) i.getSerializableExtra("Selected Month");
        body_detail.setText(monthDetail.getBody());
        exercise_detail.setText(monthDetail.getExercise());
        diet_detail.setText(monthDetail.getDiet());

    }
}
