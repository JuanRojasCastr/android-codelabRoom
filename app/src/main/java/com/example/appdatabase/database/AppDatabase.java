package com.example.appdatabase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appdatabase.dao.UserDao;
import com.example.appdatabase.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
