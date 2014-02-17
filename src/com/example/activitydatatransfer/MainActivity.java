package com.example.activitydatatransfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String CONTENT_ENTRY = "com.example.activitydatatransfer.CONTENT_ENTRY";
	public final static String ERROR_ENTRY = "com.example.activitydatatransfer.ERROR_ENTRY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Called when user clicks Process Data button
	 * 
	 * @param view
	 */
	public void processData(View view) {
		String[] entry = new String[4];
		Intent intent = new Intent(this, ProcessData.class);
		EditText editText;
		editText = (EditText) findViewById(R.id.edit_first_name);
		entry[0] = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_last_name);
		entry[1] = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_address);
		entry[2] = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_cc_number);
		entry[3] = editText.getText().toString();

		System.out.println("In the ProcessData().Entry values are " + entry[0]
				+ ", " + entry[1] + ", " + entry[2] + ", " + entry[3]);
		intent.putExtra(CONTENT_ENTRY, entry);
		startActivity(intent);
	}

	/***
	 * To move to Save Data Activity
	 * 
	 */
	public void saveData(View view) {

		String[] entry = new String[4];
		Intent intent = new Intent(this, SaveData.class);
		EditText editText;
		editText = (EditText) findViewById(R.id.edit_first_name);
		entry[0] = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_last_name);
		entry[1] = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_address);
		entry[2] = editText.getText().toString();

		editText = (EditText) findViewById(R.id.edit_cc_number);
		entry[3] = editText.getText().toString();

		System.out.println("In the SaveData().Entry values are " + entry[0]
				+ ", " + entry[1] + ", " + entry[2] + ", " + entry[3]);
		intent.putExtra(CONTENT_ENTRY, entry);
		startActivity(intent);
	}

	/***
	 * To go to Report Data Activity
	 */
	public void reportData(View view) {
		// Go to Report Activity
		Intent intent = new Intent(this, ReportActivity.class);

		intent.putExtra(CONTENT_ENTRY, "Passing intent to view the saved data");
		startActivity(intent);

	}

	/**
	 * To close the application
	 */
	public void closeApp(View view) {

		Intent mainActivityIntent = new Intent(this, MainActivity.class);
		mainActivityIntent.putExtra(MainActivity.CONTENT_ENTRY,
				"Exiting the current acivity to go to the main screen.");
		startActivity(mainActivityIntent);
		/*
		 * //Since this is not considered as a good practice I am setting the
		 * intent to go to the home page. finish(); System.exit(0);
		 */
	}
}
