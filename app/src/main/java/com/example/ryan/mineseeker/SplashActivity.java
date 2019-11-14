package com.example.ryan.mineseeker;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    public static final int FADE_IN_DURATION = 1000;
    public static int skipflag;
    public static final int TIME_BETWEEN = 3000;
    public static final int FADE_OUT_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        skipflag = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView splash = (ImageView)findViewById(R.id.chaika_splash);
        splash.setVisibility(View.INVISIBLE);

        RotateAnimation anim1 = setupSplashAnimation();
        Animation fadeIn = getFadeInAnimation();
        Animation fadeOut = getFadeOutAnimation();

        AnimationSet animation = getAnimationSet(anim1, fadeIn, fadeOut);
        splash.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setupSkipButton(splash);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Runnable r = new Runnable() {
                    @Override
                    public void run(){
                        if(skipflag ==0) {
                            Button btn = (Button) findViewById(R.id.SkipButton);
                            btn.performClick();
                        }
                    }
                };
                Handler h = new Handler();
                h.postDelayed(r, 4000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @NonNull
    private AnimationSet getAnimationSet(RotateAnimation anim1, Animation fadeIn, Animation fadeOut) {
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        animation.addAnimation(anim1);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);
        return animation;
    }

    @NonNull
    private Animation getFadeOutAnimation() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(FADE_IN_DURATION + TIME_BETWEEN);
        fadeOut.setDuration(FADE_OUT_DURATION);
        return fadeOut;
    }

    @NonNull
    private Animation getFadeInAnimation() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(FADE_IN_DURATION);
        return fadeIn;
    }

    RotateAnimation setupSplashAnimation(){
        RotateAnimation anim1 = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim1.setInterpolator(new LinearInterpolator());
        anim1.setRepeatCount(1);
        anim1.setDuration(700);
        return anim1;
    }

    void setupSkipButton(final ImageView splash){
        Button btn = (Button) findViewById(R.id.SkipButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipflag = 1;
                splash.setAnimation(null);
                Intent intent = MenuActivity.makeIntent(SplashActivity.this);
                startActivity(intent);
                finish();
            }
        });

    }
}
