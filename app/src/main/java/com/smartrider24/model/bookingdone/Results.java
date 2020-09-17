package com.smartrider24.model.bookingdone;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("Booking_Reference_No.")
@Expose
private String bookingReferenceNo;
@SerializedName("Booking_Date_Time")
@Expose
private String bookingDateTime;
@SerializedName("msg")
@Expose
private String msg;

public String getBookingReferenceNo() {
return bookingReferenceNo;
}

public void setBookingReferenceNo(String bookingReferenceNo) {
this.bookingReferenceNo = bookingReferenceNo;
}

public String getBookingDateTime() {
return bookingDateTime;
}

public void setBookingDateTime(String bookingDateTime) {
this.bookingDateTime = bookingDateTime;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}