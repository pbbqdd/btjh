package me.ioiu.btjh;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class Beitong {

    private static String[] d = {"export CLASSPATH=/data/local/tmp/.knife/inject.jar\n", "exec app_process /data/local/tmp/.knife com.coffee.injectmotionevent.main.InjectMotionEvent 0 200 300 &\n", "exec /data/local/tmp/knife_server &\n"};
    private static String[] s = {"kill -9 $(pidof com.zuoyou.inject)\n", "kill -9 $(pidof knife_server)\n"};


    private void cmd(String paramString, String[] cmdString) {
        try {
            Process localProcess = Runtime.getRuntime().exec(paramString);
            DataOutputStream param = new DataOutputStream(localProcess.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
            for (int i = 0; i < cmdString.length; i++) {
                param.writeBytes(cmdString[i].toString());
            }
            param.flush();
//            localProcess.waitFor();

        } catch (Exception p) {
            p.printStackTrace();
        }
    }

    public static boolean check_server() {
        try {
            Process localProcess = Runtime.getRuntime().exec("su -c pidof com.zuoyou.inject");
            BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
            String s;
            if (in.readLine() == null) {
                return false;
            } else {
                return true;
            }


        } catch (Exception p) {
            p.printStackTrace();
            return false;
        }
    }

    public void butten(boolean e){
        if (e){
            cmd("su",s);
        }else {
            cmd("su",d);
        }
    }

}