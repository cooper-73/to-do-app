package com.cooper73.todoapp.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.PreferencesHelper;
import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.SplashView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements SplashView {

    private ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hideActionBar();
        initPresenter();
        bindUI();
        initUI();
        initEvents();
        splashAnimation();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void bindUI() {
        splashImageView = findViewById(R.id.iv_splash_image);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)  actionBar.hide();
    }

    @Override
    public void animateFirstImage() {
        Animation scaleUpAndDown = AnimationUtils.loadAnimation(this, R.anim.scale_up_and_down_finish);
        splashImageView.startAnimation(scaleUpAndDown);
    }

    @Override
    public void animateLastImage() {
        Animation scaleUpAndDown = AnimationUtils.loadAnimation(this, R.anim.scale_up_and_down_start);
        splashImageView.startAnimation(scaleUpAndDown);
    }

    @Override
    public void splashAnimation() {
        animateFirstImage();
        new CountDownTimer(1500, 1000) {

            @Override
            public void onTick(long l) {

            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFinish() {
                splashImageView.setImageDrawable(getDrawable(R.drawable.uno_splash_second));
                animateLastImage();
                new CountDownTimer(1500, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        goNextActivity();
                    }
                }.start();
            }
        }.start();
    }

    @Override
    public void goNextActivity() {
        SharedPreferences sharedPreferences = new PreferencesHelper(this).getSharedPreferences();
        boolean hasSeenOnBoarding = sharedPreferences
                    .getBoolean(getString(R.string.shared_preferences_on_boarding), false);
        String userId = sharedPreferences
                .getString(getString(R.string.shared_preferences_user_id), null);
        Intent intent;

        if (userId != null) {
            intent = new Intent(this, HomeActivity.class);
            intent.putExtra("userId", userId);
        } else if (hasSeenOnBoarding) {
            intent = new Intent(this, StartActivity.class);
        } else {
            intent = new Intent(this, WelcomeActivity.class);
        }

        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}