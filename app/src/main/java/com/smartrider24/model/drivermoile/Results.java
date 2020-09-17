package com.smartrider24.model.drivermoile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Req_SessId")
@Expose
private String reqSessId;
@SerializedName("Req_Mobile_No.")
@Expose
private String reqMobileNo;
@SerializedName("msg")
@Expose
private String msg;

public String getReqSessId() {
return reqSessId;
}

public void setReqSessId(String reqSessId) {
this.reqSessId = reqSessId;
}

public String getReqMobileNo() {
return reqMobileNo;
}

public void setReqMobileNo(String reqMobileNo) {
this.reqMobileNo = reqMobileNo;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}