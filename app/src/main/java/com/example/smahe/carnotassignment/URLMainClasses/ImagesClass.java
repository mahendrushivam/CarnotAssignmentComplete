package com.example.smahe.carnotassignment.URLMainClasses;

/**
 * Created by smahe on 11/14/2017.
 */

public class ImagesClass {
    String title,url,thumbnailurl;
    Integer albumid,id;
    public ImagesClass()
    {}
    public ImagesClass(String title,String url,String thumbnail,Integer albumid,Integer id)
    {
        this.id=id;
        this.albumid=albumid;
        this.thumbnailurl=thumbnail;
        this.title=title;
        this.url=url;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public  void setUrl(String url)
    {
        this.url=url;
    }
    public void setThumbnailurl(String thumbnailurl)
    {
        this.thumbnailurl=thumbnailurl;
    }
    public void setAlbumid(Integer albumid)
    {
        this.albumid=albumid;
    }
    public  void setId(Integer id)
    {
        this.id=id;
    }
    public Integer getAlbumid()
    {
        return this.albumid;
    }
    public Integer getId()
    {
        return this.id;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getUrl()
    {
        return this.url;
    }
    public String getThumbnailurl()
    {
        return this.thumbnailurl;
    }


}
