package zes.projectx.data.projectxmodulplaner.SourceFiles;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

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


        ArrayList<Subject> mysubs = UserManager.getInstance().get("MY");
        ArrayList<Subject> all = UserManager.getInstance().get("ALL");


        String zeile="";
        while(null!=(zeile=file.readLine())){        //Solange die Zeile nicht null ist, lies die Daten
            //IDEE: Leg die Daten erst lokal ab, danach füg sie in das Handy auf ROOT
            String[] split=zeile.split(";");                //hier wird die Zeile zerlegt als Trennzeichen ;

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
            int sem = new Integer (split[9]);
            double note = new Double (split[10]);
            Subject sub = new Subject(id,name,kuerzel,cp,prof,aUe,site,uesite,bereich,sem,note);
            mysubs.add(sub);
            all.add(sub);
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
            context.deleteFile("logcat.txt");
            outputStream = context.openFileOutput("logcat.txt", Context.MODE_PRIVATE);

            outputStream.write(u.getName().getBytes());
            outputStream.write((""+u.getSemester()).getBytes());

            outputStream.write("AktiveFaecher".getBytes());
            for(Subject x: all){
                String out = x.toString();
                outputStream.write(out.getBytes());
            }
            outputStream.write("end".getBytes());

            outputStream.write("InaktiveFaecher".getBytes());
            for(Subject sub: UserManager.getInstance().get("NOUSE")){
                String out = sub.toString();
                outputStream.write(out.getBytes());
            }
            outputStream.write("end".getBytes());
            outputStream.write("EndData".getBytes());
            outputStream.flush();
            outputStream.close();


        } catch (IOException e) {

        }

    }


    /**
     * liest ein existierenden Logcat, falls es nichts findet, erzeugt es ein neues
     * @param context
     */
    public void readLogcat(Context context){
        FileInputStream inputStream = null;
        User u = UserManager.getInstance().getUser();


        try {
            inputStream = context.openFileInput("logcat.txt");
            BufferedReader fileReader= new BufferedReader(new InputStreamReader(inputStream));
            readInternaltStorage(fileReader);


        } catch (FileNotFoundException e) {
            // Wenn "openFileInput" die datei nicht gefunden hat, wird er sie erzeugen, aber eine Exception auswerfen
            createLogCat(u,context);

        } catch (IOException e) {
            //Wenn etwas mit dem FileReader selbst nicht stimmt, sollte diese Exception das Programm abbrechen
            e.printStackTrace();
        }
    }

    @Override
    protected Boolean doInBackground(Context... params) {
        Context context = params[0];
        dir = params[0].getFilesDir().getAbsolutePath();
        readLogcat(context);
        return true;
    }
}
