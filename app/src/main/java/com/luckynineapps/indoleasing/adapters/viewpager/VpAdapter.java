package com.luckynineapps.indoleasing.adapters.viewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VpAdapter extends FragmentPagerAdapter {

    private List<String> fragmentTitle = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    HashMap<Integer, String> mFragmentTags;
    FragmentManager fragmentManager;

    public VpAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
    }


    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        Log.d("TAG", "getFragment: " + tag);
        if (tag == null)
            return null;
        return fragmentManager.findFragmentByTag(tag);
    }
}
