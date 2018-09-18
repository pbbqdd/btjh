package me.aniceday.btjh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
//import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    private String[] d = {"export CLASSPATH=/data/local/tmp/.knife/inject.jar\n", "export ANDROID_DATA=/data/local/tmp/.knife\n", "exec app_process /data/local/tmp/.knife com.coffee.injectmotionevent.main.InjectMotionEvent 0 200 300 &\n","export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n"};
    private void a(String paramString)
    {
        try{
            Process localProcess = Runtime.getRuntime().exec(paramString);
            DataOutputStream param = new DataOutputStream(localProcess.getOutputStream());
            for(int i =0;i<d.length;i++)
                {
                    param.writeBytes(d[i].toString());
                }
            param.writeBytes("export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n");
            param.writeBytes("exit\n");
            param.flush();
            localProcess.waitFor();
        }
        catch(Exception p) {
            p.printStackTrace();
            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "已运行", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                a("su");
            }
        });
    }
}
