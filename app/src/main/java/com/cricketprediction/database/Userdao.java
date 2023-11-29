package com.cricketprediction.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Userdao {

    @Insert
    void insert(User user);

    @Update
    void update( User user);

    @Query("delete from User where id=:id")

    void delete(int id);

    @Query("Select * from User")
    List<User> getAllUsers();
}
