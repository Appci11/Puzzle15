package com.puzzle15;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

public class HighScoreController{
    static String filename = "highScores.dat";

    public ArrayList<HighScoreData> LoadHighScore(String filesDir){

        File file = new File(filesDir, filename);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ArrayList<HighScoreData> highScoreData = ((ArrayList<HighScoreData>) ois.readObject());
            ois.close();
            inputStream.close();
            return highScoreData;
        } catch (FileNotFoundException e) {
            Log.v("HighScore File not found during loading", "HighScore File not found during loading");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void SaveHighScore(ArrayList<HighScoreData> highScores, String filesDir){
        File file = new File(filesDir, filename);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream oos  = new ObjectOutputStream(outputStream);
            oos.writeObject(highScores);
            oos.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateHighScoreList(HighScoreData highScore, String filesDir){
        //Get Top 5 scores
        ArrayList<HighScoreData> highScores = LoadHighScore(filesDir);
        if(highScores.size() < 5){
            highScores.add(highScore);
            Collections.sort(highScores);
            SaveHighScore(highScores, filesDir);
        } else {
            for (HighScoreData data: highScores) {
                if(data.score < highScore.score){
                    highScores.add(highScores.indexOf(data), highScore);
                    highScores.remove(highScores.size()-1);
                    SaveHighScore(highScores, filesDir);
                }
            } // if this ends, the score is lower than all of the serialized ones, so dong
        }
    }

}
