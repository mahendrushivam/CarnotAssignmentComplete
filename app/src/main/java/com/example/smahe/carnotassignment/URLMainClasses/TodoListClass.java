package com.example.smahe.carnotassignment.URLMainClasses;

/**
 * Created by smahe on 11/14/2017.
 */

public class TodoListClass {
    String title;
    boolean completed;
    Integer userid,todoid;

public TodoListClass()
{

}
    public TodoListClass(String title,Integer userid,Integer todoid,boolean completed)
    {
        this.todoid=todoid;
        this.userid=userid;
        this.title=title;
        this.completed=completed;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setCompleted(boolean completed)
    {
        this.completed=completed;
    }
    public void setUserid(Integer userid)
    {
        this.userid=userid;
    }
    public void setTodoid(Integer todoid)
    {
        this.todoid=todoid;
    }
    public String getTitle()
    {
        return this.title;
    }
    public boolean getCompleted()
    {
        return this.completed;
    }
    public Integer getUserid()
    {
        return this.userid;
    }
    public Integer getTodoid()
    {
        return this.todoid;
    }

}
