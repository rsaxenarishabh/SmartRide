package com.smartrider24.Retrofit;


import com.smartrider24.model.CustomerRegistration;
import com.smartrider24.model.accountStatus.AccountResponse;
import com.smartrider24.model.accountdetailstatement.AccountDetailStatementResponse;
import com.smartrider24.model.bookingFare.BookingDetailFareResponse;
import com.smartrider24.model.bookingdone.BookingSuccessResponse;
import com.smartrider24.model.bookinghistory.BookingHistorySuccessResponse;
import com.smartrider24.model.customerresponse.CusstomerProfileResponse;
import com.smartrider24.model.customertracking.CustomerTrackingResponse;
import com.smartrider24.model.driver.DriverRegistrationResponse;
import com.smartrider24.model.driveraccept.DriverAcceptBookingResponse;
import com.smartrider24.model.driverbookpending.DriverBookingPendingResponse;
import com.smartrider24.model.driverconfirmride.DriverConfirmBookingResponse;
import com.smartrider24.model.drivermoile.DriverMobileVerifyResponse;
import com.smartrider24.model.driverotp.DriverOtpVerifyResponse;
import com.smartrider24.model.driverprofileresponse.DriverProfileResponse;
import com.smartrider24.model.login.LoginResponse;
import com.smartrider24.model.loginotp.MobileotpLoginResponse;
import com.smartrider24.model.notification.NotificationResponse;
import com.smartrider24.model.passwordupdate.PasswordUpdateResponse;
import com.smartrider24.model.paymentreceipt.PaymentReceiptResponse;
import com.smartrider24.model.ridestatus.RideStatusResponse;
import com.smartrider24.model.vehicle.VehicleListResponse;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<CustomerRegistration> customerregister(@Part("smrtmaruacpername") RequestBody smrtmaruacpername,
                                                @Part("smrtmaruacmobilenod") RequestBody smrtmaruacmobilenod,
                                                @Part("smrtmaruacgstnod") RequestBody smrtmaruacgstnod,
                                                @Part("smrtmaruaclocnmd") RequestBody smrtmaruaclocnmd,
                                                @Part("smrtmaruaccitynmd") RequestBody smrtmaruaccitynmd,
                                                @Part("smrtmaruacstatenmd") RequestBody smrtmaruacstatenmd,
                                                @Part("smrtmaruacreferralcded") RequestBody smrtmaruacreferralcded,
                                                @Part("smrtmaruacpasswordd") RequestBody smrtmaruacpasswordd);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<CustomerRegistration> customerregisterUsingOtp(@Part("smrtmaapvurmobno") RequestBody smrtmaapvurmobno,
                                                        @Part("smrtmaapvuroptvc") RequestBody smrtmaapvuroptvc,
                                                        @Part("smrtmaruacgstnod") RequestBody smrtmaruacgstnod,
                                                        @Part("smrtmaruaclocnmd") RequestBody smrtmaruaclocnmd,
                                                        @Part("smrtmaruaccitynmd") RequestBody smrtmaruaccitynmd,
                                                        @Part("smrtmaruacstatenmd") RequestBody smrtmaruacstatenmd,
                                                        @Part("smrtmaruacreferralcded") RequestBody smrtmaruacreferralcded,
                                                        @Part("smrtmauacpasswordd") RequestBody smrtmauacpasswordd,
                                                        @Part("smrtmauacuanamed") RequestBody smrtmauacuanamed);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<LoginResponse> loginData(@Part("smrtmaplogin") RequestBody smrtmaplogin,
                                  @Part("smrtmappass") RequestBody smrtmappass,
                                  @Part("smrtmlngrqtyp") RequestBody smrtmlngrqtyp);

    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<MobileotpLoginResponse> mobileotpCall(@Part("smrtmauchpsreqmnoval") RequestBody smrtmauchpsreqmnoval);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<PasswordUpdateResponse> updatePassword(@Part("smrtmaunpsconfmnctrlval") RequestBody smrtmaunpsconfmnctrlval,
                                                @Part("smrtmaunpsconfnewpsval") RequestBody smrtmaunpsconfnewpsval,
                                                @Part("smrtmaunpsreqsessid") RequestBody smrtmaunpsreqsessid,
                                                @Part("smrtmaucustmstunqid") RequestBody smrtmaucustmstunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<CusstomerProfileResponse> customerProfile(@Part("smrtmapucmobilenod") RequestBody smrtmapucmobilenod,
                                                   @Part("smrtmapucustuiddt") RequestBody smrtmapucustuiddt,
                                                   @Part("smrtmapucustlngsunqid") RequestBody smrtmapucustlngsunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<BookingDetailFareResponse> bookingDetail(
            @Part("serbkreqvehrefno") RequestBody serbkreqvehrefno,
            @Part("serbkreqpikuplocnm") RequestBody serbkreqpikuplocnm,
            @Part("serbkreqdellocnm") RequestBody serbkreqdellocnm,
            @Part("serbkrequregunqid") RequestBody serbkrequregunqid,
            @Part("serbkrequlngunqid") RequestBody serbkrequlngunqid,
            @Part("serbkrequlngsessid") RequestBody serbkrequlngsessid,
            @Part("serbkreqoptpikuploc") RequestBody serbkreqoptpikuploc,
            @Part("serbkreqoptdelloc") RequestBody serbkreqoptdelloc,
            @Part("serbkreqcntoptmobno") RequestBody serbkreqcntoptmobno,
            @Part("serbkreqitemremarkdt") RequestBody serbkreqitemremarkdt
    );


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<BookingSuccessResponse> bookingDone(@Part("nwbkingrefno") RequestBody nwbkingrefno,
                                             @Part("nwbkreqvehrefno") RequestBody nwbkreqvehrefno,
                                             @Part("nwbkreqvehname") RequestBody nwbkreqvehname,
                                             @Part("nwbkreqpikuplocnm") RequestBody nwbkreqpikuplocnm,
                                             @Part("nwbkreqdelivlocnm") RequestBody nwbkreqdelivlocnm,
                                             @Part("nwbkreqtotalkm") RequestBody nwbkreqtotalkm,
                                             @Part("nwbkreqkmfare") RequestBody nwbkreqkmfare,
                                             @Part("nwbkreqkmfaredetails") RequestBody nwbkreqkmfaredetails,
                                             @Part("nwbkrequsrlngtmunqid") RequestBody nwbkrequsrlngtmunqid,
                                             @Part("nwbkrequsrregunqid") RequestBody nwbkrequsrregunqid,
                                             @Part("nwbkrequsrlngsessid") RequestBody nwbkrequsrlngsessid,
                                             @Part("nwbkreqoptpikuploc") RequestBody nwbkreqoptpikuploc,
                                             @Part("nwbkreqoptdelloc") RequestBody nwbkreqoptdelloc,
                                             @Part("nwbkreqcntoptmobno") RequestBody nwbkreqcntoptmobno,
                                             @Part("nwbkreqitemremarkdt") RequestBody nwbkreqitemremarkdt

    );




    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<BookingHistorySuccessResponse> bookingHistory(@Part("mybkhstreqlngunqid") RequestBody mybkhstreqlngunqid,
                                                       @Part("mybkhstreqlngsessid") RequestBody mybkhstreqlngsessid,
                                                       @Part("mybkhstregunqid") RequestBody mybkhstregunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<VehicleListResponse> vehicleList(@Part("opvechlstmstreq") RequestBody opvechlstmstreq,
                                          @Part("oprvchrequsrmstunqid") RequestBody oprvchrequsrmstunqid,
                                          @Part("oprvchrequsrlngsessid") RequestBody oprvchrequsrlngsessid);


    // All Driver Api is mentioned below
    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverBookingPendingResponse> driverBookingPending(@Part("drvbkhstreqlngunqid") RequestBody drvbkhstreqlngunqid,
                                                            @Part("drvbkhstreqlngsessid") RequestBody drvbkhstreqlngsessid,
                                                            @Part("drvbkhstregunqid") RequestBody drvbkhstregunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverBookingPendingResponse> driverBookingConfirm(@Part("cnfdrvbkrqlngunqid") RequestBody cnfdrvbkrqlngunqid,
                                                            @Part("cnfdrvbkreqlngsessid") RequestBody cnfdrvbkreqlngsessid,
                                                            @Part("cnfdrvbkrqregunqid") RequestBody cnfdrvbkrqregunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverAcceptBookingResponse> driverBookingAccept(@Part("drvacptservbkrefno") RequestBody drvacptservbkrefno,
                                                          @Part("drvacptservbktrkrefno") RequestBody drvacptservbktrkrefno,
                                                          @Part("drvacpttmserbkamt") RequestBody drvacpttmserbkamt,
                                                          @Part("drvacptbktmlngunqid") RequestBody drvacptbktmlngunqid,
                                                          @Part("drvacptbktmlngsessid") RequestBody drvacptbktmlngsessid,
                                                          @Part("drvacptbktmregunqid") RequestBody drvacptbktmregunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverRegistrationResponse> driverRegistration(@Part("drvrgname") RequestBody drvrgname,
                                                        @Part("drvrgmobileno") RequestBody drvrgmobileno,
                                                        @Part("drvrgaadharno") RequestBody drvrgaadharno,
                                                        @Part("drvrgdrvglcence") RequestBody drvrgdrvglcence,
                                                        @Part("drvrgdrvgexp") RequestBody drvrgdrvgexp,
                                                        @Part("drvrgaddress") RequestBody drvrgaddress,
                                                        @Part("drvrgcitynm") RequestBody drvrgcitynm,
                                                        @Part("drvrgstatenm") RequestBody drvrgstatenm,
                                                        @Part("drvrgpasswrd") RequestBody drvrgpasswrd,
                                                        @Part("ownrgfullname") RequestBody ownrgfullname,
                                                        @Part("ownrgmobno") RequestBody ownrgmobno,
                                                        @Part("ownrgemilid") RequestBody ownrgemilid,
                                                        @Part("ownrgpancrdno") RequestBody ownrgpancrdno,
                                                        @Part("ownrgaadharno") RequestBody ownrgaadharno,
                                                        @Part("ownrgvechrcno") RequestBody ownrgvechrcno,
                                                        @Part("ownrgvechinsno") RequestBody ownrgvechinsno,
                                                        @Part("ownrgregaddress") RequestBody ownrgregaddress,
                                                        @Part("ownrgcitynm") RequestBody ownrgcitynm,
                                                        @Part("ownrgstatenm") RequestBody ownrgstatenm,
                                                        @Part MultipartBody.Part drvrgupldaadharcpy,
                                                        @Part MultipartBody.Part drvrgupldrivilicecpy,
                                                        @Part MultipartBody.Part ownregadhrcrdcpy,
                                                        @Part MultipartBody.Part ownrgvhrccpy,
                                                        @Part MultipartBody.Part ownrgvhcinsrncpy,
                                                        @Part("ownrgothrefnodt") RequestBody ownrgothrefnodt);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverMobileVerifyResponse> driverMobile(@Part("chkanymobnounqctrl") RequestBody chkanymobnounqctrl);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverOtpVerifyResponse> driverMobileOtpVerify(
            @Part("chkunqmobnorgreq") RequestBody chkunqmobnorgreq,
            @Part("chkunqoptvlrgreq") RequestBody chkunqoptvlrgreq,
            @Part("chkunqreqsessid") RequestBody chkunqreqsessid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<NotificationResponse> notification(
            @Part("usrnotfyreqtyp") RequestBody usrnotfyreqtyp,
            @Part("usrnotfyreqlngunqid") RequestBody usrnotfyreqlngunqid,
            @Part("usrnotfyreqrqregunqid") RequestBody usrnotfyreqrqregunqid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverConfirmBookingResponse> driverConfirm(
            @Part("cnfdrvbkrqlngunqid") RequestBody cnfdrvbkrqlngunqid,
            @Part("cnfdrvbkreqlngsessid") RequestBody cnfdrvbkreqlngsessid,
            @Part("cnfdrvbkrqregunqid") RequestBody cnfdrvbkrqregunqid,
            @Part("cnfdrvbkrqtypctrlmst") RequestBody cnfdrvbkrqtypctrlmst
    );


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverConfirmBookingResponse> driverRideClosed(
            @Part("cnfdrvbkrqlngunqid") RequestBody cnfdrvbkrqlngunqid,
            @Part("cnfdrvbkreqlngsessid") RequestBody cnfdrvbkreqlngsessid,
            @Part("cnfdrvbkrqregunqid") RequestBody cnfdrvbkrqregunqid,
            @Part("cnfdrvbkrqtypctrlmst") RequestBody cnfdrvbkrqtypctrlmst
    );


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<CustomerTrackingResponse> customerTracking(
            @Part("bktrkrefno") RequestBody bktrkrefno,
            @Part("bktrkunqno") RequestBody bktrkunqno,
            @Part("bktrkcstlngid") RequestBody bktrkcstlngid,
            @Part("bktrkcstregunqid") RequestBody bktrkcstregunqid,
            @Part("bktrkcstlngsessid") RequestBody bktrkcstlngsessid);



    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<DriverProfileResponse> driverProfile(
            @Part("drvprflngunqid") RequestBody drvprflngunqid,
            @Part("drvprfregtmunqno") RequestBody drvprfregtmunqno,
            @Part("drvprflngtmsessunqid") RequestBody drvprflngtmsessunqid
    );


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<AccountResponse> accountStatus(
            @Part("myacreqlngunqid") RequestBody myacreqlngunqid,
            @Part("myacreqrgtmunqno") RequestBody myacreqrgtmunqno,
            @Part("myaclngtmsessid") RequestBody myaclngtmsessid);


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<AccountDetailStatementResponse> accountDetail(
            @Part("myacstmtreqlngunqid") RequestBody myacstmtreqlngunqid,
            @Part("myacstmtreqrgtmunqno") RequestBody myacstmtreqrgtmunqno,
            @Part("myacstmtlngtmsessid") RequestBody myacstmtlngtmsessid);

    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<RideStatusResponse> rideCall(
            @Part("drsndjbrefno") RequestBody drsndjbrefno,
            @Part("drsndjbtrkunqno") RequestBody drsndjbtrkunqno,
            @Part("drsndjbtklatdt") RequestBody drsndjbtklatdt,
            @Part("drsndjbtklngdt") RequestBody drsndjbtklngdt,
            @Part("drsndjbtkcrntaddrss") RequestBody drsndjbtkcrntaddrss,
            @Part("drrgrefnosndtrk") RequestBody drrgrefnosndtrk,
            @Part("drlngunqnotrk") RequestBody drlngunqnotrk,
            @Part("drlgsessunqidtrk") RequestBody drlgsessunqidtrk,
            @Part("drtrpcurntstatusdt") RequestBody drtrpcurntstatusdt
    );


    @Multipart
    @POST("https://www.smartrider24.com/smrtnooneuswthotperdmsctrl.html")
    Call<PaymentReceiptResponse> paymentCall(
            @Part("drvupddeldtbkrefno") RequestBody drvupddeldtbkrefno,
            @Part("drvupddeldtbkunqno") RequestBody drvupddeldtbkunqno,
            @Part("drvupddeldtfareamt") RequestBody drvupddeldtfareamt,
            @Part("drvupddeldtrecfreamt") RequestBody drvupddeldtrecfreamt,
            @Part MultipartBody.Part drvupddeldtrecptdoc,
            @Part("drvupddeldtlngunqid") RequestBody drvupddeldtlngunqid,
            @Part("drvupddeldtregtmunqno") RequestBody drvupddeldtregtmunqno,
            @Part("drvupddeldtlngtmsessunqid") RequestBody drvupddeldtlngtmsessunqid);



}