package com.smartrider24.model.customertracking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackBookingHistory {

@SerializedName("Tarck_Location")
@Expose
private String tarckLocation;
@SerializedName("Tarck_Status")
@Expose
private String tarckStatus;
@SerializedName("Tarck_DateTime")
@Expose
private String tarckDateTime;

public String getTarckLocation() {
return tarckLocation;
}

public void setTarckLocation(String tarckLocation) {
this.tarckLocation = tarckLocation;
}

public String getTarckStatus() {
return tarckStatus;
}

public void setTarckStatus(String tarckStatus) {
this.tarckStatus = tarckStatus;
}

public String getTarckDateTime() {
return tarckDateTime;
}

public void setTarckDateTime(String tarckDateTime) {
this.tarckDateTime = tarckDateTime;
}

}