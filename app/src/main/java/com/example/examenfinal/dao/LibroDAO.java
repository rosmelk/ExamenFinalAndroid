package com.example.examenfinal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.examenfinal.entities.Libro;

import java.util.List;
@Dao
public interface LibroDAO {

    //listar
    @Query("SELECT * FROM libros")
    List<Libro>  getAll();

    //crear
    @Insert
    void crear(Libro libros);

    @Delete
    void eliminar(Libro libros);

}
