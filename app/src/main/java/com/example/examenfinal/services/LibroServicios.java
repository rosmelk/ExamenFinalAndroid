package com.example.examenfinal.services;
import com.example.examenfinal.entities.Libro;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface LibroServicios {


    @GET("Libro")
    Call<List<Libro>> getLibro();

    @GET("Libro /{id}")
    Call<Libro> getOne(@Path ("id") int id);

    //Guardar
    @POST("Libro")
    Call<Libro>  crear(@Body Libro libro);


    @PUT("Libro /{id}")
    Call<Libro> editrLibro(@Path ("id") int id, @Body Libro libro);




}
