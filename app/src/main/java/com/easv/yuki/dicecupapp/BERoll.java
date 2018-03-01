package com.easv.yuki.dicecupapp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuki on 28/02/2018.
 */

public class BERoll implements Serializable {

    Date mTime;
    int[] mEyes;

    public BERoll() {

    }

    public BERoll(Date t, int[] e) {
        mTime = t;
        mEyes = e;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public int[] getmEyes() {
        return mEyes;
    }

    public void setmEyes(int[] mEyes) {
        this.mEyes = mEyes;
    }

//    public String getmEyesToString(){
//
//        String mEyesToString = mEyes.toString();
//
//        return mEyesToString;
//
//    }


}
