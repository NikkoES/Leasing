package com.luckynineapps.indoleasing.fragments.pinjaman;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.activities.pinjaman.DetailPinjamanActivity;
import com.luckynineapps.indoleasing.activities.PerbandinganActivity;
import com.luckynineapps.indoleasing.adapters.PinjamanAdsAdapter;
import com.luckynineapps.indoleasing.data.Session;
import com.luckynineapps.indoleasing.models.pinjaman.Pinjaman;
import com.luckynineapps.indoleasing.models.pinjaman.PinjamanResponse;
import com.luckynineapps.indoleasing.utils.AdsUtil;
import com.luckynineapps.indoleasing.utils.DialogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.luckynineapps.indoleasing.data.Constant.LEASING;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinjamanFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ad_bottom)
    AdView bottomAds;

    List<Pinjaman> listPinjaman;

    PinjamanAdsAdapter adapter;

    Pinjaman item;

    Session session;

    private AdsUtil adsUtil;

    public PinjamanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pinjaman, container, false);

        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());

        initAd();
        initRecyclerView();

        return view;
    }

    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        bottomAds.loadAd(adRequest);

        adsUtil = AdsUtil.getInstance(getActivity());
    }

    private void initRecyclerView() {
        listPinjaman = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PinjamanAdsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PinjamanAdsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Pinjaman items) {
                item = items;
                if (session.getClickCount() == 0) {
                    adsUtil.setOnAdClosed(new AdsUtil.OnAdClosed() {
                        @Override
                        public void OnClosed() {
                            Intent intent = new Intent(getActivity(), DetailPinjamanActivity.class);
                            intent.putExtra("pinjaman", item);
                            startActivity(intent);
                        }
                    });

                    adsUtil.showAds();
                } else if ((session.getClickCount() % 3 == 0)) {
                    adsUtil.setOnAdClosed(new AdsUtil.OnAdClosed() {
                        @Override
                        public void OnClosed() {
                            Intent intent = new Intent(getActivity(), DetailPinjamanActivity.class);
                            intent.putExtra("pinjaman", item);
                            startActivity(intent);
                        }
                    });

                    adsUtil.showAds();
                } else {
                    Intent intent = new Intent(getActivity(), DetailPinjamanActivity.class);
                    intent.putExtra("pinjaman", item);
                    startActivity(intent);
                }
                session.setClickCount();
            }
        });
        adapter.setOnItemCheckListener(new PinjamanAdsAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(int pos) {
                Pinjaman item = adapter.getItem(pos);
                adapter.toggleCheck(pos);

                if (!item.isChecked()) {
                    if (adapter.getCheckedPinjaman().size() >= 3) {
                        Toast.makeText(getActivity(), "Max 3 Item", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loadItems();
    }

    private void loadItems() {
        DialogUtils.openDialog(getActivity());

        AndroidNetworking.get(LEASING)
                .build()
                .getAsObject(PinjamanResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof PinjamanResponse) {
                            PinjamanResponse response1 = ((PinjamanResponse) response);
                            if (response1.getStatus().equalsIgnoreCase("success") && (response1.getData().size() > 0)) {
                                listPinjaman.addAll(response1.getData());
                                adapter.swap(listPinjaman);
                                DialogUtils.closeDialog();
                            } else {
                                DialogUtils.closeDialog();
                                Toast.makeText(getActivity(), "Data Tidak Ditemukan !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getActivity(), "Kesalahan teknis : " + anError.toString(), Toast.LENGTH_SHORT).show();
                        DialogUtils.closeDialog();
                    }
                });
    }

    public void comparePinjaman() {
        if (adapter.getCheckedPinjaman().size() > 0) {
            session.setClickCountCompare();
            if (session.getClickCountCompare() % 2 != 0) {
                adsUtil.setOnAdClosed(new AdsUtil.OnAdClosed() {
                    @Override
                    public void OnClosed() {
                        Intent i = new Intent(getActivity(), PerbandinganActivity.class);
                        i.putExtra("list_pinjaman", (Serializable) adapter.getCheckedPinjaman());
                        startActivity(i);
                    }
                });

                adsUtil.showAds();
            } else {
                Intent i = new Intent(getActivity(), PerbandinganActivity.class);
                i.putExtra("list_pinjaman", (Serializable) adapter.getCheckedPinjaman());
                startActivity(i);
            }
        } else {
            Toast.makeText(getActivity(), "Please Select Item", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
