package com.example.ryan.mineseeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupLinks();
        setupHelpInfo();
    }

    private void setupHelpInfo() {
        TextView t1 = (TextView) findViewById(R.id.helpbio);
    }

    private void setupLinks() {
        TextView t1 = (TextView) findViewById(R.id.helpinfo);
        TextView t2 = (TextView) findViewById(R.id.chaikaLink);
        TextView t3 = (TextView) findViewById(R.id.chaikaWallLink);
        TextView t4 = (TextView) findViewById(R.id.pastelLink);
        t1.setMovementMethod(LinkMovementMethod.getInstance());
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        t3.setMovementMethod(LinkMovementMethod.getInstance());
        t4.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
