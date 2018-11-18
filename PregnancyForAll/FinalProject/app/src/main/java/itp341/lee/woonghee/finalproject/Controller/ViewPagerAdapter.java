package itp341.lee.woonghee.finalproject.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import itp341.lee.woonghee.finalproject.View.ListOfMonthFragment;
import itp341.lee.woonghee.finalproject.View.UserPostFragment;

/**
 * Created by WoongHee on 11/29/2016.
 */
//Fragment Pager Adapter for view pager for the Main User Activity
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String fragments[]; //how many tabs
    private ListOfMonthFragment listOfWeek; //1 tab
    private UserPostFragment userPost; //2 tab
    //constructor
    public ViewPagerAdapter(FragmentManager supportFragmentManger, String[] fragments) {
        super(supportFragmentManger);
       this.fragments = fragments;
    }
    //based on where user click or slide, open the appropriate fragmnet
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                if(listOfWeek==null){
                    listOfWeek = new ListOfMonthFragment();
                }
                return listOfWeek;
            case 1:
                if(userPost==null){
                    userPost = new UserPostFragment();
                }
                return userPost;
        }
        return null;
    }
    @Override
    public int getCount() {
        return fragments.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}