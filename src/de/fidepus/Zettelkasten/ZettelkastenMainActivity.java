package de.fidepus.Zettelkasten;

import de.fidepus.Zettelkasten.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class ZettelkastenMainActivity extends TabActivity

{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//C2DM.registerC2DM(this);

		//startService(new Intent(this, OnlineService.class));
		TabHost tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("1").setIndicator("neu", getResources().getDrawable(R.drawable.ic_tab_neu)).setContent(new Intent(this, Neu.class)));
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator("einmal", getResources().getDrawable(R.drawable.ic_tab_einmal)).setContent(new Intent(this, Einmal.class)));
		tabHost.addTab(tabHost.newTabSpec("3").setIndicator("zweimal", getResources().getDrawable(R.drawable.ic_tab_zweimal)).setContent(new Intent(this, Zweimal.class)));
		tabHost.addTab(tabHost.newTabSpec("4").setIndicator("dreimal", getResources().getDrawable(R.drawable.ic_tab_dreimal)).setContent(new Intent(this, Dreimal.class)));
	}
	
	 	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
		    case R.id.menuItemAdd:
	        Intent settingsActivity = new Intent(getBaseContext(), Add.class);
	        startActivity(settingsActivity);
		      return true;
		      
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	} 
}