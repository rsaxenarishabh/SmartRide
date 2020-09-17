package com.smartrider24.model.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Login_User_Role")
@Expose
private String loginUserRole;
@SerializedName("Customer_Unqid")
@Expose
private String customerUnqid;
@SerializedName("Customer_UName")
@Expose
private String customerUName;
@SerializedName("Customer_MobNo")
@Expose
private String customerMobNo;
@SerializedName("Customer_LSessId.")
@Expose
private String customerLSessId;
@SerializedName("Customer_status")
@Expose
private String customerStatus;
@SerializedName("Customer_Prfileimage")
@Expose
private String customerPrfileimage;
@SerializedName("msg")
@Expose
private String msg;

public String getLoginUserRole() {
return loginUserRole;
}

public void setLoginUserRole(String loginUserRole) {
this.loginUserRole = loginUserRole;
}

public String getCustomerUnqid() {
return customerUnqid;
}

public void setCustomerUnqid(String customerUnqid) {
this.customerUnqid = customerUnqid;
}

public String getCustomerUName() {
return customerUName;
}

public void setCustomerUName(String customerUName) {
this.customerUName = customerUName;
}

public String getCustomerMobNo() {
return customerMobNo;
}

public void setCustomerMobNo(String customerMobNo) {
this.customerMobNo = customerMobNo;
}

public String getCustomerLSessId() {
return customerLSessId;
}

public void setCustomerLSessId(String customerLSessId) {
this.customerLSessId = customerLSessId;
}

public String getCustomerStatus() {
return customerStatus;
}

public void setCustomerStatus(String customerStatus) {
this.customerStatus = customerStatus;
}

public String getCustomerPrfileimage() {
return customerPrfileimage;
}

public void setCustomerPrfileimage(String customerPrfileimage) {
this.customerPrfileimage = customerPrfileimage;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}