package com.example.activitydatatransfer;

import com.example.activitydatatransfer.DataContract.DataEntry;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class SaveData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_data);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void saveUsingPreferences(View view) {
		// Get the data to be saved from the intent
		Intent intent = getIntent();
		String[] entry = intent.getStringArrayExtra(MainActivity.CONTENT_ENTRY);
		System.out.println(entry[0] + "\n" + entry[1] + "\n" + entry[2] + "\n"
				+ entry[3]);

		// Save the data using preference

		// go to the Main Activity
		Intent mainActivityIntent = new Intent(this, MainActivity.class);
		mainActivityIntent.putExtra(MainActivity.CONTENT_ENTRY,
				"Entry Successfully saved using Shared Preferences.");
		startActivity(mainActivityIntent);
	}

	public void saveUsingSqlite(View view) {
		// Get the data to be saved from the intent
		Intent intent = getIntent();
		String[] entry = intent.getStringArrayExtra(MainActivity.CONTENT_ENTRY);
		System.out.println(entry[0] + "\n" + entry[1] + "\n" + entry[2] + "\n"
				+ entry[3]);
		// Save the data using SQLite
		DataDbHelper dbHelper = new DataDbHelper(this);

		// Gets the data repository in write mode
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(DataEntry.COLUMN_NAME_FIRST_NAME, entry[0]);
		values.put(DataEntry.COLUMN_NAME_LAST_NAME, entry[1]);
		values.put(DataEntry.COLUMN_NAME_ADDRESS, entry[2]);
		values.put(DataEntry.COLUMN_NAME_CC_NUMBER, entry[3]);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(DataEntry.TABLE_NAME, null, values);
		if (newRowId == -1) {
			Toast toast = Toast.makeText(this,
					"Error in saving the file. Please try again! ",
					Toast.LENGTH_SHORT);
			toast.show();
		}
		
		/**
		 *  Checking for the correct insertions
		 * 

		SQLiteDatabase dbRead = dbHelper.getWritableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { DataEntry._ID,
				DataEntry.COLUMN_NAME_FIRST_NAME,
				DataEntry.COLUMN_NAME_LAST_NAME, DataEntry.COLUMN_NAME_ADDRESS,
				DataEntry.COLUMN_NAME_CC_NUMBER };
		
		Cursor c = dbRead.query(DataEntry.TABLE_NAME, projection, null, null,
				null, null, null);
		c.moveToFirst();
		String[] data = new String[c.getCount() + 1];
		System.out.println("Cursor Position"+c.getPosition() + " :Cursor Count " + c.getCount());
		while (c.getPosition() < c.getCount()) {
			data[c.getPosition() ] = "Id: " +c.getString(0) + "\nFirst Name: " + c.getString(1)
					+ "\nLast Name: " + c.getString(2) + "\nAddress: " + c.getString(3) + "\n Credit Card Number: "
					+ c.getString(4);

			System.out.println(c.getString(0) + ":" + c.getString(1) + ":"
					+ c.getString(2) + ":" + c.getString(3) + ":"
					+ c.getString(4));
			c.moveToNext();
		}
		*/
		// go to the Main Activity
		Intent mainActivityIntent = new Intent(this, MainActivity.class);
		mainActivityIntent.putExtra(MainActivity.CONTENT_ENTRY,
				"Entry Successfully saved using SQLite.");
		startActivity(mainActivityIntent);
	}
}
