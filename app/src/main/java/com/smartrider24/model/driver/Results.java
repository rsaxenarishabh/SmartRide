package com.smartrider24.model.driver;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Reg_No.")
@Expose
private String regNo;
@SerializedName("msg")
@Expose
private String msg;

public String getRegNo() {
return regNo;
}

public void setRegNo(String regNo) {
this.regNo = regNo;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}