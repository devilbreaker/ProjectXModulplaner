package zes.projectx.data.projectxmodulplaner;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import zes.projectx.data.projectxmodulplaner.SourceFiles.MainParser;

public class SplashScreen extends AppCompatActivity {

    private ImageView questionAnimation;
    private TextView textAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        questionAnimation = (ImageView) findViewById(R.id.questionPNG);
        textAnimation = (TextView)findViewById(R.id.textAnimation);
        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.fade_in);
        textAnimation.setAnimation(animation);
        questionAnimation.setAnimation(animation);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainParser p = new MainParser();
                p.execute(getApplicationContext());
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 4000);
    }
}
