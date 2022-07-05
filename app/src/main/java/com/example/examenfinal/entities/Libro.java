package com.example.examenfinal.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "libros")
public class Libro  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public String resumen;
    public String caratula;

    public Libro() {
    }

    public Libro(int id, String titulo, String resumen, String caratula) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.caratula = caratula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumen='" + resumen + '\'' +
                ", caratula='" + caratula + '\'' +
                '}';
    }
}
