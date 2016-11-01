package zes.projectx.data.projectxmodulplaner.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zes.projectx.data.projectxmodulplaner.R;
import zes.projectx.data.projectxmodulplaner.SourceFiles.MainParser;
import zes.projectx.data.projectxmodulplaner.SourceFiles.User;
import zes.projectx.data.projectxmodulplaner.SourceFiles.UserManager;

/*
    1-TODO Toast einbauen mit: Bitte geben Sie die Daten vollständig ein !
    2-TODO

*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText semester;
    private Button registrationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_start);

        name = (EditText) findViewById(R.id.nametxt);
        semester = (EditText) findViewById(R.id.semestertxt);

        name.setOnClickListener(this);
        semester.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            if(name.getText().length()<=0 || name.getText().toString().matches(".*[^a-zA-Z].*") || semester.getText().length() <= 0){
                Toast.makeText(LoginActivity.this, "Keine vollständige Eingabe", Toast.LENGTH_SHORT).show();
                name.setText("");
                name.setHint(R.string.default_name);
                semester.setText("");
                semester.setHint(R.string.default_semester);

            } else {
                User u = new User(name.getText().toString(), Integer.valueOf(semester.getText().toString()));
                UserManager manager = new UserManager(u);
                Intent starter = new Intent(getApplicationContext(), AddSubject.class);
                MainParser parser = new MainParser();
                parser.createLogCat(u, getApplicationContext());
                startActivity(starter);
                finish();
            }
        }
    }
}
