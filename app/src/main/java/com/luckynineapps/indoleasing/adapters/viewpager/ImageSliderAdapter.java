package com.luckynineapps.indoleasing.adapters.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.models.ImageSliderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {

    private Context mContext;
    private String mUrl;
    private List<ImageSliderModel> imageList;
    private LayoutInflater inflater;

    public ImageSliderAdapter(Context context, List<ImageSliderModel> list, String url) {
        mContext = context;
        imageList = list;
        mUrl = url;
        if (mContext != null) {
            inflater = LayoutInflater.from(context);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ViewGroup imageLayout = (ViewGroup) inflater.inflate(R.layout.slider_home, collection, false);
        ImageView imageView = imageLayout.findViewById(R.id.imageView);
        String imageUrl = mUrl + imageList.get(position).getImage_drawable();
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_money_2).into(imageView);
        collection.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return imageList.get(position).getName();
    }
}
