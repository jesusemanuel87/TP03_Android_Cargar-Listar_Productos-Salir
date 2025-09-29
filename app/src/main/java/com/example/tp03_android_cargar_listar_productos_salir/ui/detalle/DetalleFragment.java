package com.example.tp03_android_cargar_listar_productos_salir.ui.detalle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentDetalleBinding;
import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

import java.text.DecimalFormat;

public class DetalleFragment extends Fragment {

    private FragmentDetalleBinding binding;
    private DetalleViewModel mv;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleViewModel.class);
        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Observar el producto
        mv.getmProducto().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                binding.tvDetalleCodigo.setText(producto.getCodigo());
                binding.tvDetalleDescripcion.setText(producto.getDescripcion());
                binding.tvDetallePrecio.setText("$ " + decimalFormat.format(producto.getPrecio()));
            }
        });

        // Listener del botón volver
        binding.btnVolver.setOnClickListener(v -> {
            Navigation.findNavController(root).navigateUp();
        });

        // Cargar el producto desde los argumentos
        if (getArguments() != null) {
            String codigo = getArguments().getString("codigo");
            String descripcion = getArguments().getString("descripcion");
            double precio = getArguments().getDouble("precio");
            
            Producto producto = new Producto(codigo, descripcion, precio);
            mv.cargarProducto(producto);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
