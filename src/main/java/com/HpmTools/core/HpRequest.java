package com.HpmTools.core;

import com.sun.net.httpserver.Headers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yh
 * 用于对目标域进行请求
 */
public class HpRequest {


    static class ResponBody{
        /**
         * 是否请求成功
         */
        boolean IsSucess;
        /**
         * 请求时间
         */
        int TimeLen;

        public boolean isSucess() {
            return IsSucess;
        }

        public int getTimeLen() {
            return TimeLen;
        }

        public ResponBody(boolean isSucess, int timeLen) {
            IsSucess = isSucess;
            TimeLen = timeLen;
        }
    }
    /**
     * 开始进行网络请求
     * @param urlConnection
     */
    public static ResponBody StartRequst(HttpURLConnection urlConnection) throws IOException {

        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(4000);
        urlConnection.connect();
        return new ResponBody(urlConnection.getResponseCode()==200,100);
    }
}
