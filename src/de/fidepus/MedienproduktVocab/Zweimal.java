package de.fidepus.MedienproduktVocab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Zweimal extends Activity {


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummytext);
        TextView textview = new TextView(this);
        textview.setText("Hier ist sp�ter alles zwei mal gelernte.");
       // getWindow().addContentView(textview, new LayoutParams());
         setContentView(textview);
    	
    }
}
