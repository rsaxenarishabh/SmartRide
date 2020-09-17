package com.smartrider24.model.vehicle;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Vehicle_List")
@Expose
private List<VehicleList> vehicleList = null;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<VehicleList> getVehicleList() {
return vehicleList;
}

public void setVehicleList(List<VehicleList> vehicleList) {
this.vehicleList = vehicleList;
}

}
