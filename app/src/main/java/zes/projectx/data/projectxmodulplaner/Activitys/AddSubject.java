package zes.projectx.data.projectxmodulplaner.Activitys;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

public class AddSubject extends AppCompatActivity {

    private static final int COL_NUM = 3;
    private static int ROW_NUM = 0;
    ImageButton checkButton;
    ArrayList<Subject> all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        checkButton = (ImageButton) findViewById(R.id.CheckButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddSubject.this, "Noch keine Implementierung", Toast.LENGTH_SHORT).show();
            }
        });


      //  DisplayMetrics dm = new DisplayMetrics();
      //  getWindowManager().getDefaultDisplay().getMetrics(dm);

      //  int width = dm.widthPixels;
      //  int height = dm.heightPixels;

      //  getWindow().setLayout((int )(width*.9), (int) (height*.4));

        UserManager um = UserManager.getInstance();
        all = um.get("ALL");
        int size = all.size();
        ROW_NUM = size/3;
        Log.d("Variable", size + "");

        addButton();


        /*
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.na.popupwindow.MainActivity">
         */

        //TODO Dynamisierung des Code oben drüber nicht fertig
        TableLayout tableLayout = new TableLayout(this);
        TableLayout.LayoutParams tlparam = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(tlparam);

        /*
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/ModulKürzel"
        android:id="@+id/buttonDS"
        android:layout_marginTop="25dp"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        />
        */


        for(int i = 0; i < size; i++){
            TableRow tr = new TableRow(this);
            tr.setOrientation(LinearLayout.HORIZONTAL);
            for(int row = 0; row < 3; row ++){
                //Wähle Sub aus:
                Subject sub = all.get(i);
                // Erzeuge den Button und seine Parameter als WrapContent
                Button button = new Button(this);
                ViewGroup.LayoutParams btnparam = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setLayoutParams(btnparam);
                //Text mit dem Kürzel festlegen:
                button.setText(sub.getKuerzel());
                button.setPadding(0,25,0,0);
                tr.addView(button);
            }
            tableLayout.addView(tr);
        }

        setContentView(tableLayout);
    }

    private void addButton() {
        int i = 0;
        TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayout);
        for(int row = 0; row < ROW_NUM; row++){

            TableRow tableRow = new TableRow(AddSubject.this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            tableLayout.addView(tableRow);
            for(int col = 0; col < COL_NUM; col++) {
                Subject subject = all.get(i);
                final Button button = new Button(AddSubject.this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        350,
                        350,
                        1.0f));
                button.setPadding(0, 0, 0, 0);
                button.setText(subject.getKuerzel());
                button.setTextSize(30);
                button.setTypeface(Typeface.DEFAULT_BOLD);
                i++;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(button.getTypeface().getStyle() == Typeface.BOLD){

                            Toast.makeText(AddSubject.this, button.getText() + " ausgewählt", Toast.LENGTH_SHORT).show();
                            button.setTypeface(Typeface.SANS_SERIF);

                        }
                        else{button.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));}
                    }
                });
                tableRow.addView(button);
            }
        }

    }
}
