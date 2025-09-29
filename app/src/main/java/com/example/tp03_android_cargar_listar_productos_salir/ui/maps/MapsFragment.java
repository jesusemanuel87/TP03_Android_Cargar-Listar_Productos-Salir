package com.example.tp03_android_cargar_listar_productos_salir.ui.maps;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsFragment extends Fragment {

    private FragmentMapsBinding binding;
    private MapsViewModel mv;

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

        // Observer para mensajes de error
        mv.getMensajeError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                if (mensaje != null && !mensaje.isEmpty()) {
                    binding.tvMensajeError.setText(mensaje);
                    binding.tvMensajeError.setVisibility(View.VISIBLE);
                } else {
                    binding.tvMensajeError.setVisibility(View.GONE);
                }
            }
        });

        // Iniciar obtención de ubicación
        mv.obtenerUbicacion();
        
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
