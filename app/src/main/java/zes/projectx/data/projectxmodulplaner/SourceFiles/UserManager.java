package zes.projectx.data.projectxmodulplaner.SourceFiles;

import java.util.ArrayList;

/**
 * Created by Robocop on 09.10.2016.
 */
public class UserManager {
    private static UserManager usermanager;
    private User user;
    private ArrayList<Subject> mysubjects;
    private ArrayList<Subject> allsubjects;


    /**
     * erzeugt einen neuen UserManager, der überall zugreifbar sein wird
     * @param x User, der angemeldet sein wird
     */
    public UserManager(User x){
        this.user = x;
        this.usermanager = new UserManager(x);
        mysubjects = new ArrayList<Subject>();
        allsubjects = new ArrayList<Subject>();
    }

    public ArrayList<Subject> get(String x){
        if (x.equals("ALL"))
            return allsubjects;

        if (x.equals("MY")) return mysubjects;

        if(x.equals("NOUSE")){
            ArrayList<Subject> unbelegt = new ArrayList<Subject>();
            for(Subject sub: allsubjects){
                if(!mysubjects.contains(sub)){
                    unbelegt.add(sub);
                }
            }
            return unbelegt;
        }

        else
            return null;
    }

    public User getUser(){
        return user;
    }
    /**
     * gibt eine Instanz des aktuellen UserManagers zurück
     * Diese Methode dient zum Zugriff auf den Manager von allen Klassen
     * @return
     */
    public static UserManager getInstance(){
        return usermanager;
    }

    /**
     * gibt eine Liste wieder, in dem alle Fächer beinhaltet sind, die beotet werden
     * @return
     */
    public ArrayList<Subject> gibBenoteteFaecher(){
        ArrayList<Subject> allsubs = new ArrayList<Subject>();

        for(Subject sub: mysubjects){
            // WEnn die Note größer 0 ist, dann ist es benotet worden und der Status auf bestanden gesetzt ist
            if (sub.getNote() > 0 && sub.isStatus()){
                allsubs.add(sub); // füge es in die Liste ein
            }
        }
        return allsubs;
    }

    /**
     * gibt die Creditpoints aller Fächer aus der gegebenen liste
     * @param subjectlist
     * @return
     */
    public double getCreditPoints(ArrayList<Subject> subjectlist){
        double credits = 0;

        for(Subject sub: subjectlist){
            credits = sub.getCp();
        }

        return credits;
    }

    /**
     * gibt die Creditpoints aller Gewichteten Fächer des Users (notenbehaftete)
     * @return
     */
    public double getCreditPointsOfCountedFromUser(){
        ArrayList<Subject> allcounted = gibBenoteteFaecher();
        return getCreditPoints(allcounted);
    }

}
