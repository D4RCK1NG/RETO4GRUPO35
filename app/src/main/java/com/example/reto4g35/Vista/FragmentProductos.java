package com.example.reto4g35.Vista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.reto4g35.Modelo.Adaptador;
import com.example.reto4g35.Modelo.BaseDatos.MotorBaseDatosSQLite;
import com.example.reto4g35.Modelo.Entidad;
import com.example.reto4g35.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentProductos extends Fragment {

    View v;
    Cursor cursor;
    ListView listaProductos;
    Adaptador adaptador;
    int [] imagen = {R.drawable.chaquetamujer, R.drawable.chaquetas, R.drawable.nino };
    String TAG = "FragmentProductos";

    // CONEXION A LA BASE DE DATOS: SQLite
    MotorBaseDatosSQLite conectar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_productos, container, false);
        listaProductos = (ListView) v.findViewById(R.id.lista_productos);
        adaptador = new Adaptador(getTablaProductos(), getActivity());

        listaProductos.setAdapter(adaptador);

        //-----------------------------------------------------------------------------
        return v;
    }
    private ArrayList<Entidad> getTablaProductos(){
        ArrayList<Entidad> listaProductos = new ArrayList<>();
        conectar = new MotorBaseDatosSQLite(this.getActivity(),"QuieroMiChaqueta", null, 1);
        SQLiteDatabase db_leer = conectar.getReadableDatabase();

        Cursor cursor = db_leer.rawQuery("SELECT * FROM productos", null);


        while(cursor.moveToNext()){

            listaProductos.add(new Entidad(imagen[cursor.getInt(0)], cursor.getString(1), cursor.getString(2)));

        }

        return listaProductos;
    }

    private ArrayList<Entidad> GetListItems(){
        ArrayList<Entidad> listaItems = new ArrayList<>();
        listaItems.add(new Entidad(R.drawable.chaquetamujer, "Dama", "encuentra todo lo relacionado con chaquetas para dama"));
        listaItems.add(new Entidad(R.drawable.chaquetas, "Caballero", "encuentra todo lo relaconado co chaquetas para caballero"));
        listaItems.add(new Entidad(R.drawable.nino, "Niño", "encuentra toda clase de cahquetas para niño"));

        return listaItems;
    }
}