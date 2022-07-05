package com.example.examenfinal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenfinal.dao.LibroDAO;
import com.example.examenfinal.entities.Libro;

@Database(entities = {Libro.class}, version = 1)
public  abstract  class AppDataBase extends RoomDatabase {
    public abstract LibroDAO libroDAO();

    public  static AppDataBase getDataBase(Context context){

        return Room.databaseBuilder(context, AppDataBase.class, "com.example.examenfinal.database").allowMainThreadQueries()
                .build();
    }
}
