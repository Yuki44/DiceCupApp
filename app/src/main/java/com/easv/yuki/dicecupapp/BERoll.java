package com.easv.yuki.dicecupapp;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yuki on 28/02/2018.
 */

public class BERoll implements Serializable {

    Date mTime;
    Integer[] mEyes;

    public BERoll() {

    }

    public BERoll(Date t, Integer[] e) {
        mTime = t;
        mEyes = e;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Integer[] getmEyes() {
        return mEyes;
    }

    public void setmEyes(Integer[] mEyes) {
        this.mEyes = mEyes;
    }

    public String getTime() {
        Date date = mTime;   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        //    Integer day = calendar.get(Calendar.DAY_OF_WEEK);
        Integer dayHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        Integer minute = calendar.get(Calendar.MINUTE);        // gets hour in 12h format
        Integer second = calendar.get(Calendar.SECOND);        // gets hour in 12h format
        String finalHour = dayHour + " : " + minute + " : " + second + "  > ";
        return finalHour.toString();
    }

}
