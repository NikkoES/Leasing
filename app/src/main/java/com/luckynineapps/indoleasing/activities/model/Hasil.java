/*
 * Copyright (c) 2018. Evren Coşkun
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.luckynineapps.indoleasing.activities.model;

import java.io.Serializable;

/**
 * Created by evrencoskun on 11/06/2017.
 */

public class Hasil implements Serializable {

    private Double hargaKendaraanOtr, uangMuka, bunga, bungaPinjaman, biayaAsuransi, biayaProvisi, biayaPolis, biayaAdministrasi, pokokHutang;
    private int tenor;
    private String tanggalMulai, tanggalBerakhir;
    private Double totalLainnya, totalUangMuka, totalAngsuran;

    public Double getTotalLainnya() {
        return totalLainnya;
    }

    public void setTotalLainnya(Double totalLainnya) {
        this.totalLainnya = totalLainnya;
    }

    public Double getBunga() {
        return bunga;
    }

    public void setBunga(Double bunga) {
        this.bunga = bunga;
    }

    public Double getHargaKendaraanOtr() {
        return hargaKendaraanOtr;
    }

    public void setHargaKendaraanOtr(Double hargaKendaraanOtr) {
        this.hargaKendaraanOtr = hargaKendaraanOtr;
    }

    public Double getUangMuka() {
        return uangMuka;
    }

    public void setUangMuka(Double uangMuka) {
        this.uangMuka = uangMuka;
    }

    public Double getBungaPinjaman() {
        return bungaPinjaman;
    }

    public void setBungaPinjaman(Double bungaPinjaman) {
        this.bungaPinjaman = bungaPinjaman;
    }

    public Double getBiayaAsuransi() {
        return biayaAsuransi;
    }

    public void setBiayaAsuransi(Double biayaAsuransi) {
        this.biayaAsuransi = biayaAsuransi;
    }

    public Double getBiayaProvisi() {
        return biayaProvisi;
    }

    public void setBiayaProvisi(Double biayaProvisi) {
        this.biayaProvisi = biayaProvisi;
    }

    public Double getBiayaPolis() {
        return biayaPolis;
    }

    public void setBiayaPolis(Double biayaPolis) {
        this.biayaPolis = biayaPolis;
    }

    public Double getBiayaAdministrasi() {
        return biayaAdministrasi;
    }

    public void setBiayaAdministrasi(Double biayaAdministrasi) {
        this.biayaAdministrasi = biayaAdministrasi;
    }

    public Double getPokokHutang() {
        return pokokHutang;
    }

    public void setPokokHutang(Double pokokHutang) {
        this.pokokHutang = pokokHutang;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalBerakhir() {
        return tanggalBerakhir;
    }

    public void setTanggalBerakhir(String tanggalBerakhir) {
        this.tanggalBerakhir = tanggalBerakhir;
    }

    public Double getTotalUangMuka() {
        return totalUangMuka;
    }

    public void setTotalUangMuka(Double totalUangMuka) {
        this.totalUangMuka = totalUangMuka;
    }

    public Double getTotalAngsuran() {
        return totalAngsuran;
    }

    public void setTotalAngsuran(Double totalAngsuran) {
        this.totalAngsuran = totalAngsuran;
    }
}

