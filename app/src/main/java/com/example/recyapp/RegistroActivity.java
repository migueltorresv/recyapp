package com.example.recyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyapp.ui.entidades.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    Button btnRegistro;
    EditText etNombre, etUsuario, etContrasenia;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnRegistro = findViewById(R.id.btnRegistro);
        etNombre = findViewById(R.id.etNombre);
        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);

        requestQueue = Volley.newRequestQueue(this);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombres = etNombre.getText().toString();
                String usuario = etUsuario.getText().toString();
                String contrasenia = etContrasenia.getText().toString();
                if (nombres.isEmpty() || usuario.isEmpty() || contrasenia.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Rellene los campos", Toast.LENGTH_SHORT).show();
                } else {
                    CargarWebService(nombres,usuario,contrasenia);
                    Intent intent = new Intent( RegistroActivity.this, SignInMainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void CargarWebService(String nombres, String usuario, String pass) {
        String url = "http://192.168.1.3/recyapp/registroUsuario.php?nombres="+nombres+"&usuario="+
                usuario+"&contrasenia="+pass;
        url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se pudo registrar, "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("HORROR: ", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Se registró correctamente", Toast.LENGTH_SHORT).show();
        etContrasenia.setText("");
        etUsuario.setText("");
        etContrasenia.setText("");
    }
}