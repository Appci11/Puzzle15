package com.puzzle15;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDAO {
    @Insert
    void insert(Score score);

    @Query("DELETE FROM Score")
    void deleteAll();

    @Query("SELECT * FROM Score ORDER BY score DESC LIMIT :n")
    List<Score> getTopNScores(int n);

    @Query("SELECT * FROM Score WHERE name = :name ORDER BY score DESC LIMIT :n")
    List<Score> getUsersNScores(int n, String name);

    @Query("SELECT * FROM Score WHERE game_mode = :gameMode ORDER BY score DESC LIMIT :n")
    List<Score> getTopNScoresMode(int n, String gameMode);

    @Query("SELECT * FROM Score WHERE name = :name AND game_mode = :gameMode ORDER BY score DESC LIMIT :n")
    List<Score> getUsersNScoresMode(int n, String name, String gameMode);
}
