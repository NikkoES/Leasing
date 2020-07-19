package com.luckynineapps.indoleasing.activities.kalkulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.activities.model.Hasil;
import com.luckynineapps.indoleasing.data.Session;
import com.luckynineapps.indoleasing.utils.AdsUtil;
import com.luckynineapps.indoleasing.utils.NumberUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HasilActivity extends AppCompatActivity {

    @BindView(R.id.txt_harga_otr)
    TextView txtHargaOtr;
    @BindView(R.id.txt_uang_muka)
    TextView txtUangMuka;
    @BindView(R.id.txt_tenor)
    TextView txtTenor;
    @BindView(R.id.txt_bunga)
    TextView txtBunga;
    @BindView(R.id.txt_perhitungan_bunga)
    TextView txtPerhitunganBunga;
    @BindView(R.id.layout_data_pinjaman)
    LinearLayout layoutDataPinjaman;
    @BindView(R.id.txt_platform_harga_otr)
    TextView txtPlatformHargaOtr;
    @BindView(R.id.txt_platform_uang_muka)
    TextView txtPlatformUangMuka;
    @BindView(R.id.txt_platform_total_pinjaman)
    TextView txtPlatformTotalPinjaman;
    @BindView(R.id.layout_platform_data_pinjaman)
    LinearLayout layoutPlatformDataPinjaman;
    @BindView(R.id.txt_asuransi)
    TextView txtAsuransi;
    @BindView(R.id.txt_provisi)
    TextView txtProvisi;
    @BindView(R.id.txt_polis_asuransi)
    TextView txtPolisAsuransi;
    @BindView(R.id.txt_administrasi)
    TextView txtAdministrasi;
    @BindView(R.id.txt_total_biaya_lainnya)
    TextView txtTotalBiayaLainnya;
    @BindView(R.id.layout_biaya_lainnya)
    LinearLayout layoutBiayaLainnya;
    @BindView(R.id.txt_angsuran_pokok)
    TextView txtAngsuranPokok;
    @BindView(R.id.layout_hasil)
    LinearLayout layoutHasil;
    @BindView(R.id.txt_total_dp)
    TextView txtTotalDp;
    @BindView(R.id.txt_tanggal_mulai)
    TextView txtTanggalMulai;
    @BindView(R.id.txt_tanggal_selesai)
    TextView txtTanggalSelesai;
    @BindView(R.id.ad_top)
    AdView topAds;

    Session session;
    Hasil hasil;
    AdsUtil adsUtil;

    boolean expDataPinjaman = false;
    boolean expPlatformDataPinjaman = false;
    boolean expLainnya = false;
    boolean expHasil = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        ButterKnife.bind(this);

        session = new Session(this);

        initAd();
        initView();
    }

    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        topAds.loadAd(adRequest);

        adsUtil = AdsUtil.getInstance(this);
    }

    private void initView() {
        hasil = (Hasil) getIntent().getSerializableExtra("hasil");

        txtHargaOtr.setText(NumberUtil.toIndoPriceFormatted(hasil.getHargaKendaraanOtr()));
        txtUangMuka.setText(NumberUtil.toIndoPriceFormatted(hasil.getUangMuka()));
        txtTenor.setText(hasil.getTenor() + " tahun");
        txtBunga.setText(hasil.getBunga() + "%");
        txtPerhitunganBunga.setText(NumberUtil.toIndoPriceFormatted(hasil.getBungaPinjaman()));

        txtPlatformHargaOtr.setText(NumberUtil.toIndoPriceFormatted(hasil.getHargaKendaraanOtr()));
        txtPlatformUangMuka.setText(NumberUtil.toIndoPriceFormatted(hasil.getUangMuka()));
        txtPlatformTotalPinjaman.setText(NumberUtil.toIndoPriceFormatted(hasil.getPokokHutang()));

        txtAsuransi.setText(NumberUtil.toIndoPriceFormatted(hasil.getBiayaAsuransi()));
        txtProvisi.setText(NumberUtil.toIndoPriceFormatted(hasil.getBiayaProvisi()));
        txtPolisAsuransi.setText(NumberUtil.toIndoPriceFormatted(hasil.getBiayaPolis()));
        txtAdministrasi.setText(NumberUtil.toIndoPriceFormatted(hasil.getBiayaAdministrasi()));
        txtTotalBiayaLainnya.setText(NumberUtil.toIndoPriceFormatted(hasil.getTotalLainnya()));

        txtAngsuranPokok.setText(NumberUtil.toIndoPriceFormatted(hasil.getTotalAngsuran()));
        txtTotalDp.setText(NumberUtil.toIndoPriceFormatted(hasil.getTotalUangMuka()));
        txtTanggalMulai.setText(hasil.getTanggalMulai());
        txtTanggalSelesai.setText(hasil.getTanggalBerakhir());
    }

    private void toggleExpanded(boolean expanded, LinearLayout layout) {
        if (expanded) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.btn_back, R.id.btn_data_pinjaman, R.id.btn_platform_data_pinjaman, R.id.btn_biaya_lainnya, R.id.btn_hasil})
    public void onViewClicked(View view) {
        boolean expanded;
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_data_pinjaman:
                expanded = expDataPinjaman;
                expDataPinjaman = !expanded;
                toggleExpanded(expDataPinjaman, layoutDataPinjaman);
                break;
            case R.id.btn_platform_data_pinjaman:
                expanded = expPlatformDataPinjaman;
                expPlatformDataPinjaman = !expanded;
                toggleExpanded(expPlatformDataPinjaman, layoutPlatformDataPinjaman);
                break;
            case R.id.btn_biaya_lainnya:
                expanded = expLainnya;
                expLainnya = !expanded;
                toggleExpanded(expLainnya, layoutBiayaLainnya);
                break;
            case R.id.btn_hasil:
                expanded = expHasil;
                expHasil = !expanded;
                toggleExpanded(expHasil, layoutHasil);
                break;
        }
    }
}
