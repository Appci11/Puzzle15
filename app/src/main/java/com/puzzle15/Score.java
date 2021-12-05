//Database able

package com.puzzle15;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "score")
    private long score;
    @NonNull
    @ColumnInfo(name = "game_mode")
    private String gameMode;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ColumnInfo(name = "date")
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @NonNull
    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(@NonNull String gameMode) {
        this.gameMode = gameMode;
    }
}
