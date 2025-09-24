package com.example.tp03_android_cargar_listar_productos_salir.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel cargarViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar ViewModel
        cargarViewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        // Configurar listeners
        setupListeners();

        // Observar LiveData
        observeViewModel();

        return root;
    }

    private void setupListeners() {
        binding.btnCargar.setOnClickListener(v -> {
            String codigo = binding.etCodigo.getText().toString();
            String descripcion = binding.etDescripcion.getText().toString();
            String precio = binding.etPrecio.getText().toString();

            cargarViewModel.cargarProducto(codigo, descripcion, precio);
        });

        binding.btnLimpiar.setOnClickListener(v -> limpiarFormulario());
    }

    private void observeViewModel() {
        // Observar mensajes de error
        cargarViewModel.getMensajeError().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });

        // Observar mensajes de éxito
        cargarViewModel.getMensajeExito().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        // Observar evento de limpiar formulario
        cargarViewModel.getLimpiarFormulario().observe(getViewLifecycleOwner(), limpiar -> {
            if (limpiar != null && limpiar) {
                limpiarFormulario();
            }
        });
    }

    private void limpiarFormulario() {
        binding.etCodigo.setText("");
        binding.etDescripcion.setText("");
        binding.etPrecio.setText("");
        binding.etCodigo.requestFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
