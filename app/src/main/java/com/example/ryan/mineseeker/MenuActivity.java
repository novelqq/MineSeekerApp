package com.example.ryan.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setupPlayButton();
        setupOptionButton();
        setupHelpButton();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MenuActivity.class);
    }

    void setupPlayButton(){
        Button btn = (Button) findViewById(R.id.playbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });
    }

    void setupOptionButton(){
        Button btn = (Button) findViewById(R.id.optionbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OptionActivity.class);
                startActivity(intent);
            }
        });
    }
    void setupHelpButton(){
        Button btn = (Button) findViewById(R.id.helpbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }
}
