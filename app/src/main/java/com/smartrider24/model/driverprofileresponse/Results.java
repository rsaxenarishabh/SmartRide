package com.smartrider24.model.driverprofileresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Driver_Name")
@Expose
private String driverName;
@SerializedName("Driver_Address_Proof")
@Expose
private String driverAddressProof;
@SerializedName("Driver_Licence")
@Expose
private String driverLicence;
@SerializedName("Vehicle_RC")
@Expose
private String vehicleRC;
@SerializedName("Vehicle_Insurance")
@Expose
private String vehicleInsurance;
@SerializedName("msg")
@Expose
private String msg;

public String getDriverName() {
return driverName;
}

public void setDriverName(String driverName) {
this.driverName = driverName;
}

public String getDriverAddressProof() {
return driverAddressProof;
}

public void setDriverAddressProof(String driverAddressProof) {
this.driverAddressProof = driverAddressProof;
}

public String getDriverLicence() {
return driverLicence;
}

public void setDriverLicence(String driverLicence) {
this.driverLicence = driverLicence;
}

public String getVehicleRC() {
return vehicleRC;
}

public void setVehicleRC(String vehicleRC) {
this.vehicleRC = vehicleRC;
}

public String getVehicleInsurance() {
return vehicleInsurance;
}

public void setVehicleInsurance(String vehicleInsurance) {
this.vehicleInsurance = vehicleInsurance;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}