package me.aniceday.btjh;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class BattenStatus extends FloatingActionButton{

    public BattenStatus(Context context){
        super(context);
    }
    public BattenStatus(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public BattenStatus(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    /**
     * @param b
     */
    public void checkbattenStatus(boolean b){//运行中显示暂停按钮，非运行显示play；
        if(b){
            this.battenStatus("pause");
        }else {
            this.battenStatus("play");
        }
    }
    public void battenStatus(String e){
        switch (e){
            case "pause":
                this.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("red")));
                this.setImageResource(android.R.drawable.ic_media_pause);
//                return R.string.activity;
                break;
            case "play":
                this.setBackgroundTintList(ColorStateList.valueOf(Resources.getSystem().getColor(android.R.color.holo_blue_light)));
                this.setImageResource(android.R.drawable.ic_media_play);
//                return R.string.inactivity;
                break;
            default:
//                return R.string.text_view;
                break;
        }
    }



}
