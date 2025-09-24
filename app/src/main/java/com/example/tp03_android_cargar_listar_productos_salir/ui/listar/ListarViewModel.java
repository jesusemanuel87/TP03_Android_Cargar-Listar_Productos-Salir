package com.example.tp03_android_cargar_listar_productos_salir.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;
import com.example.tp03_android_cargar_listar_productos_salir.repository.ProductoRepository;

import java.util.List;

public class ListarViewModel extends ViewModel {

    private ProductoRepository repository;
    private MutableLiveData<List<Producto>> productos = new MutableLiveData<>();
    private MutableLiveData<String> mensajeVacio = new MutableLiveData<>();

    public ListarViewModel() {
        repository = ProductoRepository.getInstance();
        cargarProductos();
    }

    public LiveData<List<Producto>> getProductos() {
        return productos;
    }

    public LiveData<String> getMensajeVacio() {
        return mensajeVacio;
    }

    public void cargarProductos() {
        List<Producto> listaProductos = repository.getAllProductos();
        
        if (listaProductos.isEmpty()) {
            mensajeVacio.setValue("No hay productos cargados");
            productos.setValue(null);
        } else {
            mensajeVacio.setValue(null);
            productos.setValue(listaProductos);
        }
    }

    public void actualizarLista() {
        cargarProductos();
    }
}
