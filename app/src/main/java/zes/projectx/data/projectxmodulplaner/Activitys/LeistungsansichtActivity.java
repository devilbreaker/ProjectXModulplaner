package zes.projectx.data.projectxmodulplaner.Activitys;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

/**
 * Created by Robocop on 15.08.2016.
 */
public class LeistungsansichtActivity  extends AppCompatActivity implements Runnable{

    private UserManager user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leistungsansicht_mainlayout);

        user = UserManager.getInstance();

        Thread tt = new Thread(this);
        try {

            tt.start();
           // tt.run();
            tt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawleistunglayout);
   //     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
  //              this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
  //      drawer.setDrawerListener(toggle);
  //      toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.navleistungsansicht);
//        navigationView.setNavigationItemSelectedListener(new Navigator(getApplicationContext(), this , drawer));

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawleistunglayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void run() {



        RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams rparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rl.setLayoutParams(rparam);
        rl.setGravity(RelativeLayout.CENTER_HORIZONTAL);

        View v = findViewById(R.id.mainincludeleistunglayout);
        View w = v.findViewById(R.id.includeleistungoberschicht);
        View x = w.findViewById(R.id.includelast);

        LinearLayout ll = (LinearLayout) x.findViewById(R.id.linnote);

        for(Subject f: user.get("MY")) {
            RelativeLayout rel = new RelativeLayout(this);
            RelativeLayout.LayoutParams relparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rel.setLayoutParams(relparam);
            rel.setBackgroundResource(R.drawable.solidrect);
            TextView bez = new TextView(this);
            TextView nn = new TextView(this);
            bez.setText(f.getKuerzel());
            String note = f.getNote() == 0.0 ? "": String.valueOf(f.getNote());
            nn.setText(note);
            bez.setTextSize(20f);
            nn.setTextSize(20f);
            bez.setPadding(10,0,0,0);

            RelativeLayout.LayoutParams nnparam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            nnparam.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
            nnparam.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            nnparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            nn.setLayoutParams(nnparam);
            nn.setPadding(0,0,15,0);
            //bez.setBackgroundResource(R.drawable.solidrect);
            rel.addView(bez);
            rel.addView(nn,nnparam);
            ll.addView(rel);
        }


        double note = 0.0; // TODO UserManagement die Notenrechnungsfunktion erlauben;
        TextView tv = (TextView) w.findViewById(R.id.textView7);
        TextView name = (TextView) w.findViewById(R.id.namenswert);
        TextView sem = (TextView) w.findViewById(R.id.semesterwert);
        name.setText(user.getUser().getName()+","); //TODO +user.getUser().getVorname());
        sem.setText(String.valueOf(user.getUser().getSemester()));
        String n = String.valueOf(note).substring(0,4);
        tv.setText(n);
    }
}
