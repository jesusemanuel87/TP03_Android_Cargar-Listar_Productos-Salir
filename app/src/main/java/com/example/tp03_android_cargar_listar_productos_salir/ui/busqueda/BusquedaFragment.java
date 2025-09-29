package com.example.tp03_android_cargar_listar_productos_salir.ui.busqueda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp03_android_cargar_listar_productos_salir.R;
import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentBusquedaBinding;
import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;
    private BusquedaViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BusquedaViewModel.class);
        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Observar mensajes de error o información
        mv.getmMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        // Observar cuando se encuentra un producto
        mv.getmProducto().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                // Navegar al fragment de detalle pasando campos individuales
                Bundle bundle = new Bundle();
                bundle.putString("codigo", producto.getCodigo());
                bundle.putString("descripcion", producto.getDescripcion());
                bundle.putDouble("precio", producto.getPrecio());
                Navigation.findNavController(root).navigate(R.id.action_busqueda_to_detalle, bundle);
            }
        });

        // Listener del botón buscar
        binding.btnBuscar.setOnClickListener(v -> {
            String codigo = binding.etCodigoBuscar.getText().toString();
            mv.buscarProducto(codigo);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
