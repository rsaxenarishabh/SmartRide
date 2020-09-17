package com.smartrider24.model.driverbookpending;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayBookingHistory {

@SerializedName("Booking_RefNo.")
@Expose
private String bookingRefNo;
@SerializedName("Booking_trackNo.")
@Expose
private String bookingTrackNo;
@SerializedName("Booking_Date")
@Expose
private String bookingDate;
@SerializedName("Booking_PickUp_Location")
@Expose
private String bookingPickUpLocation;
@SerializedName("Booking_Delivery_Location")
@Expose
private String bookingDeliveryLocation;
@SerializedName("Booking_Distance")
@Expose
private String bookingDistance;
@SerializedName("Booking_Fare_Amt")
@Expose
private String bookingFareAmt;
@SerializedName("Booking_Status")
@Expose
private String bookingStatus;

    public String getBooking_Vechile_Name() {
        return Booking_Vechile_Name;
    }

    public void setBooking_Vechile_Name(String booking_Vechile_Name) {
        Booking_Vechile_Name = booking_Vechile_Name;
    }

    public String getBook_OPPICKUP_Loc() {
        return Book_OPPICKUP_Loc;
    }

    public void setBook_OPPICKUP_Loc(String book_OPPICKUP_Loc) {
        Book_OPPICKUP_Loc = book_OPPICKUP_Loc;
    }

    public String getBook_OPPDEL_Loc() {
        return Book_OPPDEL_Loc;
    }

    public void setBook_OPPDEL_Loc(String book_OPPDEL_Loc) {
        Book_OPPDEL_Loc = book_OPPDEL_Loc;
    }

    public String getBook_OPT_ContactNo() {
        return Book_OPT_ContactNo;
    }

    public void setBook_OPT_ContactNo(String book_OPT_ContactNo) {
        Book_OPT_ContactNo = book_OPT_ContactNo;
    }

    public String getBook_ITEM_Remark() {
        return Book_ITEM_Remark;
    }

    public void setBook_ITEM_Remark(String book_ITEM_Remark) {
        Book_ITEM_Remark = book_ITEM_Remark;
    }

    @SerializedName("Booking_Vechile_Name")
    @Expose
    private String Booking_Vechile_Name;
    @SerializedName("Book_OPPICKUP_Loc")
    @Expose
    private String Book_OPPICKUP_Loc;

    @SerializedName("Book_OPPDEL_Loc")
    @Expose
    private String Book_OPPDEL_Loc;

    @SerializedName("Book_OPT_ContactNo")
    @Expose
    private String Book_OPT_ContactNo;

    @SerializedName("Book_ITEM_Remark")
    @Expose
    private String Book_ITEM_Remark;


public String getBookingRefNo() {
return bookingRefNo;
}

public void setBookingRefNo(String bookingRefNo) {
this.bookingRefNo = bookingRefNo;
}

public String getBookingTrackNo() {
return bookingTrackNo;
}

public void setBookingTrackNo(String bookingTrackNo) {
this.bookingTrackNo = bookingTrackNo;
}

public String getBookingDate() {
return bookingDate;
}

public void setBookingDate(String bookingDate) {
this.bookingDate = bookingDate;
}

public String getBookingPickUpLocation() {
return bookingPickUpLocation;
}

public void setBookingPickUpLocation(String bookingPickUpLocation) {
this.bookingPickUpLocation = bookingPickUpLocation;
}

public String getBookingDeliveryLocation() {
return bookingDeliveryLocation;
}

public void setBookingDeliveryLocation(String bookingDeliveryLocation) {
this.bookingDeliveryLocation = bookingDeliveryLocation;
}

public String getBookingDistance() {
return bookingDistance;
}

public void setBookingDistance(String bookingDistance) {
this.bookingDistance = bookingDistance;
}

public String getBookingFareAmt() {
return bookingFareAmt;
}

public void setBookingFareAmt(String bookingFareAmt) {
this.bookingFareAmt = bookingFareAmt;
}

public String getBookingStatus() {
return bookingStatus;
}

public void setBookingStatus(String bookingStatus) {
this.bookingStatus = bookingStatus;
}

}