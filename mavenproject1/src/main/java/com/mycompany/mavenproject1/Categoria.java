package com.mycompany.mavenproject1;

public class Categoria {
    private int id;
    private String nombre;

    public Categoria(int id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public int getId() { return id; }
}
