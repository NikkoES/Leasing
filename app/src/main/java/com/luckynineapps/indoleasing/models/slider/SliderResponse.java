
package com.luckynineapps.indoleasing.models.slider;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SliderResponse {

    @SerializedName("data")
    private List<Slider> mData;
    @SerializedName("status")
    private String mStatus;

    public List<Slider> getData() {
        return mData;
    }

    public void setData(List<Slider> data) {
        mData = data;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
