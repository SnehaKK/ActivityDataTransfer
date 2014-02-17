package com.example.activitydatatransfer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ProcessData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Sets the data from the intent to a message variable
		// setIntentMessage();

		setContentView(R.layout.activity_process_data);

		// setContentView(R.layout.activity_process_data);
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
		getMenuInflater().inflate(R.menu.process_data, menu);
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

	/**
	 * Method to save the data from one intent to message.
	 * 
	 */
	public void setIntentMessage() {
		Intent intent = getIntent();
		String[] message = intent
				.getStringArrayExtra(MainActivity.CONTENT_ENTRY);
		System.out.println(message[0] + "\n" + message[1] + "\n" + message[2]
				+ "\n" + message[3]);

		setContentView(R.layout.activity_process_data);

		TextView result = (TextView) findViewById(R.id.result);
		result.setText(message[0] + "\n" + message[1] + "\n" + message[2]
				+ "\n" + message[3]);

	}

	/**
	 * To process the data
	 * 
	 * @param view
	 */

	public void processData(View view) {
		// Take the values form the main screen.
		Intent intent = getIntent();
		String[] message = intent
				.getStringArrayExtra(MainActivity.CONTENT_ENTRY);
		System.out.println(message[0] + "\n" + message[1] + "\n" + message[2]
				+ "\n" + message[3]);

		// Check for the Radio button selected
		RadioButton chkSelection = (RadioButton) findViewById(R.id.radioButton_success);

		// if Success then go to Save Activity
		if (chkSelection.isSelected()) {
			Intent saveDataIntent = new Intent(this, SaveData.class);
			saveDataIntent.putExtra(MainActivity.CONTENT_ENTRY, message);
			startActivity(saveDataIntent);
		}
		// if Error then go to the Main Activity and print error
		else if (findViewById(R.id.radioButton_error).isSelected()) {
			Intent errorIntent = new Intent(this, MainActivity.class);
			errorIntent.putExtra(MainActivity.ERROR_ENTRY,
					"Error in Processing data. Try again.");
			startActivity(errorIntent);
		} else {
			// Toast message to Select Success or Error radio button
			Toast toast = Toast.makeText(getApplicationContext(),
					"Select 'Success' or 'Error' radio button",
					Toast.LENGTH_SHORT);
			toast.show();
		}

	}

}
