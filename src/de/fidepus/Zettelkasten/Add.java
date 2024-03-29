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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
 
public class Add extends Activity
{
	// the text fields that users input new data into
	EditText 	textFieldOne, textFieldTwo, textFieldThree,
				idField, 
				updateIDField, updateTextFieldOne, updateTextFieldTwo;
 
	// the buttons that listen for the user to select an action
	Button 		addButton, deleteButton, retrieveButton, updateButton;
 
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
    		setContentView(R.layout.add);
 
	        // create the database manager object
	        db = new AABDatabaseManager(this);
 
	        // create references and listeners for the GUI interface
	        setupViews();
 
	        // make the buttons clicks perform actions
	        addButtonListeners();
 
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
        textFieldThree= 	(EditText)findViewById(R.id.text_field_three);
        idField= 			(EditText)findViewById(R.id.id_field);
        updateIDField=		(EditText)findViewById(R.id.update_id_field);
        updateTextFieldOne=	(EditText)findViewById(R.id.update_text_field_one);
        updateTextFieldTwo=	(EditText)findViewById(R.id.update_text_field_two);
 
        // THE BUTTONS
        addButton = 		(Button)findViewById(R.id.add_button);
        deleteButton = 		(Button)findViewById(R.id.delete_button);
        retrieveButton =	(Button)findViewById(R.id.retrieve_button);
        updateButton = 		(Button)findViewById(R.id.update_button);
    }
 
 
 
 
    /**
     * adds listeners to each of the buttons and sets them to call relevant methods
     */
    private void addButtonListeners()
    {
        addButton.setOnClickListener
    	(
    		new View.OnClickListener()
	    	{
				@Override public void onClick(View v) {addRow();}
			}
    	);
 
        deleteButton.setOnClickListener
        (
        	new View.OnClickListener()
	        {
				@Override public void onClick(View v) {deleteRow();}
			}
        );
 
        updateButton.setOnClickListener
        (
        	new View.OnClickListener()
	        {
	        	@Override public void onClick(View v) {updateRow();}
	        }
        );
 
        retrieveButton.setOnClickListener
        (
        	new View.OnClickListener()
        	{
				@Override public void onClick(View v) {retrieveRow();}
			}
        );
 
    }
 
 
 
 
    /**
     * adds a row to the database based on information contained in the
     * add row fields.
     */
    private void addRow()
    {
    	try
    	{
    		// ask the database manager to add a row given the two strings
    		db.addRow
    		(
    				textFieldOne.getText().toString(),
    				//I think the null is where the gelernt field should be
    				textFieldTwo.getText().toString(),
    				textFieldThree.getText().toString()
    		);
 
    		// request the table be updated
	    	updateTable();
 
			// remove all user input from the Activity
    		emptyFormFields();
    	}
    	catch (Exception e)
    	{
    		Log.e("Add Error", e.toString());
    		e.printStackTrace();
    	}
    }
 
 
 
 
    /**
     * deletes a row from the database with the id number in the corresponding 
     * user entry field
     */
    private void deleteRow()
    {
    	try
    	{
    		// ask the database manager to delete the row with the give rowID.
    		db.deleteRow(Long.parseLong(idField.getText().toString()));
 
    		// request the table be updated
    		updateTable();
 
			// remove all user input from the Activity
    		emptyFormFields();
    	}
    	catch (Exception e)
    	{
    		Log.e("Delete Error", e.toString());
    		e.printStackTrace();
    	}
    }
 
 
 
 
    /**
     * retrieves a row from the database with the id number in the corresponding
     * user entry field
     */
    private void retrieveRow()
    {
    	try
    	{
    		// The ArrayList that holds the row data
    		ArrayList<Object> row;
    		// ask the database manager to retrieve the row with the given rowID
    		row = db.getRowAsArray(Long.parseLong(updateIDField.getText().toString()));
 
    		// update the form fields to hold the retrieved data
    		updateTextFieldOne.setText((String)row.get(1));
    		updateTextFieldTwo.setText((String)row.get(2));
    	}
    	catch (Exception e)
    	{
    		Log.e("Retrieve Error", e.toString());
    		e.printStackTrace();
    	}
    }
 
 
 
 
    /**
     * updates a row with the given information in the corresponding user entry
     * fields
     */
    private void updateRow()
    {
    	try
    	{
    		// ask the database manager to update the row based on the information
    		// found in the corresponding user entry fields
    		db.updateRow
    		(
    			Long.parseLong(updateIDField.getText().toString()),
    			updateTextFieldOne.getText().toString(),
    			// I think the null is where the gelernt value should be
    			updateTextFieldTwo.getText().toString(), null
    		);
 
    		// request the table be updated
			updateTable();
 
			// remove all user input from the Activity
    		emptyFormFields();
    	}
    	catch (Exception e)
    	{
    		Log.e("Update Error", e.toString());
    		e.printStackTrace();
    	}
    }
 
 
 
    /**
     * helper method to empty all the fields in all the forms.
     */
    private void emptyFormFields()
    {
        textFieldOne.setText("");
        textFieldTwo.setText("");
        idField.setText("");
        updateIDField.setText("");
        updateTextFieldOne.setText("");
        updateTextFieldTwo.setText("");
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
    	ArrayList<ArrayList<Object>> data = db.getAllRowsAsArrays();
 
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
 
    		TextView textTwo = new TextView(this);
    		textTwo.setText(row.get(2).toString());
    		tableRow.addView(textTwo);
 
//    		TextView textThree = new TextView(this);
//    		textThree.setText(row.get(3).toString());
//    		tableRow.addView(textThree);
    		
    		dataTable.addView(tableRow);
    	}
    }
}