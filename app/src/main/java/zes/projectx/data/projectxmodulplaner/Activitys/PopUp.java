package zes.projectx.data.projectxmodulplaner.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

import zes.projectx.data.projectxmodulplaner.R;

public class PopUp extends AppCompatActivity {
    TextView modname;
    TextView profname;
    TextView cpsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        modname = (TextView) findViewById(R.id.modulName);
        profname = (TextView) findViewById(R.id.profName);
        cpsCount = (TextView) findViewById(R.id.cpCount);

        Intent intent = getIntent();
        modname.setText(intent.getStringExtra("modulname"));
        profname.setText(intent.getStringExtra("profname"));
        cpsCount.setText(intent.getStringExtra("cpscount"));

        getWindow().setLayout((int )(width*.85), (int) (height*.36));


    }
}
