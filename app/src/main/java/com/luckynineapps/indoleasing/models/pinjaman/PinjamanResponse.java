
package com.luckynineapps.indoleasing.models.pinjaman;

import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class PinjamanResponse {

    @Expose
    private List<Pinjaman> data;
    @Expose
    private String status;

    public List<Pinjaman> getData() {
        return data;
    }

    public void setData(List<Pinjaman> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
