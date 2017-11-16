package com.example.smahe.carnotassignment.URLMainClasses;

/**
 * Created by smahe on 11/14/2017.
 */

public class URLInfoClass {

    String startTime,endTime,savedEndTime,savedStartTime;
    public URLInfoClass(){}
    public URLInfoClass(String endTime,String startTime,String savedStartTime,String savedEndTime)
    {
        this.endTime=endTime;
        this.savedEndTime=savedEndTime;
        this.startTime=startTime;
        this.savedStartTime=savedStartTime;
    }
    public String getStartTime()
    {
        return this.startTime;
    }
    public String getEndTime()
    {
        return this.endTime;
    }
    public String getSavedEndTime()
    {
        return  this.savedEndTime;
    }
    public String getSavedStartTime()
    {
        return this.savedStartTime;
    }
    public void setStartTime(String startTime)
    {
        this.startTime=startTime;
    }
    public  void  setEndTime(String endTime)
    {
        this.endTime=endTime;
    }
    public  void setSavedEndTime(String savedEndTime)
    {
        this.savedEndTime=savedEndTime;
    }
    public void setSavedStartTime(String savedStartTime)
    {
        this.savedStartTime=savedStartTime;
    }


}
