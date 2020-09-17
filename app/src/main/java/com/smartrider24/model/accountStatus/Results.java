package com.smartrider24.model.accountStatus;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Account_Balance")
@Expose
private String accountBalance;
@SerializedName("Account_Cash_Balance")
@Expose
private String accountCashBalance;
@SerializedName("Account_Smart_Cash_Balance")
@Expose
private String accountSmartCashBalance;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public String getAccountBalance() {
return accountBalance;
}

public void setAccountBalance(String accountBalance) {
this.accountBalance = accountBalance;
}

public String getAccountCashBalance() {
return accountCashBalance;
}

public void setAccountCashBalance(String accountCashBalance) {
this.accountCashBalance = accountCashBalance;
}

public String getAccountSmartCashBalance() {
return accountSmartCashBalance;
}

public void setAccountSmartCashBalance(String accountSmartCashBalance) {
this.accountSmartCashBalance = accountSmartCashBalance;
}

}