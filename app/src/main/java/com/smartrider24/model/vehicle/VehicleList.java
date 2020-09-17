package com.smartrider24.model.vehicle;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleList {

@SerializedName("Req_Vehicle_Uid")
@Expose
private String reqVehicleUid;
@SerializedName("Req_Vehicle_Name")
@Expose
private String reqVehicleName;

public String getReqVehicleUid() {
return reqVehicleUid;
}

public void setReqVehicleUid(String reqVehicleUid) {
this.reqVehicleUid = reqVehicleUid;
}

public String getReqVehicleName() {
return reqVehicleName;
}

public void setReqVehicleName(String reqVehicleName) {
this.reqVehicleName = reqVehicleName;
}


 public VehicleList(String reqVehicleName){
    this.reqVehicleName=reqVehicleName;
 }
    @Override
    public String toString() {
        return reqVehicleName;
    }
}
