package com.csbob.myfirstapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class DisplayMessageActivity extends AppCompatActivity {

    private static final String key = "SharedValues";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        Set<String> valueSet = new HashSet<>(preferences.getStringSet(key, new HashSet<String>()));
        valueSet.add(getIntent().getStringExtra(MyActivity.EXTRA_MESSAGE));

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(valueSet.toString());

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        Set<String> valueSet = new HashSet<>(preferences.getStringSet(key, new HashSet<String>()));
        valueSet.add(getIntent().getStringExtra(MyActivity.EXTRA_MESSAGE));

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.putStringSet(key, valueSet);
        editor.commit();
    }
}
