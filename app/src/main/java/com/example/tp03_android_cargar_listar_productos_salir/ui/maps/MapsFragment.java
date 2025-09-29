package com.example.tp03_android_cargar_listar_productos_salir.ui.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsFragment extends Fragment {

    private FragmentMapsBinding binding;
    private MapsViewModel mv;
    private static final int PERMISSION_REQUEST_CODE = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MapsViewModel.class);
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Observer para la ubicación
        mv.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                mv.cargarMapa(location);
            }
        });

        // Observer para el mapa
        mv.getMMapa().observe(getViewLifecycleOwner(), new Observer<MapsViewModel.MapaActual>() {
            @Override
            public void onChanged(MapsViewModel.MapaActual mapaActual) {
                SupportMapFragment mapFragment =
                        (SupportMapFragment) getChildFragmentManager().findFragmentById(binding.map.getId());
                if (mapFragment != null) {
                    mapFragment.getMapAsync(mapaActual);
                }
            }
        });

        // Observer para mensajes de error -> siempre mostrar si llega algo
        mv.getMensajeError().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvMensajeError.setText(mensaje);
            binding.tvMensajeError.setVisibility(View.VISIBLE);
        });

        // Observer para ocultar mensaje de error
        mv.getOcultarError().observe(getViewLifecycleOwner(), ocultar -> {
            binding.tvMensajeError.setVisibility(View.GONE);
        });

        // Observer para solicitud de permisos -> siempre ejecutar si llega
        mv.getSolicitarPermisos().observe(getViewLifecycleOwner(), solicitar -> {
            solicitarPermisos();
        });

        // Observer para mensajes Toast -> siempre mostrar si llega algo
        mv.getMensajeToast().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();
        });

        // Iniciar verificación de permisos
        mv.verificarPermisos();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mv.onPermisosResultado(requestCode, grantResults);
    }

    private void solicitarPermisos() {
        // Solo solicitar permisos, la lógica está en el ViewModel
        requestPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, PERMISSION_REQUEST_CODE);
    }
}
