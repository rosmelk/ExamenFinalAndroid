package com.example.examenfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examenfinal.entities.Image;
import com.example.examenfinal.entities.ImagePost;
import com.example.examenfinal.entities.Libro;
import com.example.examenfinal.factories.RetofitFactoryApi2;
import com.example.examenfinal.factories.RetroFactory;
import com.example.examenfinal.services.ImageService;
import com.example.examenfinal.services.LibroServicios;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditarLibro extends AppCompatActivity {


    Libro libro;
    EditText cTitulo,cResumen, cCaratula;
    ImageView tvImagen;
    Button editGuardar;
    LibroServicios servicios;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);

        Intent editDetalle = getIntent();
        libro = (Libro) editDetalle.getSerializableExtra("titulo");
        //Log.i("libro", libro.getTitulo());
        cTitulo = findViewById(R.id.cTitulo);
        cResumen = findViewById(R.id.cResumen);
        cCaratula = findViewById(R.id.cCaratulaImag);
        cTitulo.setText(libro.titulo);
        cResumen.setText(libro.resumen);
        cCaratula.setText(libro.caratula);
        editGuardar = findViewById(R.id.editGuardar);

        editGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Libro lbo = new Libro(id,cTitulo.getText().toString(),cResumen.getText().toString(), cCaratula.getText().toString());

                edit(lbo);
                Log.i("libro","app"+ lbo);

            }
        });


    }
    private void edit (Libro libr){

        Retrofit retrofit =   RetroFactory.build(getApplicationContext());
        servicios = retrofit.create(LibroServicios.class);
         id = libro.getId();
        Call<Libro> call = servicios.editrLibro(id,libr);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {

            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {

            }
        });



    }


}