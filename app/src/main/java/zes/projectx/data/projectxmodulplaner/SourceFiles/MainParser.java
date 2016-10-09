package zes.projectx.data.projectxmodulplaner.SourceFiles;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Robocop on 09.10.2016.
 */
public class MainParser extends AsyncTask<Context, User, Void>{

    public void createLogCat(User x){
        try {
            AssetManager am = this.getApplicationContext().getAssets();
            InputStream str = am.open("galatasaray.csv");
            BufferedReader FileReader= new BufferedReader(new InputStreamReader(str));

            String zeile="";
            int count = 0;
            while(null!=(zeile=FileReader.readLine())){

                if(0 < count) {

                    String[] split=zeile.split(";");                //hier wird die Zeile zerlegt als Trennzeichen ;
                    String name = split[0];                     //erste Zeit über index 0
                    String datum = split[1];                   //zweite Zeit über index 1
                    String ort = split[2];
                    SuperligTable eintrag = new SuperligTable(name, datum, ort);
                    table.add(eintrag);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Void doInBackground(Context... params) {
        return null;
    }
}
