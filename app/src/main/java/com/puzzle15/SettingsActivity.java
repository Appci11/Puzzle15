package com.puzzle15;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SettingsActivity extends MainActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    Button btnApply, btnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Custom1);    //bet neveiks fragmentams
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnApply = findViewById(R.id.btnSettingsApply);
        btnToMenu = findViewById(R.id.btnSettingsToMenu);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.game)), 0);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.graphics)), 1);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.sound)), 2);

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(SettingsActivity.this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) tab.setText(getString(R.string.game));
                if (position == 1) tab.setText(getString(R.string.graphics));
                if (position == 2) tab.setText(getString(R.string.sound));
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

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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