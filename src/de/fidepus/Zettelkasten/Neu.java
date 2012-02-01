//package de.fidepus.Zettelkasten;
//
//import android.app.Activity;
//import android.os.Bundle;
//
//public class Add extends Activity {
//
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add);
//
//    	
//    }
//}

package de.fidepus.Zettelkasten;
 
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
 
public class Neu extends Activity
{
//	// the text fields that users input new data into
	EditText 	textFieldOne, textFieldTwo, textFieldThree,
				idField, 
				updateIDField, updatetextView1, updateTextFieldOne, updateTextFieldTwo;
 
//	// the buttons that listen for the user to select an action
//	Button 		addButton, deleteButton, retrieveButton, updateButton;
 
	// the table that displays the data
	TableLayout dataTable;
 
	// the class that opens or creates the database and makes sql calls to it
	AABDatabaseManager db;
 
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	// this try catch block returns better error reporting to the log
    	try
    	{
	        // Android specific calls
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.neu);
 
	        // create the database manager object
	        db = new AABDatabaseManager(this);
 
	        // create references and listeners for the GUI interface
	        setupViews();
 
//	        // make the buttons clicks perform actions
//	        addButtonListeners();
 
	        // load the data table
	    	updateTable();
    	}
    	catch (Exception e)
    	{
    		Log.e("ERROR", e.toString());
    		e.printStackTrace();
    	}
    }
 
 
 
 
    /**
     * creates references and listeners for the GUI interface
     */
    private void setupViews()
    {
    	// THE DATA TABLE
        dataTable=	 		(TableLayout)findViewById(R.id.data_table);
 
        // THE DATA FORM FIELDS
        textFieldOne= 		(EditText)findViewById(R.id.text_field_one);
        textFieldTwo= 		(EditText)findViewById(R.id.text_field_two);
        idField= 			(EditText)findViewById(R.id.id_field);
        updateIDField=		(EditText)findViewById(R.id.update_id_field);
        updateTextFieldOne=	(EditText)findViewById(R.id.update_text_field_one);
        updateTextFieldTwo=	(EditText)findViewById(R.id.update_text_field_two);
 
    }

 
    /**
     * retrieves a row from the database with the gelernt number 
     */
    private void retrieveRow()
    {
    	try
    	{
    		// The ArrayList that holds the row data
    		ArrayList<ArrayList<Object>> row;
    		// ask the database manager to retrieve the row with the given rowID
    		row = db.getGelerntRowsAsArrays(0);
 
    		// update the form fields to hold the retrieved data
//    		updatetextView1.setText((String)row.get(2));
//    		updateTextFieldTwo.setText((String)row.get(2));
    	}
    	catch (Exception e)
    	{
    		Log.e("Retrieve Error", e.toString());
    		e.printStackTrace();
    	}
    }

 
    /**
     * updates the table from the database.
     */
    private void updateTable()
    {
    	// delete all but the first row.  remember that the count 
    	// starts at one and the index starts at zero
    	while (dataTable.getChildCount() > 1)
    	{
    		// while there are at least two rows in the table widget, delete
    		// the second row.
    		dataTable.removeViewAt(1);
    	}
 
    	// collect the current row information from the database and
    	// store it in a two dimensional ArrayList
    	ArrayList<ArrayList<Object>> data = db.getGelerntRowsAsArrays(0);
 
    	// iterate the ArrayList, create new rows each time and add them
    	// to the table widget.
    	for (int position=0; position < data.size(); position++)
    	{
    		TableRow tableRow= new TableRow(this);
    		
    		ArrayList<Object> row = data.get(position);
    		
    		TextView idText = new TextView(this);
    		idText.setText(row.get(0).toString());
    		tableRow.addView(idText);
 
    		TextView textOne = new TextView(this);
    		textOne.setText(row.get(1).toString());
    		tableRow.addView(textOne);
    		
    		dataTable.addView(tableRow);
    	}
    }
}