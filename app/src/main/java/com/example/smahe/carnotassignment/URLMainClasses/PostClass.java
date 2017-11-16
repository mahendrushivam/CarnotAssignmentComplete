package com.example.smahe.carnotassignment.URLMainClasses;

/**
 * Created by smahe on 11/15/2017.
 */

public class PostClass {
    Integer userId;
    Integer id;
    String body, title;

    public PostClass(String title, String body, Integer userid, Integer postid) {
        this.title = title;
        this.body = body;
        this.userId = userid;
        this.id = id;


    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public String getTitle() {
        return this.title;
    }
    public Integer getId()
    {
        return this.id;
    }
}
