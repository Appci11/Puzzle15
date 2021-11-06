package com.puzzle15;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsTabGraphicsFragment extends Fragment {

    private Spinner spnCardBackground, spnColorTheme;

    private int backgroundSave,themeSave;
    public static final String SHARED_PREFS = "graphicsSettings";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_settings_tab_graphics, container, false);
        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();


        spnCardBackground = (Spinner) view.findViewById(R.id.spnCardBackground);
        spnColorTheme = (Spinner) view.findViewById(R.id.spnColorTheme);

        updateViews();

        spnCardBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), spnCardBackground.getSelectedItem().toString() + " Selected", Toast.LENGTH_SHORT).show();
                saveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnColorTheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), spnColorTheme.getSelectedItem().toString() + " Selected", Toast.LENGTH_SHORT).show();
                saveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("graphic", spnCardBackground.getSelectedItemPosition());
        editor.putInt("theme", spnColorTheme.getSelectedItemPosition());

        editor.apply();


        //Toast.makeText(this.getActivity(), "Data saved Graphic id: " +spnCardBackground.getSelectedItemPosition() , Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        backgroundSave = (int) sharedPreferences.getInt("graphic", 0);

        themeSave = (int) sharedPreferences.getInt("theme", 0);

    }

    public void updateViews() {
        spnCardBackground.setSelection(backgroundSave);
        spnColorTheme.setSelection(themeSave);
        }

}
