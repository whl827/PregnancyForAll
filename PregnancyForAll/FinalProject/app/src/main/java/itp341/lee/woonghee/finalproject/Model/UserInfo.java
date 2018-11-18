package itp341.lee.woonghee.finalproject.Model;

/**
 * Created by WoongHee on 12/5/2016.
 */

//final constants used for sqlite database
public class UserInfo {

    public static abstract class NewUserInfo{
        //Constant sql database
        public static final String USER_FIRST_NAME = "user_first_name";
        public static final String USER_LAST_NAME = "user_last_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_PASSWORD = "user_password";
        public static final String TABLE_NAME = "USERINFO";

    }

    public static abstract class NewPostInfo{
        //Constant sql database
        public static final String POST_TITLE = "post_title";
        public static final String POST_DETAIL = "post_detail";
        public static final String POST_MONTH = "post_month";
        public static final String TABLE_NAME = "POSTINFO";
    }


}
