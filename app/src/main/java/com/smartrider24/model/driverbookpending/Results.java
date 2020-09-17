package com.smartrider24.model.driverbookpending;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Today_Booking_History")
@Expose
private List<TodayBookingHistory> todayBookingHistory = null;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<TodayBookingHistory> getTodayBookingHistory() {
return todayBookingHistory;
}

public void setTodayBookingHistory(List<TodayBookingHistory> todayBookingHistory) {
this.todayBookingHistory = todayBookingHistory;
}

}