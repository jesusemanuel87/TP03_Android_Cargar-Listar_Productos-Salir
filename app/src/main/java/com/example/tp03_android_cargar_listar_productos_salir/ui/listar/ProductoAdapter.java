package com.example.tp03_android_cargar_listar_productos_salir.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp03_android_cargar_listar_productos_salir.R;
import com.example.tp03_android_cargar_listar_productos_salir.model.Producto;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private ArrayList<Producto> productos;
    private LayoutInflater inflater;
    private Context context;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ProductoAdapter(ArrayList<Producto> productos, LayoutInflater inflater, Context context) {
        this.productos = productos != null ? productos : new ArrayList<>();
        this.inflater = inflater;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCodigo;
        private TextView tvDescripcion;
        private TextView tvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tv_codigo);
            tvDescripcion = itemView.findViewById(R.id.tv_descripcion);
            tvPrecio = itemView.findViewById(R.id.tv_precio);
        }

        public void bind(Producto producto) {
            tvCodigo.setText("Código: " + producto.getCodigo());
            tvDescripcion.setText(producto.getDescripcion());
            tvPrecio.setText("$ " + decimalFormat.format(producto.getPrecio()));
        }
    }
}
