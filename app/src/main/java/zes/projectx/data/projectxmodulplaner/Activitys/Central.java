package zes.projectx.data.projectxmodulplaner.Activitys;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.security.AccessController;
import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Navigator;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Subject;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;
// TODO Zesshan andere Farbe für den RelativeLayout
//TODO Saif: Größe des Mains in Navogationsbar anpassen über den Layout

// TODO SPECIAL: Suchfunktion für spezielle Fächer + Reload Activity dadurch aktivieren

public class Central extends AppCompatActivity implements View.OnClickListener {
    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_central);

        manager = UserManager.getInstance();

        setContentView(R.layout.activity_main_nav_layer);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        View includedLayour = findViewById(R.id.bar_mainlayer);
        RelativeLayout includedmain = (RelativeLayout) includedLayour.findViewById(R.id.mainlayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_central,includedmain);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      //  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      //          this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      //  drawer.setDrawerListener(toggle);
      //  toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new Navigator(getApplicationContext(), this, drawer));
        populatelayout();
    }



    private void populatelayout() {
        ArrayList<Subject> mysubs = manager.get("ALL");
        int size = mysubs.size();
        LinearLayout f = (LinearLayout) findViewById(R.id.lin);




        for(int row = 0; row<size; row++){

            Subject sub = mysubs.get(row);
            AbsoluteLayout ko =new AbsoluteLayout(this);//new RelativeLayout(this);
            /*
            ko.setLayoutParams(new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
                    */
            LayoutInflater inflater = LayoutInflater.from(this);
            inflater.inflate(R.layout.central_relativebuilder,ko);
            f.addView(ko);

            /*Button button = new Button(this);
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
            /*TextView text2 = new TextView(this);
            ko.addView(text2);*/
            ko.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            //text2.setBackgroundColor(R.color.colorAccent);

            //f.addView(ko); */

            TextView lkurz = (TextView) ko.findViewById(R.id.lkuerzel);
            TextView lbesch = (TextView) ko.findViewById(R.id.lbesch);
            TextView lmodul = (TextView) ko.findViewById(R.id.lmodul);

            lmodul.setText(sub.getName());
            lkurz.setText(sub.getKuerzel());

            ko.setOnClickListener(this);
            lmodul.setOnClickListener(this);
            lkurz.setOnClickListener(this);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent weiterleitung = new Intent(getApplicationContext(),PopUp.class);
        startActivity(weiterleitung);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_nav_layer, menu);
        return true;
    }

}
