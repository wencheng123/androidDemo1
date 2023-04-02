package com.wen.demo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TabLayout tabLayout  = findViewById(R.id.tab_layout);

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("北京望京");
        titles.add("上海");
        titles.add("广州张杰演唱会");
        titles.add("深圳");
        titles.add("长沙世界之窗");
        titles.add("武汉");
        titles.add("南京");

        List<Fragment> fragments = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
            fragments.add(MainFragment.newInstance(titles.get(i),""));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String s = tab.getText().toString();
                SpannableString ss = new SpannableString(s);
                ss.setSpan(new StyleSpan(Typeface.BOLD),0,s.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                ss.setSpan(new AbsoluteSizeSpan(18,true),0,s.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                tab.setText(ss);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                String s = tab.getText().toString();
                SpannableString ss = new SpannableString(s);
                ss.setSpan(new StyleSpan(Typeface.NORMAL),0,s.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                ss.setSpan(new AbsoluteSizeSpan(16,true),0,s.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                tab.setText(ss);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
//                return super.getPageTitle(position);
               return titles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }
}