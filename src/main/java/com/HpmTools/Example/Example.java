package com.HpmTools.Example;

import com.HpmTools.core.HpCommandLine;

import java.io.IOException;

/**
 * 测试
 */
public class Example
{
    public static void main(String[] args) throws IOException, InterruptedException {
        args=new String[]{"-o","http://127.0.0.1:8400/","-m","GET","-p","100","-c","10"};
        HpCommandLine.StartCmd(args);

    }
}
