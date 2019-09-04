package com.example.dell.dss.Modal;

public class MenuData {

    private String txtDescription;
    private int imgDescription;

    public MenuData(String tatDescription, int imgDescription) {
        this.txtDescription = tatDescription;
        this.imgDescription = imgDescription;
    }

    public String getTxtDesctiption() {
        return txtDescription;
    }

    public void setTxtDesctiption(String txtDesctiption) {
        this.txtDescription = txtDesctiption;
    }

    public int getImgDescription() {
        return imgDescription;
    }

    public void setImgDescription(int imgDescription) {
        this.imgDescription = imgDescription;
    }
}
