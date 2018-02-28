package com.easv.yuki.dicecupapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    public Vibrator vibrator;
    ArrayList<Integer> diceList = new ArrayList<Integer>();
    private Button rollDiceBtn;
    private ImageView dice1, dice2;

    private static int randomDiceValue() {
        //Number from 1-6
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDiceBtn = findViewById(R.id.rollDiceBtn);
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


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
        final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        final Animation.AnimationListener animationListener = startAnimation(anim1, anim2);


        anim1.setAnimationListener(animationListener);
        anim2.setAnimationListener(animationListener);

        //Runs the animation.
        dice1.startAnimation(anim1);
        dice2.startAnimation(anim2);
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
                RandomDice(animation, anim1, anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    public void RandomDice(Animation animation, Animation anim1, Animation anim2) {
        int diceNumber = randomDiceValue();

        //Drawable is where images is located. defPackage is for MainActivity.
        int newRandomDice = getResources().getIdentifier("dice" + diceNumber, "drawable", "com.easv.yuki.dicecupapp");

        //Sets a "new" dice if the dice is the same.
        if (animation == anim1) {
            dice1.setImageResource(newRandomDice);
        } else if (animation == anim2) {
            dice2.setImageResource(newRandomDice);
        }

        addDiceToSpinner(diceNumber);

    }

    public void addDiceToSpinner(int diceNumber) {
        diceList.add(diceNumber);
        //Sort by newest to be on top.
//        Collections.reverse(diceList);
        Log.d("Catching", diceList + "TEST");

        //Adds the new roll to the spinner.
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, diceList);
        // mSpinner.setAdapter(adapter);
    }
}
