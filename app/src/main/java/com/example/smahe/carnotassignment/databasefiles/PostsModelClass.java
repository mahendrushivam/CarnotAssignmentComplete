package com.example.smahe.carnotassignment.databasefiles;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by smahe on 11/15/2017.
 */
@Table(name = "Posts")
public class PostsModelClass extends Model {
    public PostsModelClass()
    {
        super();
    }
@Column(name = "title")
public String title;
    @Column(name = "body")
    public String body;
    @Column(name = "postId")
    public Integer postId;
    @Column(name = "userId")
    public Integer userId;




}
