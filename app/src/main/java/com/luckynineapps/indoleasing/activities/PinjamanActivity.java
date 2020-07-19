package com.luckynineapps.indoleasing.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.adapters.viewpager.TabViewPagerAdapter;
import com.luckynineapps.indoleasing.data.Session;
import com.luckynineapps.indoleasing.fragments.DialogInformation;
import com.luckynineapps.indoleasing.fragments.pinjaman.KalkulatorFragment;
import com.luckynineapps.indoleasing.fragments.pinjaman.PinjamanFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PinjamanActivity extends AppCompatActivity {

    @BindView(R.id.image_title)
    TextView imageTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    Session session;

    TabViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        session = new Session(this);

        initViewPager();
    }

    private void initViewPager() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new TabViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PinjamanFragment(), "List Leasing");
        adapter.addFragment(new KalkulatorFragment(), "Kalkulator Dana");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    @OnClick({R.id.btn_information, R.id.btn_share, R.id.btn_pinjaman, R.id.btn_perbandingan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_information:
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogInformation fragment1 = new DialogInformation();
                fragment1.setCancelable(true);
                fragment1.show(fragmentManager, "Dialog Information");
                break;
            case R.id.btn_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Mau mendapatkan pinjaman leasing dengan cepat.\nSilahkan download aplikasi ini, sekarang juga :\nhttp://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();

                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Indo Leasing");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Indo Leasing"));
                break;
            case R.id.btn_pinjaman:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn_perbandingan:
                PinjamanFragment fragment = (PinjamanFragment) adapter.getItem(0);
                fragment.comparePinjaman();
                break;
        }
    }
}
