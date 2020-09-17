package com.smartrider24.model.bookinghistory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyBookingHistory {

@SerializedName("Booking_RefNo.")
@Expose
private String bookingRefNo;
@SerializedName("Booking_trackNo.")
@Expose
private String bookingTrackNo;
@SerializedName("Booking_Vechile_Name")
@Expose
private String bookingVechileName;
@SerializedName("Booking_PickUp_Location")
@Expose
private String bookingPickUpLocation;
@SerializedName("Booking_Delivery_Location")
@Expose
private String bookingDeliveryLocation;
@SerializedName("Booking_Distance")
@Expose
private String bookingDistance;
@SerializedName("Booking_Fare_Amt")
@Expose
private String bookingFareAmt;
@SerializedName("Booking_Status")
@Expose
private String bookingStatus;
@SerializedName("Driver_Name")
@Expose
private String driverName;
@SerializedName("Driver_MobileNo.")
@Expose
private String driverMobileNo;

public String getBookingRefNo() {
return bookingRefNo;
}

public void setBookingRefNo(String bookingRefNo) {
this.bookingRefNo = bookingRefNo;
}

public String getBookingTrackNo() {
return bookingTrackNo;
}

public void setBookingTrackNo(String bookingTrackNo) {
this.bookingTrackNo = bookingTrackNo;
}

public String getBookingVechileName() {
return bookingVechileName;
}

public void setBookingVechileName(String bookingVechileName) {
this.bookingVechileName = bookingVechileName;
}

public String getBookingPickUpLocation() {
return bookingPickUpLocation;
}

public void setBookingPickUpLocation(String bookingPickUpLocation) {
this.bookingPickUpLocation = bookingPickUpLocation;
}

public String getBookingDeliveryLocation() {
return bookingDeliveryLocation;
}

public void setBookingDeliveryLocation(String bookingDeliveryLocation) {
this.bookingDeliveryLocation = bookingDeliveryLocation;
}

public String getBookingDistance() {
return bookingDistance;
}

public void setBookingDistance(String bookingDistance) {
this.bookingDistance = bookingDistance;
}

public String getBookingFareAmt() {
return bookingFareAmt;
}

public void setBookingFareAmt(String bookingFareAmt) {
this.bookingFareAmt = bookingFareAmt;
}

public String getBookingStatus() {
return bookingStatus;
}

public void setBookingStatus(String bookingStatus) {
this.bookingStatus = bookingStatus;
}

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

}