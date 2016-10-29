package zes.projectx.data.projectxmodulplaner.SourceFiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import zes.projectx.data.projectxmodulplaner.Activitys.Central;
import zes.projectx.data.projectxmodulplaner.Activitys.LoginActivity;

/**
 * Created by Robocop on 09.10.2016.
 *
 * Der Aufbau eines Logcats ist fest
 * Name: xyz
 * Semester: n
 *
 * AktiveFaecher:
 * Subject1.toString
 * Subject2.toString
 * Subject3.toString
 * end
 *
 * AlleFaecher:
 * Subject1.toString
 * Subject2.toString
 *
 * >> Hinweis: in AlleFaecher sind nur die nicht belegten drin
 * >> BEi Aktive sind sowohl bestandene als auch nicht bestandene aber belegte Fächer drin
 *
 */

// 1- TODO Der PARSER MUSS VOLLSTÄNDIG PER HAND FESTGELEGT WERDEN SODASS ER UNVERÄNDERBAR IST, DAMIT IMPLEMENTIERUNG KORRIGIERT WIRD
public class MainParser extends AsyncTask<Context, User, Boolean>{
    private String dir;

    public void createLogCat(User x, Context con){

        try {
            // AssetsManager holt sich die Daten aus dem assetsordner (Speziell interne Funktion in Android)
            AssetManager am = con.getApplicationContext().getAssets();
            InputStream str = am.open("modul.csv"); //öffnet die zu parsende Datei
            BufferedReader fileReader= new BufferedReader(new InputStreamReader(str));
            readInternaltStorage(fileReader);

            saveLogCat(con);
            Log.d("Parser", "new File created !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * liest die einzelnen Daten aus dem BufferedReader heraus, der Zugriff auf den Storage hat
     * @param file
     * @throws IOException
     */
    private void readInternaltStorage(BufferedReader file) throws IOException {

 // ArrayList<Subject> mysubs = UserManager.getInstance().get("MY");
        ArrayList<Subject> all = UserManager.getInstance().get("ALL");

        String zeile="";
        zeile=file.readLine();
        while(null!=(zeile=file.readLine())){        //Solange die Zeile nicht null ist, lies die Daten
            //IDEE: Leg die Daten erst lokal ab, danach füg sie in das Handy auf ROOT
            String[] splitted=zeile.split(";");                //hier wird die Zeile zerlegt als Trennzeichen ;

            //______GEPARSTE DATEN_______//
            int id = new Integer(splitted[0]);
            String name = splitted[1];
            String kuerzel = splitted[2];
            int cp = new Integer(splitted[3]);
            String prof = splitted[4];
            int aUe = new Integer (splitted[5]);
            String site = splitted[6];
            String uesite = splitted[7];
            char bereich = splitted[8].charAt(0);
            int sem = new Integer (splitted[9]);
            double note = splitted[10].contains("N")? 0:new Double (splitted[10]);
            Subject sub = new Subject(id,name,kuerzel,cp,prof,aUe,site,uesite,bereich,sem,note);
            //mysubs.add(sub);
            all.add(sub);
        }
    }

    private void readInternaltStorageFor(BufferedReader file, String activ) throws IOException {

        ArrayList<Subject> mysubs = UserManager.getInstance().get("MY");
        ArrayList<Subject> all = UserManager.getInstance().get("ALL");

        String zeile="";
        zeile=file.readLine();
        while(null!=(zeile=file.readLine())){        //Solange die Zeile nicht null ist, lies die Daten
            //IDEE: Leg die Daten erst lokal ab, danach füg sie in das Handy auf ROOT

            if(zeile.equals("end") || zeile.equals("EndData")) return;
            Log.d("ok2", zeile);
            String[] split=zeile.split(":");                //hier wird die Zeile zerlegt als Trennzeichen ;

            //______GEPARSTE DATEN_______//
            int id = new Integer(split[0]);
            String name = split[1];
            String kuerzel = split[2];
            int cp = new Integer(split[3]);
            String prof = split[4];
            int aUe = new Integer (split[5]);
            String site = split[6];
            String uesite = split[7];
            char bereich = split[8].charAt(0);
            // int sem = new Integer (split[9]);
            double note = split[9].contains("N")? 0:new Double (split[9]);
            Subject sub = new Subject(id,name,kuerzel,cp,prof,aUe,site,uesite,bereich,1,note);
            all.add(sub);
            if(activ.equals("AktiveFaecher"))mysubs.add(sub);
        }

    }




    /**
     * speichert die aktuellen Daten ins Logcat
     * @param context
     */
    public void saveLogCat(Context context) {
        FileOutputStream outputStream = null;
        ArrayList<Subject> all = UserManager.getInstance().get("ALL");
        User u = UserManager.getInstance().getUser();
        try {
            //context.deleteFile("logcat.txt");
            outputStream = context.openFileOutput("logcat.txt", Context.MODE_PRIVATE);

            outputStream.write((u.getName()+ ":"+ u.getSemester() + "\n").getBytes());

            outputStream.write(("AktiveFaecher"+"\n").getBytes());
            for(Subject x: all){
                String out = x.toString() + "\n";
                outputStream.write(out.getBytes());
            }
            outputStream.write(("end"+"\n").getBytes());

            outputStream.write(("InaktiveFaecher"+"\n").getBytes());
            for(Subject sub: UserManager.getInstance().get("NOUSE")){
                String out = sub.toString() + "\n";
                outputStream.write(out.getBytes());
            }
            outputStream.write(("end"+"\n").getBytes());
            outputStream.write("EndData".getBytes());
            outputStream.flush();
            outputStream.close();
            Log.d("Parser", "saved Data !!");
        } catch (IOException e) {

        }

    }


    /**
     * liest ein existierenden Logcat, falls es nichts findet, erzeugt es ein neues
     * @param context
     */
    public void readLogcat(Context context){
        FileInputStream inputStream = null;
        User u = new User();

        UserManager manager = new UserManager(u);
        try {
            //Erdem: TODO nächste Zeile auskommentieren, wenn du Central testen willst
            context.deleteFile("logcat.txt");
            inputStream = context.openFileInput("logcat.txt");
            BufferedReader fileReader= new BufferedReader(new InputStreamReader(inputStream));
            String zeile="";
            zeile=fileReader.readLine();
            Log.d("ok", zeile);

            while(null!=(zeile=fileReader.readLine())){
                Log.d("ok2", zeile);
                switch(zeile){
                    case "AktiveFaecher":
                        readInternaltStorageFor(fileReader,"AktiveFaecher");
                        break;

                    case "InaktiveFaecher":
                        readInternaltStorageFor(fileReader,"InaktiveFaecher");
                        break;

                    case "Name":
                        String [] splitted = zeile.split(":");
                        u.setName(splitted[1]);
                        new UserManager(u);
                        break;

                    case "EndData":
                        Log.d("Parser", "readdata from InternalStorage succesfull !!");
                        Intent intent = new Intent(context.getApplicationContext(), Central.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        return;
                }
            }
        } catch (FileNotFoundException e) {
            // Wenn "openFileInput" die datei nicht gefunden hat, wird er sie erzeugen, aber eine Exception auswerfen
            Log.d("Parser", "There is no such a logcat file!!");
            Intent intent = new Intent(context.getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (IOException e) {
            //Wenn etwas mit dem FileReader selbst nicht stimmt, sollte diese Exception das Programm abbrechen
            e.printStackTrace();
        }
    }

    @Override
    protected Boolean doInBackground(Context... params) {
        Context context = params[0];
        readLogcat(context);
        return true;
    }
}
