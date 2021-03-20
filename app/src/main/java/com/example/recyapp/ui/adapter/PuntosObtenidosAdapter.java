package com.example.recyapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyapp.R;
import com.example.recyapp.ui.entidades.PuntosObtenidos;

import java.util.List;

public class PuntosObtenidosAdapter extends RecyclerView.Adapter<PuntosObtenidosAdapter.PuntosObtenidosHolder> {

    List<PuntosObtenidos> puntosObtenidosList;

    public PuntosObtenidosAdapter(List<PuntosObtenidos> puntosObtenidosList) {
        this.puntosObtenidosList = puntosObtenidosList;
    }

    @NonNull
    @Override
    public PuntosObtenidosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pts_obt,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new PuntosObtenidosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PuntosObtenidosHolder holder, int position) {
        holder.tvPuntosObt.setText(String.valueOf(puntosObtenidosList.get(position).getPuntos().toString()));
        holder.tvResiduo.setText(String.valueOf(puntosObtenidosList.get(position).getResiduo().toString()));
        holder.tvFechaObt.setText(String.valueOf(puntosObtenidosList.get(position).getFecha().toString()));
    }

    @Override
    public int getItemCount() {
        return puntosObtenidosList.size();
    }

    public class PuntosObtenidosHolder extends RecyclerView.ViewHolder{
        TextView tvPuntosObt, tvResiduo, tvFechaObt;
        public PuntosObtenidosHolder(@NonNull View itemView) {
            super(itemView);
            tvPuntosObt = itemView.findViewById(R.id.tvPuntosObt);
            tvResiduo = itemView.findViewById(R.id.tvResiduo);
            tvFechaObt = itemView.findViewById(R.id.tvFechaObt);
        }
    }
}
