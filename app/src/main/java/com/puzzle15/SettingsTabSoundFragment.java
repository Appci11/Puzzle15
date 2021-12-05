package com.puzzle15;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
    CheckBox chkBoxSong1, chkBoxSong2, chkBoxSong3;

    private boolean musicswitchOnOff,effectswitchOnOff;


    public static final String SHARED_PREFS = "musicSettings";



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
        loadData();
        updateViews();



        swchEffects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getActivity(),"Effect Sound: ON",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"Effect Sound: OFF",Toast.LENGTH_SHORT).show();
                }
                saveData();
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
                saveData();


            }

        });

        chkBoxSong1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                saveData();
            }
        });
        chkBoxSong2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                saveData();
            }
        });
        chkBoxSong3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                saveData();
            }
        });




    }

    private void initViews(@NonNull View view){
        swchEffects = view.findViewById(R.id.swchEffects);
        swchMusic = view.findViewById(R.id.swchMusic);

        chkBoxSong1 = view.findViewById(R.id.chkBoxSong1);
        chkBoxSong2 = view.findViewById(R.id.chkBoxSong2);
        chkBoxSong3 = view.findViewById(R.id.chkBoxSong3);

    }

    public void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("swchMusic", swchMusic.isChecked());
        editor.putBoolean("swchEffect", swchEffects.isChecked());

        editor.putBoolean("song1",chkBoxSong1.isChecked());
        editor.putBoolean("song2",chkBoxSong2.isChecked());
        editor.putBoolean("song3",chkBoxSong3.isChecked());

        editor.apply();

        //Toast.makeText(this.getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        musicswitchOnOff = sharedPreferences.getBoolean("swchMusic", true);
        effectswitchOnOff = sharedPreferences.getBoolean("swchEffect", true);

        chkBoxSong1.setChecked(sharedPreferences.getBoolean("song1", true));
        chkBoxSong2.setChecked(sharedPreferences.getBoolean("song2", true));
        chkBoxSong3.setChecked(sharedPreferences.getBoolean("song3", true));

    }

    public void updateViews() {
        swchMusic.setChecked(musicswitchOnOff);
        swchEffects.setChecked(effectswitchOnOff);
    }

}