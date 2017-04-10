package com.nmid.headline.data.source.remote;

/**
 * Created by xwysu on 2017/4/10.
 */

public class HttpMethods {

    private HttpMethods(){

    }
    private static class SingletonHolder{
        public static final HttpMethods sInstance=new HttpMethods();
    }
    public static HttpMethods getInstance(){
        return SingletonHolder.sInstance;
    }

}
