package com.example.smahe.carnotassignment.URLMainClasses;

/**
 * Created by smahe on 11/14/2017.
 */

public class CommentsClass {

    Integer postid,id;
    String emailid,name,body;
    public  CommentsClass(String name,String email,String body,Integer postid,Integer id)
    {
        this.name=name;
        this.body=body;
        this.emailid=email;
        this.postid=postid;
        this.id=id;
    }
    public void setpostId(Integer postid)
    {
        this.postid=postid;
    }
    public void setId(Integer id)
    {
        this.id=id;

    }
    public void setEmailid(String emailid)
    {
        this.emailid=emailid;
    }
    public  void setName(String name)
    {
        this.name=name;
    }
    public  void setBody(String emailbody)
    {
        this.body=emailbody;
    }
    public String getEmailid()
    {
        return this.emailid;
    }
    public String getName()
    {
        return this.name;
    }
    public String getBody()
    {
        return this.body;

    }

    public Integer getPostid()
    {
        return this.postid;
    }
    public Integer getId()
    {
        return this.id;
    }
}
