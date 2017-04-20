package com.henryzu.henryzu.factory;

import android.support.v4.app.Fragment;

import com.henryzu.henryzu.fragment.Product_PageFragment;
import com.henryzu.henryzu.fragment.ClassRoom_PageFragment;
import com.henryzu.henryzu.fragment.Home_PageFragment;
import com.henryzu.henryzu.fragment.Mine_PageFragment;
import com.henryzu.henryzu.fragment.Community_PageFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class FragmentFactory {
    public static HashMap<Integer,Fragment> fragmentHashMap=new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentHashMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new Home_PageFragment();
                break;
            case 1:
                fragment = new Product_PageFragment();
                break;
            case 2:
                fragment = new ClassRoom_PageFragment();
                break;
            case 3:
                fragment = new Community_PageFragment();
                break;
            case 4:
                fragment = new Mine_PageFragment();
                break;
        }
        fragmentHashMap.put(position,fragment);
        return fragment;
    }
}
