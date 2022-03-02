package com.HpmTools.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author yh
 * 程序入口 处理命令
 */
public class HpCommandLine {
    interface ICommand{
        void Cmd(HpmCore hpmCore,String val) throws IOException;
    }

    /**
     * 保存如何处理某个参数
     */
    private HashMap<String,ICommand> CmdMap=new HashMap<>();
    {
        CmdMap.put("-o",CmdFunc::UseHost);
        CmdMap.put("-m",CmdFunc::UseMethod);
        CmdMap.put("-p",CmdFunc::SetPmCount);
        CmdMap.put("-c",CmdFunc::SetConCount);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        args=new String[]{"-o","http://127.0.0.1:5000/test","-m","GET","-p","100","-c","10"};
        if (args.length==0){
            CmdFunc.GetHerper();
            return;
        }
        HpCommandLine line=new HpCommandLine();
        HpmCore core=new HpmCore();
        for (int i=0;i<args.length-1;i+=2){
            line.CmdMap.get(args[i]).Cmd(core,args[i+1]);
        }

        core.StartManometry();

    }

}
