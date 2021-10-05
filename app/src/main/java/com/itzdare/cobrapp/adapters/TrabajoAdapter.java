package com.itzdare.cobrapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.itzdare.cobrapp.R;
import com.itzdare.cobrapp.actividades.VerTrabajoActivity;
import com.itzdare.cobrapp.pojos.Trabajo;

import java.util.List;

public class TrabajoAdapter extends RecyclerView.Adapter<TrabajoAdapter.viewHolderAdapter> {

    List<Trabajo> trabajoList;
    Context context;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences mPref;



    public TrabajoAdapter(List<Trabajo> trabajoList, Context context) {
        this.trabajoList = trabajoList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trabajo,parent,false);
        viewHolderAdapter holder = new viewHolderAdapter(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAdapter holder, int position) {
        Trabajo trabajo = trabajoList.get(position);

        holder.nombre.setText(trabajo.getTitulo());
        holder.descripcion.setText(trabajo.getDescripcion());
        holder.precio.setText("$ " + trabajo.getPrecio());

        //MOSTRAR TRABAJOS CREADOS POR EL USUARIO
        if(user.getUid().equals(trabajo.getId_user())){
            holder.cardView.setVisibility(View.VISIBLE);
        }else{
            holder.cardView.setVisibility(View.GONE);
        }

        //MOSTRAR TRABAJO SELECCIONADO
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), VerTrabajoActivity.class);
                i.putExtra("nombre", trabajo.getTitulo());
                i.putExtra("descripcion", trabajo.getDescripcion());
                i.putExtra("precio", trabajo.getPrecio());
                i.putExtra("hora", trabajo.getHora());
                i.putExtra("valorHora", trabajo.getValor_hora());
                i.putExtra("id", trabajo.getId_trabajo());

                v.getContext().startActivity(i);
            }
        });


        //COMPARTIR TRABAJO EN OTRA APP
        holder.compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom_s = TextUtils.htmlEncode(trabajo.getTitulo());
                String des_s = trabajo.getDescripcion();
                String pre_s = trabajo.getPrecio();
                String cvShare = "Nombre: "+nom_s + "\nDescripción: " + des_s + " " + " " + "\nPrecio: $"+pre_s ;

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, cvShare );
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                v.getContext().startActivity(shareIntent);;

            }
        });

    }//FIN DEL ON BIND VIEW HOLDER

    @Override
    public int getItemCount() {
        return trabajoList.size();
    }

    public class viewHolderAdapter extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio;
        CardView cardView;
        ImageButton compartir;
        public viewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tv_nombre_trabajo);
            descripcion = itemView.findViewById(R.id.tv_descripcion_trabajo);
            precio = itemView.findViewById(R.id.tv_precio_trabajo);
            cardView = itemView.findViewById(R.id.cv_trabajo);
            compartir = itemView.findViewById(R.id.btn_compartir);

        }
    }
}
