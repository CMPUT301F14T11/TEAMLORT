package com.github.cmput301f14t11.teamlort;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.Profile;

public class SettingsActivity extends AppBaseActivity implements Observer,
		LocationListener {

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
	
	private TextView mAddress;
    private ProgressBar mActivityIndicator;

    /**
     * Method to set up location services, get profile information, attach listeners, and send it to the location string. 
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// Create location Manager
		locationManager = (LocationManager) getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);
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

	/**
	 * This method is called when the location of the user is changed or initalized. 
	 * It gets the latitude and longitude from the user and prints out the city name using the geocoder
	 */
	private void updateLocationString() {
		latitude = usrProfile.getLocation(locationManager).getLatitude();
		longitude = usrProfile.getLocation(locationManager).getLongitude();
		Context context = getApplicationContext();
		View view = new View(context);
		getAddress(view);

		// locationString.setText(latitude + "° " + longitude + "°");
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
		if (id == R.id.action_help) {
			helpscreen = getResources().getDrawable(
					R.drawable.helpscreen_geolocation);
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

	/**
	 * Gets the user profile.
	 * Used to get the users current location
	 */
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
		
		/**
		 * This method is used to set the location manually. 
		 * The user must have valid inputs in the latitude and longitude
		 * "Set Manually Button"
		 */
		setLocationBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SettingsActivity.this.onSetLocationButtonClicked();
			}
		});

		/**
		 * Used to set the location by GPS and not manually
		 * "Set By GPS Button"
		 */
		setByGPSBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SettingsActivity.this.onSetByGPSButtonClicked();
			}
		});

		/**
		 * Switches if the user wants to attach their location to posts
		 * "Attach Location"
		 */
		// set the switch to ON
		locationSwitch.setChecked(true);
		// attach a listener to check for changes in state
		locationSwitch
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						SettingsActivity.this.onSwitchChanged(isChecked);

					}

				});
	}

	/**
	 * This method controls the Attach location switch
	 * If the user wants to have their locations attached to their post they want the switch "on"
	 * This sets the setLocationServices boolean to true indicating that
	 * If the user does not want to have their location attached to their posts they want the swith "off"
	 * This sets the setLocaitonServices boolean to false indicating that 
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
	 * When the user clicks the "Set Manually" button, they are indicating that they do not want to use GPS to get their location 
	 * and that they want to set it manually.
	 * The user needs to enter valid latitude and longitude inputs.
	 * This sets the locationSetManually boolean to true indicating not to use GPS
	 * This sets their location and updates it
	 */
	protected void onSetLocationButtonClicked() {
		getInputFields();

		if (!isInputValid()) {
			return;
		} else {
			latitude = Double.parseDouble(edit_latitude.getText().toString());
			longitude = Double.parseDouble(edit_longitude.getText().toString());
			usrProfile.locationSetManually(true);
			usrProfile.setLocation(latitude, longitude);
			
			updateLocationString();
			this.setResult(RESULT_OK);

		}
	}

	/**
	 * This method is called when the user clicks on the set by GPS button. 
	 * This suggests that they no longer want to set their location manually
	 * It changes the locationSetManually to false saying that location is not being set manually anymore
	 * The location of the user is found then printed
	 */
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

	/**
	 * Checks that the input of the latitude and longitude is valid.
	 * A valid latitude input is > -90 and < 90 
	 * A valid longitude is > -180 and < 180
	 * @return true if input is valid, else false
	 */
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
	
	public void getAddress(View v) {
        // Ensure that a Geocoder services is available
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.GINGERBREAD
                            &&
                Geocoder.isPresent()) {
        	
        	Location location = locationManager.getLastKnownLocation(provider);
            // Show the activity indicator
            mActivityIndicator.setVisibility(View.VISIBLE);
            /*
             * Reverse geocoding is long-running and synchronous.
             * Run it on a background thread.
             * Pass the current location to the background task.
             * When the task finishes,
             * onPostExecute() displays the address.
             */
            (new GetAddressTask(this)).execute(location);
        }
    }

	private class GetAddressTask extends AsyncTask<Location, Void, String> {
		Context mContext;

		public GetAddressTask(Context context) {
			super();
			mContext = context;
		}

		/**
		 * Get a Geocoder instance, get the latitude and longitude look up the
		 * address, and return it
		 * 
		 * @params params One or more Location objects
		 * @return A string containing the address of the current location, or
		 *         an empty string if no address can be found, or an error
		 *         message
		 */
		@Override
		protected String doInBackground(Location... params) {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
			// Get the current location from the input parameter list
			Location loc = params[0];
			// Create a list to contain the result address
			List<Address> addresses = null;
			try {
				/*
				 * Return 1 address.
				 */
				addresses = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e1) {
				Log.e("LocationSampleActivity",
						"IO Exception in getFromLocation()");
				e1.printStackTrace();
				return ("IO Exception trying to get address");
			} catch (IllegalArgumentException e2) {
				// Error message to post in the log
				String errorString = "Illegal arguments "
						+ Double.toString(loc.getLatitude()) + " , "
						+ Double.toString(loc.getLongitude())
						+ " passed to address service";
				Log.e("LocationSampleActivity", errorString);
				e2.printStackTrace();
				return errorString;
			}
			// If the reverse geocode returned an address
			if (addresses != null && addresses.size() > 0) {
				// Get the first address
				Address address = addresses.get(0);
				/*
				 * Format the first line of address (if available), city, and
				 * country name.
				 */
				String addressText = String.format(
						"%s, %s, %s",
						// If there's a street address, add it
						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "",
						// Locality is usually a city
						address.getLocality(),
						// The country of the address
						address.getCountryName());
				// Return the text
				return addressText;
			} else {
				return "No address found";
			}
		}
		
        /**
         * A method that's called once doInBackground() completes. Turn
         * off the indeterminate activity indicator and set
         * the text of the UI element that shows the address. If the
         * lookup failed, display the error message.
         */
        @Override
        protected void onPostExecute(String address) {
            // Set activity indicator visibility to "gone"
            mActivityIndicator.setVisibility(View.GONE);
            // Display the results of the lookup.
            locationString.setText(address);
        }

	}
	
	

}