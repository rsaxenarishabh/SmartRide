package com.smartrider24.model.notification;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse {

@SerializedName("results")
@Expose
private Results results;
@SerializedName("status")
@Expose
private String status;

public Results getResults() {
return results;
}

public void setResults(Results results) {
this.results = results;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}
