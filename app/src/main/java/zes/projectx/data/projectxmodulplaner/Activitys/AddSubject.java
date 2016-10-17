package zes.projectx.data.projectxmodulplaner.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

public class AddSubject extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        Thread t = new Thread(this);
        t.run();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


        //  DisplayMetrics dm = new DisplayMetrics();
        //  getWindowManager().getDefaultDisplay().getMetrics(dm);

        //  int width = dm.widthPixels;
        //  int height = dm.heightPixels;

        //  getWindow().setLayout((int )(width*.9), (int) (height*.4));

        UserManager um = UserManager.getInstance();
        ArrayList<Subject> all = um.get("MY");
        int size = all.size();
        Log.d("VariableALL", size + "");


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
           // TableRow tr = new TableRow(this);
           // TableRow.LayoutParams trparam = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //tr.setOrientation(LinearLayout.HORIZONTAL);
           // tr.setLayoutParams(trparam);
           // for(int row = 0; row < 3; row ++){
                //Wähle Sub aus:
                Subject sub = all.get(i);
                // Erzeuge den Button und seine Parameter als WrapContent
                Button button = new Button(this);
                ViewGroup.LayoutParams btnparam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setLayoutParams(btnparam);
                //Text mit dem Kürzel festlegen:
                button.setText(sub.getKuerzel());

            //    tr.addView(button);
            tableLayout.addView(button);
            }

          //  tableLayout.addView(tr);
       // }

        setContentView(tableLayout);

    }
}
