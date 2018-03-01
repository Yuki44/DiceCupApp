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
import android.widget.Toast;

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
    private ImageView dice1, dice2, dice3, dice4, dice5, dice6;
    private RollListAdapter rollListAdapter;
    private Date currentTime;
    //    private boolean dice_state_clicked = false;
//    private HashMap<Integer, Boolean> dices_hashMap = new HashMap<Integer, Boolean>();
    private boolean dice2Enabled = true;
    private boolean dice3Enabled = true;
    private boolean dice4Enabled = true;
    private boolean dice5Enabled = true;
    private boolean dice6Enabled = true;


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

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);
        dice6 = findViewById(R.id.dice6);

        final ImageView[] dicesArray = {dice1, dice2, dice3, dice4, dice5, dice6};

        for (int i = 0; i < 6; i++) {
            dicesArray[i].setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.dice1:
                Toast.makeText(MainActivity.this, "You must have at least 1 dice", Toast.LENGTH_LONG).show();
                break;
            case R.id.dice2:
                if (dice2Enabled) {
                    dice2.setColorFilter(Color.rgb(240, 240, 240));
                    dice2Enabled = false;
                } else {
                    dice2.setColorFilter(Color.rgb(38, 38, 38));
                    dice2Enabled = true;
                }
//                diceClicked(dice2);
                break;
            case R.id.dice3:
                //               dice3.setColorFilter(Color.rgb(240, 240, 240));
                if (dice3Enabled) {
                    dice3.setColorFilter(Color.rgb(240, 240, 240));
                    dice3Enabled = false;
                } else {
                    dice3.setColorFilter(Color.rgb(38, 38, 38));
                    dice3Enabled = true;
                }
//                diceClicked(dice3);
                break;
            case R.id.dice4:
                //  dice4.setColorFilter(Color.rgb(240, 240, 240));
                if (dice4Enabled) {
                    dice4.setColorFilter(Color.rgb(240, 240, 240));
                    dice4Enabled = false;
                } else {
                    dice4.setColorFilter(Color.rgb(38, 38, 38));
                    dice4Enabled = true;
                }
//                diceClicked(dice4);
                break;
            case R.id.dice5:
                //      dice5.setColorFilter(Color.rgb(240, 240, 240));
                if (dice5Enabled) {
                    dice5.setColorFilter(Color.rgb(240, 240, 240));
                    dice5Enabled = false;
                } else {
                    dice5.setColorFilter(Color.rgb(38, 38, 38));
                    dice5Enabled = true;
                }
//                diceClicked(dice5);
                break;
            case R.id.dice6:
                //    dice6.setColorFilter(Color.rgb(240, 240, 240));
                if (dice6Enabled) {
                    dice6.setColorFilter(Color.rgb(240, 240, 240));
                    dice6Enabled = false;
                } else {
                    dice6.setColorFilter(Color.rgb(38, 38, 38));
                    dice6Enabled = true;
                }
//                diceClicked(dice6);
                break;
        }
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

    //    private void diceClicked(ImageView dice_id) {
//        if (dices_hashMap.get(dice_id) == false) {
//            dice_id.setColorFilter(Color.rgb(240, 240, 240));
//
//            dices_hashMap.put(dice_id.getId(), !dice_state_clicked);
//        }
//    }
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

