package com.smartrider24.Interfaces;

import java.io.Serializable;

public class Datum implements Serializable {
    String id,rating,sourceName,sDistrict,destinationName,dDistrict,vehicleName,vehicleDetails,timing,goodsInfo,distance,chargeFees;

    public Datum(String id,String rating,String sourceName,String sDistrict,String destinationName,String dDistrict,
                 String vehicleName,String vehicleDetails,String timing,String goodsInfo,String distance,String chargeFees) {

        this.id=id;
        this.rating=rating;
        this.sourceName=sourceName;
        this.sDistrict=sDistrict;
        this.destinationName=destinationName;
        this.dDistrict=dDistrict;
        this.vehicleName=vehicleName;
        this.vehicleDetails=vehicleDetails;
        this.timing=timing;
        this.goodsInfo=goodsInfo;
        this.distance=distance;
        this.chargeFees=chargeFees;

    }

    public Datum(String id,String sourceName,String sDistrict,String destinationName,String dDistrict,String vehicleName,String vehicleDetails,String timing,String goodsInfo,String distance,String chargeFees) {
    this.id=id;
    this.sourceName=sourceName;
    this.sDistrict=sDistrict;
        this.id=id;
        this.destinationName=destinationName;
        this.dDistrict=dDistrict;
        this.vehicleName=vehicleName;
        this.vehicleDetails=vehicleDetails;
        this.timing=timing;
        this.goodsInfo=goodsInfo;
        this.distance=distance;
        this.chargeFees=chargeFees;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getsDistrict() {
        return sDistrict;
    }

    public void setsDistrict(String sDistrict) {
        this.sDistrict = sDistrict;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getdDistrict() {
        return dDistrict;
    }

    public void setdDistrict(String dDistrict) {
        this.dDistrict = dDistrict;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getChargeFees() {
        return chargeFees;
    }

    public void setChargeFees(String chargeFees) {
        this.chargeFees = chargeFees;
    }
}
