package com.smartrider24.model.driverconfirmride;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Driver_Booking_Details")
@Expose
private List<DriverBookingDetail> driverBookingDetails = null;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<DriverBookingDetail> getDriverBookingDetails() {
return driverBookingDetails;
}

public void setDriverBookingDetails(List<DriverBookingDetail> driverBookingDetails) {
this.driverBookingDetails = driverBookingDetails;
}

}