package de.fidepus.Zettelkasten;

import de.fidepus.Zettelkasten.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Dreimal extends Activity {


    @Override
	public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummytext);
        TextView textview = new TextView(this);
        textview.setText("Hier ist später alles drei mal gelernte.");
       // getWindow().addContentView(textview, new LayoutParams());
         setContentView(textview);
    	
    }
}
