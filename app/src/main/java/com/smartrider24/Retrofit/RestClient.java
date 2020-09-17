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
import retrofit2.Callback;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class RestClient {

    public static void customerRegistration(RequestBody smrtmaruacpername, RequestBody smrtmaruacmobilenod,
                                            RequestBody smrtmaruacgstnod, RequestBody smrtmaruaclocnmd,
                                            RequestBody smrtmaruaccitynmd, RequestBody smrtmaruacstatenmd,
                                            RequestBody smrtmaruacreferralcded, RequestBody smrtmaruacpasswordd, Callback<CustomerRegistration> callback) {
        RetrofitClient.getClient().customerregister(smrtmaruacpername, smrtmaruacmobilenod, smrtmaruacgstnod, smrtmaruaclocnmd, smrtmaruaccitynmd, smrtmaruacstatenmd, smrtmaruacreferralcded, smrtmaruacpasswordd).enqueue(callback);
    }


    public static void customerRegistrationUsingOtp(RequestBody smrtmaapvurmobno, RequestBody smrtmaapvuroptvc, RequestBody smrtmaruacgstnod,
                                                    RequestBody smrtmaruaclocnmd, RequestBody smrtmaruaccitynmd,
                                                    RequestBody smrtmaruacstatenmd, RequestBody smrtmaruacreferralcded,
                                                    RequestBody smrtmauacpasswordd, RequestBody smrtmauacuanamed, Callback<CustomerRegistration> callback) {
        RetrofitClient.getClient().customerregisterUsingOtp(smrtmaapvurmobno, smrtmaapvuroptvc, smrtmaruacgstnod, smrtmaruaclocnmd, smrtmaruaccitynmd, smrtmaruacstatenmd, smrtmaruacreferralcded, smrtmauacpasswordd, smrtmauacuanamed).enqueue(callback);
    }


    public static void loginData(RequestBody smrtmaplogin, RequestBody smrtmappass, RequestBody smrtmlngrqtyp, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().loginData(smrtmaplogin, smrtmappass, smrtmlngrqtyp).enqueue(callback);
    }


    public static void mobileotpcall(RequestBody smrtmauchpsreqmnoval, Callback<MobileotpLoginResponse> responseCallback) {
        RetrofitClient.getClient().mobileotpCall(smrtmauchpsreqmnoval).enqueue(responseCallback);
    }


    public static void updatePassword(RequestBody smrtmaunpsconfmnctrlval, RequestBody smrtmaunpsconfnewpsval, RequestBody smrtmaunpsreqsessid, RequestBody smrtmaucustmstunqid, Callback<PasswordUpdateResponse> passwordUpdateResponseCallback) {
        RetrofitClient.getClient().updatePassword(smrtmaunpsconfmnctrlval, smrtmaunpsconfnewpsval, smrtmaunpsreqsessid, smrtmaucustmstunqid).enqueue(passwordUpdateResponseCallback);
    }

    public static void customerProfile(RequestBody smrtmapucmobilenod, RequestBody smrtmapucustuiddt, RequestBody smrtmapucustlngsunqid, Callback<CusstomerProfileResponse> cusstomerProfileResponseCallback) {
        RetrofitClient.getClient().customerProfile(smrtmapucmobilenod, smrtmapucustuiddt, smrtmapucustlngsunqid).enqueue(cusstomerProfileResponseCallback);
    }

    public static void bookingDetail(RequestBody serbkreqvehrefno, RequestBody serbkreqpikuplocnm, RequestBody serbkreqdellocnm, RequestBody serbkrequregunqid,
                                     RequestBody serbkrequlngunqid, RequestBody serbkrequlngsessid,
                                     RequestBody serbkreqoptpikuploc, RequestBody serbkreqoptdelloc,
                                     RequestBody serbkreqcntoptmobno,RequestBody serbkreqitemremarkdt,

                                     Callback<BookingDetailFareResponse> detailFareResponseCallback) {
        RetrofitClient.getClient().bookingDetail(serbkreqvehrefno, serbkreqpikuplocnm,
                serbkreqdellocnm, serbkrequregunqid, serbkrequlngunqid, serbkrequlngsessid,
                serbkreqoptpikuploc, serbkreqoptdelloc, serbkreqcntoptmobno,serbkreqitemremarkdt).enqueue(detailFareResponseCallback);
    }


    public static void bookingDone(RequestBody nwbkingrefno, RequestBody nwbkreqvehrefno, RequestBody nwbkreqvehname, RequestBody nwbkreqpikuplocnm,
                                   RequestBody nwbkreqdelivlocnm, RequestBody nwbkreqtotalkm, RequestBody nwbkreqkmfare, RequestBody nwbkreqkmfaredetails,
                                   RequestBody nwbkrequsrlngtmunqid, RequestBody nwbkrequsrregunqid, RequestBody nwbkrequsrlngsessid,
                                   RequestBody nwbkreqoptpikuploc, RequestBody nwbkreqoptdelloc, RequestBody nwbkreqcntoptmobno,RequestBody nwbkreqitemremarkdt, Callback<BookingSuccessResponse> bookingSuccessResponseCallback) {
        RetrofitClient.getClient().bookingDone(nwbkingrefno, nwbkreqvehrefno, nwbkreqvehname, nwbkreqpikuplocnm,
                nwbkreqdelivlocnm, nwbkreqtotalkm, nwbkreqkmfare, nwbkreqkmfaredetails, nwbkrequsrlngtmunqid, nwbkrequsrregunqid,
                nwbkrequsrlngsessid, nwbkreqoptpikuploc, nwbkreqoptdelloc, nwbkreqcntoptmobno,nwbkreqitemremarkdt).enqueue(bookingSuccessResponseCallback);
    }


    public static void bookingHistory(RequestBody mybkhstreqlngunqid, RequestBody mybkhstreqlngsessid, RequestBody mybkhstregunqid, Callback<BookingHistorySuccessResponse> callback) {
        RetrofitClient.getClient().bookingHistory(mybkhstreqlngunqid, mybkhstreqlngsessid, mybkhstregunqid).enqueue(callback);
    }

    public static void vehicleList(RequestBody opvechlstmstreq, RequestBody oprvchrequsrmstunqid, RequestBody oprvchrequsrlngsessid, Callback<VehicleListResponse> vehicleListResponseCallback) {
        RetrofitClient.getClient().vehicleList(opvechlstmstreq, oprvchrequsrmstunqid, oprvchrequsrlngsessid).enqueue(vehicleListResponseCallback);
    }

    public static void bookingPending(RequestBody drvbkhstreqlngunqid, RequestBody drvbkhstreqlngsessid, RequestBody drvbkhstregunqid, Callback<DriverBookingPendingResponse> callback) {
        RetrofitClient.getClient().driverBookingPending(drvbkhstreqlngunqid, drvbkhstreqlngsessid, drvbkhstregunqid).enqueue(callback);
    }

    public static void bookingConfirm(RequestBody cnfdrvbkrqlngunqid, RequestBody cnfdrvbkreqlngsessid, RequestBody cnfdrvbkrqregunqid, Callback<DriverBookingPendingResponse> callback) {
        RetrofitClient.getClient().driverBookingConfirm(cnfdrvbkrqlngunqid, cnfdrvbkreqlngsessid, cnfdrvbkrqregunqid).enqueue(callback);
    }


    public static void driverBookingAccept(RequestBody drvacptservbkrefno, RequestBody drvacptservbktrkrefno, RequestBody drvacpttmserbkamt, RequestBody drvacptbktmlngunqid, RequestBody drvacptbktmlngsessid, RequestBody drvacptbktmregunqid, Callback<DriverAcceptBookingResponse> callback) {
        RetrofitClient.getClient().driverBookingAccept(drvacptservbkrefno, drvacptservbktrkrefno, drvacpttmserbkamt, drvacptbktmlngunqid, drvacptbktmlngsessid, drvacptbktmregunqid).enqueue(callback);
    }

    public static void driverRegistration(RequestBody drvrgname, RequestBody drvrgmobileno, RequestBody drvrgaadharno, RequestBody drvrgdrvglcence, RequestBody drvrgdrvgexp, RequestBody drvrgaddress, RequestBody drvrgcitynm, RequestBody drvrgstatenm, RequestBody drvrgpasswrd,
                                          RequestBody ownrgfullname, RequestBody ownrgmobno, RequestBody ownrgemilid, RequestBody ownrgpancrdno, RequestBody ownrgaadharno,
                                          RequestBody ownrgvechrcno, RequestBody ownrgvechinsno, RequestBody ownrgregaddress, RequestBody ownrgcitynm, RequestBody ownrgstatenm,
                                          MultipartBody.Part drvrgupldaadharcpy,
                                          MultipartBody.Part drvrgupldrivilicecpy,
                                          MultipartBody.Part ownregadhrcrdcpy,
                                          MultipartBody.Part ownrgvhrccpy,
                                          MultipartBody.Part ownrgvhcinsrncpy,
                                          RequestBody ownrgothrefnodt, Callback<DriverRegistrationResponse> driverRegistrationResponseCallback) {


        RetrofitClient.getClient().driverRegistration(drvrgname, drvrgmobileno, drvrgaadharno, drvrgdrvglcence, drvrgdrvgexp, drvrgaddress, drvrgcitynm, drvrgstatenm, drvrgpasswrd, ownrgfullname,
                ownrgmobno, ownrgemilid,
                ownrgpancrdno, ownrgaadharno, ownrgvechrcno,
                ownrgvechinsno, ownrgregaddress, ownrgcitynm, ownrgstatenm
                , drvrgupldaadharcpy, drvrgupldrivilicecpy, ownregadhrcrdcpy, ownrgvhrccpy, ownrgvhcinsrncpy, ownrgothrefnodt).enqueue(driverRegistrationResponseCallback);
    }


    public static void driverMobile(RequestBody chkanymobnounqctrl, Callback<DriverMobileVerifyResponse> callback) {
        RetrofitClient.getClient().driverMobile(chkanymobnounqctrl).enqueue(callback);
    }

    public static void driverMobileOtpVerify(RequestBody chkunqmobnorgreq, RequestBody chkunqoptvlrgreq, RequestBody chkunqreqsessid, Callback<DriverOtpVerifyResponse> callback) {
        RetrofitClient.getClient().driverMobileOtpVerify(chkunqmobnorgreq, chkunqoptvlrgreq, chkunqreqsessid).enqueue(callback);
    }

    public static void notification(RequestBody usrnotfyreqtyp, RequestBody usrnotfyreqlngunqid, RequestBody usrnotfyreqrqregunqid, Callback<NotificationResponse> callback) {
        RetrofitClient.getClient().notification(usrnotfyreqtyp, usrnotfyreqlngunqid, usrnotfyreqrqregunqid).enqueue(callback);
    }

    public static void driverConfirm(RequestBody cnfdrvbkrqlngunqid, RequestBody cnfdrvbkreqlngsessid
            , RequestBody cnfdrvbkrqregunqid, RequestBody cnfdrvbkrqtypctrlmst, Callback<DriverConfirmBookingResponse> callback) {
        RetrofitClient.getClient().driverConfirm(cnfdrvbkrqlngunqid, cnfdrvbkreqlngsessid, cnfdrvbkrqregunqid, cnfdrvbkrqtypctrlmst).enqueue(callback);
    }

    public static void driverRideClosed(RequestBody cnfdrvbkrqlngunqid, RequestBody cnfdrvbkreqlngsessid, RequestBody cnfdrvbkrqregunqid, RequestBody cnfdrvbkrqtypctrlmst, Callback<DriverConfirmBookingResponse> callback) {
        RetrofitClient.getClient().driverRideClosed(cnfdrvbkrqlngunqid, cnfdrvbkreqlngsessid, cnfdrvbkrqregunqid, cnfdrvbkrqtypctrlmst).enqueue(callback);
    }

    public static void customerTrack(RequestBody bktrkrefno, RequestBody bktrkunqno, RequestBody bktrkcstlngid, RequestBody bktrkcstregunqid, RequestBody bktrkcstlngsessid, Callback<CustomerTrackingResponse> customerTrackingResponseCallback) {
        RetrofitClient.getClient().customerTracking(bktrkrefno, bktrkunqno, bktrkcstlngid, bktrkcstregunqid, bktrkcstlngsessid).enqueue(customerTrackingResponseCallback);
    }

    public static void driverProfile(RequestBody drvprflngunqid, RequestBody drvprfregtmunqno, RequestBody drvprflngtmsessunqid, Callback<DriverProfileResponse> driverProfileResponseCallback) {
        RetrofitClient.getClient().driverProfile(drvprflngunqid, drvprfregtmunqno, drvprflngtmsessunqid).enqueue(driverProfileResponseCallback);
    }

    public static void accountStatus(RequestBody myacreqlngunqid, RequestBody myacreqrgtmunqno, RequestBody myaclngtmsessid, Callback<AccountResponse> accountResponseCallback) {
        RetrofitClient.getClient().accountStatus(myacreqlngunqid, myacreqrgtmunqno, myaclngtmsessid).enqueue(accountResponseCallback);
    }


    public static void accountDetail(RequestBody myacstmtreqlngunqid, RequestBody myacstmtreqrgtmunqno, RequestBody myacstmtlngtmsessid, Callback<AccountDetailStatementResponse> accountResponseCallback) {
        RetrofitClient.getClient().accountDetail(myacstmtreqlngunqid, myacstmtreqrgtmunqno, myacstmtlngtmsessid).enqueue(accountResponseCallback);
    }


    public static void rideCall(RequestBody drsndjbrefno, RequestBody drsndjbtrkunqno, RequestBody drsndjbtklatdt,
                                RequestBody drsndjbtklngdt, RequestBody drsndjbtkcrntaddrss, RequestBody drrgrefnosndtrk,
                                RequestBody drlngunqnotrk, RequestBody drlgsessunqidtrk, RequestBody drtrpcurntstatusdt,
                                Callback<RideStatusResponse> accountResponseCallback) {
        RetrofitClient.getClient().rideCall(drsndjbrefno, drsndjbtrkunqno, drsndjbtklatdt,
                drsndjbtklngdt, drsndjbtkcrntaddrss, drrgrefnosndtrk, drlngunqnotrk, drlgsessunqidtrk, drtrpcurntstatusdt).enqueue(accountResponseCallback);
    }

    public static void paymentCall(RequestBody drvupddeldtbkrefno, RequestBody drvupddeldtbkunqno, RequestBody drvupddeldtfareamt,
                                   RequestBody drvupddeldtrecfreamt, MultipartBody.Part drvupddeldtrecptdoc, RequestBody drvupddeldtlngunqid,
                                   RequestBody drvupddeldtregtmunqno, RequestBody drvupddeldtlngtmsessunqid, Callback<PaymentReceiptResponse> callback) {
        RetrofitClient.getClient().paymentCall(drvupddeldtbkrefno, drvupddeldtbkunqno, drvupddeldtfareamt, drvupddeldtrecfreamt, drvupddeldtrecptdoc,
                drvupddeldtlngunqid, drvupddeldtregtmunqno, drvupddeldtlngtmsessunqid).enqueue(callback);
    }


}

