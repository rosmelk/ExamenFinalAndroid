package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenfinal.dao.LibroDAO;
import com.example.examenfinal.database.AppDataBase;
import com.example.examenfinal.entities.Libro;
import com.example.examenfinal.factories.RetroFactory;
import com.example.examenfinal.services.LibroServicios;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class VerDetalle extends AppCompatActivity {
   Button btnFavorito, btnQuitarFavorito, btnEditar;


   AppDataBase db;

    Libro libro ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle);
        btnFavorito = findViewById(R.id.btnFavorito);
        btnQuitarFavorito = findViewById(R.id.btnQuitarfavorito);
        btnEditar = findViewById(R.id.btnEditar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();

            }
        });
       Log.i("APP", "onCreate");

        db =AppDataBase.getDataBase(getApplicationContext());

        String libroJSON = getIntent().getStringExtra("LIBRO");
        libro = new Gson().fromJson(libroJSON, Libro.class);

        ImageView dCaratula = findViewById(R.id.dCaratula);
        TextView dTitulo = findViewById(R.id.dTitulo);
        TextView dResumen = findViewById(R.id.dResumen);
        Picasso.get().load(libro.caratula).into(dCaratula);
        dTitulo.setText(libro.titulo);
        dResumen.setText(libro.resumen);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String libroJSON = getIntent().getStringExtra("LIBRO");
                libro = new Gson().fromJson(libroJSON, Libro.class);
                ImageView dCaratula = findViewById(R.id.dCaratula);
                TextView dTitulo = findViewById(R.id.dTitulo);
                TextView dResumen = findViewById(R.id.dResumen);
                Picasso.get().load(libro.caratula).into(dCaratula);
                dTitulo.setText(libro.titulo);
                dResumen.setText(libro.resumen);
                guardarDatos(libro);



            }
        });

       btnQuitarFavorito.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               LibroDAO dao = db.libroDAO();
               dao.eliminar(libro);

           }
       });



    }

    private void editar(){

        Intent intent = new Intent(getApplicationContext(), EditarLibro.class);
        intent.putExtra("titulo", libro);
        startActivity(intent);



    }



    private void guardarDatos(Libro libros) {

        LibroDAO dao = db.libroDAO();
        dao.crear(libros);
        List<Libro> libross =dao.getAll();
        Log.i("APP_VJ20202", new Gson().toJson(libross));

    }
}