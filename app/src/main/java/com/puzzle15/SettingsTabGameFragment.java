package com.puzzle15;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsTabGameFragment extends Fragment {

    private Spinner spnLanguage, spnCardStyle, spnAnimationSpeed;

    private int language,animationSpeed, cardStyle;
    public static final String SHARED_PREFS = "gameSettings";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_settings_tab_game, container, false);
        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        spnLanguage = view.findViewById(R.id.spnCountry);
        spnCardStyle = view.findViewById(R.id.spnCardStyle);
        spnAnimationSpeed = view.findViewById(R.id.spnAnimationSpeed);
        updateViews();
        spnLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), spnLanguage.getSelectedItem().toString() + " Selected", Toast.LENGTH_SHORT).show();
                saveData();
                //((MainActivity)getActivity()).recreate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnCardStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getActivity(), spnCardStyle.getSelectedItem().toString() + " Selected", Toast.LENGTH_SHORT).show();
                saveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnAnimationSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), spnAnimationSpeed.getSelectedItem().toString() + " Selected", Toast.LENGTH_SHORT).show();
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
        editor.putInt("language", spnLanguage.getSelectedItemPosition());
        editor.putInt("style", spnCardStyle.getSelectedItemPosition());
        editor.putInt("animation", spnAnimationSpeed.getSelectedItemPosition());

        editor.apply();


    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        language = (int) sharedPreferences.getInt("language", 0);
        animationSpeed = (int) sharedPreferences.getInt("style", 0);
        cardStyle = (int) sharedPreferences.getInt("animation", 0);
    }

    public void updateViews() {
        spnLanguage.setSelection(language);
        spnCardStyle.setSelection(animationSpeed);
        spnAnimationSpeed.setSelection(cardStyle);
    }

}
