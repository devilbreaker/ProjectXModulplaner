package zes.projectx.data.projectxmodulplaner.SourceFiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;

import zes.projectx.data.projectxmodulplaner.R;


/**
 * Created by Robocop on 15.08.2016.
 */

//TODO Saif: anpassen der Bedingung im IF abhängig vom Nav.bar
public class Navigator  implements NavigationView.OnNavigationItemSelectedListener {

    private Context con;
    private Activity a;
    private DrawerLayout d;

    public Navigator(Context con, Activity a, DrawerLayout d) {
        this.con = con;
        this.a = a;
        this.d = d;


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        a.getMenuInflater().inflate(R.menu.main_nav_layer, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Bundle temp_bundle = new Bundle();
       // a.onSaveInstanceState(temp_bundle);
        Intent next;
        if (id == R.id.anzeige) {
            // Handle the camera action
        } else if (id == R.id.addSub) {
      //      next = new Intent(con, AddSubject.class);
      //      next.putExtra("bundle", temp_bundle);
     //       next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     //       con.startActivity(next);

        } else if (id == R.id.leistung) {
     //       next = new Intent(con, LeistungsansichtActivity.class);
     //       next.putExtra("bundle", temp_bundle);
     //       next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     //       con.startActivity(next);

        } else if (id == R.id.modulkatalog) {
     //       next = new Intent(con, estPDFAct.class);
     //       next.putExtra(estPDFAct.EXTRA_PDFFILENAME,"modulbeschreibung.pdf");
     //       next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //        con.startActivity(next);
        } else if (id == R.id.setting) {

        } else if (id == R.id.nav_send) {
            a.finish();
        }

        DrawerLayout drawer = d;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
