package com.example.tp03_android_cargar_listar_productos_salir.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp03_android_cargar_listar_productos_salir.R;
import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;
    private SalirViewModel salirViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSalirBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar ViewModel
        salirViewModel = new ViewModelProvider(this).get(SalirViewModel.class);

        // Configurar listeners
        setupListeners();

        // Observar LiveData
        observeViewModel();

        // Mostrar diálogo automáticamente al entrar al fragment
        salirViewModel.solicitarSalir();
    }

    private void setupListeners() {
        binding.btnSalir.setOnClickListener(v -> salirViewModel.confirmarSalir());
        
        binding.btnCancelar.setOnClickListener(v -> {
            salirViewModel.cancelarSalir();
            // Navegar de vuelta al fragment de cargar
            Navigation.findNavController(v).navigate(R.id.nav_cargar);
        });
    }

    private void observeViewModel() {
        // Observar evento de mostrar diálogo
        salirViewModel.getMostrarDialogoSalir().observe(getViewLifecycleOwner(), mostrar -> {
            if (mostrar != null && mostrar) {
                mostrarDialogoConfirmacion();
            }
        });

        // Observar evento de cerrar aplicación
        salirViewModel.getCerrarAplicacion().observe(getViewLifecycleOwner(), cerrar -> {
            if (cerrar != null && cerrar) {
                requireActivity().finish();
            }
        });
    }

    private void mostrarDialogoConfirmacion() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Salir de la aplicación")
                .setMessage("¿Está seguro que desea salir de la aplicación?")
                .setPositiveButton("Salir", (dialog, which) -> {
                    salirViewModel.confirmarSalir();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    salirViewModel.cancelarSalir();
                    // Navegar de vuelta al fragment de cargar
                    Navigation.findNavController(requireView()).navigate(R.id.nav_cargar);
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
