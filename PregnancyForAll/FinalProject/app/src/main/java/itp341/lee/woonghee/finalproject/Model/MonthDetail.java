package itp341.lee.woonghee.finalproject.Model;

import java.io.Serializable;

/**
 * Created by WoongHee on 12/5/2016.
 */
public class MonthDetail implements Serializable{

    //what each month contains
    private String body;
    private String exercise;
    private String diet;

    public MonthDetail(){} //default constructor
    //constructor with info
    public MonthDetail(String body, String exercise, String diet){
        this.body = body;
        this.exercise = exercise;
        this.diet = diet;
    }
    //getters and setters
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

}
