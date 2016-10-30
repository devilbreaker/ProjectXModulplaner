package zes.projectx.data.projectxmodulplaner.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;
//TODO  Erdem Rahmen für den Relative Layout
// TODO Zesshan andere Farbe für den RelativeLayout
//TODO Saif Navigationsbar einbauen

public class Central extends AppCompatActivity {
    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);

        manager = UserManager.getInstance();



        populatelayout();
    }



    private void populatelayout() {
        ArrayList<Subject> mysubs = manager.get("ALL");
        int size = mysubs.size();
        LinearLayout f = (LinearLayout) findViewById(R.id.lin);
        for(int row = 0; row<size; row++){
            Subject sub = mysubs.get(row);
            RelativeLayout ko = new RelativeLayout(this);
            ko.setLayoutParams(new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            f.addView(ko);

            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));
            button.setText(sub.getKuerzel());
            ko.addView(button);
            TextView text = new TextView(this);
            text.setLayoutParams(new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            ko.addView(text);
            text.setText(sub.getName());
            //text.setBackgroundColor(R.color.colorAccent);
            TextView text2 = new TextView(this);
            ko.addView(text2);
            ko.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            //text2.setBackgroundColor(R.color.colorAccent);

            //f.addView(ko);
        }
    }}
