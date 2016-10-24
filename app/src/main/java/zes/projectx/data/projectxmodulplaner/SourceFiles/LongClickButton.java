package zes.projectx.data.projectxmodulplaner.SourceFiles;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import zes.projectx.data.projectxmodulplaner.Activitys.PopUp;

/**
 * Created by Robocop on 23.10.2016.
 */
public class LongClickButton extends Button implements View.OnClickListener, View.OnLongClickListener {
   private Context context;
   private Subject sub;

    public LongClickButton(Context context, Subject sub) {
        super(context);
        this.context = context;
        this.sub = sub;
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        this.setText(sub.getKuerzel());
        this.setTextSize(30);
        this.setLayoutParams(new TableRow.LayoutParams(
                350,
                350,
                1.0f ));
        this.setPadding(0,0,0,0);

        this.setTypeface(Typeface.DEFAULT_BOLD);
        assert this != null;

    }

    public LongClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        this.setText(sub.getKuerzel());
        this.setTextSize(30);
        this.setLayoutParams(new TableRow.LayoutParams(
                350,
                350,
                1.0f ));
        this.setPadding(0,0,0,0);

        this.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public LongClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        this.setText(sub.getKuerzel());
        this.setTextSize(30);
        this.setLayoutParams(new TableRow.LayoutParams(
                350,
                350,
                1.0f ));
        this.setPadding(0,0,0,0);

        this.setTypeface(Typeface.DEFAULT_BOLD);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(getTypeface().getStyle() == Typeface.BOLD){
            Toast.makeText(context.getApplicationContext(), sub.getName() + " ausgewählt", Toast.LENGTH_SHORT).show();
            setTypeface(Typeface.SANS_SERIF);
        }
        else{
            setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    /**
     * Called when a view has been clicked and held.
     *
     * @param v The view that was clicked and held.
     * @return true if the callback consumed the long click, false otherwise.
     */
    @Override
    public boolean onLongClick(View v) {
        Intent outent = new Intent(context.getApplicationContext(), PopUp.class);
        outent.putExtra("modulname", sub.getName());
        outent.putExtra("profname", sub.getProf());
        outent.putExtra("cpscount", sub.getCp());
        context.startActivity(outent);
        return true;
    }
}
