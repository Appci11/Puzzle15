//Nepavyksta sio varijanto naudot.
//Lieka, jei netycia suzinotume kaip.

package com.puzzle15;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDAO accountDAO();
}
