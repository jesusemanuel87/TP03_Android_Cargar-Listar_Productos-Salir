package com.example.tp03_android_cargar_listar_productos_salir.ui.detalle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

public class DetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Producto> mProducto;

    public DetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getmProducto() {
        if (mProducto == null) {
            mProducto = new MutableLiveData<>();
        }
        return mProducto;
    }

    public void cargarProducto(Producto producto) {
        if (producto != null) {
            mProducto.setValue(producto);
        }
    }
}
