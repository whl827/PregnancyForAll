package itp341.lee.woonghee.finalproject.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by WoongHee on 12/5/2016.
 */

//Retrieving and Seding data from/to sql database
public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;

    private static UserDbHelper instance;
    SQLiteDatabase sqLiteDatabase;

    private ArrayList<MonthDetail> monthDetails;
    private ArrayList<Post> postList;

    //way to create table for users
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + UserInfo.NewUserInfo.TABLE_NAME+"( "
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserInfo.NewUserInfo.USER_FIRST_NAME+" TEXT, "
                    + UserInfo.NewUserInfo.USER_LAST_NAME+" TEXT, "
                    + UserInfo.NewUserInfo.USER_EMAIL+" TEXT, "
                    + UserInfo.NewUserInfo.USER_PASSWORD+" TEXT);";

    //way to create table for post
    private static final String CREATE_POST_TABLE =
            "CREATE TABLE " + UserInfo.NewPostInfo.TABLE_NAME+"( "
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserInfo.NewPostInfo.POST_TITLE+" TEXT, "
                    + UserInfo.NewPostInfo.POST_DETAIL+" TEXT, "
                    + UserInfo.NewPostInfo.POST_MONTH+" TEXT);";

    public static synchronized UserDbHelper getInstance(Context context){
        if(instance==null){
            instance = new UserDbHelper(context);
        }
        return instance;
    }

    private UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    //if database is not already exist. For first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_POST_TABLE);
    }

    //TODO FOR USERS

    //insert data for user
    public void add_user_to_database(String fName, String lName, String email, String password){
        //add each info to contentvalue
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfo.NewUserInfo.USER_FIRST_NAME,fName);
        contentValues.put(UserInfo.NewUserInfo.USER_LAST_NAME,lName);
        contentValues.put(UserInfo.NewUserInfo.USER_EMAIL,email);
        contentValues.put(UserInfo.NewUserInfo.USER_PASSWORD,password);
        sqLiteDatabase.insert(UserInfo.NewUserInfo.TABLE_NAME, null,contentValues);
    }
    //TODO FOR POSTS

    //insert data for user
    public void add_post_to_database(Post post){

        //add each info to contentvalue
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfo.NewPostInfo.POST_TITLE, post.getTitle());
        contentValues.put(UserInfo.NewPostInfo.POST_DETAIL,post.getDetail());
        contentValues.put(UserInfo.NewPostInfo.POST_MONTH, post.getMonth());
        sqLiteDatabase.insert(UserInfo.NewPostInfo.TABLE_NAME, null,contentValues);
        add_post_to_arrayList(post);
    }
    public void add_post_to_arrayList(Post post){
        if(postList==null){
            postList = new ArrayList<>();
        }
        postList.add(post);
    }
    public void add_posts_from_database_to_arrayList(){

        //get the password from database if user email exist
        Cursor cursor = sqLiteDatabase.query(UserInfo.NewPostInfo.TABLE_NAME, //table name
                new String[] {UserInfo.NewPostInfo.POST_TITLE, UserInfo.NewPostInfo.POST_DETAIL, UserInfo.NewPostInfo.POST_MONTH}, //what you want to get
                null,null,null, null, null);

        while(cursor.moveToNext()){

            String title = cursor.getString(cursor.getColumnIndex(UserInfo.NewPostInfo.POST_TITLE));
            String detail = cursor.getString(cursor.getColumnIndex(UserInfo.NewPostInfo.POST_DETAIL));
            String month = cursor.getString(cursor.getColumnIndex(UserInfo.NewPostInfo.POST_MONTH));
            Post p = new Post(title, detail, month);
            postList.add(p);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERINFO;");
        onCreate(db);
    }
    //check if the email already exists in the database when trying to sign up
    public boolean userExists(String email){
        Cursor cursor = sqLiteDatabase.query(UserInfo.NewUserInfo.TABLE_NAME,
                                new String[] {UserInfo.NewUserInfo.USER_EMAIL},
                                UserInfo.NewUserInfo.USER_EMAIL+"=?",
                                new String[]{email},
                                null, null, null);
        boolean exists = (cursor.getCount()>0);
        cursor.close();
        return exists;
        //true => exists, false => doens't exists
    }
    //check is user exists in database
    public boolean validUser(String email, String password){

        //check if email exists in the database first
        if(!userExists(email)){
           // close();
            return false;
        }
        //get the password from database if user email exist
        Cursor cursor = sqLiteDatabase.query(UserInfo.NewUserInfo.TABLE_NAME, //table name
                            new String[] {UserInfo.NewUserInfo.USER_PASSWORD}, //what you want to get
                            UserInfo.NewUserInfo.USER_EMAIL+"=?", //what you want to compare
                            new String[]{email}, ///actual value you want to compare
                            null, null, null);
        //store the password in to string
        String password_in_database = null;
        while(cursor.moveToNext()){
            password_in_database = cursor.getString(cursor.getColumnIndex(
                                                    UserInfo.NewUserInfo.USER_PASSWORD));
        }
        //check if password is correct
        if(!password.equals(password_in_database)){
            cursor.close();
            //close();
            return false;
        }
        cursor.close();
        return true; //every condition is met, valid user
    }
    //add month detail to sqlite
    public void addMonthDetail(){

        for(int i=1; i<=9; i++) {
            MonthDetail m = new MonthDetail();
            switch(i){
                case 1: m.setBody(PregnancyInfo.pregnancyInfo.M1_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M1_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M1_DIET); break;
                case 2: m.setBody(PregnancyInfo.pregnancyInfo.M2_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M2_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M2_DIET); break;
                case 3: m.setBody(PregnancyInfo.pregnancyInfo.M3_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M3_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M3_DIET); break;
                case 4: m.setBody(PregnancyInfo.pregnancyInfo.M4_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M4_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M4_DIET); break;
                case 5: m.setBody(PregnancyInfo.pregnancyInfo.M5_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M5_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M5_DIET); break;
                case 6: m.setBody(PregnancyInfo.pregnancyInfo.M6_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M6_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M6_DIET); break;
                case 7: m.setBody(PregnancyInfo.pregnancyInfo.M7_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M7_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M7_DIET); break;
                case 8: m.setBody(PregnancyInfo.pregnancyInfo.M8_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M8_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M8_DIET); break;
                case 9: m.setBody(PregnancyInfo.pregnancyInfo.M9_BODY);
                    m.setExercise(PregnancyInfo.pregnancyInfo.M9_EXERCISE);
                    m.setDiet(PregnancyInfo.pregnancyInfo.M9_DIET); break;
            }
            monthDetails.add(m);
        }
    }
    //get array list of month details
    public ArrayList<MonthDetail> getMonthDetails(){
        if(monthDetails == null){
            monthDetails = new ArrayList<>();
            addMonthDetail();
        }
        return monthDetails;
    }
    //get array list of post
    public ArrayList<Post> getPostList(){
        if(postList==null){
            postList = new ArrayList<>();
            add_posts_from_database_to_arrayList();
        }
        return postList;
    }
    public void close(){
        close();
    }
}