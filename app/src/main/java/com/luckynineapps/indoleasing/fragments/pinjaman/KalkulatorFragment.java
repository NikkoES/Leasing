package com.luckynineapps.indoleasing.fragments.pinjaman;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.activities.kalkulator.HasilActivity;
import com.luckynineapps.indoleasing.activities.model.Hasil;
import com.luckynineapps.indoleasing.data.Session;
import com.luckynineapps.indoleasing.utils.AdsUtil;
import com.luckynineapps.indoleasing.utils.CommonUtil;
import com.luckynineapps.indoleasing.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class KalkulatorFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.et_harga_otr)
    EditText etHargaOtr;
    @BindView(R.id.et_uang_muka)
    EditText etUangMuka;
    @BindView(R.id.et_lama_tenor)
    EditText etLamaTenor;
    @BindView(R.id.et_bunga_pertahun)
    EditText etBungaPertahun;
    @BindView(R.id.et_biaya_asuransi)
    EditText etBiayaAsuransi;
    @BindView(R.id.et_biaya_provisi)
    EditText etBiayaProvisi;
    @BindView(R.id.et_biaya_polis_asuransi)
    EditText etBiayaPolisAsuransi;
    @BindView(R.id.et_biaya_administrasi)
    EditText etBiayaAdministrasi;
    @BindView(R.id.et_tanggal_mulai)
    EditText etTanggalMulai;
    @BindView(R.id.ad_bottom)
    AdView bottomAds;
    private AdsUtil adsUtil;

    Session session;

    private Double hargaKendaraanOtr, uangMuka, bungaPinjaman, biayaAsuransi, biayaProvisi, biayaPolis, biayaAdministrasi, pokokHutang;
    private int tenor;
    private Double totalLainnya, totalUangMuka, totalAngsuran;

    public KalkulatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kalkulator, container, false);

        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());

        initAd();
        initEditText();

        //initDummy();

        return view;
    }

    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        bottomAds.loadAd(adRequest);

        adsUtil = AdsUtil.getInstance(getActivity());
    }

    private void initDummy() {
        etHargaOtr.setText(String.valueOf(120000000));
        etUangMuka.setText(String.valueOf(10));
        etLamaTenor.setText(String.valueOf(5));
        etBungaPertahun.setText(String.valueOf(4.8));
        etBiayaAsuransi.setText(String.valueOf(5));
        etBiayaProvisi.setText(String.valueOf(0.45));
        etBiayaPolisAsuransi.setText(String.valueOf(40000));
        etBiayaAdministrasi.setText(String.valueOf(700000));
    }

    private void initEditText() {
        CommonUtil.editTextCurrency(etHargaOtr);
        CommonUtil.editTextCurrency(etBiayaPolisAsuransi);
        CommonUtil.editTextCurrency(etBiayaAdministrasi);

        etBungaPertahun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains(".")) {
                    int indexSeparator = s.toString().indexOf('.');

                    int numStart = s.toString().substring(0, indexSeparator - 1).length();
                    int numEnd = s.toString().substring(indexSeparator + 1).length();

                    if (numStart > 2) {
                        etBungaPertahun.setError("Maksimal 2 digit di depan koma !");
                    }
                    if (numEnd > 1) {
                        etBungaPertahun.setError("Maksimal 1 digit di belakang koma !");
                    }
                } else {
                    if (s.toString().length() > 2) {
                        etBungaPertahun.setError("Maksimal 2 digit di depan koma !");
                    }
                }
            }
        });

        etBiayaAsuransi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains(".")) {
                    int indexSeparator = s.toString().indexOf('.');

                    int numStart = s.toString().substring(0, indexSeparator - 1).length();
                    int numEnd = s.toString().substring(indexSeparator + 1).length();

                    if (numStart > 2) {
                        etBiayaAsuransi.setError("Maksimal 2 digit di depan koma !");
                    }
                    if (numEnd > 2) {
                        etBiayaAsuransi.setError("Maksimal 2 digit di belakang koma !");
                    }
                } else {
                    if (s.toString().length() > 2) {
                        etBiayaAsuransi.setError("Maksimal 2 digit di depan koma !");
                    }
                }
            }
        });

        etBiayaProvisi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains(".")) {
                    int indexSeparator = s.toString().indexOf('.');

                    int numStart = s.toString().substring(0, indexSeparator - 1).length();
                    int numEnd = s.toString().substring(indexSeparator + 1).length();

                    if (numStart > 2) {
                        etBiayaProvisi.setError("Maksimal 2 digit di depan koma !");
                    }
                    if (numEnd > 2) {
                        etBiayaProvisi.setError("Maksimal 2 digit di belakang koma !");
                    }
                } else {
                    if (s.toString().length() > 2) {
                        etBiayaProvisi.setError("Maksimal 2 digit di depan koma !");
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private Double replacePrice(String price) {
        return Double.parseDouble(price.replace(".", ""));
    }

    private void hitung() {
        hargaKendaraanOtr = replacePrice(etHargaOtr.getText().toString());
        uangMuka = (Double.parseDouble(etUangMuka.getText().toString()) * 0.01) * hargaKendaraanOtr;
        pokokHutang = hargaKendaraanOtr - uangMuka;
        bungaPinjaman = (pokokHutang * ((Double.parseDouble(etBungaPertahun.getText().toString()) * 0.01) / 12));
        biayaAsuransi = (Double.parseDouble(etBiayaAsuransi.getText().toString()) * 0.01) * hargaKendaraanOtr;
        biayaProvisi = (Double.parseDouble(etBiayaProvisi.getText().toString()) * 0.01) * hargaKendaraanOtr;
        biayaPolis = replacePrice(etBiayaPolisAsuransi.getText().toString());
        biayaAdministrasi = replacePrice(etBiayaAdministrasi.getText().toString());
        tenor = Integer.parseInt(etLamaTenor.getText().toString());

        totalAngsuran = pokokHutang / (tenor * 12) + bungaPinjaman;
        totalLainnya = biayaAsuransi + biayaProvisi + biayaPolis + biayaAdministrasi;
        totalUangMuka = uangMuka + totalAngsuran + totalLainnya;

        Hasil hasil = new Hasil();
        hasil.setHargaKendaraanOtr(hargaKendaraanOtr);
        hasil.setUangMuka(uangMuka);
        hasil.setBunga(Double.parseDouble(etBungaPertahun.getText().toString()));
        hasil.setBungaPinjaman(bungaPinjaman);
        hasil.setBiayaAsuransi(biayaAsuransi);
        hasil.setBiayaProvisi(biayaProvisi);
        hasil.setBiayaPolis(biayaPolis);
        hasil.setBiayaAdministrasi(biayaAdministrasi);
        hasil.setTotalLainnya(totalLainnya);
        hasil.setPokokHutang(pokokHutang);
        hasil.setTenor(tenor);
        hasil.setTanggalMulai(etTanggalMulai.getText().toString());
        hasil.setTanggalBerakhir(CommonUtil.addDaysToDate(etTanggalMulai.getText().toString(), "dd-MM-yyyy", (tenor * 12 * 30)));
        hasil.setTotalUangMuka(totalUangMuka);
        hasil.setTotalAngsuran(totalAngsuran);

        Intent intent = new Intent(getActivity(), HasilActivity.class);
        intent.putExtra("hasil", hasil);
        startActivity(intent);
    }

    @OnClick({R.id.et_uang_muka, R.id.et_lama_tenor, R.id.et_tanggal_mulai, R.id.btn_hitung})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_uang_muka:
                final String[] dps = getActivity().getResources().getStringArray(R.array.array_uang_muka);
                DialogUtils.dialogArray(getActivity(), dps, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etUangMuka.setText(dps[which].replace("%", ""));
                        etUangMuka.setError(null);
                    }
                });
                break;
            case R.id.et_lama_tenor:
                final String[] tenors = getActivity().getResources().getStringArray(R.array.array_tenor);
                DialogUtils.dialogArray(getActivity(), tenors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etLamaTenor.setText(tenors[which].replace(" tahun", ""));
                        etLamaTenor.setError(null);
                    }
                });
                break;
            case R.id.et_tanggal_mulai:
                DialogUtils.dialogDateBefore(getActivity(), etTanggalMulai);
                break;
            case R.id.btn_hitung:
                ArrayList<View> list = new ArrayList<>();
                list.add(etHargaOtr);
                list.add(etUangMuka);
                list.add(etBungaPertahun);
                list.add(etBiayaAsuransi);
                list.add(etBiayaProvisi);
                list.add(etLamaTenor);
                list.add(etBiayaPolisAsuransi);
                list.add(etBiayaAdministrasi);
                list.add(etTanggalMulai);
                if (CommonUtil.validateEmptyEntries(list)) {
                    session.setClickCountCalculator();
                    if (session.getClickCountCalculator() % 2 != 0) {
                        adsUtil.setOnAdClosed(new AdsUtil.OnAdClosed() {
                            @Override
                            public void OnClosed() {
                                hitung();
                            }
                        });

                        adsUtil.showAds();
                    } else {
                        hitung();
                    }
                }
                break;
        }
    }
}
