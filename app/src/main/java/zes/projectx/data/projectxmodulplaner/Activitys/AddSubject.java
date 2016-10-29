package zes.projectx.data.projectxmodulplaner.Activitys;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.LongClickButton;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;


/*
1-TODO  Checke ob die ausgewählten Fächer erzeugt werden ! Dafür muss der Parser aktiviert werden nachdem hacken anklicken.
2-TODO größe des Hackens anpassen
3-TODO Entfernen des doppelten Fehlers
4-TODO kennzeichnungsfarbe für ausgewählte Fächer ?
5-TODO größe der Buttons verkleinern
*/
public class AddSubject extends AppCompatActivity implements Runnable{
    private ScrollView sc;
    private ImageButton ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addsubjectovercontent);
        ok = (ImageButton) findViewById(R.id.button2);

        ok.setVisibility(View.INVISIBLE);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent central = new Intent(getApplicationContext(), Central.class);
                startActivity(central);
                finish();
            }
        });
        Thread t = new Thread(this);
        try {
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void subjectClicked(){
            ok.setVisibility((UserManager.getInstance().getCountStartUpSubjects() > 0) ? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void run() {

        FrameLayout fl = (FrameLayout)findViewById(R.id.framelayout);
        int i = 0;
        ArrayList<Subject> allsub = UserManager.getInstance().get("ALL");
        final int ROW_NUM = (allsub.size())/3;
        final int COL_NUM = 3;
        final TableLayout tableLayout = (TableLayout) fl.findViewById(R.id.TableLayout);
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
