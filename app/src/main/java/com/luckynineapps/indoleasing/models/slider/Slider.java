
package com.luckynineapps.indoleasing.models.slider;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Slider {

    @SerializedName("deskripsi")
    private String mDeskripsi;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        mDeskripsi = deskripsi;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

}
