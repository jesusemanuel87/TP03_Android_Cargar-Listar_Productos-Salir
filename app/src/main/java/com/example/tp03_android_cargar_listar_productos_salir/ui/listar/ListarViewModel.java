package com.example.tp03_android_cargar_listar_productos_salir.ui.listar;

import static com.example.tp03_android_cargar_listar_productos_salir.MainActivity.productos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mLista;

    public LiveData<ArrayList<Producto>> getMLista(){
        if(mLista==null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void cargarLista(){
        // Crear una copia de la lista para no modificar la original
        ArrayList<Producto> productosOrdenados = new ArrayList<>(productos);
        
        // Ordenar alfabéticamente por descripción
        Collections.sort(productosOrdenados, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion());
            }
        });
        
        mLista.setValue(productosOrdenados);
    }
}
