package me.aniceday.btjh;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

class Beitong {

    private static String[] d = {"export CLASSPATH=/data/local/tmp/.knife/inject.jar\n","exec app_process /data/local/tmp/.knife com.coffee.injectmotionevent.main.InjectMotionEvent 0 200 300 &\n" ,"exec /data/local/tmp/knife_server &\n"};
    private static String[] s = {"kill -9 $(pidof com.zuoyou.inject)\n","kill -9 $(pidof knife_server)\n"};

    private Process localProcess;


    private void cmd(String paramString, String[] cmdString) {
        try {
            localProcess = Runtime.getRuntime().exec(paramString);
            DataOutputStream param = new DataOutputStream(localProcess.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
            for (String i:cmdString) {
                param.writeBytes(i);
//                Log.d("print", i);
            }

            param.flush();

        } catch (Exception p) {
            p.printStackTrace();
        }
    }

    public Process getLocalProcess() {
        return localProcess;
    }

    protected static PlayAndPause check_server() {//服务检查，如果启动1，非启动0；
        try {
            Process localProcess = Runtime.getRuntime().exec("su -c pidof com.zuoyou.inject && su -c pidof knife_server");
            BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
            if (in.readLine() == null) {


                return PlayAndPause.PAUSE;

            } else {
//                Log.d("print", thisline);
//                while ((thisline =in.readLine())!= null){
//                    Log.d("print",in.readLine());
//                }

                return PlayAndPause.PLAY;
//                Log.d("print","dsjfksl"+String.valueOf(in.readLine()));

            }


        } catch (Exception p) {
            p.printStackTrace();
            return PlayAndPause.PAUSE;
        }
    }

    protected void butten(boolean e) {
        if (e) {
//            Log.d("print","stop");
            cmd("su", s);//stop

        } else {
//            Log.d("print","start");
            cmd("su", d);
            //start


        }
    }

}