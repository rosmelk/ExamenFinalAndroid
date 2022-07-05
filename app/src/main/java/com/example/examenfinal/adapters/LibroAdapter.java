package com.example.examenfinal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.R;
import com.example.examenfinal.VerDetalle;
import com.example.examenfinal.entities.Libro;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolderLibro> {

    List<Libro> libro;
    public LibroAdapter(List<Libro> libro) {
        this.libro = libro;
    }

    @NonNull
    @Override
    public LibroAdapter.ViewHolderLibro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_layout, parent, false);
        return new ViewHolderLibro(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLibro vh, int position) {
        View itemView =vh.itemView;
        Libro libr = libro.get(position);
        TextView tTitulo = itemView.findViewById(R.id.tvTitulo);
        ImageView tvImagen = itemView.findViewById(R.id.tvImagen);
        Picasso.get().load(libr.caratula).into(tvImagen);
        tTitulo.setText(libr.titulo);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), VerDetalle.class);
                String libroJSON = new Gson().toJson(libr);
                intent.putExtra("LIBRO", libroJSON);
                itemView.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return  libro.size();
    }

    public class ViewHolderLibro extends RecyclerView.ViewHolder {
        public ViewHolderLibro(@NonNull View itemView) {
            super(itemView);
        }
    }
}
