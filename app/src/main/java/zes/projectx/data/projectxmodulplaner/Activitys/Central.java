package zes.projectx.data.projectxmodulplaner.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;
//TODO füge Erdemsklasse ein !
//TODO Navigationsbar einbauen
public class Central extends AppCompatActivity {


    private static final int NUM_ROWS =2 ;
    private static final int NUM_COLS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);



        populateButtons();
    }



    private void populateButtons() {
        ArrayList<Subject> my = UserManager.getInstance().get("MY");
        for (Subject x : my){
        LinearLayout k = (LinearLayout) findViewById(R.id.lin);
        for(int row=0; row<NUM_ROWS; row++){
            TableRow table= new TableRow (this);
            k.addView(table);
            for(int col = 0; col<NUM_COLS; col++){
                Button button = new Button(this);
                table.addView(button);
                x.getKuerzel();
            }
        }
    }
}}
