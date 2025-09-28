package com.example.tp03_android_cargar_listar_productos_salir.ui.cargar;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;

import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;
import com.example.tp03_android_cargar_listar_productos_salir.repository.ProductoRepository;

public class CargarViewModel extends AndroidViewModel {

    private ProductoRepository repository;
    private MutableLiveData<String> mensajeError;
    private MutableLiveData<String> mensajeExito;
    private MutableLiveData<Boolean> limpiarFormulario;

    public CargarViewModel(Application application) {
        super(application);
        repository = ProductoRepository.getInstance();
    }

    public LiveData<String> getMensajeError() {
        if (mensajeError == null) {
            mensajeError = new MutableLiveData<>();
        }
        return mensajeError;
    }

    public LiveData<String> getMensajeExito() {
        if (mensajeExito == null) {
            mensajeExito = new MutableLiveData<>();
        }
        return mensajeExito;
    }

    public LiveData<Boolean> getLimpiarFormulario() {
        if (limpiarFormulario == null) {
            limpiarFormulario = new MutableLiveData<>();
        }
        return limpiarFormulario;
    }

    public void cargarProducto(String codigo, String descripcion, String precioStr) {
        if (codigo == null || codigo.trim().isEmpty()) {
            mensajeError.setValue("El código no puede estar vacío");
            return;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            mensajeError.setValue("La descripción no puede estar vacía");
            return;
        }
        if (precioStr == null || precioStr.trim().isEmpty()) {
            mensajeError.setValue("El precio no puede estar vacío");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr.trim());
            if (precio <= 0) {
                mensajeError.setValue("El precio debe ser mayor a 0");
                return;
            }
        } catch (NumberFormatException e) {
            mensajeError.setValue("El precio debe ser un número válido");
            return;
        }

        if (repository.existeCodigo(codigo.trim())) {
            mensajeError.setValue("Ya existe un producto con el código: " + codigo.trim());
            return;
        }

        Producto producto = new Producto(codigo.trim(), descripcion.trim(), precio);
        boolean agregado = repository.addProducto(producto);

        if (agregado) {
            mensajeExito.setValue("Producto agregado exitosamente");
            limpiarFormulario.setValue(true);
        } else {
            mensajeError.setValue("Error al agregar el producto");
        }
    }
}

