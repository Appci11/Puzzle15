package com.puzzle15;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsTabSoundFragment extends Fragment {

    SwitchCompat swchEffects, swchMusic;
    SeekBar sbEffects, sbMusic;
    CheckBox chkBoxSong1, chkBoxSong2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_settings_tab_sound, container, false);
        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        swchEffects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getActivity(),"Effect Sound: ON",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"Effect Sound: OFF",Toast.LENGTH_SHORT).show();
                }

            }

        });
        swchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getActivity(),"Music Sound: ON",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"Music Sound: OFF",Toast.LENGTH_SHORT).show();
                }

            }

        });
        sbEffects.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(getActivity(),"Effects Bar at: " + sbEffects.getProgress() ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(getActivity(),"Music Bar at: " + sbMusic.getProgress() ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        chkBoxSong1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getActivity(), "Song 1 checked: " + compoundButton.isChecked(), Toast.LENGTH_SHORT).show();
            }
        });
        chkBoxSong2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getActivity(), "Song 2 checked: " + b, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(@NonNull View view){
        swchEffects = view.findViewById(R.id.swchEffects);
        swchMusic = view.findViewById(R.id.swchMusic);
        sbEffects = view.findViewById(R.id.seekBarEffects);
        sbMusic = view.findViewById(R.id.seekBarMusic);
        chkBoxSong1 = view.findViewById(R.id.chkBoxSong1);
        chkBoxSong2 = view.findViewById(R.id.chkBoxSong2);

    }

}