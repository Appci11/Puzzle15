package com.puzzle15;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SettingsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Game"), 0);        //unhardcode text
        tabLayout.addTab(tabLayout.newTab().setText("Graphics"), 1);    //unhardcode text
        tabLayout.addTab(tabLayout.newTab().setText("Sound"), 2);       //unhardcode text

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(SettingsActivity.this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) tab.setText("Game");     //unhardcode text
                if (position == 1) tab.setText("Graphics"); //unhardcode text
                if (position == 2) tab.setText("Sound");    //unhardcode text
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setCurrentItem(0);
    }


    class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(AppCompatActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            Fragment frag_new = null;
            if (position == 0) frag_new = new SettingsTabGameFragment();
            if (position == 1) frag_new = new SettingsTabGraphicsFragment();
            if (position == 2) frag_new = new SettingsTabSoundFragment();
            return frag_new;
        }

        @Override
        public int getItemCount() {
            return 3;   //nes 3 tab'ai, jei ka pakeist
        }


    }
}