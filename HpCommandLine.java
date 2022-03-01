package HHpm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author yh
 * 程序入口 处理命令
 */
public class HpCommandLine {


    public static void main(String[] args) throws IOException, InterruptedException {

        HpmCore core = new HpmCore();
        core.SetUri(new URL("http://127.0.0.1:5000/test"));
        core.AddMethod("GET");
        core.StartManometry();

    }

}
