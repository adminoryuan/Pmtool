package com.HpmTools.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CmdFunc {

    /**
     * 指定请求的目标地址
     * @param hpmCore
     * @param val
     */
    static void UseHost(HpmCore hpmCore,String val) throws MalformedURLException {

            hpmCore.SetUri(new URL(val));

    }

    /**
     * 指定请求的方法 只指针POST GET PUT DELETE
     * @param hpmCore
     * @param val
     * @throws IOException
     */
    static void UseMethod(HpmCore hpmCore,String val) throws IOException {
        hpmCore.AddMethod(val);
    }

    /**
     * 设置请求头
     * @param hpmCore
     * @param val
     */
    static void Setheaders(HpmCore hpmCore,String val){

    }

    /**
     * 设置单次请求并发
     * @param hpmCore
     * @param val
     */
    static void SetConCount(HpmCore hpmCore,String val){
        int ConCount=Integer.parseInt(val);
        hpmCore.ConCount(ConCount);
    }

    /**
     * 设置总请求量
     * @param hpmCore
     * @param val
     */
    static void SetPmCount(HpmCore hpmCore,String val){
        int pmCount=Integer.parseInt(val);

        hpmCore.SetPmCount(pmCount);
    }
    /**
     * 获取帮助
     */
    static void GetHerper(){
        System.out.println("-o      指定的你的url");
        System.out.println("-m      指定请求方法");
        System.out.println("-p      指定总请求量");
        System.out.println("-c      指定并发量");
        System.out.println("-h      添加请求头使用,隔开");
        System.out.println("-d      指定请求参数");
    }
}
