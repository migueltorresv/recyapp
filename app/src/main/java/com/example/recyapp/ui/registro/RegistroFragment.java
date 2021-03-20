package com.example.recyapp.ui.registro;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyapp.R;

import org.json.JSONObject;

public class RegistroFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    Button btnRegistro;
    EditText etNombre, etUsuario, etContrasenia;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    private RegistroViewModel mViewModel;

    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registro_fragment, container, false);
        btnRegistro = v.findViewById(R.id.btnRegistro);
        etNombre = v.findViewById(R.id.etNombre);
        etUsuario = v.findViewById(R.id.etUsuario);
        etContrasenia = v.findViewById(R.id.etContrasenia);

        requestQueue = Volley.newRequestQueue(getContext());

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });

        return v;
    }

    private void cargarWebService() {
        String url = "http://192.168.1.3/recyapp/registroUsuario.php?nombres="+etNombre.getText().toString()+"&usuario="+
                etUsuario.getText().toString()+"&contrasenia="+etContrasenia.getText().toString();
        url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
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
        etContrasenia.setText("");
        etUsuario.setText("");
        etContrasenia.setText("");
    }
}