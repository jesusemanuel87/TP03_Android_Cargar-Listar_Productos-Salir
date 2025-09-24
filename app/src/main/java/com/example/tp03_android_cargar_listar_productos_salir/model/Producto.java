package com.example.tp03_android_cargar_listar_productos_salir.model;

public class Producto {
    private String codigo;
    private String descripcion;
    private double precio;

    public Producto() {
    }

    public Producto(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Producto)){
            return false;
        }

        Producto producto = (Producto) obj;
        if (producto.getCodigo().equals(this.codigo)) {
            return true;
        }
        return false;
    }
        /*
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo != null ? codigo.equals(producto.codigo) : producto.codigo == null;
        */
    

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}
