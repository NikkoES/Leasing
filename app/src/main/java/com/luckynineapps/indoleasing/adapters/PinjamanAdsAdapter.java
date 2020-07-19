package com.luckynineapps.indoleasing.adapters;
/* 
    Created by Hakim on 2019-10-28
    Email hakimmarsudi@outlook.com
*/

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.config.manager.ConfigurationManager;
import com.luckynineapps.indoleasing.models.pinjaman.Pinjaman;
import com.luckynineapps.indoleasing.utils.CommonUtil;
import com.luckynineapps.indoleasing.views.TemplateView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.luckynineapps.indoleasing.data.Constant.WEB_URL_IMAGE_FINTECH;

public class PinjamanAdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int VIEW_ADS = 1;
    private final static int VIEW_ITEM = 2;

    private static final int LIST_AD_DELTA = 5;

    Context context;
    List<Pinjaman> list;

    public interface OnItemClickListener {
        void onClick(Pinjaman item);
    }

    public interface OnItemCheckListener {
        void onItemCheck(int pos);
    }

    OnItemCheckListener onItemCheckListener;
    OnItemClickListener mOnItemClickListener;

    public PinjamanAdsAdapter(Context context, List<Pinjaman> list) {
        this.context = context;
        this.list = list;
    }

    public PinjamanAdsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        int pos = position + 1;
        if (pos > 0 && pos % LIST_AD_DELTA == 0) {
            return VIEW_ADS;
        }
        return VIEW_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == VIEW_ITEM) {
            return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_pinjaman, viewGroup, false), onItemCheckListener);
        }

        return new AdsHolder(LayoutInflater.from(context).inflate(R.layout.view_table_ads, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        if (getItemViewType(pos) == VIEW_ITEM) {
            final Pinjaman item = list.get(getRealPosition(pos));

            ItemHolder holder = (ItemHolder) viewHolder;

            holder.txtNama.setText(item.getNama());
            holder.txtDeskripsi.setText(Html.fromHtml(item.getDeskripsi()));

            if (item.getJenis().size() > 0) {
                StringBuilder jenis = new StringBuilder();
                for (int i = 0; i < item.getJenis().size(); i++) {
                    if (i == 0) {
                        jenis.append(item.getJenis().get(i));
                    } else {
                        jenis.append(" - ").append(item.getJenis().get(i));
                    }
                }
                holder.txtJenis.setVisibility(View.VISIBLE);
                holder.txtJenis.setText(jenis.toString());
            }

            holder.cbPinjaman.setChecked(item.isChecked());

            Picasso.get().load(item.getImage()).placeholder(R.drawable.placeholder_leasing).into(holder.image);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(item);
                }
            });
        } else {
            final AdsHolder adsHolder = (AdsHolder) viewHolder;
            AdLoader adLoader = new AdLoader.Builder(context, context.getString(R.string.ad_native_data))
                    .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                        @Override
                        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                            adsHolder.adView.setNativeAd(unifiedNativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    @Override
    public int getItemCount() {
        int additionalContent = 0;
        if (list.size() > 0 && LIST_AD_DELTA > 0 && list.size() > LIST_AD_DELTA) {
            additionalContent = list.size() / LIST_AD_DELTA;
        }

        return list.size() + additionalContent;
    }

    private int getRealPosition(int pos) {
        if (LIST_AD_DELTA == 0) {
            return pos;
        } else {
            return pos - (pos / LIST_AD_DELTA);
        }
    }

    public void resetListData() {
        this.list = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public void setOnItemCheckListener(final OnItemCheckListener mItemCheckListener) {
        this.onItemCheckListener = mItemCheckListener;
    }

    public void toggleCheck(int pos) {
        if (getCheckedPinjaman().size() < 3 || list.get(pos).isChecked()) {
            list.get(pos).toggleCheck();
        }

        notifyDataSetChanged();
    }

    public Pinjaman getItem(int pos) {
        return list.get(pos);
    }

    public List<Pinjaman> getCheckedPinjaman() {
        List<Pinjaman> checked = new ArrayList<>();

        for (Pinjaman pinjaman : list) {
            if (pinjaman.isChecked()) {
                checked.add(pinjaman);
            }
        }

        return checked;
    }

    public void swap(List<Pinjaman> datas) {
        if (datas == null || datas.size() == 0) list.clear();
        if (list != null && list.size() > 0)
            list.clear();
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_pinjaman)
        CheckBox cbPinjaman;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txt_nama)
        TextView txtNama;
        @BindView(R.id.txt_deskripsi)
        TextView txtDeskripsi;
        @BindView(R.id.txt_jenis)
        TextView txtJenis;

        public ItemHolder(View itemView, final OnItemCheckListener checkListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cbPinjaman.setClickable(false);
            cbPinjaman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkListener.onItemCheck(getRealPosition(getAdapterPosition()));
                }
            });
        }
    }

    public class AdsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ads)
        TemplateView adView;

        public AdsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
