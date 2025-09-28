package com.example.tp03_android_cargar_listar_productos_salir.ui.listar;

import static com.example.tp03_android_cargar_listar_productos_salir.MainActivity.productos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;

import com.example.tp03_android_cargar_listar_productos_salir.databinding.FragmentListarBinding;
import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel mv;
    private ProductoAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListarViewModel.class);
        binding = FragmentListarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Inicializar ViewModel
        mv.getProductos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>> {
            @Override
                public void onChanged(ArrayList<Producto> productos) {

        // Configurar RecyclerView
        setupRecyclerView();

        // Configurar SwipeRefreshLayout
        setupSwipeRefresh();

        // Observar LiveData
        observeViewModel();


    }


    @Override
    public void onResume() {
        super.onResume();
        // Actualizar la lista cada vez que se muestra el fragment
        mv.actualizarLista();
    }

    private void setupRecyclerView() {
        adapter = new ProductoAdapter();
        binding.recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerProductos.setAdapter(adapter);
    }

    private void setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener(() -> {
            mv.actualizarLista();
            binding.swipeRefresh.setRefreshing(false);
        });
    }

    private void observeViewModel() {
        // Observar lista de productos
        mv.getProductos().observe(getViewLifecycleOwner(), productos -> {
            if (productos != null && !productos.isEmpty()) {
                adapter.setProductos(productos);
                binding.recyclerProductos.setVisibility(View.VISIBLE);
                binding.tvMensajeVacio.setVisibility(View.GONE);
            } else {
                adapter.setProductos(null);
                binding.recyclerProductos.setVisibility(View.GONE);
                binding.tvMensajeVacio.setVisibility(View.VISIBLE);
            }
        });

        // Observar mensaje vacío
        mv.getMensajeVacio().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                binding.tvMensajeVacio.setText(mensaje);
                binding.tvMensajeVacio.setVisibility(View.VISIBLE);
                binding.recyclerProductos.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
