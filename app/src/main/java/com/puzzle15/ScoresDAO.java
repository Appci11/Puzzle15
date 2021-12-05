package com.puzzle15;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoresDAO {
    @Insert
    void insert(Scores scores);

    @Query("DELETE FROM Scores")
    void deleteAll();

    @Query("SELECT * FROM Scores ORDER BY score DESC LIMIT :n")
    List<Scores> getTopNScores(int n);

    @Query("SELECT * FROM Scores WHERE name = :name ORDER BY score DESC LIMIT :n")
    List<Scores> getUsersNScores(int n, String name);
}
