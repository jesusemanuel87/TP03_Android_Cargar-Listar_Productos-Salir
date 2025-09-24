package com.example.tp03_android_cargar_listar_productos_salir.repository;

import com.example.tp03_android_cargar_listar_productos_salir.MainActivity;
import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductoRepository {
    private static ProductoRepository instance;

    private ProductoRepository() {
    }

    public static ProductoRepository getInstance() {
        if (instance == null) {
            instance = new ProductoRepository();
        }
        return instance;
    }

    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>(MainActivity.productos);
        // Ordenar alfabéticamente por descripción
        Collections.sort(productos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion());
            }
        });
        return productos;
    }

    public boolean addProducto(Producto producto) {
        // Verificar si ya existe un producto con el mismo código
        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                return false; // Código duplicado
            }
        }
        MainActivity.productos.add(producto);
        return true;
    }

    public boolean existeCodigo(String codigo) {
        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public int getTotalProductos() {
        return MainActivity.productos.size();
    }
}
