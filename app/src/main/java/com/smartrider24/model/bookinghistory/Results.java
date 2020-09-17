package com.smartrider24.model.bookinghistory;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("My_Booking_History")
@Expose
private List<MyBookingHistory> myBookingHistory = null;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<MyBookingHistory> getMyBookingHistory() {
return myBookingHistory;
}

public void setMyBookingHistory(List<MyBookingHistory> myBookingHistory) {
this.myBookingHistory = myBookingHistory;
}

}