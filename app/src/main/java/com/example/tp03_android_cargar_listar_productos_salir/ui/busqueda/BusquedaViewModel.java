package com.example.tp03_android_cargar_listar_productos_salir.ui.busqueda;

import static com.example.tp03_android_cargar_listar_productos_salir.MainActivity.productos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

public class BusquedaViewModel extends AndroidViewModel {

    private MutableLiveData<Producto> mProducto;
    private MutableLiveData<String> mMensaje;

    public BusquedaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getmProducto() {
        if (mProducto == null) {
            mProducto = new MutableLiveData<>();
        }
        return mProducto;
    }

    public LiveData<String> getmMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void buscarProducto(String codigoBuscado) {
        if (codigoBuscado == null || codigoBuscado.trim().isEmpty()) {
            mMensaje.setValue("Debe ingresar un código para buscar");
            return;
        }

        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigoBuscado.trim())) {
                mProducto.setValue(producto);
                return;
            }
        }

        mMensaje.setValue("No se encontró un producto con el código: " + codigoBuscado);
    }
}
