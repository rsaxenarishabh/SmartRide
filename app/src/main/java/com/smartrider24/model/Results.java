package com.smartrider24.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Results {

@SerializedName("Customer_UName")
@Expose
private String customerUName;
@SerializedName("Customer_MobileNo.")
@Expose
private String customerMobileNo;
@SerializedName("Customer_GSTNO.")
@Expose
private String customerGSTNO;
@SerializedName("Customer_AreaName")
@Expose
private String customerAreaName;
@SerializedName("Customer_CityName")
@Expose
private String customerCityName;
@SerializedName("Customer_StateName")
@Expose
private String customerStateName;
@SerializedName("Customer_RefralCode")
@Expose
private String customerRefralCode;
@SerializedName("Customer_Password")
@Expose
private String customerPassword;
@SerializedName("Customer_status")
@Expose
private String customerStatus;
@SerializedName("Customer_Prfileimage")
@Expose
private String customerPrfileimage;
@SerializedName("msg")
@Expose
private String msg;

public String getCustomerUName() {
return customerUName;
}

public void setCustomerUName(String customerUName) {
this.customerUName = customerUName;
}

public String getCustomerMobileNo() {
return customerMobileNo;
}

public void setCustomerMobileNo(String customerMobileNo) {
this.customerMobileNo = customerMobileNo;
}

public String getCustomerGSTNO() {
return customerGSTNO;
}

public void setCustomerGSTNO(String customerGSTNO) {
this.customerGSTNO = customerGSTNO;
}

public String getCustomerAreaName() {
return customerAreaName;
}

public void setCustomerAreaName(String customerAreaName) {
this.customerAreaName = customerAreaName;
}

public String getCustomerCityName() {
return customerCityName;
}

public void setCustomerCityName(String customerCityName) {
this.customerCityName = customerCityName;
}

public String getCustomerStateName() {
return customerStateName;
}

public void setCustomerStateName(String customerStateName) {
this.customerStateName = customerStateName;
}

public String getCustomerRefralCode() {
return customerRefralCode;
}

public void setCustomerRefralCode(String customerRefralCode) {
this.customerRefralCode = customerRefralCode;
}

public String getCustomerPassword() {
return customerPassword;
}

public void setCustomerPassword(String customerPassword) {
this.customerPassword = customerPassword;
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