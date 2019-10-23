package me.aniceday.btjh;


import android.os.Bundle;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;



import com.google.android.gms.ads.AdRequest;
//import android.view.Menu;

public class MainActivity extends AppCompatActivity  {
    private AdView mAdView;
    public int text= R.string.text_view;
    private  TextView textView;
    private  static PlayAndPause serverFlag = PlayAndPause.PAUSE;

    protected void setText(String text){
        textView.setText(text);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.showtext);
        setSupportActionBar(toolbar);
        BattenStatus fab = findViewById(R.id.fab);
        serverFlag=Beitong.check_server();
        fab.checkbattenStatus(serverFlag.isaBoolean());//初始化检查
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                Log.d("print",serverFlag.toString());
                if (!serverFlag.isaBoolean()) {//运行中需要关掉
//                    textView.setText(fab.battenStatus("play"));
                    setText("服务待激活");
                    new Beitong().butten(serverFlag.isaBoolean());
                    try {

                        Thread.currentThread().sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    serverFlag =Beitong.check_server();
                    if(serverFlag.isaBoolean()){
                        setText("激活服务成功");
                        fab.battenStatus(PlayAndPause.PAUSE.getType());
                    }else {
                        setText("激活服务失败，请使用电脑端先激活一次");
                    }
                } else {
//                    textView.setText(fab.battenStatus("pause"));
                    new Beitong().butten(serverFlag.isaBoolean());
                    try {

                        Thread.currentThread().sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    serverFlag =Beitong.check_server();
                    if(serverFlag.isaBoolean()){
                        setText("关闭服务失败");
                    }else {
                        setText("关闭服务成功");
                        fab.battenStatus(PlayAndPause.PLAY.getType());
                    }
                }
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();

    }


}
