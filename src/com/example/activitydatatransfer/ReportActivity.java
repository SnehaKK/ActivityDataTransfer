package com.example.activitydatatransfer;

import java.util.Map;

import com.example.activitydatatransfer.DataContract.DataEntry;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.method.ScrollingMovementMethod;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class ReportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_report);
		
		//To enable the scroll bars
		TextView sqliteView =(TextView) findViewById(R.id.sqlite_content);
		sqliteView.setMovementMethod(new ScrollingMovementMethod());
		TextView preferenceView =(TextView) findViewById(R.id.preference_content);
		preferenceView.setMovementMethod(new ScrollingMovementMethod());
		//Print all data from Shared Preferences
		getSharedPreferenceData();
		// Print all the data from SQLite
		getDataFromSqliteData();
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	
	public void getSharedPreferenceData(){
		SharedPreferences preference= getSharedPreferences(SaveData.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Map<String, ?> allData= preference.getAll();
		TextView preferenceView =(TextView) findViewById(R.id.preference_content);
		for(Map.Entry<String, ?> entry:allData.entrySet())
		{
			preferenceView.append(entry.getValue().toString() +"\n");
		}
	}

	public void getDataFromSqliteData(){
		DataDbHelper dbHelper = new DataDbHelper(this);
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
			data[c.getPosition() ] = "\nId: " +c.getString(0) + "\nFirst Name: " + c.getString(1)
					+ "\nLast Name: " + c.getString(2) + "\nAddress: " + c.getString(3) + "\n Credit Card Number: "
					+ c.getString(4);
			TextView text =(TextView) findViewById(R.id.sqlite_content);
			text.append("Id: " +c.getString(0) + "\nFirst Name: " + c.getString(1)
					+ "\nLast Name: " + c.getString(2) + "\nAddress: " + c.getString(3) + "\n Credit Card Number: "
					+ c.getString(4));
			System.out.println(c.getString(0) + ":" + c.getString(1) + ":"
					+ c.getString(2) + ":" + c.getString(3) + ":"
					+ c.getString(4));
			c.moveToNext();
		}
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
		getMenuInflater().inflate(R.menu.report, menu);
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

	public void closeApp(View view) {
		Intent mainActivityIntent = new Intent(this, MainActivity.class);
		mainActivityIntent.putExtra(MainActivity.CONTENT_ENTRY, "Exiting the current acivity to go to the main screen.");
		startActivity(mainActivityIntent);
		/*
		 * //Since this is not considered as a good practice I am setting the
		 * intent to go to the home page. finish(); System.exit(0);
		 */
	}

}
