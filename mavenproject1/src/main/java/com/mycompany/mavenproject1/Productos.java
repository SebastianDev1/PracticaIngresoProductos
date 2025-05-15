/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
public class Productos {
    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Productos(String codigo, String nombre, double precio, Categoria categoria) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public void mostrarResumen() {
        System.out.println("Producto: " + nombre + " | Código: " + codigo + " | Precio: $" + precio + " | Categoría: " + categoria.getNombre());
    }
}


