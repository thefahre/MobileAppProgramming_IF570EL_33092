package com.example.week13_33092;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private UiSettings mUiSettings;
    private GoogleMap mMap;
    static LatLng currentLocation;
    private List<MarkerAndShape> listMarkerAndShape = new ArrayList<MarkerAndShape>();
    private List<LatLng> localPoints = new ArrayList<LatLng>();
    private Button btnAdd;
    static final int REQUEST_ADD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnAdd = findViewById(R.id.addBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MapsActivity.this,AddObject.class);
                startActivityForResult(addIntent,REQUEST_ADD);
            }
        });

        currentLocation = new LatLng(-6.2574591,106.6183484);
        localPoints.add(currentLocation);
        listMarkerAndShape.add(new MarkerAndShape("Marker","UMN",0.0,localPoints));
        listMarkerAndShape.add(new MarkerAndShape("Circle","500m from UMN",500.0,localPoints));
        localPoints.clear();
        localPoints.add(new LatLng(-6.256718,106.618209));
        localPoints.add(new LatLng(-6.255982,106.618434));
        localPoints.add(new LatLng(-6.256061,106.621020));
        localPoints.add(new LatLng(-6.254611,106.622085));
        localPoints.add(new LatLng(-6.254752,106.622383));
        listMarkerAndShape.add(new MarkerAndShape("PolyLine","Path from UMN to Bethsaida",0.0,localPoints));
        localPoints.clear();
        localPoints.add(new LatLng(-6.256302,106.617534));
        localPoints.add(new LatLng(-6.256099,106.619744));
        localPoints.add(new LatLng(-6.256558,106.619851));
        localPoints.add(new LatLng(-6.259374,106.618639));
        localPoints.add(new LatLng(-6.258659,106.616740));
        listMarkerAndShape.add(new MarkerAndShape("Polygon","Area UMN",0.0,localPoints));
        localPoints.clear();
    }

    protected void DrawMarkerAndShape(){
        for(int i = 0;i<listMarkerAndShape.size();i++){
            MarkerAndShape ms = listMarkerAndShape.get(i);
            if(ms.getType().equalsIgnoreCase("Marker")){
                ms.setMapObj(mMap.addMarker(new MarkerOptions().position(ms.getPoints().get(0)).title(ms.getDesc())));
            }else if(ms.getType().equalsIgnoreCase("Circle")){
                ms.setMapObj(mMap.addCircle(new CircleOptions().center(ms.getPoints().get(0)).radius(ms.getRadius()).strokeColor(Color.YELLOW).fillColor(Color.argb(30,255,255,0))));
            } else if(ms.getType().equalsIgnoreCase("PolyLine")){
                PolylineOptions pl = new PolylineOptions();
                for(int j = 0; j<ms.getPointCount();j++){
                    pl.add(ms.getPoints().get(j));
                }
                pl.color(Color.RED).width(10.0f);
                ms.setMapObj(mMap.addPolyline(pl));
            } else if(ms.getType().equalsIgnoreCase("Polygon")){
                PolygonOptions pg = new PolygonOptions();
                for(int j =0;j<ms.getPointCount();j++){
                    pg.add(ms.getPoints().get(j));
                }
                pg.add(ms.getPoints().get(0)).strokeColor(Color.BLUE).strokeWidth(
                        10.0f).fillColor(Color.argb(20,0,255,255));
                ms.setMapObj(mMap.addPolygon(pg));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,16));
        DrawMarkerAndShape();
    }

    @Override
    public void onActivityResult(int reqCode,int resultCode, Intent data){
        super.onActivityResult(reqCode,resultCode,data);
        if(resultCode == RESULT_OK && reqCode == REQUEST_ADD){
            String type = data.getStringExtra("Type");
            String desc = data.getStringExtra("Description");
            double radius = data.getDoubleExtra("Radius",0.0);
            double[] dPoints = data.getDoubleArrayExtra("Points");
            List<LatLng> mPoints = new ArrayList<LatLng>();
            for(int i =0;i<dPoints.length/2;i++){
                mPoints.add(new LatLng(dPoints[2*i],dPoints[2*i+1]));
            }
            MarkerAndShape mds = new MarkerAndShape(type,desc,radius,mPoints);
            listMarkerAndShape.add(mds);
            if(mMap!=null){
                mMap.clear();
                DrawMarkerAndShape();
            }
        }
    }
}