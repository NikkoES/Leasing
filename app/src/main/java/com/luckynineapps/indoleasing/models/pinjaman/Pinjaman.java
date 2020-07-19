
package com.luckynineapps.indoleasing.models.pinjaman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class Pinjaman implements Serializable {

    @Expose
    private String bank;
    @Expose
    private String bunga;
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private String deskripsi;
    @Expose
    private String konten;
    @Expose
    private String dokumen;
    @SerializedName("dp_baru")
    private String dpBaru;
    @SerializedName("dp_bekas")
    private String dpBekas;
    @Expose
    private String id;
    @Expose
    private String image;
    @Expose
    private List<String> jenis;
    @Expose
    private String kewarganegaraan;
    @SerializedName("limit_pinjaman")
    private String limitPinjaman;
    @SerializedName("max_lambat")
    private String maxLambat;
    @Expose
    private String nama;
    @Expose
    private String nominal;
    @Expose
    private String penghasilan;
    @Expose
    private String playstore;
    @Expose
    private String status;
    @Expose
    private String tenor;
    @Expose
    private String umur;
    @Expose
    private String website;

    private boolean checked;

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public boolean isChecked() {
        return checked;
    }

    public void toggleCheck(){
        this.checked = !this.checked;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBunga() {
        return bunga;
    }

    public void setBunga(String bunga) {
        this.bunga = bunga;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDokumen() {
        return dokumen;
    }

    public void setDokumen(String dokumen) {
        this.dokumen = dokumen;
    }

    public String getDpBaru() {
        return dpBaru;
    }

    public void setDpBaru(String dpBaru) {
        this.dpBaru = dpBaru;
    }

    public String getDpBekas() {
        return dpBekas;
    }

    public void setDpBekas(String dpBekas) {
        this.dpBekas = dpBekas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getJenis() {
        return jenis;
    }

    public void setJenis(List<String> jenis) {
        this.jenis = jenis;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getLimitPinjaman() {
        return limitPinjaman;
    }

    public void setLimitPinjaman(String limitPinjaman) {
        this.limitPinjaman = limitPinjaman;
    }

    public String getMaxLambat() {
        return maxLambat;
    }

    public void setMaxLambat(String maxLambat) {
        this.maxLambat = maxLambat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getPenghasilan() {
        return penghasilan;
    }

    public void setPenghasilan(String penghasilan) {
        this.penghasilan = penghasilan;
    }

    public String getPlaystore() {
        return playstore;
    }

    public void setPlaystore(String playstore) {
        this.playstore = playstore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
