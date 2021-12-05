package com.example.week13_33092;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

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

public class AddObject extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int choice = 0;
    private Button btnSave;
    private EditText etRadius,etDesc;
    private RadioGroup rgShape;
    private List<LatLng> points;
    private UiSettings mUiSettings;
    private static MarkerAndShape objMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addMap);
        mapFragment.getMapAsync(this);
        btnSave = findViewById(R.id.btnSave);
        etRadius = findViewById(R.id.etRadius);
        etDesc = findViewById(R.id.etDesc);
        rgShape = findViewById(R.id.rgShape);
        points = new ArrayList<LatLng>();
        rgShape.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                points.clear();
                btnSave.setEnabled(false);
                if(mMap!=null){
                    mMap.clear();
                }switch (checkedId){
                    case R.id.rMarker:
                        choice = 0;
                        etRadius.setEnabled(false);
                        break;
                    case R.id.rCircle:
                        choice=1;
                        etRadius.setEnabled(true);
                        break;
                    case R.id.rPolyline:
                        choice=2;
                        etRadius.setEnabled(false);
                        break;
                    case R.id.rPolygon:
                        choice=3;
                        etRadius.setEnabled(false);
                        break;
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objMS.getDesc().equalsIgnoreCase(etDesc.getText().toString()));
                Intent repIntent = new Intent();
                repIntent.putExtra("Type",objMS.getType());
                repIntent.putExtra("Description",objMS.getDesc());
                repIntent.putExtra("Radius",objMS.getRadius());
                List<LatLng> mPoints = objMS.getPoints();
                double[] dPoints = new double[mPoints.size()*2];
                for(int i =0;i<mPoints.size();i++){
                    dPoints[2*i] = mPoints.get(i).latitude;
                    dPoints[2*i+1]=mPoints.get(i).longitude;
                }
                repIntent.putExtra("Points",dPoints);
                setResult(RESULT_OK,repIntent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapsActivity.currentLocation,15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                switch (choice){
                    case 0:
                        if(points.size()==0){
                            points.add(latLng);
                        }else{
                            points.set(0,latLng);
                        }
                        if(mMap!=null){
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions().position(points.get(0)).title(etDesc.getText().toString()));
                            objMS = new MarkerAndShape("Marker",etDesc.getText().toString(),0.0,points);
                            btnSave.setEnabled(true);
                        }
                        break;
                    case 1:
                        if(points.size()==0){
                            points.add(latLng);
                        }else{
                            points.set(0,latLng);
                        }
                        if(mMap!=null){
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions().position(points.get(0)).title(etDesc.getText().toString()));
                            String rad = etRadius.getText().toString().trim();
                            if(rad.length()>0 && Double.parseDouble(rad)>0.0){
                                double radius = Double.parseDouble(rad);
                                mMap.addCircle(new CircleOptions().radius(radius).center(points.get(0)).strokeColor(Color.YELLOW).fillColor(Color.argb(30,255,255,0)));
                                objMS = new MarkerAndShape("Circle",etDesc.getText().toString(),radius,points);
                                btnSave.setEnabled(true);
                            }
                        }
                        break;
                    case 2:
                        points.add(latLng);
                        if(mMap!=null){
                            mMap.clear();
                        }
                        if(points.size()==1){
                            mMap.addMarker(new MarkerOptions().position(points.get(0)));
                        }else{
                            PolylineOptions pl = new PolylineOptions().color(Color.RED).width(10.0f);
                            for(int i = 0;i<points.size();i++){
                                mMap.addMarker(new MarkerOptions().position(points.get(i)));
                                pl.add(points.get(i));
                            }
                            mMap.addPolyline(pl);
                            objMS = new MarkerAndShape("PolyLine",etDesc.getText().toString(),0,points);
                            btnSave.setEnabled(true);
                        }
                        break;
                    case 3:
                        points.add(latLng);
                        if(mMap!=null){
                            mMap.clear();
                    }if(points.size()==1){
                            mMap.addMarker(new MarkerOptions().position(points.get(0)));
                    }else if(points.size()==2){
                            PolylineOptions pl = new PolylineOptions().color(Color.RED).width(10.0f);
                            for(int i = 0;i<points.size();i++){
                                mMap.addMarker(new MarkerOptions().position(points.get(i)));
                                pl.add(points.get(i));
                            }
                            mMap.addPolyline(pl);
                    }else if(points.size()>=2){
                        PolygonOptions pg = new PolygonOptions().strokeColor(Color.BLUE).strokeWidth(10.0f).fillColor(Color.argb(20,0,255,255));
                        for(int i =0;i<points.size();i++){
                            mMap.addMarker(new MarkerOptions().position(points.get(i)));
                            pg.add(points.get(i));
                        }
                        pg.add(points.get(0));
                        mMap.addPolygon(pg);
                        objMS = new MarkerAndShape("Polygon",etDesc.getText().toString(),0,points);
                        btnSave.setEnabled(true);
                    }
                    break;
                }
            }
        });
    }
}