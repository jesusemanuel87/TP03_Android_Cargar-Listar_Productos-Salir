package com.example.tp03_android_cargar_listar_productos_salir.ui.listar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;
import com.example.tp03_android_cargar_listar_productos_salir.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

public class ListarViewModel extends AndroidViewModel {

    private ProductoRepository repository;
    private MutableLiveData<ArrayList<Producto>> mProductos;
    private MutableLiveData<String> mensajeVacio;

    public ListarViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<ArrayList<Producto>> getProductos() {
        if (mProductos == null) {
            mProductos = new MutableLiveData<>();
            cargarProductos();
        }
        return mProductos;
    }

    public LiveData<String> getMensajeVacio() {
        return mensajeVacio;
    }

    public void cargarProductos() {
        repository = ProductoRepository.getInstance();

        List<Producto> listaProductos = repository.getAllProductos();
        
        if (listaProductos.isEmpty()) {
            mensajeVacio.setValue("No hay productos cargados");
            mProductos.setValue(null);
        } else {
            mensajeVacio.setValue(null);
            mProductos.setValue((ArrayList<Producto>) listaProductos);
        }
    }

    public void actualizarLista() {
        cargarProductos();
    }
}
