package com.smartrider24.model.notification;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Notification_Details")
@Expose
private List<NotificationDetail> notificationDetails = null;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<NotificationDetail> getNotificationDetails() {
return notificationDetails;
}

public void setNotificationDetails(List<NotificationDetail> notificationDetails) {
this.notificationDetails = notificationDetails;
}

}