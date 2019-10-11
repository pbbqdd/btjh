package me.aniceday.btjh;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
//import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private String[] d = {"export CLASSPATH=/data/local/tmp/.knife/inject.jar\n","exec app_process /data/local/tmp/.knife com.coffee.injectmotionevent.main.InjectMotionEvent 0 200 300 &\n","exec /data/local/tmp/knife_server &\n"};
    private String[] s = {"kill -9 $(pidof com.zuoyou.inject)\n","kill -9 $(pidof knife_server)\n"};
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



    public Boolean check_server() {
        try {
            Process localProcess = Runtime.getRuntime().exec("su -c pidof com.zuoyou.inject");
            BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
            String s;
            if(in.readLine()==null){
                return false;
            }else{
                return true;
            }



        } catch (Exception p) {
            p.printStackTrace();
            return false;
        }
    }
    private void buttenStats(Boolean s,FloatingActionButton fab){
        if(s){
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("red")));
            fab.setImageResource(android.R.drawable.ic_media_pause);


        }else {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_blue_light)));
            fab.setImageResource(android.R.drawable.ic_media_play);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        buttenStats(check_server(),fab);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (check_server().toString()){
                            case "false":
                                fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("red")));
                                fab.setImageResource(android.R.drawable.ic_media_pause);
                                cmd("su",d);
                                break;
                            case "true":
                                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_blue_light)));
                                fab.setImageResource(android.R.drawable.ic_media_play);
                                cmd("su",s);

                        }


                    }
                });
                // Code to be executed when an ad finishes loading.
//


            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
//                fab.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        switch (check_server().toString()){
//                            case "false":
//                                fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("red")));
//                                fab.setImageResource(android.R.drawable.ic_media_pause);
//                                cmd("su",d);
//                                break;
//                            case "true":
//                                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_blue_light)));
//                                fab.setImageResource(android.R.drawable.ic_media_play);
//                                cmd("su",s);
//
//                        }
//
//
//                    }
//                });
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();

    }
}
