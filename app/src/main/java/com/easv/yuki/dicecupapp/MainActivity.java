package com.easv.yuki.dicecupapp;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    public Vibrator vibrator;
    //    private RecyclerView mDiceList;
    ArrayList<BERoll> diceList = new ArrayList<>();
    private Button rollDiceBtn;
    private Button historyButton;
    private ImageView dice1, dice2;
    private RollListAdapter rollListAdapter;
    private Date currentTime;
    private boolean dice1anim = false;
    private boolean dice2anim = false;


    private static int randomDiceValue() {
        //Number from 1-6
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceList = new ArrayList<>();
        rollListAdapter = new RollListAdapter(diceList);

//        mDiceList = (RecyclerView) findViewById(R.id.dice_list);
//        mDiceList.setHasFixedSize(true);
//        mDiceList.setLayoutManager(new LinearLayoutManager(this));
//        mDiceList.setAdapter(rollListAdapter);


        rollDiceBtn = findViewById(R.id.rollDiceBtn);
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
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

    private void doAnimation() {

        //Animation shake from anim folder. (anim folder is being used for animations).
        final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        dice1anim = true;
        final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        dice2anim = true;
        final Animation.AnimationListener animationListener = startAnimation(anim1, anim2);


        anim1.setAnimationListener(animationListener);
        anim2.setAnimationListener(animationListener);

        //Runs the animation.
        dice1.startAnimation(anim1);
        dice2.startAnimation(anim2);

        RandomDice();
        //Length of the vibrate.
        vibrator.vibrate(80);

    }

    @NonNull
    private Animation.AnimationListener startAnimation(final Animation anim1, final Animation anim2) {
        //Animation class
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //onAnimationEnd since we want to see the result AFTER the animation.
            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    public void RandomDice() {


        int diceNumber1 = randomDiceValue();
        int diceNumber2 = randomDiceValue();


        //Drawable is where images is located. defPackage is for MainActivity.
        int newRandomDice1 = getResources().getIdentifier("dice" + diceNumber1, "drawable", "com.easv.yuki.dicecupapp");
        int newRandomDice2 = getResources().getIdentifier("dice" + diceNumber2, "drawable", "com.easv.yuki.dicecupapp");

        //Sets a "new" dice if the dice is the same.
            dice1.setImageResource(newRandomDice1);
            dice2.setImageResource(newRandomDice2);

        Integer[] a = {diceNumber1, diceNumber2};
        diceList.add(new BERoll(Calendar.getInstance().getTime(), a));

        Log.i("rollingDices_", "#############");
        Log.i("rollingDices",
                "    " + Integer.toString(diceNumber1)
                        + "    " + Integer.toString(diceNumber2)
                        + "     "
                        + diceList.get(diceList.size() - 1).mTime);

        rollListAdapter.notifyDataSetChanged();
    }


}
