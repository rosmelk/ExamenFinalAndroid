package com.example.examenfinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.R;
import com.example.examenfinal.entities.Libro;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritoAdapter extends RecyclerView.Adapter <FavoritoAdapter.ViewHolderFavorito>{

    List<Libro> libroo;
    public FavoritoAdapter(List<Libro> libro) {
        this.libroo = libro;
    }

    @NonNull
    @Override
    public FavoritoAdapter.ViewHolderFavorito onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_favorito, parent, false);
        return new ViewHolderFavorito (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavorito vh, int position) {
        View item =vh.itemView;
        Libro libr = libroo.get(position);
        TextView fTitulo = item.findViewById(R.id.fTitulo);
        TextView fResumen = item.findViewById(R.id.fResumen);
        ImageView fImagen = item.findViewById(R.id.fImagen);
        Picasso.get().load(libr.caratula).into(fImagen);
        fTitulo.setText(libr.titulo);
        fResumen.setText(libr.resumen);

    }

    @Override
    public int getItemCount() {
        return libroo.size();
    }

    public class ViewHolderFavorito extends RecyclerView.ViewHolder {
        public ViewHolderFavorito(@NonNull View itemView) {
            super(itemView);
        }
    }
}
