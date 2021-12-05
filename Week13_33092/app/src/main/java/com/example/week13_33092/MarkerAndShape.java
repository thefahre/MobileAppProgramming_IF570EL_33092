package com.example.week13_33092;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MarkerAndShape {
    private String type = "Market";
    private String desc = "";
    private double radius = 0.0;
    private List<LatLng> points = new ArrayList<LatLng>();
    private Object mapObj = null;

    public MarkerAndShape(String type, String desc, double radius, List<LatLng> points){
        this.type = type;
        this.desc = desc;
        this.radius = radius;
        this.points = new ArrayList<LatLng>();
        this.points.addAll(points);
    }

    public String getType(){return this.type;}
    public String getDesc(){return this.desc;}
    public double getRadius(){return this.radius;}
    public List<LatLng> getPoints(){return this.points;}
    public int getPointCount(){return this.points.size();}
    public Object getMapObj(){return this.mapObj;}

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setPoints(List<LatLng> points) {
        this.points = points;
    }

    public void setMapObj(Object mapObj) {
        this.mapObj = mapObj;
    }
}
