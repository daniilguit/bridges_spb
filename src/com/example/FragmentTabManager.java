package com.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class FragmentTabManager extends android.support.v4.app.FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    private final ViewPager viewPager;
    private final List<Class<? extends Fragment>> fragmentsClass = new ArrayList<Class<? extends Fragment>>();
    private ActionBar actionBar;
    private SherlockFragmentActivity activity;

    public FragmentTabManager(SherlockFragmentActivity activity, ViewPager viewPager) {
        super(activity.getSupportFragmentManager());
        this.activity = activity;
        this.viewPager = viewPager;
        this.actionBar = activity.getSupportActionBar();
        viewPager.setAdapter(this);
        viewPager.setOnPageChangeListener(this);
    }

    public void addTab(int label, Class<? extends Fragment> fragmentClass) {
        ActionBar.Tab tab = actionBar.newTab();
        tab.setText(activity.getString(label));
        tab.setTabListener(this);
        tab.setTag(actionBar.getTabCount());
        actionBar.addTab(tab);
        fragmentsClass.add(fragmentClass);
        notifyDataSetChanged();
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        Object tag = tab.getTag();
        if (tag instanceof Integer) {
            viewPager.setCurrentItem((Integer) tag);
        } else if (tag instanceof Runnable) {
            ((Runnable) tag).run();
        }
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public Fragment getItem(int i) {
        return Fragment.instantiate(activity, fragmentsClass.get(i).getName());
    }

    @Override
    public int getCount() {
        return fragmentsClass.size();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }


}