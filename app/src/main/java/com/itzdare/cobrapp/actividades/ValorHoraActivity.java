package com.itzdare.cobrapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itzdare.cobrapp.R;

public class ValorHoraActivity extends AppCompatActivity {

    EditText sueldoTrabajo;
    TextView valorHora;
    Button calcular;
    ValidarActivity objValidar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_hora);

        sueldoTrabajo = findViewById(R.id.et_sueldo);
        valorHora = findViewById(R.id.tv_valor_hora);
        calcular = findViewById(R.id.btn_calcular);
        objValidar = new ValidarActivity();


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (objValidar.Vacio(sueldoTrabajo)) {
                    Toast.makeText(getApplicationContext(), "Faltan Campos", Toast.LENGTH_SHORT).show();
                } else {
                    int ecuacion = Integer.parseInt(sueldoTrabajo.getText().toString()) / 180;
                    valorHora.setText("$" + " " + ecuacion + "");
                }

            }
        });
    }


}









