package zes.projectx.data.projectxmodulplaner.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.User;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

/*TODO
    1- Toast einbauen mit: Bitte geben Sie die Daten vollständig ein !
    2- Testen

*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (EditText) findViewById(R.id.nametxt);
        semester = (EditText) findViewById(R.id.semestertxt);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v instanceof EditText){
            EditText etext = (EditText) v;
            ((EditText) v).setText("");
        }

        if(v instanceof Button){
            User u = new User(name.getText().toString(), new Integer(semester.getText().toString()));
            UserManager manager = new UserManager(u);
            Intent starter = new Intent(getApplicationContext(),AddSubject.class);
        }
    }
}
