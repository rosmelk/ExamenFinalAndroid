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

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CrearLibro extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_CAMERA_PERMISSION = 100;

    private  ImageView tvImagen;
    private  String imagBase64;
    private EditText cTitulo;
    private EditText cResumen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_libro);

        Button btnTomarfoto = findViewById(R.id.btnFoto);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        cTitulo = findViewById(R.id.cTitulo);
        cResumen = findViewById(R.id.cResumen);
        tvImagen = findViewById(R.id.tvImagen);





        btnTomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    tomarFoto();
                } else {
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarPelicula();
            }
        });

    }

    private void guardarPelicula() {

        Retrofit retrofit = RetroFactory.build(getApplicationContext());
        LibroServicios libroServicios = retrofit.create(LibroServicios.class);

        Libro libro = new Libro();
        libro.titulo = String.valueOf(cTitulo.getText());
        libro.resumen = String.valueOf(cResumen.getText());

        ImagePost imagePost = new ImagePost();
        imagePost.image = imagBase64;
        Retrofit retrofitImagen = RetofitFactoryApi2.build(getApplicationContext());
        ImageService imageService = retrofitImagen.create(ImageService.class);

        Call<Image> call = imageService.crear(imagePost);

        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                libro.caratula = response.body().data.link;
                Call<Libro> callLibro = libroServicios.crear(libro);

                callLibro.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        Intent intent = new Intent(getApplicationContext(), ListaLibros.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });

    }

    private void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //porcesar la imagen al momento de tomarlas
        if (requestCode== REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle  extras = data.getExtras();
            Bitmap imageBitmap =(Bitmap) extras.get("data");
            //convertir de Bitmap a base64
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            imagBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.e("DATOS", "Titulo: "+ imagBase64);
            tvImagen.setImageBitmap(imageBitmap);
        }

    }
}