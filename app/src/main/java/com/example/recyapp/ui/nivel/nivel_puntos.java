package com.example.recyapp.ui.nivel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyapp.R;
import com.example.recyapp.ui.adapter.PuntosObtenidosAdapter;
import com.example.recyapp.ui.entidades.PuntosObtenidos;
import com.example.recyapp.ui.entidades.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class nivel_puntos extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    private NivelPuntosViewModel mViewModel;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    RecyclerView recyclerViewPuntosObt;
    ArrayList<PuntosObtenidos> puntosObtenidosList;
    TextView tvTotalPuntos;
    public static nivel_puntos newInstance() {
        return new nivel_puntos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nivel_puntos_fragment, container, false);
        tvTotalPuntos = v.findViewById(R.id.tvTotalPuntos);
        puntosObtenidosList = new ArrayList<>();
        recyclerViewPuntosObt = (RecyclerView) v.findViewById(R.id.rvListaPuntosObt);
        recyclerViewPuntosObt.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewPuntosObt.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());
        Usuarios usuarios = new Usuarios();
        cargarWebService(usuarios.getIdUsuario().toString());
        return v;
    }

    private void cargarWebService(String idUsuario) {
        String url = "http://192.168.1.3/recyapp/listarPuntosObt.php?idUsuario="+idUsuario;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NivelPuntosViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Aun no cuenta con puntos, "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("HORROR: ", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        PuntosObtenidos puntosObtenidos=null;
        JSONArray json = response.optJSONArray("listPuntosObt");
        try {
            Integer total = 0;
            for (int i=0;i<json.length();i++) {
                puntosObtenidos = new PuntosObtenidos();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                puntosObtenidos.setPuntos(jsonObject.optInt("puntos"));
                puntosObtenidos.setResiduo(jsonObject.optString("residuo"));
                puntosObtenidos.setFecha(jsonObject.optString("fecha"));
                puntosObtenidosList.add(puntosObtenidos);
                total += jsonObject.optInt("puntos");
            }
            tvTotalPuntos.setText(total.toString()+" pts");
            PuntosObtenidosAdapter adapter = new PuntosObtenidosAdapter(puntosObtenidosList);
            recyclerViewPuntosObt.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Sin conexión con el server"+response, Toast.LENGTH_SHORT).show();
        }
    }
}