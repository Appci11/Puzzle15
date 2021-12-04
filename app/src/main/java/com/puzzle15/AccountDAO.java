package com.puzzle15;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDAO {
    @Insert
    void insert(Account account);

    @Query("DELETE FROM Account")
    void deleteAll();

    @Query("SELECT 1 FROM Account WHERE name = :name")
    int exists(String name);

    @Query("Select 1 from Account WHERE name = :name AND password = :pass")
    int tryLogin(String name, String pass);

    @Query("SELECT * from Account ORDER BY name ASC")
    List<Account> getAllPersons();
}
