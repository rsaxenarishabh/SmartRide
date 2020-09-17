package com.smartrider24.model.accountdetailstatement;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountStatement {

@SerializedName("Trans_Date")
@Expose
private String transDate;
@SerializedName("Trans_Debit")
@Expose
private String transDebit;
@SerializedName("Trans_Credit")
@Expose
private String transCredit;

public String getTransDate() {
return transDate;
}

public void setTransDate(String transDate) {
this.transDate = transDate;
}

public String getTransDebit() {
return transDebit;
}

public void setTransDebit(String transDebit) {
this.transDebit = transDebit;
}

public String getTransCredit() {
return transCredit;
}

public void setTransCredit(String transCredit) {
this.transCredit = transCredit;
}

}
