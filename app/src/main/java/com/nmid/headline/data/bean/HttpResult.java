package com.nmid.headline.data.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwysu on 2017/4/11.
 */

public class HttpResult<T> {
    @SerializedName("data")
    @Expose
    private List<T> data = new ArrayList<T>();
    @SerializedName("code")
    @Expose
    private Integer code;

    /**
     * @return The freshNews
     */
    public List<T> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }
}
