package zes.projectx.data.projectxmodulplaner.SourceFiles;

/**
 * Created by Robocop on 09.10.2016.
 */
public class User {
    private String name;
    private int semester;

    public User(){
        this.name = name;
        this.semester = 1;
    }


    public User(String name){
        this.name = name;
        this.semester = 1;
    }

    public User(String name, int semester){
        this.name = name;
        this.semester = semester;
    }


    //___________________GETTER + SETTER - METHODEN_____________________________//


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
