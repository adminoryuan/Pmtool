package com.HpmTools.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yh
 * 实现线程开辟等功能
 */
public class HpmCore {

    private  int PMCount=1000; //默认100个请求
    private  int ConConut=100; //默认并发
    private URL url;
    private String Method;

    private static final List<String> RequstMethod   = Arrays.asList("POST","GET","PUT","DELETE") ;

    /**
     * 设置最大请求量
     * @param count
     */
    public  void SetPmCount(int count){
        PMCount=count;
    }

    /**
     * 设置单次请求量
     * @param conConut
     */
    public void ConCount(int conConut){
        this.ConConut=conConut;
    }

    /**
     *
     * @param PMCount 总请求数量
     * @param conConut 并发数量
     * @param url 请求目标地址
     */
    public HpmCore(int PMCount, int conConut,URL url) {
        this.PMCount = PMCount;
        ConConut = conConut;
    }


    public HpmCore() {

    }
    public void SetUri(URL _url){
        url=_url;
    }

    /**
     * 添加请求头
     * @param method
     * @throws IOException
     */
    public void  AddMethod(String method) throws IOException {


        if (!RequstMethod.contains(method)){
            throw new RuntimeException("参数错误，请填写正确的参数");
        }

        this.Method=method;
    }


    private HttpURLConnection GetUrlConnection(){
        try {
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(Method);
            return connection;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 开始测压
     */
    public void StartManometry() throws InterruptedException {
        List<HpRequest.ResponBody> list=new LinkedList<>();

        ExecutorService service = Executors.newFixedThreadPool(ConConut+1);
        for (int i=0;i<PMCount/ConConut;i++) {
            CountDownLatch Latch = new CountDownLatch(ConConut);
           for (int j = 0; j < ConConut; j++) {
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HpRequest.ResponBody body = HpRequest.StartRequst(GetUrlConnection());
                            Latch.countDown();
                            synchronized (list) {
                                list.add(body);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
             Latch.await();
            System.out.println("第"+i+"轮并发");
        }
        System.out.println("执行完毕");
    }

}
