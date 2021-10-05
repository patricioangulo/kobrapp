package com.itzdare.cobrapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itzdare.cobrapp.R;
import com.itzdare.cobrapp.actividades.ResultadoActivity;
import com.itzdare.cobrapp.actividades.ValidarActivity;
import com.itzdare.cobrapp.actividades.ValorHoraActivity;

public class CalculadoraFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_calculadora, container, false);

        EditText horas, sueldo, materiales;
        ValidarActivity objValidar;


        horas = view.findViewById(R.id.et_horas);
        sueldo = view.findViewById(R.id.et_sueldo);
        materiales = view.findViewById(R.id.et_materiales);
        objValidar = new ValidarActivity();

        view.findViewById(R.id.btn_calcular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objValidar.Vacio(horas) | objValidar.Vacio(sueldo) | objValidar.Vacio(materiales)) {

                } else {
                    int resultado = Integer.parseInt(horas.getText().toString()) * Integer.parseInt(sueldo.getText().toString()) + Integer.parseInt(materiales.getText().toString());

                    Intent i = new Intent(v.getContext(), ResultadoActivity.class);

                    i.putExtra("horas", horas.getText().toString());
                    i.putExtra("sueldo", "$ " + sueldo.getText().toString());
                    i.putExtra("materiales", "$ " + materiales.getText().toString());
                    i.putExtra("resultado", "$ " + Integer.toString(resultado));


                    v.getContext().startActivity(i);
                }
            }
        });

        view.findViewById(R.id.btn_calcular_hora).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ValorHoraActivity.class);
                v.getContext().startActivity(i);
            }
        });


        return view;

    }//FIN DEL ON CREATE VIEW
}