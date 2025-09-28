package com.example.tp03_android_cargar_listar_productos_salir.ui.listar;

import static com.example.tp03_android_cargar_listar_productos_salir.MainActivity.productos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

import java.util.ArrayList;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mLista;

    public LiveData<ArrayList<Producto>> getMLista(){
        if(mLista==null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void cargarLista(){
        mLista.setValue(productos);
    }
}
