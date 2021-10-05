package com.itzdare.cobrapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.itzdare.cobrapp.R;

public class ResultadoActivity extends AppCompatActivity {

    TextView horaTrabajo, sueldoTrabajo, materialTrabajo, resultadoTrabajo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Bundle parametros = getIntent().getExtras();

        String horas = parametros.getString("horas") + " Hora(s)";
        String sueldo = parametros.getString("sueldo");
        String materiales = parametros.getString("materiales");
        String resultado = parametros.getString("resultado");

        horaTrabajo = findViewById(R.id.tv_horas_trabajadas);
        sueldoTrabajo = findViewById(R.id.tv_sueldo);
        materialTrabajo = findViewById(R.id.tv_materiales);
        resultadoTrabajo = findViewById(R.id.tv_resultado);

        horaTrabajo.setText(horas);
        sueldoTrabajo.setText(sueldo);
        materialTrabajo.setText(materiales);
        resultadoTrabajo.setText(resultado);




    }//FIN DEL ON CREATE
}