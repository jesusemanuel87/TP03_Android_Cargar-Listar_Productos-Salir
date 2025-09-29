package com.example.tp03_android_cargar_listar_productos_salir.ui.maps;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsViewModel extends AndroidViewModel {

    private FusedLocationProviderClient fused;
    private Context context;
    private MutableLiveData<Location> mLocation;
    private MutableLiveData<MapaActual> mMapa;
    private MutableLiveData<String> mensajeError;
    private MutableLiveData<Boolean> ocultarError;
    private MutableLiveData<Boolean> solicitarPermisos;
    private MutableLiveData<String> mensajeToast;
    private static final int PERMISSION_REQUEST_CODE = 1000;

    public MapsViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
        fused = LocationServices.getFusedLocationProviderClient(context);
    }

    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public LiveData<MapaActual> getMMapa() {
        if (mMapa == null) {
            mMapa = new MutableLiveData<>();
        }
        return mMapa;
    }

    public LiveData<String> getMensajeError() {
        if (mensajeError == null) {
            mensajeError = new MutableLiveData<>();
        }
        return mensajeError;
    }

    public LiveData<Boolean> getOcultarError() {
        if (ocultarError == null) {
            ocultarError = new MutableLiveData<>();
        }
        return ocultarError;
    }

    public LiveData<Boolean> getSolicitarPermisos() {
        if (solicitarPermisos == null) {
            solicitarPermisos = new MutableLiveData<>();
        }
        return solicitarPermisos;
    }

    public LiveData<String> getMensajeToast() {
        if (mensajeToast == null) {
            mensajeToast = new MutableLiveData<>();
        }
        return mensajeToast;
    }

    public void cargarMapa(Location ubicacion) {
        MapaActual mapaActual = new MapaActual(ubicacion);
        mMapa.setValue(mapaActual);
        // Ocultar mensaje de error si existe
        ocultarError.setValue(true);
    }

    public void verificarPermisos() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Emitir evento para solicitar permisos
            solicitarPermisos.setValue(true);
        } else {
            // Permisos disponibles, obtener ubicación
            obtenerUbicacion();
        }
    }

    public void onPermisosResultado(int requestCode, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion();
            } else {
                mensajeError.setValue("Se requieren permisos de ubicación para mostrar el mapa");
                mensajeToast.setValue("Permiso de ubicación denegado. No se puede mostrar el mapa.");
            }
        }
    }

    public void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            mensajeError.setValue("Se requieren permisos de ubicación para mostrar el mapa");
            return;
        }

        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLocation.postValue(location);
                } else {
                    mensajeError.setValue("No se pudo obtener la ubicación actual");
                }
            }
        });

        tarea.addOnFailureListener(context.getMainExecutor(), e -> {
            mensajeError.setValue("Error al obtener ubicación: " + e.getMessage());
        });
    }

    public class MapaActual implements OnMapReadyCallback {

        LatLng ubi;

        public MapaActual(Location ubicacion) {
            this.ubi = new LatLng(ubicacion.getLatitude(), ubicacion.getLongitude());
        }

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            // Configurar marcador
            MarkerOptions marcador = new MarkerOptions();
            marcador.position(ubi);
            marcador.title("Mi Ubicación");

            googleMap.addMarker(marcador);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            // Configurar cámara
            CameraPosition cam = new CameraPosition.Builder()
                    .target(ubi)
                    .zoom(18)
                    .bearing(45)
                    .tilt(15)
                    .build();

            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cam);
            googleMap.animateCamera(cameraUpdate);

            // Habilitar controles de zoom
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
        }
    }
}
