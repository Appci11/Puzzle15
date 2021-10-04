package com.puzzle15;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SettingsTabFragmentAdapter extends FragmentStateAdapter {
    public SettingsTabFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1:
                return new SettingsTabGraphicsFragment();
            case 2:
                return new SettingsTabSoundFragment();
        }
        return new SettingsTabGameFragment();
    }

    @Override
    public int getItemCount() {
        return 3;       //Nes 3 tab'ai, pakeist kintant tab'u skaiciui
    }
}
