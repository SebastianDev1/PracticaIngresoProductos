/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

public class Categoria {
    private String nombre;
    private String id;
    
    public Categoria(String nombre, String id){
        this.id = id;
        this.nombre = nombre;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String nombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        if(nombre != null && nombre.trim().isEmpty()){
        this.nombre = nombre;    
        }
    }
}
