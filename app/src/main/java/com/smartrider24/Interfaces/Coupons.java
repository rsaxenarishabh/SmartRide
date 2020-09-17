package com.smartrider24.Interfaces;

public class Coupons {

    String id,Vheading,Vshortinfo,validUpto;
Integer image;
    public Coupons(String id,String Vheading,String Vshortinfo,String validUpto) {

        this.id=id;
        this.Vheading=Vheading;
        this.Vshortinfo=Vshortinfo;
        this.validUpto=validUpto;

    }

    public Coupons(String id,String Vheading,String Vshortinfo,String validUpto,int image) {
        this.id=id;
        this.Vheading=Vheading;
        this.Vshortinfo=Vshortinfo;
        this.validUpto=validUpto;
        this.image=image;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVheading() {
        return Vheading;
    }

    public void setVheading(String vheading) {
        Vheading = vheading;
    }

    public String getVshortinfo() {
        return Vshortinfo;
    }

    public void setVshortinfo(String vshortinfo) {
        Vshortinfo = vshortinfo;
    }

    public String getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(String validUpto) {
        this.validUpto = validUpto;
    }

}
