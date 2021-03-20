package com.example.recyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.recyapp.ui.escaneo.VideoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInMainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    Button btn_ingresar, btn_registrar;
    String usuario, pass;
    EditText edt_user, edt_pass;
    Boolean iniciar=false;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public SignInMainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main);
        request = Volley.newRequestQueue(this);
        edt_user = (EditText) findViewById(R.id.edt_user);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        btn_registrar = findViewById(R.id.btn_registrar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = edt_user.getText().toString();
                pass = edt_pass.getText().toString();
                if (usuario.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(SignInMainActivity.this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    CargarWebService(usuario,pass);
                    Usuarios usuarios = new Usuarios();
                    Intent intent = new Intent( SignInMainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SignInMainActivity.this, RegistroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void CargarWebService(String usuario, String contrasenia) {
        try {
            String url = "http://192.168.1.3/recyapp/consultarUsuario.php?usuario="+usuario+"&contrasenia="+contrasenia;
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            request.add(jsonObjectRequest);
        } catch (Exception e) {
            Toast.makeText(this, "no se pudo consultar"+e.toString(), Toast.LENGTH_SHORT).show();
            Log.i("Error", e.toString());
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "no se pudo consultar"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuarios usuarios = new Usuarios();
        JSONArray json = response.optJSONArray("usuario");
        JSONObject jsonObject = null;

        try {
            jsonObject = json.getJSONObject(0);
            usuarios.setIdUsuario(jsonObject.optInt("idUsuario"));
            usuarios.setNombres(jsonObject.optString("nombres"));
            usuarios.setUsuario(jsonObject.optString("usuario"));
            usuarios.setContrasenia(jsonObject.optString("contrasenia"));
            usuarios.setFechaRegistro(jsonObject.optString("fechaRegistro"));

        } catch (JSONException e) {
            Toast.makeText(this, "OnResponse"+e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}