package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.examenfinal.adapters.LibroAdapter;
import com.example.examenfinal.entities.Libro;
import com.example.examenfinal.factories.RetroFactory;
import com.example.examenfinal.services.LibroServicios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaLibros extends AppCompatActivity {

    List<Libro> libro = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libros);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearLibro.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = RetroFactory.build(getApplicationContext());
        LibroServicios service = retrofit.create(LibroServicios.class);
        Call<List<Libro>> call = service.getLibro();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {

                libro = response.body();
                LibroAdapter adapter = new LibroAdapter(libro);
                RecyclerView rv = findViewById(R.id.rvLibros);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv.setHasFixedSize(true);
                rv.setAdapter(adapter);
                Log.e("DATOS", "Titulo: "+ libro);

            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("APP_VJ20202", "No hubo conectividad con el servicio web");

            }
        });


    }

}