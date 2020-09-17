package com.smartrider24.Interfaces;

public class trackDataRides {

    String id,name,timing;
    int imageLine;


    public trackDataRides(String name,String status,String timing) {
        this.name=name;
        this.id=status;
        this.timing=timing;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getImageLine() {
        return imageLine;
    }

    public void setImageLine(int imageLine) {
        this.imageLine = imageLine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
