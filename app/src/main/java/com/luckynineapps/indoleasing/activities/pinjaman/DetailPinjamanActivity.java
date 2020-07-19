package com.luckynineapps.indoleasing.activities.pinjaman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.data.Session;
import com.luckynineapps.indoleasing.models.pinjaman.Pinjaman;
import com.luckynineapps.indoleasing.utils.AdsUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPinjamanActivity extends AppCompatActivity {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_bunga)
    TextView txtBunga;
    @BindView(R.id.txt_jenis)
    TextView txtJenis;
    @BindView(R.id.txt_deskripsi)
    TextView txtDeskripsi;
    @BindView(R.id.txt_konten)
    TextView txtKonten;
    @BindView(R.id.ad_bottom)
    AdView bottomAds;

    Pinjaman pinjaman;
    Session session;
    private AdsUtil adsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pinjaman);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        session = new Session(this);

        pinjaman = (Pinjaman) getIntent().getSerializableExtra("pinjaman");

        initAd();
        initView();
    }


    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        bottomAds.loadAd(adRequest);

        adsUtil = AdsUtil.getInstance(this);
    }

    private void initView() {
        Picasso.get().load(pinjaman.getImage()).placeholder(R.drawable.ic_money_2).into(image);
        txtTitle.setText(pinjaman.getNama());
        txtNama.setText(pinjaman.getNama());
        txtBunga.setText(pinjaman.getBunga());
        txtDeskripsi.setText(Html.fromHtml(pinjaman.getDeskripsi()));
        txtKonten.setText(Html.fromHtml(pinjaman.getKonten()));

        if (pinjaman.getJenis().size() > 0) {
            StringBuilder jenis = new StringBuilder();
            for (int i = 0; i < pinjaman.getJenis().size(); i++) {
                if (i == 0) {
                    jenis.append(pinjaman.getJenis().get(i));
                } else {
                    jenis.append(" - ").append(pinjaman.getJenis().get(i));
                }
            }
            txtJenis.setVisibility(View.VISIBLE);
            txtJenis.setText(jenis.toString());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.btn_back, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_download:
                session.setClickCountDownload();
                if (session.getClickCountDowwload() % 2 != 0) {
                    adsUtil.setOnAdClosed(new AdsUtil.OnAdClosed() {
                        @Override
                        public void OnClosed() {
                            open();
                        }
                    });
                    adsUtil.showAds();
                } else {
                    open();
                }
                break;
        }
    }

    void open() {
        Intent intent = new Intent(this, KonfirmasiDownloadActivity.class);
        intent.putExtra("pinjaman", pinjaman);
        startActivity(intent);
    }
}
