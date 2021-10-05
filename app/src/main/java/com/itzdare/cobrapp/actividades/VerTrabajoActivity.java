package com.itzdare.cobrapp.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itzdare.cobrapp.R;

public class VerTrabajoActivity extends AppCompatActivity {

    TextView nombreTrabajo, desTrabajo, precioTrabajo, horaTrabajo, valorHoraTrabajo;
    ImageButton borrarTrabajo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_trabajo);

        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString("nombre");
        String descripcion = parametros.getString("descripcion");
        String precio = parametros.getString("precio");
        String hora = parametros.getString("hora");
        String valorHora = parametros.getString("valorHora");
        String id = parametros.getString("id");

        nombreTrabajo = findViewById(R.id.tv_nombre_trabajo);
        desTrabajo = findViewById(R.id.tv_descripcion_trabajo);
        precioTrabajo = findViewById(R.id.tv_precio_trabajo);
        horaTrabajo = findViewById(R.id.tv_horas_trabajadas);
        valorHoraTrabajo = findViewById(R.id.tv_valor_hora);

        nombreTrabajo.setText(nombre);
        desTrabajo.setText(descripcion);
        precioTrabajo.setText(precio);
        horaTrabajo.setText(hora);
        valorHoraTrabajo.setText(valorHora);

        //BOTÓN BORRAR TRABAJO
        borrarTrabajo = findViewById(R.id.btn_borrar);
        borrarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database= FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();
                Query query = ref.child("Trabajo").orderByChild("id_trabajo").equalTo(id);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot data: snapshot.getChildren()){
                                String key = data.getKey();
                                FirebaseDatabase database= FirebaseDatabase.getInstance();
                                DatabaseReference ref = database.getReference("Trabajo").child(key);
                                ref.removeValue();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                finish();
                Toast.makeText(VerTrabajoActivity.this, "Trabajo Borrado", Toast.LENGTH_SHORT).show();

            }
        });//FIN BOTÓN BORRAR TRABAJO




    }//FIN DEL ON
    // CREATE
}