//Database able

package com.puzzle15;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scores {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "score")
    private int score;
    @NonNull
    @ColumnInfo(name = "game_mode")
    private String gameMode;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
