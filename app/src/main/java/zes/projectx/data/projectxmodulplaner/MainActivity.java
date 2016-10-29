package zes.projectx.data.projectxmodulplaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import zes.projectx.data.projectxmodulplaner.Activitys.AddSubject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.MainParser;
import zes.projectx.data.projectxmodulplaner.SourceFiles.User;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //User u = new User("Saif Samir", 5);
        //UserManager manager = new UserManager(u);
        MainParser p = new MainParser();
        p.execute(this.getApplicationContext());
        finish();
    }
}
