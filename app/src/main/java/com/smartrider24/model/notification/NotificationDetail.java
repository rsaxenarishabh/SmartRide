package com.smartrider24.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationDetail {

@SerializedName("Notification_Topic_Heading")
@Expose
private String notificationTopicHeading;
@SerializedName("Notification_Details")
@Expose
private String notificationDetails;
@SerializedName("Notification_Date")
@Expose
private String notificationDate;

public String getNotificationTopicHeading() {
return notificationTopicHeading;
}

public void setNotificationTopicHeading(String notificationTopicHeading) {
this.notificationTopicHeading = notificationTopicHeading;
}

public String getNotificationDetails() {
return notificationDetails;
}

public void setNotificationDetails(String notificationDetails) {
this.notificationDetails = notificationDetails;
}

public String getNotificationDate() {
return notificationDate;
}

public void setNotificationDate(String notificationDate) {
this.notificationDate = notificationDate;
}

}
