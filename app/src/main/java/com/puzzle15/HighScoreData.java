package com.puzzle15;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HighScoreData implements Serializable, Comparable { //data structure for holding highScore info
    public double score;
    public String dateTime;

    public HighScoreData(double _score){
        score = _score;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        dateTime = formatter.format(date);
    }
    @Override
    public int compareTo(Object o) {
        HighScoreData highScore = (HighScoreData) o;
        return Double.compare(this.score, highScore.score);
    }
}

