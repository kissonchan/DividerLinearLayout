package com.example.testui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testui.widget.DividerLinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DividerLinearLayout dividerLinearLayout = (DividerLinearLayout) findViewById(R.id.activity_main);
        //set divider color
        dividerLinearLayout.setDividerColor(Color.parseColor("#4a4a4a"));
        //set divider width
        dividerLinearLayout.setDividerWidth(10);

        dividerLinearLayout.setFooterDividerPadding(10,10);
        dividerLinearLayout.setHeaderDividerPadding(10,10);
        dividerLinearLayout.showFooterDivider(true);
        dividerLinearLayout.showHeaderDivider(true);
    }
}
