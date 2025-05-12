/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
public class Productos {
    private String nombre;
    private double precio;
    private String id;
    private Categoria categoria;
    
    public Productos(String nombre, String id, double precio, Categoria categoria){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String setNombre(String nombre){
        if(nombre != null && nombre.trim().isEmpty()){
            this.nombre = nombre;
        }else{
            System.out.println("El nombre no puede estar vacio. ");
        }
        return null;
    }
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        if(precio > 0){
            this.precio = precio;
        }else{
            System.out.println("El precio debe ser mayor que 0. ");
        }
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public void serCategoria(Categoria categoria){
        this.categoria = categoria;
    }
} 
