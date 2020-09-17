package com.smartrider24.model.customerresponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Customer_UnqId")
@Expose
private String customerUnqId;
@SerializedName("Customer_Nameval")
@Expose
private String customerNameval;
@SerializedName("Customer_MobNo")
@Expose
private String customerMobNo;
@SerializedName("Customer_EmailId")
@Expose
private String customerEmailId;
@SerializedName("Customer_Prfileimage")
@Expose
private Object customerPrfileimage;
@SerializedName("msg")
@Expose
private String msg;

public String getCustomerUnqId() {
return customerUnqId;
}

public void setCustomerUnqId(String customerUnqId) {
this.customerUnqId = customerUnqId;
}

public String getCustomerNameval() {
return customerNameval;
}

public void setCustomerNameval(String customerNameval) {
this.customerNameval = customerNameval;
}

public String getCustomerMobNo() {
return customerMobNo;
}

public void setCustomerMobNo(String customerMobNo) {
this.customerMobNo = customerMobNo;
}

public String getCustomerEmailId() {
return customerEmailId;
}

public void setCustomerEmailId(String customerEmailId) {
this.customerEmailId = customerEmailId;
}

public Object getCustomerPrfileimage() {
return customerPrfileimage;
}

public void setCustomerPrfileimage(Object customerPrfileimage) {
this.customerPrfileimage = customerPrfileimage;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}