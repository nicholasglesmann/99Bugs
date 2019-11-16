package com.glesmannn.a99bugs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BUG_NUMBER = "bug_number";
    public static final String BUGS_REMAINING = "bugs_remaining";
    public static final int RESULT_OK = 1;
    public static final int FIRST_REQUEST = 0;
    private int bugsRemaining;
    private TextView bugsMain;
    private Button bOne;
    private Button bTwo;
    private Button bThree;
    private Button bAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bOne = findViewById(R.id.takeOneDown);
        bTwo = findViewById(R.id.takeTwoDown);
        bThree = findViewById(R.id.takeThreeDown);
        bAll = findViewById(R.id.takeEmAllDown);
        bugsMain = findViewById(R.id.bugsMain);

        bOne.setOnClickListener(this);
        bTwo.setOnClickListener(this);
        bThree.setOnClickListener(this);
        bAll.setOnClickListener(this);

        // initialize stuff
        this.bugsRemaining = 99;
        bugsMain.setText(getBugs());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);

        switch (v.getId()) {
            case R.id.takeOneDown:
                intent.putExtra(BUG_NUMBER, 1);
                break;
            case R.id.takeTwoDown:
                intent.putExtra(BUG_NUMBER, 2);
                break;
            case R.id.takeThreeDown:
                intent.putExtra(BUG_NUMBER, 3);
                break;
            case R.id.takeEmAllDown:
                intent.putExtra(BUG_NUMBER, 0);
        }

        intent.putExtra(BUGS_REMAINING, this.bugsRemaining);

        // start the second activity with the intention of getting a result back
        startActivityForResult(intent, FIRST_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);

        if(requestCode == FIRST_REQUEST) {

            if (resultCode == RESULT_OK) {
                // get the bugs remaining from the second activity
                this.bugsRemaining = resultIntent.getExtras().getInt(BUGS_REMAINING);
                bugsMain.setText(getBugs());
                if(this.bugsRemaining <= 0) {
                    bugsMain.setText("No bugs left!!! (Or so you think...)");
                    bOne.setEnabled(false);
                    bTwo.setEnabled(false);
                    bThree.setEnabled(false);
                    bAll.setEnabled(false);
                }
            }
        }
    }

    private String getBugs() {
        return this.bugsRemaining + " little bugs left...";
    }
}
