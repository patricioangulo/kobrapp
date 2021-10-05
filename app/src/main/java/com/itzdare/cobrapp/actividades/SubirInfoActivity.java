package com.itzdare.cobrapp.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.itzdare.cobrapp.MainActivity;
import com.itzdare.cobrapp.R;
import com.itzdare.cobrapp.fragments.AgregarFragment;
import com.itzdare.cobrapp.pojos.Trabajo;

public class SubirInfoActivity extends AppCompatActivity {
    EditText titulo, descripcion, precioHora, tiempo, materiales, precio;
    Button subirInfo, precioManual;
    ProgressDialog cargando;
    ValidarActivity objValidar;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref_info = FirebaseDatabase.getInstance().getReference("Trabajo");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String idpush = ref_info.push().getKey();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_info);

        titulo = findViewById(R.id.et_titulo);
        descripcion = findViewById(R.id.et_descripcion);
        precioHora = findViewById(R.id.et_valor_hora);
        tiempo = findViewById(R.id.et_horas);
        materiales = findViewById(R.id.et_valor_materiales);
        subirInfo = findViewById(R.id.btn_subir_info);
        objValidar = new ValidarActivity();


        subirInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(objValidar.Vacio(titulo) || objValidar.Vacio(descripcion) || objValidar.Vacio(precioHora) || objValidar.Vacio(tiempo) || objValidar.Vacio(materiales)){
                    Toast.makeText(getApplicationContext(), "Faltan Campos", Toast.LENGTH_SHORT).show();
                }else {


                    int ecuacion = Integer.parseInt(precioHora.getText().toString()) * Integer.parseInt(tiempo.getText().toString()) + Integer.parseInt(materiales.getText().toString());

                    String titulo_s = titulo.getText().toString();
                    String descripcion_s = descripcion.getText().toString();
                    String precioHora_s = precioHora.getText().toString();
                    String tiempo_s = tiempo.getText().toString();
                    String materiales_s = materiales.getText().toString();
                    String precio_s = ecuacion+"";



                    Trabajo trabajo = new Trabajo(user.getUid(), idpush, titulo_s, descripcion_s, precio_s, precioHora_s, materiales_s, tiempo_s);
                    ref_info.push().setValue(trabajo);

                    finish();
                    Toast.makeText(SubirInfoActivity.this, "Información Guardada con Éxito", Toast.LENGTH_LONG).show();

                }
                

            }
        });//FIN DEL BOTÓN SUBIR INFO



    }//FIN DEL ON CREATE
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_cerrar:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                                Toast.makeText(SubirInfoActivity.this, "Cerrando Sesión", Toast.LENGTH_SHORT).show();
                                vamosalogin();
                            }
                        });

        }
        return super.onOptionsItemSelected(item);
    }

    private void vamosalogin() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}