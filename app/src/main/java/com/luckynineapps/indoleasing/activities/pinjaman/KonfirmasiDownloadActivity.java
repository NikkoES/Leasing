package com.luckynineapps.indoleasing.activities.pinjaman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.models.pinjaman.Pinjaman;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class KonfirmasiDownloadActivity extends AppCompatActivity {

    Pinjaman pinjaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_download);
        ButterKnife.bind(this);

        pinjaman = (Pinjaman) getIntent().getSerializableExtra("pinjaman");

        initView();

    }

    private void initView() {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.btn_back, R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pinjaman.getPlaystore())));
                break;
        }
    }
}
