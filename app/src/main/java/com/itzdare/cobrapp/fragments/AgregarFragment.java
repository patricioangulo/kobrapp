package com.itzdare.cobrapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itzdare.cobrapp.R;
import com.itzdare.cobrapp.actividades.SubirInfoActivity;
import com.itzdare.cobrapp.adapters.TrabajoAdapter;
import com.itzdare.cobrapp.pojos.Trabajo;

import java.util.ArrayList;


public class AgregarFragment extends Fragment {


    //INICIO DEL CÓDIGO
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        View view = inflater.inflate(R.layout.fragment_agregar, container, false);

        //MUESTRA CARDVIEW DE LOS TRABAJOS CREADOS
        RecyclerView recyclerView;
        ArrayList<Trabajo> trabajoArrayList;
        TrabajoAdapter trabajoAdapter;
        LinearLayoutManager layoutManager;

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView = view.findViewById(R.id.rv_trabajo);
        recyclerView.setLayoutManager(layoutManager);

        trabajoArrayList = new ArrayList<>();
        trabajoAdapter = new TrabajoAdapter(trabajoArrayList, getContext());
        recyclerView.setAdapter(trabajoAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Trabajo");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    recyclerView.setVisibility(View.VISIBLE);
                    trabajoArrayList.removeAll(trabajoArrayList);
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        Trabajo trabajo = snapshot1.getValue(Trabajo.class);
                        trabajoArrayList.add(trabajo);
                    }
                    trabajoAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "No se han agregado Trabajos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        view.findViewById(R.id.btn_agregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SubirInfoActivity.class);
                startActivity(i);
            }
        });


        return view;
    }//FIN DEL ON CREATE
}