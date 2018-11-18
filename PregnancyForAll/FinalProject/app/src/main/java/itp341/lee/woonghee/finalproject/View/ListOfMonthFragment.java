package itp341.lee.woonghee.finalproject.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import itp341.lee.woonghee.finalproject.Controller.ListOfMonthAdapter;
import itp341.lee.woonghee.finalproject.Model.MonthDetail;
import itp341.lee.woonghee.finalproject.Model.UserDbHelper;
import itp341.lee.woonghee.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListOfMonthFragment extends Fragment {

    private ArrayList<String> months;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<MonthDetail> monthDetails;

    public ListOfMonthFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_of_week, container, false);

        list = (ListView) v.findViewById(R.id.listView);

        //build new arraylist for title of development page
        months = new ArrayList<>();
        //create list of month
        for(int i=1; i<=9; i++){
            months.add("Month"+i);
        }
        //get array list of from database singleton
        monthDetails = UserDbHelper.getInstance(getActivity().getApplicationContext()).getMonthDetails();

        //set custom adapter
        adapter = new ListOfMonthAdapter(getActivity(), 0, months);
        list.setAdapter(adapter);

        //load a new activity with information based on which one in the list was clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //need to pass data
                MonthDetail m = monthDetails.get(i);
                Intent intent = new Intent(getActivity().getApplicationContext(), MonthDetailActivity.class);
                intent.putExtra("Selected Month", m);
                startActivity(intent);
            }
        });
        return v;
    }
}