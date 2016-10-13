package zes.projectx.data.projectxmodulplaner.SourceFiles;

/**
 * Created by Robocop on 23.06.2016.
 */
public class Subject {
    private int id;
    private String name;
    private String kuerzel;
    private int cp;
    private String prof;
    private boolean status;
    private int sem;
    private int aUe;
    private double note;
    private String site;
    private String uesite;
    private char bereich;


    /**
     * Konstruktor für bestandene  Fächer, da die Note mit eingetragen wird
     * @param id
     * @param name
     * @param kuerzel
     * @param cp
     * @param prof
     * @param aUe
     * @param site
     * @param ueSite
     * @param bereich
     * @param semester
     * @param note
     */
    public Subject(int id,String name, String kuerzel, int cp, String prof, int aUe, String site, String ueSite, char bereich, int semester, double note) {
        this.id = id;
        this.name = name;
        this.kuerzel = kuerzel;
        this.cp = cp;
        this.prof = prof;
        this.aUe = aUe;
        this.note = note;
        this.site = site;
        this.uesite = ueSite;
        this.bereich = bereich;
        this.sem = semester;
        this.status = (note == 0) ? false : true;
    }

    /**
     * Konstruktor für Fächer, die aktuell belegt werden oder noch nicht belegt wurden
     * @param id
     * @param name
     * @param kuerzel
     * @param cp
     * @param prof
     * @param aUe
     * @param site
     * @param uesite
     * @param bereich
     * @param semester
     */
    public Subject(int id,String name, String kuerzel, int cp, String prof, int aUe, String site,String uesite, char bereich, int semester) {
        this.id = id;
        this.name = name;
        this.kuerzel = kuerzel;
        this.cp = cp;
        this.prof = prof;
        this.aUe = aUe;
        this.note = 0;
        this.site = site;
        this.uesite = uesite;
        this.bereich = bereich;
        this.sem = semester;
        this.status = (note == 0) ? false : true;
    }


    @Override
    public String toString(){
        String s = ":";
        return id+s+name+s+kuerzel+s+cp+s+prof+s+aUe+s+site+s+uesite+s+bereich+s+note;
    }

    //_____________________GETTER + SETTER - METHODEN __________________________________________________//


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getaUe() {
        return aUe;
    }

    public void setaUe(int aUe) {
        this.aUe = aUe;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public char getBereich() {
        return bereich;
    }

    public void setBereich(char bereich) {
        this.bereich = bereich;
    }
}