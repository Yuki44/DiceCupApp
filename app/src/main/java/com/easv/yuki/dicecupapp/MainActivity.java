package com.easv.yuki.dicecupapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Random RANDOM = new Random();
    public Vibrator vibrator;
    //    private RecyclerView mDiceList;
    ArrayList<BERoll> diceList = new ArrayList<>();
    private Button rollDiceBtn;
    private Button historyButton;
    private ImageView[] dices;
    private RollListAdapter rollListAdapter;
    private Date currentTime;
    //    private boolean dice_state_clicked = false;
//    private HashMap<Integer, Boolean> dices_hashMap = new HashMap<Integer, Boolean>();
    private boolean[] diceEnabled;
    private ImageView closeButton;


    private static int randomDiceValue() {
        //Number from 1-6
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        diceList = new ArrayList<>();
        rollListAdapter = new RollListAdapter(diceList);
        int[] ids = {R.id.dice1, R.id.dice2, R.id.dice3, R.id.dice4, R.id.dice5, R.id.dice6};

        dices = new ImageView[6];
        diceEnabled = new boolean[6];
        for (int i = 0; i < 6; i++) {
            dices[i] = findViewById(ids[i]);
            dices[i].setTag(i);
            dices[i].setOnClickListener(this);

            diceEnabled[i] = true;
//            dices_hashMap.put(levelsArray[i].getId(), dice_state_clicked);
        }

        rollDiceBtn = findViewById(R.id.rollDiceBtn);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        historyButton = findViewById(R.id.historyButton);

        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putExtra("dicelist", diceList);
                startActivity(intent);
            }
        });

        rollDiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimation();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int idx = (Integer) view.getTag();
        if (diceEnabled[idx]) {
            dices[idx].setColorFilter(Color.rgb(240, 240, 240));
        } else {
            dices[idx].setColorFilter(Color.rgb(38, 38, 38));
        }
        diceEnabled[idx] = !diceEnabled[idx];
    }

    private void changeDice(ImageView imageView, boolean diceState) {
        if (diceState) {
            imageView.setColorFilter(Color.rgb(240, 240, 240));
            diceState = false;
        } else {
            imageView.setColorFilter(Color.rgb(38, 38, 38));
            diceState = true;
        }
    }

    private void doAnimation() {

        //Animation shake from anim folder. (anim folder is being used for animations).
        final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);

        final Animation.AnimationListener animationListener = startAnimation(anim);


        anim.setAnimationListener(animationListener);

        //Runs the animation.

        for (ImageView d : dices) {
            int idx = (Integer) d.getTag();

            if (diceEnabled[idx])
                dices[idx].startAnimation(anim);
        }

        //Length of the vibrate.
        vibrator.vibrate(80);

    }

    @NonNull
    private Animation.AnimationListener startAnimation(final Animation anim) {
        //Animation class
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //onAnimationEnd since we want to see the result AFTER the animation.
            @Override
            public void onAnimationEnd(Animation animation) {
                RandomDice();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    public void RandomDice() {


        int[] diceNumber = new int[6];
        for (int i = 0; i < 6; i++) {
            int idx = (Integer) dices[i].getTag();
            if (diceEnabled[idx]) {
                diceNumber[i] = randomDiceValue();
                dices[i].setImageResource(getImageId(diceNumber[i]));
            }
        }

        diceList.add(new BERoll(Calendar.getInstance().getTime(), diceNumber));


        Log.i("rollingDices_", "#############");
        Log.i("rollingDices",
                "    " + diceNumber.toString()
                        +
                        diceList.get(diceList.size() - 1).mTime);

        rollListAdapter.notifyDataSetChanged();
    }

    int getImageId(int eyes) {
        return getResources().getIdentifier("dice" + eyes, "drawable", "com.easv.yuki.dicecupapp");
    }


}

