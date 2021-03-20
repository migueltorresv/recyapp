package com.example.recyapp.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyapp.R;
import com.example.recyapp.ui.entidades.Usuarios;

public class perfil extends Fragment {

    TextView tvNombre, tvUsuario, tvFechaReg;

    private PerfilViewModel mViewModel;

    public static perfil newInstance() {
        return new perfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.perfil_fragment, container, false);

        tvNombre = v.findViewById(R.id.tvNombre);
        tvUsuario = v.findViewById(R.id.tvUsuario);
        tvFechaReg = v.findViewById(R.id.tvFechaReg);
        try {
            Usuarios usuarios = new Usuarios();

            tvNombre.setText(usuarios.getNombres().toString());
            tvUsuario.setText(usuarios.getUsuario().toString());
            tvFechaReg.setText(usuarios.getFechaRegistro().toString());
        } catch (Exception e) {
            Log.i("Horror",e.toString());
        }

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}