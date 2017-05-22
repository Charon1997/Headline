package com.nmid.headline.data.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by xwysu on 2017/4/8.
 */

public class Teacher implements Serializable{
    @SerializedName("resume")
    @Expose
    private Object resume;
    @SerializedName("home_page")
    @Expose
    private String homePage;
    @SerializedName("teacherInfo_pid")
    @Expose
    private Integer teacherInfoPid;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("name")
    @Expose
    private String name;

    public Object getResume() {
        return resume;
    }

    public void setResume(Object resume) {
        this.resume = resume;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public Integer getTeacherInfoPid() {
        return teacherInfoPid;
    }

    public void setTeacherInfoPid(Integer teacherInfoPid) {
        this.teacherInfoPid = teacherInfoPid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
