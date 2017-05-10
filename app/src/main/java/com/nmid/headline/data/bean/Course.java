package com.nmid.headline.data.bean;

/**
 * Created by xwysu on 2017/4/12.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course implements Serializable{

    @SerializedName("hash_day")
    @Expose
    private Integer hashDay;
    @SerializedName("hash_lesson")
    @Expose
    private Integer hashLesson;
    @SerializedName("begin_lesson")
    @Expose
    private Integer beginLesson;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("lesson")
    @Expose
    private String lesson;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("teacher")
    @Expose
    private String teacher;
    @SerializedName("classroom")
    @Expose
    private String classroom;
    @SerializedName("rawWeek")
    @Expose
    private String rawWeek;
    @SerializedName("weekModel")
    @Expose
    private String weekModel;
    @SerializedName("weekBegin")
    @Expose
    private Integer weekBegin;
    @SerializedName("weekEnd")
    @Expose
    private Integer weekEnd;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("week")
    @Expose
    private List<Integer> week = null;

    public Integer getHashDay() {
        return hashDay;
    }

    public void setHashDay(Integer hashDay) {
        this.hashDay = hashDay;
    }

    public Integer getHashLesson() {
        return hashLesson;
    }

    public void setHashLesson(Integer hashLesson) {
        this.hashLesson = hashLesson;
    }

    public Integer getBeginLesson() {
        return beginLesson;
    }

    public void setBeginLesson(Integer beginLesson) {
        this.beginLesson = beginLesson;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getRawWeek() {
        return rawWeek;
    }

    public void setRawWeek(String rawWeek) {
        this.rawWeek = rawWeek;
    }

    public String getWeekModel() {
        return weekModel;
    }

    public void setWeekModel(String weekModel) {
        this.weekModel = weekModel;
    }

    public Integer getWeekBegin() {
        return weekBegin;
    }

    public void setWeekBegin(Integer weekBegin) {
        this.weekBegin = weekBegin;
    }

    public Integer getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(Integer weekEnd) {
        this.weekEnd = weekEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getWeek() {
        return week;
    }

    public void setWeek(List<Integer> week) {
        this.week = week;
    }

}
