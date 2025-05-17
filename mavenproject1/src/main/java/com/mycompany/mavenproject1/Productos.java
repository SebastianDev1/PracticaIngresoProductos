package com.mycompany.mavenproject1;

public class Productos {
    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private String correo;

    public Productos(String codigo, String nombre, double precio, Categoria categoria, String correo)
            throws PrecioInvalidoException, FormatoCorreoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precio <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a 0.");
        }
        if (correo == null || !correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new FormatoCorreoInvalidoException("Correo con formato inválido.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.correo = correo;
    }

    public void mostrarResumen() {
        System.out.println("Producto: " + nombre + " | Código: " + codigo + " | Precio: $" + precio +
                " | Categoría: " + categoria.getNombre() + " | Correo: " + correo);
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public Categoria getCategoria() { return categoria; }
    public String getCorreo() { return correo; }
}
