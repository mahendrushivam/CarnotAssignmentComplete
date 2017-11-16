package com.example.smahe.carnotassignment.databasefiles;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by smahe on 11/15/2017.
 */
@Table(name = "Comments")
public class CommentsModelClass extends Model {
public CommentsModelClass()
{
    super();
}
    @Column(name = "name")
    public String name;
    @Column(name = "email")
    public String email;
    @Column(name = "body")
    public String body;
    @Column(name = "commentId")
    public Integer commentId;
    @Column(name = "postId")
    public Integer postId;
}
