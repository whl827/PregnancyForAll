package itp341.lee.woonghee.finalproject.Model;

import java.io.Serializable;

/**
 * Created by WoongHee on 11/29/2016.
 */
public class Post implements Serializable{

    //variables for post
    private String title;
    private String detail;
    private String month;

    //default constructor and constructor with title and inquiry
    public Post(){}
    public Post(String title, String detail, String month){
        this.title = title;
        this.detail = detail;
        this.month = month;
    }
    //getters and setters for post info
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }

}
