package com.nmid.headline.data.source.remote;

import com.nmid.headline.data.bean.Course;
import com.nmid.headline.data.bean.CourseResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by xwysu on 2017/4/12.
 */

public interface CourseHttpService {
    String END_POINT="http://hongyan.cqupt.edu.cn/";
    String STU_NUM="stuNum";
    String STU_ID="idNum";

    @FormUrlEncoded
    @POST("/redapi2/api/kebiao")
    Call<CourseResult<Course>>getCourse(@Field(STU_NUM) String stuNum, @Field(STU_ID) String stuId);
}
