package com.example.reto4g35.Vista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.reto4g35.Modelo.Adaptador;
import com.example.reto4g35.Modelo.BaseDatos.MotorBaseDatosSQLite;
import com.example.reto4g35.Modelo.Entidad;
import com.example.reto4g35.R;

import java.util.ArrayList;


public class FragmentServicios extends Fragment {


    View v;
    String TAG = "FragmentServicios";
    int [] imagen = {R.drawable.domicilio, R.drawable.arreglos, R.drawable.estampado };
    ListView listaServicios;
    Adaptador adaptador;

    // CONEXION A LA BASE DE DATOS: SQLite
    MotorBaseDatosSQLite conectar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_servicios, container, false);

        listaServicios = (ListView) v.findViewById(R.id.lista_servicios);
        adaptador = new Adaptador(GetArrayItems(), getContext());

        listaServicios.setAdapter(adaptador);
        return v;
    }
    private ArrayList<Entidad> getTablaServicios(){
        ArrayList<Entidad> listaServicios = new ArrayList<>();
        conectar = new MotorBaseDatosSQLite(getContext(),"QuieroMiChaqueta", null, 1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();
        Cursor cursor = db_leer.rawQuery("SELECT * FROM servicios", null);
        Log.v(TAG, "leyendo bas de datos");

        while(cursor.moveToNext()){
            Log.v(TAG, "dentro de while");
            listaServicios.add(new Entidad(imagen[cursor.getInt(0)], cursor.getString(1), cursor.getString(2)));
            Log.v(TAG, "despues del while");
        }


        return listaServicios;
    }
    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listaItems = new ArrayList<Entidad>();
        listaItems.add(new Entidad(R.drawable.domicilio, "Domicilios", "hacemos entrega de todos tus pedidos a cualquier pate del pais"));
        listaItems.add(new Entidad(R.drawable.estampado, "Estampados", " personaliza tu chaqueta como mas te guste."));
        listaItems.add(new Entidad(R.drawable.arreglos, "Reparaciones", "tu cahqueta favorita esta extropeadad, nosotros te ayudamos y la dejamos como nueva"));

        return listaItems;
    }
}