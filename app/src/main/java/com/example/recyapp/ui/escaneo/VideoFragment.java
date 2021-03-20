package com.example.recyapp.ui.escaneo;

import androidx.fragment.app.FragmentResultListener;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyapp.R;
import com.example.recyapp.ui.entidades.Usuarios;

import org.json.JSONObject;

public class VideoFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    private VideoViewModel mViewModel;
    TextView txt_resultado;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.video_fragment, container, false);
        txt_resultado = vista.findViewById(R.id.txt_resultado);

        requestQueue = Volley.newRequestQueue(getContext());
        Usuarios usuarios = new Usuarios();
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String resultado = bundle.getString("clave");

                switch (resultado) {
                    case "botellaplastico":
                        cargarWebService(usuarios.getIdUsuario().toString(),"1");
                        txt_resultado.setText("Obtuviste 10 puntos");
                        break;
                    case "lata":
                        cargarWebService(usuarios.getIdUsuario().toString(),"2");
                        txt_resultado.setText("Obtuviste 15 puntos");
                        break;
                    case "papel":
                        cargarWebService(usuarios.getIdUsuario().toString(),"3");
                        txt_resultado.setText("Obtuviste 5 puntos");
                        break;
                    default:
                }
            }
        });
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se pudo registrar, "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("HORROR: ", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se registró correctamente", Toast.LENGTH_SHORT).show();
    }

    private void cargarWebService(String idUsuario, String idResiduo) {
        String url = "http://192.168.1.3/recyapp/registroPuntoObt.php?idUsuario="+idUsuario+"&Residuos_idResiduo="+idResiduo;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }
}