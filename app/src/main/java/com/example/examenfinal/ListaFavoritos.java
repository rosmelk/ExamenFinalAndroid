package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.examenfinal.adapters.FavoritoAdapter;
import com.example.examenfinal.dao.LibroDAO;
import com.example.examenfinal.database.AppDataBase;
import com.example.examenfinal.entities.Libro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFavoritos extends AppCompatActivity {

    AppDataBase db;
    List<Libro> libroo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_favoritos);

        db =AppDataBase.getDataBase(getApplicationContext());
        LibroDAO dao = db.libroDAO();
        List<Libro> libroo =dao.getAll();


        FavoritoAdapter favoritoAdapter = new FavoritoAdapter(libroo);
        RecyclerView rv = findViewById(R.id.rvListaFavoritos);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(favoritoAdapter);
        Log.e("DATOS", "Titulo: "+ libroo);



    }
}