package com.smartrider24.model.customertracking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Driver_Details")
@Expose
private DriverDetails driverDetails;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public DriverDetails getDriverDetails() {
return driverDetails;
}

public void setDriverDetails(DriverDetails driverDetails) {
this.driverDetails = driverDetails;
}

}