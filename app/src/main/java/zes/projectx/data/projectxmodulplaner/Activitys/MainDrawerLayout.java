package zes.projectx.data.projectxmodulplaner.Activitys;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.Navigator;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;


/**
 * HOW TO USER ?
 * MeineKlasse extends MainDrawerLayout {
 *
 *  @Override
 *  protected void onCreate(Bundle savedInstanceState) {
 *  super.onCreate(savedInstanceState);
 *  super.inflateMyData(R.layout.meinLayout);
 *  }
 *
 *  // Jetzt könnt ihr die Aktivity nach euren Belieben nutzen
 *  Wenn ihr an euren Layout was einfügen oder ändern wollt, dann müsst ihr auf das existierende
 *  RelativeLayout zugreifen mit:
 *  RelativeLayout rl = this.getMain();
 *
 *  und dann könnt ihr auf euren Inhalt iwas basteln oder zugreifen.
 *
 * }
 */

public class MainDrawerLayout extends AppCompatActivity {
    private RelativeLayout includedmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_nav_layer);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new Navigator(getApplicationContext(), this, drawer));
    }

    /**
     *
     * @param r_layout
     */
    public void inflateMyData(int r_layout){

        View includedLayour = findViewById(R.id.bar_mainlayer);
        includedmain = (RelativeLayout) includedLayour.findViewById(R.id.mainlayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(r_layout,includedmain);
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


    public RelativeLayout getMain(){
        return includedmain;
    }
}
