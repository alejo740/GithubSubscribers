package com.github.globant.githubsubscribers.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.utils.Constants;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.main.MainActivity;

/**
 * This is the main activity where the App starts with splash screen
 *
 * @author edwin.cobos
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils.hideBar(this);
        if (savedInstanceState == null) {
            startAnimation();
            startTimer();
        }
    }

    /**
     * Function to set up and play the image's animation
     */
    private void startAnimation() {
        logoImage = (ImageView) findViewById(R.id.splashLogo);

        logoImage.setScaleX(Constants.SplashLogoAnimation.INIT_SCALE);
        logoImage.setScaleY(Constants.SplashLogoAnimation.INIT_SCALE);
        logoImage.animate().
                setStartDelay(Constants.SplashLogoAnimation.START_DELAY).
                setDuration(Constants.SplashLogoAnimation.DURATION).
                scaleX(Constants.SplashLogoAnimation.FINAL_SCALE).
                scaleY(Constants.SplashLogoAnimation.FINAL_SCALE);
    }

    /**
     * Function to start the timer delay before changing activity
     */
    private void startTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Constants.SPLASH_DELAY);
    }
}
