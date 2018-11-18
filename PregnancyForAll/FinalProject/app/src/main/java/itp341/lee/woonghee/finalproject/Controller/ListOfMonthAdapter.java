package itp341.lee.woonghee.finalproject.Controller;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itp341.lee.woonghee.finalproject.R;

/**
 * Created by WoongHee on 12/4/2016.
 */
//custom adapter
public class ListOfMonthAdapter extends ArrayAdapter<String> {
    //get context and store lists of months
    private ArrayList<String> months;
    private Context context;
    //overloaded constructor
    public ListOfMonthAdapter(Context context, int resourceId, ArrayList<String> months) {
        super(context, resourceId, months);
        this.months = months;
        this.context = context;
    }
    //change the view of each item in listview
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            LayoutInflater li = LayoutInflater.from(context);
            convertView = li.inflate(R.layout.fragment_custom_month, null);
        }
        String m = months.get(position);
        TextView month = (TextView) convertView.findViewById(R.id.title);
        month.setText(m);

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        if(position<3) {
            image.setImageResource(R.drawable.month1);
        }else if(position<6){
            image.setImageResource(R.drawable.month2);
        }else {
            image.setImageResource(R.drawable.month3);
        }
        return convertView;
    }
}
