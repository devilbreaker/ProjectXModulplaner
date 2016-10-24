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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.LongClickButton;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

public class AddSubject extends AppCompatActivity implements Runnable{
    private ScrollView sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        Thread t = new Thread(this);
        t.run();
        //sc = (ScrollView) findViewById(R.id.)
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int i = 0;

        ArrayList<Subject> allsub = UserManager.getInstance().get("ALL");
        final int ROW_NUM = (allsub.size())/3;
        final int COL_NUM = 3;
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayout);
        for(int row = 0; row < ROW_NUM; row++){

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f ));

            tableLayout.addView(tableRow);
            for(int col = 0; col < COL_NUM; col++){
                final LongClickButton button = new LongClickButton(this, allsub.get(i));
                i++;
                tableRow.addView(button);

            }
        }
    }
}
