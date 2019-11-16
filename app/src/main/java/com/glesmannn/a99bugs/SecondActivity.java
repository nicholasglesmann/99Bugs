package com.glesmannn.a99bugs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private int bugsRemaining;
    private int numberBugsToRemove;
    private TextView bugsTextView;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        b = findViewById(R.id.patchItAround);
        b.setOnClickListener(this);

        bugsTextView = findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // get the main activity intent
        Intent intent = getIntent();

        // pull variables from the main activity intent
        this.numberBugsToRemove = intent.getExtras().getInt(MainActivity.BUG_NUMBER);
        this.bugsRemaining = intent.getExtras().getInt(MainActivity.BUGS_REMAINING);

        // set some text
        if(this.numberBugsToRemove > 0) {
            bugsTextView.setText(getBugs());
        } else {
            bugsTextView.setText("YOU WISH!!!");
            b.setText("Be realistic");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.patchItAround) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.BUGS_REMAINING, (this.bugsRemaining - this.numberBugsToRemove));

            // send the intent back to the main activity
            setResult(MainActivity.RESULT_OK, intent);

            // close this activity
            finish();
        }
    }

    private String getBugs() {
        String extra = " bug";
        if(this.numberBugsToRemove > 1)
            extra += "s";
        return this.bugsRemaining + " bugs left. Patch " + this.numberBugsToRemove + extra + "?";
    }
}
