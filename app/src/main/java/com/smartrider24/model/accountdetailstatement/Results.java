package com.smartrider24.model.accountdetailstatement;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("msg")
@Expose
private String msg;
@SerializedName("Account_Details")
@Expose
private AccountDetails accountDetails;
@SerializedName("Account_Statement")
@Expose
private List<AccountStatement> accountStatement = null;

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public AccountDetails getAccountDetails() {
return accountDetails;
}

public void setAccountDetails(AccountDetails accountDetails) {
this.accountDetails = accountDetails;
}

public List<AccountStatement> getAccountStatement() {
return accountStatement;
}

public void setAccountStatement(List<AccountStatement> accountStatement) {
this.accountStatement = accountStatement;
}

}