package com.smartrider24.model.loginotp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Req_SessId")
@Expose
private String reqSessId;
@SerializedName("msg")
@Expose
private String msg;

public String getReqSessId() {
return reqSessId;
}

public void setReqSessId(String reqSessId) {
this.reqSessId = reqSessId;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}