package com.example.smahe.carnotassignment.databasefiles;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by smahe on 11/15/2017.
 */
@Table(name = "Photos")
public class ImagesModelClass extends Model{

    public ImagesModelClass()
    {
        super();
    }
    @Column(name = "title")
    public String title;
    @Column(name = "url")
    public String url;
    @Column(name = "thumbnailUrl")
    public String thumbnailUrl;
    @Column(name = "albumId")
    public Integer albumId;
    @Column(name = "photoId")
    public Integer phpotoId;
}
