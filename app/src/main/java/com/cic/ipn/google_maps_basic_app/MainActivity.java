package com.cic.ipn.google_maps_basic_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private LatLng[] sitios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGoogleMaps();
    }

    private void initGoogleMaps() {
        //Obtain the SupportMapFragment and get notified whem the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Mexico City and move the camera
        LatLng mexicoCity = new LatLng(19.432608, -99.133209);
        mMap.addMarker(new MarkerOptions().position(mexicoCity).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexicoCity));
        initMarkers();
    }

    private void initMarkers() {
        int numero_sitios = 8;
        this.sitios = new LatLng[numero_sitios];
        this.sitios[0] = new LatLng(19.685520,-98.868019);
        this.sitios[1] = new LatLng(-13.164419,-72.544764);
        this.sitios[2] = new LatLng(37.971722,23.726382);
        this.sitios[3] = new LatLng(-27.104671,-109.360481);
        this.sitios[4] = new LatLng(40.431908,116.570374);
        this.sitios[5] = new LatLng(40.749180,14.500740);
        this.sitios[6] = new LatLng(29.935167,52.890404);
        this.sitios[7] = new LatLng(17.222040,-89.623703);
    }

    private void moveCameraToPosition(LatLng newPosition) {
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(newPosition)      // Sets the center of the map to Mountain View
                .zoom(14)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().position(newPosition).title("Marker"));
    }

    @Override
    public void onClick(View view) {
        moveCameraToPosition(sitios[Integer.valueOf(view.getTag().toString())]);
    }

}
