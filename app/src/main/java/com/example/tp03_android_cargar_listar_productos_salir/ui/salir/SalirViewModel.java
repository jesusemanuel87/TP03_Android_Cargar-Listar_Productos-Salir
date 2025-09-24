package com.example.tp03_android_cargar_listar_productos_salir.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {

    private MutableLiveData<Boolean> mostrarDialogoSalir = new MutableLiveData<>();
    private MutableLiveData<Boolean> cerrarAplicacion = new MutableLiveData<>();

    public LiveData<Boolean> getMostrarDialogoSalir() {
        return mostrarDialogoSalir;
    }

    public LiveData<Boolean> getCerrarAplicacion() {
        return cerrarAplicacion;
    }

    public void solicitarSalir() {
        mostrarDialogoSalir.setValue(true);
    }

    public void confirmarSalir() {
        cerrarAplicacion.setValue(true);
    }

    public void cancelarSalir() {
        // No hacer nada, simplemente no cerrar la aplicación
    }
}
