package com.nmid.headline.data.bean;

/**
 * Created by xwysu on 2017/4/24.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseResult<T> {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("stuNum")
    @Expose
    private String stuNum;
    @SerializedName("cachedTimestamp")
    @Expose
    private String cachedTimestamp;
    @SerializedName("outOfDateTimestamp")
    @Expose
    private String outOfDateTimestamp;
    @SerializedName("data")
    @Expose
    private List<T> data = null;
    @SerializedName("nowWeek")
    @Expose
    private Integer nowWeek;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getCachedTimestamp() {
        return cachedTimestamp;
    }

    public void setCachedTimestamp(String cachedTimestamp) {
        this.cachedTimestamp = cachedTimestamp;
    }

    public String getOutOfDateTimestamp() {
        return outOfDateTimestamp;
    }

    public void setOutOfDateTimestamp(String outOfDateTimestamp) {
        this.outOfDateTimestamp = outOfDateTimestamp;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getNowWeek() {
        return nowWeek;
    }

    public void setNowWeek(Integer nowWeek) {
        this.nowWeek = nowWeek;
    }

}
