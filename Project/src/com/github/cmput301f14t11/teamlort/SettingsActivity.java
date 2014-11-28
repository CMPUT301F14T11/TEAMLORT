package com.github.cmput301f14t11.teamlort;

import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.Profile;

public class SettingsActivity extends AppBaseActivity implements Observer {

	private double latitude, longitude;

	private Profile usrProfile;

	private EditText edit_latitude;
	private EditText edit_longitude;

	private Button setLocationBtn;

	private Switch locationSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		GetProfile();
		GetLayoutElements();
		AttachListeners();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}

	private void GetProfile() {
		usrProfile = AppCache.getInstance().getProfile();
	}

	/**
	 * Auxiliary method. Gets references to the view's layout elements.
	 */
	private void GetLayoutElements() {
		edit_latitude = (EditText) this.findViewById(R.id.latitude);
		edit_longitude = (EditText) this.findViewById(R.id.longitude);

		setLocationBtn = (Button) this.findViewById(R.id.set_location_btn);

		locationSwitch = (Switch) this.findViewById(R.id.locationSwitch);

	}

	/**
	 * Auxiliary method. Attaches onClick and onEditorAction listeners to the
	 * appropriate views.
	 */
	private void AttachListeners() {
		setLocationBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SettingsActivity.this.onSetLocationButtonClicked();
			}
		});

		// set the switch to ON
		locationSwitch.setChecked(true);
		// attach a listener to check for changes in state
		locationSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				
				SettingsActivity.this.onSwitchChanged(isChecked);

			}

		});
	}
	
	/**
	 * Auxiliary method. When the user clicks Accept, put everything together
	 * and send it to the controller.
	 */
	protected void onSwitchChanged(boolean isChecked) {
		if (isChecked) {
			usrProfile.setLocationServices(true);
		} else {
			usrProfile.setLocationServices(false);
		}
	}

	/**
	 * Auxiliary method. When the user clicks Accept, put everything together
	 * and send it to the controller.
	 */
	protected void onSetLocationButtonClicked() {
		getInputFields();

		if (!isInputValid())
			return;

		AppCache.getInstance().getProfile().setLocation(latitude, longitude);
		Log.i("distance","we just sat the locatiom, there better be some coordinates! :  "+AppCache.getInstance().getProfile().getLocation().toString());
		this.setResult(RESULT_OK);
		this.finish();

	}

	private void getInputFields() {
		latitude = Double.parseDouble(((EditText) findViewById(R.id.latitude))
				.getText().toString());
		longitude = Double
				.parseDouble(((EditText) findViewById(R.id.longitude))
						.getText().toString());

	}

	private boolean isInputValid() {
		// Make sure the user has a valid latitude (i.e. between -180 and 180)
		if (latitude < -180 || latitude > 180) {
			Toast.makeText(getApplicationContext(),
					"You must enter a valid latitude", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		// Make sure the user has a valid longitude (i.e. between -90 and 90)
		if (longitude > 90 || longitude < -90) {
			Toast.makeText(getApplicationContext(),
					"You must enter a valid longitude", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		return true;
	}
}
