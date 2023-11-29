package com.cricketprediction.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class Userdatabase extends RoomDatabase {


    public abstract Userdao getdao();

    public static Userdatabase INSTANCE;

    public static Userdatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, Userdatabase.class, "Userdatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
