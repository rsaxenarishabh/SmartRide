package com.smartrider24.model.customertracking;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverDetails {

@SerializedName("Driver_Name")
@Expose
private String driverName;
@SerializedName("Driver_MobileNo.")
@Expose
private String driverMobileNo;
@SerializedName("Driver_VechNo.")
@Expose
private String driverVechNo;
@SerializedName("Track_Booking_History")
@Expose
private List<TrackBookingHistory> trackBookingHistory = null;

public String getDriverName() {
return driverName;
}

public void setDriverName(String driverName) {
this.driverName = driverName;
}

public String getDriverMobileNo() {
return driverMobileNo;
}

public void setDriverMobileNo(String driverMobileNo) {
this.driverMobileNo = driverMobileNo;
}

public String getDriverVechNo() {
return driverVechNo;
}

public void setDriverVechNo(String driverVechNo) {
this.driverVechNo = driverVechNo;
}

public List<TrackBookingHistory> getTrackBookingHistory() {
return trackBookingHistory;
}

public void setTrackBookingHistory(List<TrackBookingHistory> trackBookingHistory) {
this.trackBookingHistory = trackBookingHistory;
}

}