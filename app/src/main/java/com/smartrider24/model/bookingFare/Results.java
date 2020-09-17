package com.smartrider24.model.bookingFare;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("Service_Vehicle_RefNo")
    @Expose
    private String serviceVehicleRefNo;
    @SerializedName("Service_Vehicle_Type")
    @Expose
    private String serviceVehicleType;
    @SerializedName("Service_Vehicle_Name")
    @Expose
    private String serviceVehicleName;
    @SerializedName("Service_Vehicle_Size")
    @Expose
    private String serviceVehicleSize;
    @SerializedName("Service_Pickup_Location")
    @Expose
    private String servicePickupLocation;
    @SerializedName("Service_Delivery_Location")
    @Expose
    private String serviceDeliveryLocation;
    @SerializedName("Service_Total_Distance")
    @Expose
    private Integer serviceTotalDistance;
    @SerializedName("Service_Fare")
    @Expose
    private Integer serviceFare;
    @SerializedName("Service_Fare_Details")
    @Expose
    private Integer serviceFareDetails;
    @SerializedName("Service_Booking_RefNo")
    @Expose
    private Integer serviceBookingRefNo;

    public String getService_Booking_Optional_PickupLoc() {
        return Service_Booking_Optional_PickupLoc;
    }

    public void setService_Booking_Optional_PickupLoc(String service_Booking_Optional_PickupLoc) {
        Service_Booking_Optional_PickupLoc = service_Booking_Optional_PickupLoc;
    }

    public String getService_Booking_Optional_DeliveryLoc() {
        return Service_Booking_Optional_DeliveryLoc;
    }

    public void setService_Booking_Optional_DeliveryLoc(String service_Booking_Optional_DeliveryLoc) {
        Service_Booking_Optional_DeliveryLoc = service_Booking_Optional_DeliveryLoc;
    }

    public String getService_Booking_Optional_ContactNo() {
        return Service_Booking_Optional_ContactNo;
    }

    public void setService_Booking_Optional_ContactNo(String service_Booking_Optional_ContactNo) {
        Service_Booking_Optional_ContactNo = service_Booking_Optional_ContactNo;
    }

    @SerializedName("Service_Booking_Optional_PickupLoc")
    @Expose
    private String Service_Booking_Optional_PickupLoc;
    @SerializedName("Service_Booking_Optional_DeliveryLoc")
    @Expose
    private String Service_Booking_Optional_DeliveryLoc;
    @SerializedName("Service_Booking_Optional_ContactNo")
    @Expose
    private String Service_Booking_Optional_ContactNo;

    public String getService_Booking_ITEM_Remark() {
        return Service_Booking_ITEM_Remark;
    }

    public void setService_Booking_ITEM_Remark(String service_Booking_ITEM_Remark) {
        Service_Booking_ITEM_Remark = service_Booking_ITEM_Remark;
    }

    @SerializedName("Service_Booking_ITEM_Remark")
    @Expose
    private String Service_Booking_ITEM_Remark;




    @SerializedName("msg")
    @Expose
    private String msg;

    public String getServiceVehicleRefNo() {
        return serviceVehicleRefNo;
    }

    public void setServiceVehicleRefNo(String serviceVehicleRefNo) {
        this.serviceVehicleRefNo = serviceVehicleRefNo;
    }

    public String getServiceVehicleType() {
        return serviceVehicleType;
    }

    public void setServiceVehicleType(String serviceVehicleType) {
        this.serviceVehicleType = serviceVehicleType;
    }

    public String getServiceVehicleName() {
        return serviceVehicleName;
    }

    public void setServiceVehicleName(String serviceVehicleName) {
        this.serviceVehicleName = serviceVehicleName;
    }

    public String getServiceVehicleSize() {
        return serviceVehicleSize;
    }

    public void setServiceVehicleSize(String serviceVehicleSize) {
        this.serviceVehicleSize = serviceVehicleSize;
    }

    public String getServicePickupLocation() {
        return servicePickupLocation;
    }

    public void setServicePickupLocation(String servicePickupLocation) {
        this.servicePickupLocation = servicePickupLocation;
    }

    public String getServiceDeliveryLocation() {
        return serviceDeliveryLocation;
    }

    public void setServiceDeliveryLocation(String serviceDeliveryLocation) {
        this.serviceDeliveryLocation = serviceDeliveryLocation;
    }

    public Integer getServiceTotalDistance() {
        return serviceTotalDistance;
    }

    public void setServiceTotalDistance(Integer serviceTotalDistance) {
        this.serviceTotalDistance = serviceTotalDistance;
    }

    public Integer getServiceFare() {
        return serviceFare;
    }

    public void setServiceFare(Integer serviceFare) {
        this.serviceFare = serviceFare;
    }

    public Integer getServiceFareDetails() {
        return serviceFareDetails;
    }

    public void setServiceFareDetails(Integer serviceFareDetails) {
        this.serviceFareDetails = serviceFareDetails;
    }

    public Integer getServiceBookingRefNo() {
        return serviceBookingRefNo;
    }

    public void setServiceBookingRefNo(Integer serviceBookingRefNo) {
        this.serviceBookingRefNo = serviceBookingRefNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}