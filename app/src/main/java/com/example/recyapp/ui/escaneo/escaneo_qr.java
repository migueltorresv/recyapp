package com.example.recyapp.ui.escaneo;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyapp.CaptureActivityPortrait;
import com.example.recyapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class escaneo_qr extends Fragment {

    private EscaneoQrViewModel mViewModel;

    public static escaneo_qr newInstance() {
        return new escaneo_qr();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.escaneo_qr_fragment, container, false);

        IntentIntegrator scanqr = IntentIntegrator.forSupportFragment(escaneo_qr.this);
        scanqr.setPrompt("Escanear QR");
        scanqr.setCameraId(0);
        scanqr.setOrientationLocked(true);
        scanqr.setCaptureActivity(CaptureActivityPortrait.class);
        scanqr.initiateScan();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EscaneoQrViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String resultado_qr = result.getContents().toString();
        String puntaje_obtenido = "";

        if (result != null) {

            switch (resultado_qr) {
                case "botellaplastico":
                    puntaje_obtenido = "botellaplastico";
                    break;
                case "lata":
                    puntaje_obtenido = "lata";
                    break;
                case "papel":
                    puntaje_obtenido = "papel";
                    break;
                default:
            }

            Bundle bundle = new Bundle();
            bundle.putString("clave",puntaje_obtenido);
            getParentFragmentManager().setFragmentResult("key",bundle);

            VideoFragment videoFragment = new VideoFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.escaneo_qr_main,videoFragment);
            transaction.commit();

        }

    }
}