package com.example.bar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.bar.view.Constants;
import com.example.bar.view.FetchAddressService;
import com.example.bar.view.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class ProjetoMapasActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int MY_LOCATION_REQUEST_CODE = 123;
    FusedLocationProviderClient client;
    AddressResultReceiver resultReceiver;
    GoogleMap mMap;
    PlacesClient placesClient;
    AutocompleteSessionToken token;
    int result;
    private boolean permissao = false;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        client = LocationServices.getFusedLocationProviderClient(this);
        resultReceiver = new AddressResultReceiver(null);

        String apikey = Constants.API_PLACES_KEY;
        Places.initialize(getApplicationContext(), apikey);
        placesClient = Places.createClient(this);
        token = AutocompleteSessionToken.newInstance();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("aaaaaaaaaaaaa", "cheguei");

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(permissao) {
            mMap = googleMap;
            mMap.setMinZoomPreference(6.0f);
            mMap.setMaxZoomPreference(20.0f);
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        result = ActivityCompat.checkSelfPermission(ProjetoMapasActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result != PackageManager.PERMISSION_GRANTED) {
            dialog = showDialog();
            dialog.show();
        }
        if (result == PackageManager.PERMISSION_GRANTED) {
            resumeMap();

        }
    }

    AlertDialog showDialog() {
        Log.e("aaaaaaaaaaaaaaa", "Mostrando a merda do dialogo");
        AlertDialog.Builder builder = new AlertDialog.Builder(ProjetoMapasActivity.this);
        builder.setMessage("Precisamos de acesso a sua localização para funcionar")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null)));
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        return builder.create();
    }

    private void resumeMap() {
        permissao = true;
        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        switch (errorCode) {
            case ConnectionResult.SERVICE_MISSING:
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
            case ConnectionResult.SERVICE_DISABLED:
                Log.d("Teste", "show dialog");
                GoogleApiAvailability.getInstance().getErrorDialog(this, errorCode, 0, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                }).show();
                break;
            case ConnectionResult.SUCCESS:
                Log.d("Teste", "Google Play Services up-to-date");
                break;

        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.i("Teste", location.getLatitude() + " " + location.getLongitude());

                    // Add a marker in Sydney and move the camera
                    LatLng origin = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(origin).title("Estou Aqui"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15));
                    mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                        @Override
                        public void onCameraIdle() {
                            FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder()
                                    .setCountry("BR")
                                    .setTypeFilter(TypeFilter.ADDRESS)
                                    .setSessionToken(token).setLocationRestriction(RectangularBounds.newInstance(
                                            mMap.getProjection().getVisibleRegion().latLngBounds
                                    ))
                                    .setQuery("Rua").build();


                            placesClient.findAutocompletePredictions(predictionsRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                                @Override
                                public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                                    if (task.isSuccessful()) {
                                        FindAutocompletePredictionsResponse result = task.getResult();
                                        if (result != null) {
                                            List<AutocompletePrediction> predictions = result.getAutocompletePredictions();
                                            for (AutocompletePrediction p : predictions) {
                                              /*  List<Place.Type> placeTypes = p.getPlaceTypes();
                                                for(Place.Type type: placeTypes){
                                                    Log.i("Teste Places", "type" + type.name());
                                                }

                                               */
                                                Log.i("Teste Places", p.getFullText(null).toString());
                                                Log.i("Teste Places", p.getPlaceId());
                                                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                                                FetchPlaceRequest request = FetchPlaceRequest.builder(p.getPlaceId(), fields).setSessionToken(token).build();
                                                placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                                                    @Override
                                                    public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                                                        Place place = fetchPlaceResponse.getPlace();
                                                        LatLng latLng = place.getLatLng();
                                                        mMap.addMarker(new MarkerOptions().position(latLng).title(place.getName()));

                                                    }
                                                });
                                            }

                                        }
                                    } else {
                                        Log.i("Teste Places", task.getException().getMessage());
                                    }
                                }
                            });
                        }
                    });

                    if (!Geocoder.isPresent()) {
                        Log.i("Teste", "Geocoder indisponivel");
                        return;
                    }

                } else {
                    Log.i("Teste", "null");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(15 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(builder.build()).addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Log.i("Teste", locationSettingsResponse.getLocationSettingsStates().isNetworkLocationPresent() + "");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(ProjetoMapasActivity.this, 10);
                    } catch (IntentSender.SendIntentException e1) {

                    }
                }
            }
        });

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult == null) {
                    Log.i("Teste2", "local is null");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    Log.i("Teste2", location.getLatitude() + "");
                    if (!Geocoder.isPresent()) {
                        return;
                    }
                    // startIntentService(location);
                }
            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                Log.i("Teste", locationAvailability.isLocationAvailable() + "");
            }
        };
        client.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void startIntentService(Location location) {
        Intent intent = new Intent(this, FetchAddressService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    private class AddressResultReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null) return;

            final String addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);

            if (addressOutput != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ProjetoMapasActivity.this, addressOutput, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

}
