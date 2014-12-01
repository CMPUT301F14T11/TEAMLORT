package com.github.cmput301f14t11.teamlort;

import java.util.Observable;
import java.util.Observer;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.LocationController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.Profile;
import com.google.android.gms.internal.lc;

public class SettingsActivity extends AppBaseActivity implements Observer, LocationListener {

	private double latitude, longitude;

	private Profile usrProfile;

	private EditText edit_latitude;
	private EditText edit_longitude;

	private Button setLocationBtn;
	private Button setByGPSBtn;

	private Switch locationSwitch;
	
	private TextView locationString;
	
	private LocationManager locationManager;
	
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");

		}
		GetProfile();
		GetLayoutElements();
		AttachListeners();
		
		updateLocationString();





	}

	private void updateLocationString() {
		latitude = usrProfile.getLocation(locationManager).getLatitude();
		longitude = usrProfile.getLocation(locationManager).getLongitude();
		
		String cityName = "No Location Found";
		
		LocationController lc = new LocationController();
		cityName = lc.getLocationInfo(latitude, longitude);
		
		locationString.setText(cityName);
		
		
	
       
		//locationString.setText(latitude + "° " + longitude + "°");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_base, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_help) 
		{
	        helpscreen = getResources().getDrawable(R.drawable.helpscreen_geolocation);
	        AlertDialog.Builder alert = buildhelp(helpscreen);
			alertDialog = alert.show();
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
		usrProfile.setLocationServices(true);
	}

	/**
	 * Auxiliary method. Gets references to the view's layout elements.
	 */
	private void GetLayoutElements() {
		edit_latitude = (EditText) this.findViewById(R.id.latitude);
		edit_longitude = (EditText) this.findViewById(R.id.longitude);

		setLocationBtn = (Button) this.findViewById(R.id.set_location_btn);
		setByGPSBtn = (Button) this.findViewById(R.id.setByGPS_btn);

		locationSwitch = (Switch) this.findViewById(R.id.locationSwitch);
		
		locationString = (TextView) this.findViewById(R.id.location_string);

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
		
		setByGPSBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SettingsActivity.this.onSetByGPSButtonClicked();
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
			updateLocationString();
			locationString.setText(latitude + "° " + longitude + "°");
		} else {
			usrProfile.setLocationServices(false);
			locationString.setText("Location Services Off");
		}
	}

	/**
	 * Auxiliary method. When the user clicks Accept, put everything together
	 * and send it to the controller.
	 */
	protected void onSetLocationButtonClicked() {
		getInputFields();

		if (!isInputValid()) {
			return;
		} else {
			latitude = Double.parseDouble(edit_latitude.getText().toString());
			longitude = Double.parseDouble(edit_longitude.getText().toString());
			
			usrProfile.setLocation(latitude, longitude);
			usrProfile.locationSetManually(true);
			updateLocationString();
			this.setResult(RESULT_OK);

		}
	}
	
	protected void onSetByGPSButtonClicked() {
		usrProfile.locationSetManually(false);
		updateLocationString();
		this.setResult(RESULT_OK);
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
		if (latitude < -90 || latitude > 90) {
			Toast.makeText(getApplicationContext(),
					"You must enter a valid latitude", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		// Make sure the user has a valid longitude (i.e. between -90 and 90)
		if (longitude > 180 || longitude < -180) {
			Toast.makeText(getApplicationContext(),
					"You must enter a valid longitude", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	
	
	
	
	
	
	
	
}